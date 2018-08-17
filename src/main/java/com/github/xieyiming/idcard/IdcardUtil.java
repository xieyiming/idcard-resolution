package com.github.xieyiming.idcard;

import java.io.InputStream;
import java.util.HashMap;


import org.apache.commons.lang3.StringUtils;

public class IdcardUtil {
    static HashMap<String, String> provice = new HashMap<>();
    static HashMap<String, String> city = new HashMap<>();
    static HashMap<String, String> area = new HashMap<>();

    static {
//		String path = IdcardUtil.class.getResource("/").getPath();
//		System.out.println(path);
        try {
            InputStream is = IdcardUtil.class.getResourceAsStream("/province.txt");
            String p = FileUtil.getStringFromStream(is);
            provice = init(p);
            is = IdcardUtil.class.getResourceAsStream("/city.txt");
            p = FileUtil.getStringFromStream(is);
            city = init(p);
            is = IdcardUtil.class.getResourceAsStream("/area.txt");
            p = FileUtil.getStringFromStream(is);
            area = init(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HashMap<String, String> init(String data) {
        HashMap<String, String> hm = new HashMap<String, String>();
        if (!StringUtils.isEmpty(data)) {
            String[] datas = data.split("\n");
            for (String d : datas) {
                String[] dd = d.split("\t");
                if (dd.length > 1) {
                    hm.put(dd[0].trim(), dd[1].trim());
                }
            }
        }
        return hm;
    }

    public static String getProvince(String code) {
        String result = "";
        if (provice != null) {
            result = provice.get(code.trim());
        }
        return result;
    }

    public static String getCity(String code) {
        String result = "";
        if (provice != null) {
            result = city.get(code.trim());
        }
        return result;
    }

    public static String getArea(String code) {
        String result = "";
        if (provice != null) {
            result = area.get(code.trim());
        }
        return result;
    }


}
