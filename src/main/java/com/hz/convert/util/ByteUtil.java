package com.hz.convert.util;

import org.springframework.util.Assert;

/**
 * 数组工具类
 *
 * @author hz
 * @date 2019-8-19 15:41
 */
public class ByteUtil {

    /**
     * byte数组转换为16进制字符串
     *
     * @param src : 数组
     * @return String
     */
    public static String byteArrayToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aSrc : src) {
            String hv = Integer.toHexString(aSrc & 0xff);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * byte数组转对应进制字符串
     *
     * @param src   : 数组
     * @param radix : 进制
     * @return String : 返回一个字节对应进制字符串
     */
    public static String byteArrayToRadixString(byte[] src, int radix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aSrc : src) {
            String hv = byteToRadixString(aSrc, radix);
            Assert.notNull(hv, "==>【the byte convert radix string is null】");
            int length = hv.length();

            if (radix == 2 && length < 8) {
                //未处理,hv<11111111
                for (int i = length; i < 8; i++) {
                    stringBuilder.append(0);
                }
            }

            //按16进制计算
            if (radix == 8 && hv.length() < 2) {
                stringBuilder.append(0);
            }

            if (radix == 16 && hv.length() < 2) {
                stringBuilder.append(0);
            }

            stringBuilder.append(hv);
        }

        //从16进制字符串转到8进制字符串
        if (radix == 8) {
            return HexUtil.hexStringToOctalString(stringBuilder.toString());
        }

        return stringBuilder.toString();
    }

    /**
     * byte转换对应进制字符串
     *
     * @param src   : byte
     * @param radix : 进制
     * @return String
     */
    public static String byteToRadixString(byte src, int radix) {
        String str;
        switch (radix) {
            case 2:
                str = Integer.toBinaryString(src & 0xff);
                break;
            case 8:
                str = Integer.toHexString(src & 0xff);
                break;
            case 16:
                str = Integer.toHexString(src & 0xff);
                break;
            default:
                str = null;
                break;
        }
        return str;
    }

}
