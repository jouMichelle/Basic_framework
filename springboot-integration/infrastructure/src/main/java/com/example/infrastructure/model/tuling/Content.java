package com.example.infrastructure.model.tuling;


import com.example.infrastructure.utils.json.JsonUtil;
import lombok.Data;
import lombok.SneakyThrows;

/**
 * @program: tuling-demo
 * @description: 输入信息
 * @author: urbane
 * @create: 2020-04-23 16:42
 **/
@Data
public class Content {
    /**
     * 是否必须：N
     *
     * 输入类型:
     * type=0-文本(默认)
     * type=1-图片
     * type=2-音频(asr)
     * type=4-主动交互
     * type=5-音频(技能）
     */
    private Integer type;

    // 关于当 type等于4时
    // data	        说明
    // osgreet	    开机提示语
    // osactive	    主动交互
    // authorize	口语评测授权

    /**
     * 是否必须：Y
     *
     * type=0 输入文本类型内容;
     * type=1 输入图片的url;
     * type=2或5 输入字节Base64编码字符;
     * type=4 主动交互，具体说明如下
     */
    private String data;

    @Override
    @SneakyThrows
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
