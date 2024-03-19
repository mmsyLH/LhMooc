package asia.lhweb.lhmooc.http;

import asia.lhweb.lhmooc.constant.LhMoocConstant;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

/**
 * http请求 V3版本
 *
 * @author 罗汉
 * @date 2024/03/05
 */

public class LhRequest {
    private String method;// 访问方法
    private String uri;// url中的一部分
    private String url;// 访问链接 全称
    private String body;// 请求体
    private String protocol;// 协议版本
    // 参数列表 key value 所以用hashMap
    private final HashMap<String, String> parametersMap = new HashMap<>();// 请求头url中的参数列表
    private final HashMap<String, String> headers = new HashMap<>();// 请求头中的参数
    private final SocketChannel socketChannel;

    public LhRequest(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192); // 初始分配1MB缓冲区 最多 8192
        StringBuilder requestDataBuilder = new StringBuilder(); // 用于保存完整的请求数据
        try {
            int bytesRead = 0;
            while (true) {
                // 读取数据到缓冲区
                int n = socketChannel.read(byteBuffer);
                if (n == -1) {
                    //关闭通道
                    socketChannel.close();
                    break;
                }
                if (n == 0) {
                    break;
                }
                bytesRead += n;
                // 判断是否已经读取完整请求数据
                if (bytesRead >= LhMoocConstant.MAX_REQUEST_SIZE) {
                    // todo 请求数据限制的处理
                    throw new IOException("请求数据超过最大限制");
                }
                // 切换为读模式
                byteBuffer.flip();
                // 将缓冲区的数据读取到字符串构建器中
                while (byteBuffer.hasRemaining()) {
                    requestDataBuilder.append((char) byteBuffer.get());
                }
                // 清空缓冲区以便下次读取
                byteBuffer.clear();
            }
            String requestData = requestDataBuilder.toString();
            // 处理请求数据
            processRequestData(requestData);
        } catch (Exception e) {
            e.printStackTrace();
            // 客户端发生异常
            try {
                socketChannel.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 处理请求数据
     *
     * @param requestData 请求数据
     * @throws IOException ioexception
     */
    private void processRequestData(String requestData) throws IOException {
        // 请求： 请求行 请求头 请求体
            /*
              请求行 回车换行
              请求头 回车换行
              请求头2 回车换行
              回车换行
              请求体

              1.回车换行回车换行拆分
              2.回车换行拆分
             */
        String[] splitArr = requestData.split("\r\n\r\n");// 根据2个回车换行符进行分割
        String[] splitArr2 = splitArr[0].split("\r\n");// 请求行 回车换行 请求头 注意~！请求头有多个
        String requestLine = splitArr2[0];// 请求行字符串
        if (splitArr.length > 1) {// 如果有请求体
            body = splitArr[1];// 请求体字符串
        }
        // 只有requestLine不为空才能往下进行
        // 按照空格分成一个数组
        String[] requestLineArr;
        if (requestLine != null) {
            requestLineArr = requestLine.split(" ");
            // System.out.println("requestLineArr:"+ Arrays.toString(requestLineArr));
            if (requestLineArr.length < 3) {
                return;
                // throw new IOException("请求格式错误~"); // 请求头一般是    请求方法 请求路径 请求协议

            }

            // 得到method
            method = requestLineArr[0];

            // 得到url
            url = requestLineArr[1];

            // 得到protocol
            protocol = requestLineArr[2];

            // 解析请求头中url中的参数列表
            getParameterByUrl(requestLineArr);

            // 解析请求头中的参数
            for (int i = 1; i < splitArr2.length; i++) {
                String requestHeader = splitArr2[i];// 请求头字符串
                getParameterByHeard(requestHeader);
            }

            if (body != null) {
                // 解析请求体
                getBodys(body);
            }
        }
    }

    /**
     * 获取请求头中的参数列表
     *
     * @param requestHeader 请求头
     */
    private void getParameterByHeard(String requestHeader) {
        if (requestHeader.isEmpty()) return;
        String[] splitArr;
        splitArr = requestHeader.split(": ");
        headers.put(splitArr[0], splitArr[1]);
    }

    /**
     * 通过请求头中的第一行的url获取请求的参数参数
     * 并把这个参数封装到参数的map集合中parametersMap
     *
     * @param requestLineArr 请求线路arr
     */
    private void getParameterByUrl(String[] requestLineArr) {
        // System.out.println("requestLineArr:"+ Arrays.toString(requestLineArr));//requestLineArr:[GET, /aa.html?a=1&b=2&c=3, HTTP/1.1]
        // 得到uri：/ 可以用正则表达式 这里就简单的用字符串切割了
        int index;
        // 看看是否有参数列表
        index = requestLineArr[1].indexOf("?");
        // System.out.println("index=" + index);//index=8
        if (index == -1) {// 说明没有参数列表 存储的就是/+   例如/haha
            uri = requestLineArr[1];
        } else {// 有参数列表
            //[0,index)
            uri = requestLineArr[1].substring(0, index);
            // 获取参数列表 放到parametersMap中去
            // parameters 相当于 name=1&password=2
            String parameters = requestLineArr[1].substring(index + 1);
            // System.out.println("parameters: " + parameters);//parameters: a=1&b=2&c=3
            String[] parametersPair = parameters.split("&");
            // System.out.println("parametersPair: " + Arrays.toString(parametersPair));//parametersPair: [a=1, b=2, c=3]
            // 防止用户提交时http://localhost:8888/haha?   后面只给?不给参数
            // if(null!=parametersPair&& "".equals(parametersPair)){
            // 再次分割 parameterPair=    name=1
            for (String parameterPair : parametersPair) {
                // parameterVal["name","1"]
                String[] parameterVal = parameterPair.split("=");
                if (parameterVal.length == 2) {// 说明的的确确有参数值
                    // 放入到parametersMap里去
                    // 单独拿参数进行解码
                    String key = parameterVal[0];
                    String value = parameterVal[1];
                    try {
                        // System.err.println(key + "=" + value);
                        // System.err.println(URLDecoder.decode(key, "utf-8"));
                        // System.err.println(URLDecoder.decode(value, "utf-8"));
                        parametersMap.put(URLDecoder.decode(key, "utf-8"), URLDecoder.decode(value, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 解析请求体
     *
     * @param body 身体
     */
    private void getBodys(String body) {
        // 解析请求体
        String[] parametersPair = body.split("&");// body: action=register&username=12312&password=3124123123
        for (String parameterPair : parametersPair) {
            // parameterVal["action","register"]
            String[] parameterVal = parameterPair.split("=");
            if (parameterVal.length == 2) {// 说明的的确确有参数值
                // 放入到parametersMap里去
                try {
                    parametersMap.put(URLDecoder.decode(parameterVal[0], "utf-8"), URLDecoder.decode(parameterVal[1], "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    // request对象有一个特别重要的方法
    public String getParameter(String name) {
        return parametersMap.getOrDefault(name, "");

    }

    // request对象有一个特别重要的方法
    public String getHeardParameter(String name) {
        return headers.getOrDefault(name, "");

    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public String getProtocol() {
        return protocol;
    }

    public HashMap<String, String> getParametersMap() {
        return parametersMap;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}

