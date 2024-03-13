package asia.lhweb.lhmooc.http;

import asia.lhweb.lhmooc.constant.LhMoocConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * 向浏览器/客户端发送文件
     *
     * @param file 要发送的文件
     */
    public void write(File file) {
        // 修改状态行中的Content-Length字段为文件长度
        headers.put("Content-Length", String.valueOf(file.length()));

        // 根据文件扩展名设置Content-Type字段
        setFileTypeByName(file);

        // 获取Http响应头部字节数组
        byte[] buffer = getOkHeaderBytes();

        // 将头部字节数组写入byteBuffer中
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        try {
            socketChannel.write(byteBuffer);
            byteBuffer = ByteBuffer.allocate(1024);
            InputStream inputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int len;
            // 逐块读取文件内容并发送
            while ((len = inputStream.read(buff)) != -1) {
                byteBuffer.clear();
                byteBuffer.put(buff, 0, len);
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向浏览器/客户端发送指定状态码和数据
     *
     * @param code 状态码
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
            throw new RuntimeException(e);
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
     *
     * @param file 要发送的文件
     */
    private void setFileTypeByName(File file) {
        // todo 根据文件类型去判断
        String fileType;
        try {
            Path filePath = Paths.get(file.getName());
            fileType = Files.probeContentType(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("FileType: " + fileType);
        headers.put("Content-Type", fileType + ";charset=UTF-8");
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
