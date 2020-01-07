package com.hz.convert.util;

/**
 * 二进制转换
 *
 * @author hz
 * @date 2019-8-19 11:43
 */
public class BinaryUtil {

    /**
     * 描述: 二进制字符串转八进制字符串
     *
     * @param binary : 二进制字符串
     * @return 八进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:44
     */
    public static String binaryStringToOctalString(String binary) {
        return Integer.toOctalString(Integer.valueOf(binary, 2));
    }

    /**
     * 描述: 二进制字符串转八进制字符串
     *
     * @param binary : 二进制字符串
     * @return 十六进制字符串
     * @auther: hz
     * @date: 2019-8-19 19:44
     */
    public static String binaryStringToHexString(String binary) {
        return Integer.toHexString(Integer.valueOf(binary, 2));
    }


    /**
     * 描述: 把二进制字符串转换成字节数组
     *
     * @param binary : 二进制字符串
     * @return 十六进制字节数组
     * @auther: hz
     * @date: 2019-8-19 19:32
     */
    public static byte[] binaryStringToByteArray(String binary) {
        if (null == binary || binary.isEmpty()) {
            return null;
        }
        StringBuilder builder = new StringBuilder(binary);
        while ((builder.length() % 8) != 0) {
            builder.insert(0, "0");
        }
        binary = builder.toString();
        int len = binary.length() / 8;
        byte[] result = new byte[len];

        for (int i = 0; i < len; i++) {
            Integer integer = Integer.valueOf(binary.substring(i << 3, (i + 1) << 3), 2);
            result[i] = integer.byteValue();
        }
        return result;
    }


}
