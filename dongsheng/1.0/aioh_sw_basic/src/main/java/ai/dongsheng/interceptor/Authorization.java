package ai.dongsheng.interceptor;

import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.exception.BaseException;


@Getter
public class
Authorization {
    public int error = ErrorCode.OK;
    public final String timestamp;
    public final String appId;
    public final String token;
    public final String sig;

    public boolean isSuccess() {
        return error == ErrorCode.OK;
    }

    @Override
    public String toString() {
        return "appId:" + appId + ", timestamp:" + timestamp + ", token:" + token + ", sig:" + sig;
    }

    private Authorization(String appId, String timestamp, String token, String sig) {
        this.timestamp = Empty.nullable(timestamp);
        this.appId = Empty.nullable(appId);
        this.token = Empty.nullable(token);
        this.sig = Empty.nullable(sig);
    }

    public static Authorization of(String appId, String timestamp, String token, String sig) throws BaseException {
        return new Authorization(appId, timestamp, token, sig);
    }

    public static String auth(String appId, String appToken, String accessToken) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        if (accessToken == null) {
            return appId + "," + timestamp + ":" + sig(appId, appToken, timestamp);
        }
        return appId + "," + timestamp + "," + accessToken + ":" + sig(appId, appToken, timestamp, accessToken);
    }

    private static String sig(String appId, String appToken, String timestamp) {
        return md5(appId + appToken + timestamp);
    }

    static String sig(String appId, String appToken, String timestamp, String accessToken) {
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
