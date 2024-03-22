package asia.lhweb.lhmooc.utils;


import org.apache.tika.Tika;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * base64工具类
 *
 * @author 陈思敏
 * @date 2024/03/21
 */
public class Base64Util {
    public static String generateFile(String base64, String path)   {
        // 解密
        try {
            String savePath = "";
            // 图片分类路径+图片名+图片后缀

            String type = base64.substring(base64.indexOf("/",1)+1,base64.indexOf(";",1));


            // 去掉base64前缀 data:image/jpeg;base64,
            base64 = base64.substring(base64.indexOf(",", 1) + 1);
            // 解密，解密的结果是一个byte数组

            Base64.Decoder decoder = Base64.getDecoder();

            byte[] decode = decoder.decode(base64);

            /*这个循环是为了确保将解码后的byte数组中的负值转换为它们对应的正值。
            在Java中，byte类型的取值范围是-128到127，但有时在处理二进制数据时，解码后的值可能会超出这个范围。
            因此，为了确保处理这些值时不出现问题，循环将负值加上256，使其转换为对应的正值。
            这样可以保证解密后的byte数组中的所有值都在0到255之间，以便正确处理后续的操作。*/
            for (int i = 0; i < decode.length; ++i) {
                if (decode[i] < 0) {
                    decode[i] += 256;
                }
            }



            //拼接路径，并附带文件后缀名
            String classPath = path.concat(UUID.randomUUID().toString()).concat("."+type);
            String totalPath = savePath.concat(classPath);

            // 保存数据
            OutputStream out = new  FileOutputStream(totalPath);
            out.write(decode);
            out.flush();
            out.close();

            //使用tika库解析文件后缀名
            Tika tika = new Tika();
            File file = new File(savePath.concat(classPath));
            String mimeType = tika.detect(file);
            String extension = mimeType.split("/")[1];


            System.out.println(type+"base64 的");
            System.out.println(extension+"tika 的");

            //检测文件后缀名是否准确
            if(!extension.equals(type)){
                File newFile = new File(totalPath.replace(type,extension));
                if(file.renameTo(newFile)){
                    System.out.println("修改成功");
                }else {
                    System.out.println("修改失败");
                }
            }



            // 返回相对路径 = 图片分类路径+图片名+图片后缀
            return classPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
