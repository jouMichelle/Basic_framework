package com.example.infrastructure.model.tuling;


import cn.hutool.json.JSONObject;
import com.example.infrastructure.utils.json.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @program: aiui
 * @description: 图灵aiui返回数据
 * @author:
 * @create: 2020-09-24 15:29
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TLAiUiRespData {

    /**
     * 返回码:
     * 2xxxx 功能码；
     * 4xxxx 错误码
     */
    private Integer code;

    /**
     * 请求令牌
     */
    private String token;


    /**
     * 分段音频输出，具体含义根据请求type定义：
     * 当type=0时：普通聊天音频；
     * 当type=1时：主动交互音频；
     * 当type=2时：开机等提示语音频
     */
    private List nlp;

    /**
     * 语音识别文本结果，根据输入字段flag控制是否输出
     */
    private String asr;

    /**
     * 语义解析文本结果，根据输入字段flag控制是否输出
     */
    private String tts;

    /**
     * 返回类型及对应数据结构根据功能码定义，详细参考功能码列表
     */
    private JSONObject func;

    /**
     * 知识库中设置的动作
     */
    private Integer action;

    /**
     * 情绪ID返回数据，具体ID情绪ID对应表；
     * 如果在知识库中设置表情，会覆盖系统返回的表情值
     */
    private Integer emotion;

    /**
     * 版本号
     */
    private String ver;


    @Override
    public String toString() {
        return GsonUtil.toJson(this, this.getClass());
    }
}
