package com.hz.crypto.util;

import com.hz.convert.util.ByteUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author hz
 * @date 2019-8-28 15:20
 */
public class AESUtil {


    /**
     * 分组加密的四种模式
     * <p>
     * ECB:电码本模式
     * CBC:密文分组链接方式
     * CFB:密文反馈模式
     * OFB:输出反馈模式
     * CTR:计数器模式
     */
    public static final String KEY_ALGORITHM = "AES";


    /**
     * 密钥默认偏移，可更改
     */
    private static String IV_PARAMETER = "elim123";

    /**
     * 算法名称/加密模式/填充方式
     */
    public static final String CIPHER_ALGORITHM_AES = "AES/CBC/PKCS5Padding";


    public static String SECRET_KEY = "1234567890abcdef";


    /**
     * 生成秘钥  ❤ 自定义方法
     *
     * @param secretKey
     * @return String
     */
    public static String generateKey(String secretKey) {
        if (secretKey != null) {
            return secretKey;
        }
        return SECRET_KEY;
    }

    /**
     * 还原密钥
     *
     * @param key
     * @return Key
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        return keySpec;
    }


    /**
     * 加密
     *
     * @param data            原文
     * @param key             秘钥
     * @param ivParameterSpec 偏移量
     * @return 密文
     */
    public static String encrypt(byte[] data, byte[] key, byte[] ivParameterSpec) {
        try {
            //偏移量
            IvParameterSpec iv = new IvParameterSpec(ivParameterSpec);

            Key secureKey = toKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, iv);
            return ByteUtil.byteArrayToHexString(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param data            密文
     * @param key             秘钥
     * @param ivParameterSpec 偏移量
     * @return 明文、原文
     */
    public static String decrypt(byte[] data, byte[] key, byte[] ivParameterSpec) {
        try {
            //偏移量
            IvParameterSpec iv = new IvParameterSpec(ivParameterSpec);

            Key k = toKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, k, iv);

            int length = data.length;

            if (length % 16 != 0) {

            }

            byte[] bytes = cipher.doFinal(data);

            return ByteUtil.byteArrayToHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    // 加密
//    public static String Encrypt(String sSrc, String sKey) throws Exception {
//        if (sKey == null) {
//            System.out.print("Key为空null");
//            return null;
//        }
//        // 判断Key是否为16位
//        if (sKey.length() != 16) {
//            System.out.print("Key长度不是16位");
//            return null;
//        }
//        byte[] raw = sKey.getBytes("utf-8");
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
//
//        return ByteUtil.byteArrayToRadixString(encrypted, 16);
//
//    }

//    // 解密
//    public static String Decrypt(String sSrc, String sKey) throws Exception {
//        try {
//            // 判断Key是否正确
//            if (sKey == null) {
//                System.out.print("Key为空null");
//                return null;
//            }
//            // 判断Key是否为16位
//            if (sKey.length() != 16) {
//                System.out.print("Key长度不是16位");
//                return null;
//            }
//            byte[] raw = sKey.getBytes("utf-8");
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//            //先用base64解密
//            byte[] encrypted1 = HexUtil.hexStringToByteArray(sSrc);
//            try {
//                byte[] original = cipher.doFinal(encrypted1);
//                String originalString = new String(original, "utf-8");
//                return originalString;
//            } catch (Exception e) {
//                System.out.println(e.toString());
//                return null;
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.toString());
//            return null;
//        }
//    }

//    public static void main(String[] args) throws Exception {
//        /*
//         * 此处使用AES-128-ECB加密模式，key需要为16位。
//         */
//        String cKey = "1234567890123456";
//        // 需要加密的字串
//        String cSrc = "www.gowhere.so";
//        System.out.println(cSrc);
//        // 加密
//        String enString = AESUtil.Encrypt(cSrc, cKey);
//        System.out.println("加密后的字串是：" + enString);
//
//        // 解密
//        String DeString = AESUtil.Decrypt(enString, cKey);
//        System.out.println("解密后的字串是：" + DeString);
//    }

}
