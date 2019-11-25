package ai.dongsheng.interceptor;

import lombok.Data;


/**
 * @program: aioh_sw_im
 * @description: 验证sign类
 * @author: MichelleJou
 * @create: 2019-09-12 16:51
 **/
@Data
public class OpenPlatform {
    /*
 应用ID。由32个英文字母和阿拉伯数字组成的应用唯一标识符
  */
    private String appId;
    /*
    时间戳（单位毫秒）,发起调用到请求达到服务器，如果超过60秒，该次请求将无效。
     */
    private long timestamp;
    /*
    H5从动声后台获得的授权码（时效默认：4小时过期）
     */
    private String authToken;
    /*
    设备ID
     */
    private String deviceId;
    /*
    签名
     */
    private String sign;
    /*
    当前接口地址
     */
    private String url;
    /*
    用户id
     */
    private Long userId;
}
