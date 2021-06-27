package com.example.infrastructure.utils.tuling;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * AES 加密
 *
 */
public class AESUtils {
    /**
     * 编码方式
     */
    private static final String ENCODING = "UTF-8";
    /**
     * 对称加密算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * AES使用的模式
     */
    private static final String TRANSFORMATION = "AES/CBC/NOPadding";

    /**
     * 预设的Initialization Vector，16 Bits的0
     */
    private static final IvParameterSpec DEFAULT_IV = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    /**
     * AES使用CBC模式與PKCS5Padding
     */
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";


    /**
     * AES 加密
     *
     * @param content  明文
     * @param password 生成秘钥的关键字
     * @param offset   密钥偏移量
     * @return
     */
    public static String aesEncrypt(String content, String password, String offset) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(offset.getBytes(ENCODING));
            SecretKeySpec key = new SecretKeySpec(password.getBytes(ENCODING), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes(ENCODING));
            return ByteHexStringUtil.byte2HexString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密
     *
     * @param content 密文
     * @param aesKey  生成秘钥的关键字
     * @param iv      密钥偏移量
     * @return
     */
    public static String aesDecrypt(String content, String aesKey, String iv) {
        try {
            byte[] byteMi = ByteHexStringUtil.hexString2Byte(content);
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes(ENCODING));
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(ENCODING), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 1.5请求内容加密方式
     *
     * @param content  密文
     * @param password 生成秘钥的关键字
     * @return
     */
 /*
    public static String aesEncrypt2(String content, String password) {
        try {
            IvParameterSpec zeroIv = DEFAULT_IV;
            SecretKeySpec key = new SecretKeySpec(getHash("MD5", password), ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes(ENCODING));
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(encryptedData), ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
*/
    /**
     * 取得字串的雜湊值
     *
     * @param algorithm 傳入雜驟演算法
     * @param text      傳入要雜湊的字串
     * @return 傳回雜湊後資料內容
     */
    private static byte[] getHash(final String algorithm, final String text) {
        try {
            return getHash(algorithm, text.getBytes("UTF-8"));
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 取得資料的雜湊值
     *
     * @param algorithm 傳入雜驟演算法
     * @param data      傳入要雜湊的資料
     * @return 傳回雜湊後資料內容
     */
    private static byte[] getHash(final String algorithm, final byte[] data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(data);
            return digest.digest();
        } catch (final Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

}
