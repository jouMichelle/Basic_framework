package ai.dongsheng.utils;

import java.util.*;

/**
 * @program: aioh_sw_im
 * @description: 排序工具类
 * @author: MichelleJou
 * @create: 2019-09-11 14:12
 **/
public class RankUtils {
    /*
     * description  TODO    将map中的数据以key进行 字符ASCII码升序排列
     * date         2019/9/11 14:16
     * @author      MichelleJou
     * @param       map
     * @return
     * @return: java.lang.String
     */
    public static String rankData(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            Object value = map.get(key);
            sb.append(key + "=" + value + "&");
        }
        String signStr = sb.substring(0, sb.length() - 1);
        return signStr;
    }

    /*
     * description  TODO    定制化排序字符串
     * date         2019/9/11 14:23
     * @author      MichelleJou
     * @param       map
     * @param       url
     * @return
     * @return: java.lang.String
     */
    public static String customData(Map<String, Object> map, String url) {
        if (map == null) {
            return null;
        }
        String data = RankUtils.rankData(map);
        return new StringBuffer().append(url).append("&").append(data).toString();
    }


}
