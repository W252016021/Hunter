package com.walixiwa.webhunter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

public class WebTool {
    public static String uriEncode(String string)  {
        StringBuilder resultURL = new StringBuilder();
        //遍历字符串
        try {
            for (int i = 0; i < string.length(); i++) {
                char charAt = string.charAt(i);
                //只对汉字处理
                if (isChineseChar(charAt)) {
                    String encode = URLEncoder.encode(charAt + "", "UTF-8");
                    resultURL.append(encode);
                } else {
                    resultURL.append(charAt);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return (resultURL.toString());
    }

    //判断汉字的方法,只要编码在\u4e00到\u9fa5之间的都是汉字
    private static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }
}
