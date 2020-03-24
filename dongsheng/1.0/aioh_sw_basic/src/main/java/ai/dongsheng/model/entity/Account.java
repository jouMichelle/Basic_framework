package ai.dongsheng.model.entity;

import ai.dongsheng.utils.GsonUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author xujing
 * Created by Dell on 2019/8/19.
 */
@Data
public class Account extends BaseEntity{

    /**
     * 用户ID
     */
    private Long id;
    /**
     * 证件号码（type=0时为手机号，type=1时是机器人编号）
     */
    private String passport;
    /**
     * 证件号类型（0-手机号，1-机器人）
     */
    private Short passportType;
    /**
     * 用户微信openid
     */
    private String openid;
    /**
     * 用户微信unionid
     */
    private String unionid;
    /**
     * 名字
     */
    private String name;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 头像
     */
    private String head;
    /**
     * 性别（0-其他，1-男，2-女）
     */
    private Short sex;
    /**
     * 更新时间戳
     */
    private Long syncKey;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 其他用户信息
     */
    private String info;
    /**
     * 客户端所在系统名称（1.0超级宝宝/2.0康恩斯/3.0动声平台）
     */
    private String osName;
    /**
     * 客户端所在系统版本号
     */
    private String osVersion;
    /**
     * 登录途径：loginType : 1-APP/2-web/3-微信小程序/4-带屏机器人/5-无屏机器人
     */
    private String loginType;

}
