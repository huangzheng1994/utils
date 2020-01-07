package com.hz.convert.util;

/**
 * 十六进制转换
 *
 * @author hz
 * @date 2019-8-19 11:44
 */
public class HexUtil {

    /**
     * 描述: 十六进制字符串转2进制字符串
     *
     * @param hex : 16进制字符串
     * @return : 二进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:33
     */
    public static String hexStringToBinaryString(String hex) {
        return Integer.toBinaryString(Integer.valueOf(hex, 16));
    }

    /**
     * 描述: 十六进制字符串转八进制字符串
     *
     * @param hex : 16进制字符串
     * @return : 八进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:24
     */
    public static String hexStringToOctalString(String hex) {
        return Integer.toOctalString(Integer.valueOf(hex, 16));
    }

    /**
     * 描述: 把十六进制字符串转换成字节数组
     *
     * @param hex : 16进制字符串
     * @return 字节数组
     * @auther: hz
     * @date: 2019-8-19 19:32
     */
    public static byte[] hexStringToByteArray(String hex) {
        if (null == hex || hex.isEmpty()) {
            return null;
        }

        if ((hex.length() % 2) != 0) {
            hex = "0" + hex;
        }
        int len = hex.length() / 2;

        byte[] result = new byte[len];
        char[] aChar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) ((Character.digit(aChar[pos], 16) << 4) | Character.digit(aChar[pos + 1], 16));
        }
        return result;
    }

}
