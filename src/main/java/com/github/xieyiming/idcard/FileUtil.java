package com.github.xieyiming.idcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class FileUtil {

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

}
