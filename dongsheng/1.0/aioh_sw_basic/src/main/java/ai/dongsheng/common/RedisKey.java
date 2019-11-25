package ai.dongsheng.common;

/**
 * @program: aioh_sw_im
 * @description: Redis的key
 * @author: MichelleJou
 * @create: 2019-09-17 17:44
 **/
public class RedisKey {

    // TODO  im:token:  + device + : + userId字符串组成key，用于存放   access_token（hashkey）与
    //  refresh_token（hashkey）
    //  这个存放格式是hash
    public static final String IM_TOKEN = "im:token:%s:%s";
    // TODO  im_appid_refresh_token_ + userId 字符串组成key，用于存放   refresh_token（hashkey）的次数 与 userId deviceId  rehtoken
    //  （hashkey）  这个存放格式是hash
    public static final String IM_REHTOKEN = "im:rehtoken:%s";

    // TODO device:Info:+  device_id 字符串组成key，用于存放设备信息 bright （hashkey）与  volume （hashkey） 这个存放格式是hash
    public static final String DEVICE_INFO = "device:Info:%s";
    // TODO  im:picture:+ userId + ":" +  deviceId 为key ，用于存放 用户指定设备截图的图片地址，string格式存储，有效期为 1天
    public static final String IM_PICTURE = "im:picture:%s:%s";


}
