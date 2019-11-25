package ai.dongsheng.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @program: aioh_sw_im
 * @description: 随机字符串工具类
 * @author: MichelleJou
 * @create: 2019-09-11 13:48
 **/
public class RandomTrainUtils {
    /*
     * description  TODO    使用UUID生成一个32位随机不重复字符串
     * date         2019/9/11 13:50
     * @author      MichelleJou
     * @param
     * @return
     * @return: java.lang.String
     */
    public static String getUuuid() {
        String result = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        // long msgServerId = IdTemplate.nextId();
        // String result =  RandomTrainUtils.RandomString(20) + msgServerId;
        return result;
    }
    /*
     * description  TODO    生成指定长度的随机字符串
     * date         2019/9/11 14:01
     * @author      MichelleJou
     * @param       count
     * @return
     * @return: java.lang.String
     */
    public static String RandomString(int count) {
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random(count);
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


}
