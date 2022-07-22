package com.zhang.utilslibiary.utils.random;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @Author : zhang
 * @Create Time : 2022/7/21
 * @Class Describe : 获取汉字
 * @Project Name : MyDemo
 */
public class ChineseChars {
    public static String genOneChineseChars() {
        Random random = new Random();
        String str = null;
        int highPos = (176 + Math.abs(random.nextInt(39)));
        int lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[]{(new Integer(highPos)).byteValue(),
                (new Integer(lowPos)).byteValue()};

        try {
            str = new String(b, "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }
}
