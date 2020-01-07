package com.hz.convert.util;

/**
 * 八进制转换
 *
 * @author hz
 * @date 2019-8-19 11:43
 */
public class OctalUtil {

    /**
     * 描述: 八进制字符串转二进制字符串
     *
     * @param octal : 八进制字符串
     * @return 二进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:35
     */
    public static String octalStringToBinaryString(String octal) {
        return Integer.toBinaryString(Integer.valueOf(octal, 8));
    }

    /**
     * 描述: 八进制字符串转十六进制字符串
     *
     * @param octal : 八进制字符串
     * @return 十六进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:35
     */
    public static String octalStringToHexString(String octal) {
        return Integer.toHexString(Integer.valueOf(octal, 8));
    }

    /**
     * 把8进制字符串转换成字节数组
     * TODO 未完成:返回8进制字节数组
     *
     * @param octal : 8进制字符串
     * @return 16进制字节数组
     */
    public static byte[] octalStringToByteArray(String octal) {
        if (null == octal || octal.isEmpty()) {
            return null;
        }
        return HexUtil.hexStringToByteArray(Integer.toHexString(Integer.valueOf(octal, 8)));
    }

}
