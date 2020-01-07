package com.hz.crypto.util;


import com.hz.convert.util.ByteUtil;
import com.hz.number.util.LongUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

/**
 * @author hz
 * @date 2019-7-17 14:14
 */
public class DESUtil {

    /**
     * 分组加密的四种模式
     * <p>
     * ECB:电码本模式
     * CBC:密文分组链接方式
     * CFB:密文反馈模式
     * OFB:输出反馈模式
     * CTR:计数器模式
     */
    public static final String KEY_ALGORITHM = "DES";


    /**
     * 填充方式
     * 对于加密，因为DES是块加密，数据长度必须是8的倍数，然而实际上加密前的明文getBytes()后基本不会恰好是8的倍数，
     * 所以一般需要进行填充，填充的规则这里不说，想知道的百度吧，反正这个只需要设置参数 PKCS5Padding ，
     * JDK就帮你填充了，若不填充，且数据长度不是8倍数，则会抛异常
     * <p>
     * ZeroPadding  : 数据长度不对齐时使用0填充，否则不填充。
     * PKCS5Padding : PKCS7Padding的子集，块大小固定为8字节。
     * PKCS7Padding : 假设数据长度需要填充n(n>0)个字节才对齐，那么填充n个字节，每个字节都是n;如果数据本身就已经对齐了，则填充一块长度为块大小的数据，每个字节都是块大小。
     * NoPadding    : Java支持NoPadding填充方式
     */
    public static final String CIPHER_PADDING = "NoPadding";

    /**
     * 算法名称/加密模式/填充方式
     */
    public static final String CIPHER_ALGORITHM_ECB = "DES/ECB/NoPadding";

    /**
     * 生成秘钥  ❤ 自定义方法
     */
    public static String generateKey(String randomNum) {
        //随机数+1379得秘钥前4字节
        Long randomNumLong = Long.parseLong(randomNum, 16) + 1379L;
        String key4 = LongUtil.toRadixString(randomNumLong, 8, 16);
        //后4位由前4位按位取反
        randomNumLong ^= 0xffffffff;
        String key8 = Long.toHexString(randomNumLong);
        //完整密钥
        return key8.replace("ffffffff", key4).toUpperCase();
    }

    /**
     * 还原密钥
     *
     * @param key 长度固定为8
     * @return Key
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec des = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(des);
    }

    /**
     * 加密
     *
     * @param data 原文
     * @param key  秘钥
     * @return 密文
     */
    public static String encrypt(byte[] data, byte[] key) {
        try {
            Key secureKey = toKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, secureKey);
            return ByteUtil.byteArrayToHexString(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param data 密文
     * @param key
     * @return 明文、原文
     */
    public static String decrypt(byte[] data, byte[] key) {
        try {
            Key k = toKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.DECRYPT_MODE, k);
            return ByteUtil.byteArrayToHexString(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
