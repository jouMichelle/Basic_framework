package com.example.infrastructure.model.tuling;


import com.example.infrastructure.utils.json.GsonUtil;
import lombok.Data;

import java.util.List;

/**
 * @program: aiui
 * @description: 图灵aiWiFi接入所用参数
 * @author: urbane
 * @create: 2020-08-31 09:57
 **/
@Data
public class AiWifiParam {
    /**
     * 必选
     * apiKey,用于权限验证
     */
    private String ak;

    /**
     * 必选
     * 设备id加密后的字符串（长度为32位）
     */
    private String uid;

    /**
     * 非必选
     * 请求令牌,首次请求可以为空,后续必需带上token，最好是上次返回token
     */
    private String token;

    /**
     * 必选
     * <p>
     * 针对上传音频字段设置控制:
     * 当asr=2(不推荐)时：amr_8K_16bit;
     * 当asr=3时：amr_16K_16bit；
     * 当asr=4(推荐)时：opus；
     * 当asr=5(不推荐)时：speex（需要用特定的编码工具）;
     * 如果需要其他格式，请联系商务
     */
    private Integer asr = 2;


    /**
     * 语义解析输出结果音频格式控制:
     * 当tts=2时：mp3_24;
     * 当tts=3时：mp3_16(推荐);
     * 当tts=4时：amr_nb;
     * 如果需要其他格式，请联系商务
     */
    private Integer tts = 3;


    /**
     * 必选
     * <p>
     * tts音色选择:
     * 取值范围20~22;
     */
    private Integer tone = 22;

    /**
     * 非必选
     * <p>
     * 结果输出控制标识:
     * 当flag=0时：不输出文本（默认）；
     * 当flag=1时：输出asr文本信息；
     * 当flag=2时：输出tts文本信息；
     * 当flag=3时：输出asr&tts文本信息；
     */
    private Integer flag = 3;

    /**
     * 非必选
     * <p>
     * 流式识别控制字段
     * 当realTime=0时：非流式识别（默认），上传音频大小不能超过360KB；
     * 当realTime=1时：流式识别，分段的每包音频的大小不能超过20KB;
     */
    private Integer realTime;

    /**
     * 非必选
     * <p>
     * 当realTime=1为流式识别时，此字段必选，用以标识音频片段索引。
     * index从1开始计数，且最后一个音频片段索引必须为负数，如index=1、2、3、-4。
     * 注意index最大不能超过30
     */
    private Integer index;

    /**
     * 非必选
     * <p>
     * 当realTime=1时，该字段有效，用于标识一个流式识别过程，所以每个流式识别过程该identify值必须保证唯一性。(要
     * 求32位随机数，可由数字和小写字母组成，不支持大写字母和特殊字符)
     */
    private String identify;

    /**
     * 非必选
     * <p>
     * 上传音频编码方式，主要用于自定义编码支持，非自定义编码可忽略该字段:
     * 当encode=0时：通用编码，即asr字段支持的编码方式（默认）;
     * 当encode=1时：自定编码，若使用该编码方式，则需要提供转码工具，且转码目标格式必须是asr字段支持的格式。
     */
    private Integer encode;

    /**
     * 非必选
     * <p>
     * 请求类型标识:
     * type=0时：智能聊天（默认）;
     * type=1时：主动交互;
     * type=2时：开机提示语;
     * type=3时：口语评测（需要商务沟通）;
     * type=4时：绘本模式（需要商务沟通）;
     * type=5时：文本输入
     */
    private Integer type;

    /**
     * 非必选
     * tts语速设置:
     * 取值范围1~9， 默认5
     */
    private Integer speed;

    /**
     * 非必选
     * <p>
     * tts语调设置:
     * 取值范围1~9， 默认5
     */
    private Integer pitch;

    /**
     * 非必选
     * <p>
     * tts的音量:
     * 取值范围1~9, 默认5
     */
    private Integer volume = 8;

    /**
     * 非必选
     * <p>
     * 指定使用相应的功能进行文本解析:
     * 例如[20015]为指定中英互译
     */
    private List seceneCodes;

    /**
     * 非必选
     * <p>
     * 选择ASR语言:
     * 0为中文（默认）;
     * 1为英文
     */
    private Integer asr_lan;

    /**
     * 非必选
     * <p>
     * 选择TTS语言:
     * 0为中文（默认）;
     * 1为英文
     */
    private Integer tts_lan;

    /**
     * 非必选
     * <p>
     * 当type=5时，用来表示需要输入的文本
     */
    private String textStr;

    /**
     * 非必选
     * <p>
     * 需要进入上下文的code，对应功能码:
     * 比如需要使用【下一集】进入视频播放，那么contextCode=20032.
     */
    private Integer contextCode;

    /**
     * 非必选
     * <p>
     * 扩展字段；一些特殊技能中会用到
     */
    private Object extra;

    @Override
    public String toString() {
        return GsonUtil.toJson(this, this.getClass());
    }
}
