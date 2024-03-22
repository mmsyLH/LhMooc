package asia.lhweb.lhmooc.http;

import asia.lhweb.lhmooc.constant.LhMoocConstant;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * 罗汉响应 V3版本
 *
 * @author 罗汉
 * @date 2024/02/25
 */
public class LhResponse {
    private SocketChannel socketChannel;

    // Http响应状态行
    public static final String respHeaderOK = "HTTP/1.1 200 OK";

    // Http响应头部信息
    private final HashMap<String, String> headers = new HashMap<>();

    /**
     * http响应
     * 创建LhResponse对象
     *
     * @param socketChannel 套接字通道
     */
    public LhResponse(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        // 设置默认的Http响应头部信息
        headers.put("Content-Type", "text/html;charset=UTF-8");
        headers.put("Date", String.valueOf(new java.sql.Date(System.currentTimeMillis()).toLocaleString()));
        headers.put("Content-Length", "0");
        headers.put("Server", LhMoocConstant.SERVER_NAME);
    }


    /**
     * 写
     * 向浏览器/客户端发送文件
     *
     * @param file 要发送的文件
     */
    public void write(File file) {
        // 设置响应头的Content-Length为文件长度
        headers.put("Content-Length", String.valueOf(file.length()));

        // System.out.println("发送文件Content-Length：" + file.getName()+":"+headers.get("Content-Length"));
        // 根据文件扩展名设置Content-Type字段，以便浏览器正确解析文件类型
        setFileTypeByName(file);

        try {
            // 准备HTTP响应头，并将其写入网络通道
            byte[] buffer = getOkHeaderBytes();
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            socketChannel.write(byteBuffer);

            // 打开文件输入流，读取文件内容并发送至客户端
            InputStream inputStream = Files.newInputStream(file.toPath());
            // 读取文件内容到字节数组
            byte[] buff = new byte[inputStream.available()];
            int read = inputStream.read(buff);
            ByteBuffer wrap = ByteBuffer.wrap(buff);
            // 逐块读取并发送文件内容
            while (wrap.hasRemaining()) {
                socketChannel.write(wrap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端异常关闭");
            try {
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * 向浏览器/客户端发送指定状态码和数据
     * <p>
     * .
     *
     * @param data 要发送的数据
     */
    public void write(int code, String data) {
        // 设置响应状态码
        String statusLine = "HTTP/1.1 " + code + " " + getResponseStatus(code);
        headers.put("Status", statusLine);
        // 设置响应数据长度和类型
        headers.put("Content-Length", String.valueOf(data.length()));
        headers.put("Content-Type", "text/plain;charset=UTF-8");
        // 获取响应头部字节数组
        byte[] headerBytes = getHeaderBytes();
        try {
            // 将头部和数据发送给客户端
            ByteBuffer[] bufferArray = {ByteBuffer.wrap(headerBytes), ByteBuffer.wrap(data.getBytes())};
            socketChannel.write(bufferArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取响应状态码对应的状态信息
     *
     * @param code 状态码
     * @return 状态信息
     */
    private String getResponseStatus(int code) {
        switch (code) {
            case 200:
                return "OK";
            case 404:
                return "Not Found";
            case 500:
                return "Internal Server Error";
            default:
                return "Unknown";
        }
    }

    /**
     * 向浏览器/客户端发送JSON数据
     *
     * @param data JSON数据
     */
    public void writeToJson(String data) {
        // 将 JSON 数据转换为字节数组
        byte[] jsonData = data.getBytes();
        // 设置 Content-Type 和 Content-Length
        headers.put("Content-Type", "application/json;charset=UTF-8");
        headers.put("Content-Length", String.valueOf(jsonData.length));
        // 获取成功响应的 Http 头部字节数组
        byte[] headerBytes = getOkHeaderBytes();

        try {
            // 将 Http 头部字节数组和 JSON 数据发送给客户端
            ByteBuffer[] bufferArray = {ByteBuffer.wrap(headerBytes), ByteBuffer.wrap(jsonData)};
            socketChannel.write(bufferArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取完整的Http头部字节数组
     *
     * @return Http头部字节数组
     */
    private byte[] getHeaderBytes() {
        // 构建Http头部字符串
        StringBuilder stringBuilder = new StringBuilder(1000);
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        // 返回Http头部字节数组
        return stringBuilder.toString().getBytes();
    }

    /**
     * 获取成功响应的Http头部字节数组
     *
     * @return 成功响应的Http头部字节数组
     */
    private byte[] getOkHeaderBytes() {
        // 构建Http头部字符串
        StringBuilder stringBuilder = new StringBuilder(1000);
        stringBuilder.append(respHeaderOK);
        stringBuilder.append("\r\n");
        for (String key : headers.keySet()) {
            String value = headers.get(key);
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        // 返回Http头部字节数组
        return stringBuilder.toString().getBytes();
    }

    /**
     * 设置响应状态为404
     */
    public void set404() {

    }

    /**
     * 根据文件名设置Content-Type字段
     * 此方法使用Tika库来分析文件的类型，然后根据文件类型设置HTTP头中的Content-Type字段。
     * 对于XML文件，会额外检查文件实际的字符集编码。
     *
     * @param file 要发送的文件，不可为null。
     */
    private void setFileTypeByName(File file) {
        // 初始化Tika对象用于文件类型检测
        Tika tika = new Tika();
        String fileType = null;
        try {
            // 检测文件类型
            fileType = tika.detect(file);
        } catch (IOException e) {
            // 当文件类型无法检测时，抛出运行时异常
            throw new RuntimeException("没有这个文件类型", e);
        }
        if ("application/xml".equals(fileType)) {
            try {
                // 对于XML文件，尝试通过文件系统探测其实际类型
                String type = Files.probeContentType(Paths.get(file.getPath()));
                // 判断探测到的类型是否为XML，以确定Content-Type的具体值
                if ("xml".equals(type.split("/")[1])) {
                    headers.put("Content-Type", fileType + ";charset=UTF-8");
                } else {
                    // 如果不是纯XML文件，则认为是HTML文件
                    headers.put("Content-Type", "text/html;charset=UTF-8");
                }
            } catch (IOException e) {
                // 探测文件类型失败时，抛出运行时异常
                throw new RuntimeException(e);
            }
        } else {
            // 非XML文件，直接设置Content-Type
            headers.put("Content-Type", fileType + ";charset=UTF-8");
        }
    }


    /**
     * 获取Http响应头部信息
     *
     * @return Http响应头部信息
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

}
