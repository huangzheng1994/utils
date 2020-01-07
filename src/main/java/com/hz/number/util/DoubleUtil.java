package com.hz.number.util;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author hz
 * @date 2019-8-19 11:38
 */
public class DoubleUtil {

    /**
     * 默认加法运算精度
     */
    private static final Integer DEFAULT_ADD_SCALE = 2;

    /**
     * 默认减法运算精度
     */
    private static final Integer DEFAULT_SUB_SCALE = 2;

    /**
     * 默认乘法运算精度
     */
    private static final Integer DEFAULT_MUL_SCALE = 2;

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
    public static double add(double value1, double value2) {
        return add(value1, value2, DEFAULT_ADD_SCALE);
    }

    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return format(b1.add(b2), scale);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        return sub(value1, value2, DEFAULT_SUB_SCALE);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2, int scale) {
        Assert.isTrue(value1 >= value2, "==>【subtracted less than minuend】");
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return format(b1.subtract(b2), scale);
    }


    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        return mul(value1, value2, DEFAULT_MUL_SCALE);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale  小数点保留位数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return format(b1.multiply(b2), scale);
    }


    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static double divide(double dividend, double divisor) {
        return divide(dividend, divisor, DEFAULT_DIVIDE_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divide(double dividend, double divisor, int scale) {
        Assert.isTrue(divisor != 0, "==>【the divisor cannot be zero】");
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 格式化精度
     *
     * @param value
     * @param scale :小数位数
     * @return double
     */
    public static double format(double value, int scale) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return format(bigDecimal, scale);
    }

    /**
     * 格式化精度
     *
     * @param value
     * @param scale :小数位数
     * @return double
     */
    public static double format(BigDecimal value, int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
