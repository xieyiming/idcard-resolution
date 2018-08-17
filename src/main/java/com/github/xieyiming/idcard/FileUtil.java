package com.github.xieyiming.idcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

public class FileUtil {
    public static String getStringFromFile(String path, String charset) {
        String ret = "";
        try {
            File file = new File(path);
            if (!file.exists() || file.isDirectory())
                ret = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), charset));
            String temp = null;
            StringBuffer sb = new StringBuffer();
            temp = br.readLine();
            while (temp != null) {
                sb.append(temp + "\n");
                temp = br.readLine();
            }
            ret = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getStringFromStream(InputStream in) {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(in, Charset.forName("utf-8")));

        StringBuffer buffer = new StringBuffer();
        String str = null;
        try {
            while ((str = reader.readLine()) != null) {
                buffer.append(str + "\n");
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return buffer.toString();
    }

    public static void writeFileFromString(String fileName, String content) {
        if (fileName == null || fileName.trim().length() == 0)
            fileName = "tmp.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        Base64 decoder = new Base64();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static byte[] getImageBate(String imgStr) {
        if (imgStr == null) // 图像数据为空
            return null;
        Base64 decoder = new Base64();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }

            return bytes;
        } catch (Exception e) {
            return null;
        }
    }


}
