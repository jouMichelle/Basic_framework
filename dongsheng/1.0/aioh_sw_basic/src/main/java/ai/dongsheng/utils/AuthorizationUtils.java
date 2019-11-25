package ai.dongsheng.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @program: aioh_sw_im
 * @description: 组装平台验证请求包头
 * @author: MichelleJou
 * @create: 2019-10-11 20:02
 **/
public class AuthorizationUtils {
    private static String appId = "1c3bc6cc73a2a4b90681fc934d633b53";
    private static String secretKey = "6c6c77ad7eab9b9fc96c1c026e815ce0";

    public static String getAuthorization(String accessToken) {
        // 时间戳（单位毫秒）
        long timestamp = System.currentTimeMillis();
        String sig = sig(appId, secretKey, String.valueOf(timestamp), accessToken);
        String data =
                new StringBuilder().append(appId).append(",").append(timestamp).append(",").append(accessToken).append(":").append(sig).toString();
        String encodedString = Base64.getEncoder().encodeToString(data.getBytes());
        return new StringBuilder().append("Basic ").append(encodedString).toString();
    }

    /*
     * description  TODO    生成加密串
     * date         2019/10/11 20:11
     * @author      MichelleJou
     * @param       appId       服务器提供统一的appId
     * @param       appToken    服务器提供统一的 secretKey
     * @param       timestamp   时间戳
     * @param       accessToken     token是用户登陆时
     * @return
     * @return: java.lang.String
     */
    private static String sig(String appId, String appToken, String timestamp, String accessToken) {
        if (accessToken == null) {
            return md5(appId + appToken + timestamp);
        }
        return md5(appId + appToken + timestamp + accessToken);
    }

    private static String md5(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(src.getBytes(StandardCharsets.UTF_8));
            return byte2HexStr(b);
        } catch (Throwable e) {
            throw new AssertionError("Unsupported MD5 algorithm", e);
        }
    }

    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s.toLowerCase());
        }
        return sb.toString();
    }
}
