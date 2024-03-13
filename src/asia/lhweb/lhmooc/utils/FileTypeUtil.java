package asia.lhweb.lhmooc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 读取文件头前4个字节，判断文件类型
 *
 * @author liuxb
 * @date 2022/8/20 16:57
 */
public class FileTypeUtil {
    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    static {
        FILE_TYPE_MAP.put("FFD8FF", "jpg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("89504E47", "png"); // PNG (png)
        FILE_TYPE_MAP.put("47494638", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("49492a00227105008037", "tif"); // TIFF (tif)
        FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); // 16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); // 24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); // 256色位图(bmp)
        FILE_TYPE_MAP.put("41433130313500000000", "dwg"); // CAD (dwg)

        FILE_TYPE_MAP.put("68746D6C3E", "html"); // HTML (html)
        FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); // css
        FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); // js
        FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); // Rich Text Format
        // (rtf)
        FILE_TYPE_MAP.put("38425053000100000000", "psd"); // Photoshop (psd)
        FILE_TYPE_MAP.put("44656C69766572792D646174653A", "eml"); // Email
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); // MS Excel
        // 注意：word、msi 和
        // excel的文件头一样
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); // Visio 绘图
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); // MS Access (mdb)
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
        FILE_TYPE_MAP.put("255044462d312e", "pdf");
        FILE_TYPE_MAP.put("75736167", "txt");

        FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); // rmvb/rm相同
        FILE_TYPE_MAP.put("464c5601050000000900", "flv"); // flv与f4v相同
        FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
        FILE_TYPE_MAP.put("49443303000000002176   ", "mp3");
        FILE_TYPE_MAP.put("000001b", "mpg"); //MPEG (mpg)，文件头：000001BA MPEG (mpg)，文件头：000001B3
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
        FILE_TYPE_MAP.put("57415645", "wav"); // Wave (wav)
        FILE_TYPE_MAP.put("41564920", "avi");

        FILE_TYPE_MAP.put("4d546864", "mid"); // MIDI (mid)
        FILE_TYPE_MAP.put("504b0304", "zip");
        FILE_TYPE_MAP.put("52617221", "rar");
        FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
        FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
        FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");// 可执行文件

        FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");// jsp文件
        FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");// MF文件
        FILE_TYPE_MAP.put("3C3F786D6C", "xml");// xml文件
        FILE_TYPE_MAP.put("494e5345525420494e54", "sql");// xml文件
        FILE_TYPE_MAP.put("7061636b616765207765", "java");// java文件
        FILE_TYPE_MAP.put("406563686f206f66660d", "bat");// bat文件
        FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");// gz文件
        FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");// bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");// bat文件
        FILE_TYPE_MAP.put("49545346030000006000", "chm");// bat文件
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");// bat文件
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");// docx文件
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");// WPS文字wps、表格et、演示dps都是一样的
        FILE_TYPE_MAP.put("6431303a637265617465", "torrent");

        FILE_TYPE_MAP.put("6D6F6F76", "mov"); // Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); // WordPerfect (wpd)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
        FILE_TYPE_MAP.put("2142444E", "pst"); // Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); // Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); // Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); // Real Audio (ram)
        FILE_TYPE_MAP.put("2E524D46", "rm");
    }

    /**
     * 得到上传文件的文件头
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF; // 去除高位0， 03，80
            String hv = Integer.toHexString(v).toUpperCase(); // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取文件类型
     *
     * @param filePath
     * @return
     */
    public static String getFileType(String filePath) {
        System.out.println("filePath:"+filePath);
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException("路径不存在或者该路径可能是目录");
        }
        System.err.println("file.getName:"+file.getName());
        System.err.println("file.length:"+file.length());
        String res = null;
        try (FileInputStream in = new FileInputStream(filePath)) {
            byte[] b = new byte[10];
            in.read(b, 0, b.length);
            String fileCode = bytesToHexString(b);
            System.err.println("fileCode:"+fileCode);
            // 这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                // System.err.println("key1:"+key);
                boolean flag = key.toUpperCase().startsWith(fileCode);
                if (flag) {
                    res = FILE_TYPE_MAP.get(key);
                    System.err.println("key2:"+key);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件找不到", e);
        } catch (IOException e) {
            throw new RuntimeException("文件读取异常", e);
        }
        System.out.println("FileType: " + res);
        return res;
    }

    public static void main(String[] args) {
        FileTypeUtil.getFileType("webapp\\index.html");
    }
}
