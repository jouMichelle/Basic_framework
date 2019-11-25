package ai.dongsheng.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: aioh_sw_im
 * @description: 签名生成与token生成工具类
 * @author: MichelleJou
 * @create: 2019-09-11 14:58
 **/
public class SignUtils {
    private static Logger logger = LoggerFactory.getLogger(SignUtils.class);
    /*
     * description  TODO    生成一个32位小写的 access_token 或 refresh_token (用于首次生成)
     * date         2019/9/11 16:16
     * @author      MichelleJou
     * @param       map
     * @return
     * @return: java.lang.String
     */
    public static String getToken(Map<String, Object> map) {
        String data = RankUtils.rankData(map);
        // String token = MD5Utils.getMD5(data, false, 32);
        String token =  DigestUtil.md5Hex(data);
        return token;
    }

    /*
     * description  TODO    生成一个32位小写的 access_token 或 refresh_token （用于第二次刷新）
     * date         2019/9/11 16:15
     * @author      MichelleJou
     * @param       data
     * @return
     * @return: java.lang.String
     */
    public static String getRefreshToken(String data){
        String refreshToken =  DigestUtil.md5Hex(data);
        return refreshToken;
    }

    /*
     * description  TODO      生成一个sign字符串
     * date         2019/9/11 15:07
     * @author      MichelleJou
     * @param       url      接口地址
     * @param       appsecret   加密密钥
     * @param       map         参与加密数据
     * @return
     * @return: java.lang.String
     */
    public static String getSign(String url, String appsecret, Map<String, Object> map) {
        String data = RankUtils.customData(map, url);
        logger.debug("SignUtils  Encrypted string:{}",data);
        // System.out.println(data);
        // System.out.println(data + "&" + appsecret);
        logger.debug("SignUtils appsecret Encrypted string:{}",data + "&" + appsecret);
        String sign = DigestUtil.md5Hex(new StringBuffer().append(data).append("&").append(appsecret).toString());
        return sign;
    }



}
