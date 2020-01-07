package com.hz.number.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author hz
 * @date 2019-8-19 11:39
 */
public class LongUtil {


    /**
     * 默认除法运算精度
     */
    private static final Integer DEFAULT_DIVIDE_SCALE = 2;


    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static long add(long value1, long value2) {
        BigDecimal b1 = new BigDecimal(Long.toString(value1));
        BigDecimal b2 = new BigDecimal(Long.toString(value2));
        return b1.add(b2).longValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static long sub(long value1, long value2) {
        Assert.isTrue(value1 >= value2, "==>【subtracted less than minuend】");
        BigDecimal b1 = new BigDecimal(Long.toString(value1));
        BigDecimal b2 = new BigDecimal(Long.toString(value2));
        return b1.subtract(b2).longValue();
    }


    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static long mul(long value1, long value2) {
        BigDecimal b1 = new BigDecimal(Long.toString(value1));
        BigDecimal b2 = new BigDecimal(Long.toString(value2));
        return b1.multiply(b2).longValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static long divide(long dividend, long divisor) {
        return divide(dividend, divisor, DEFAULT_DIVIDE_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    小数点保留位数
     * @return 两个参数的商
     */
    public static long divide(long dividend, long divisor, int scale) {
        Assert.isTrue(divisor != 0, "==>【the divisor cannot be zero】");
        BigDecimal b1 = new BigDecimal(Long.toString(dividend));
        BigDecimal b2 = new BigDecimal(Long.toString(divisor));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).longValue();
    }


    /**
     * Long类型转各进制字符串
     *
     * @param value : 需转换的值
     * @param num   : 指定返回位数
     * @param radix : 进制
     */
    public static String toRadixString(long value, int num, int radix) {
        StringBuilder builder;
        switch (radix) {
            case 2:
                builder = new StringBuilder(Long.toBinaryString(value));
                break;
            case 8:
                builder = new StringBuilder(Long.toOctalString(value));
                break;
            case 16:
                builder = new StringBuilder(Long.toHexString(value));
                break;
            default:
                builder = null;
        }
        Assert.notNull(builder, "==>【the shift num is unmatched】");
        for (int i = builder.length(); i < num; i++) {
            builder.insert(0, "0");
        }
        return builder.toString();
    }

}
