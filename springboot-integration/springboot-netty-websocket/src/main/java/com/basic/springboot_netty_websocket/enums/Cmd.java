package com.basic.springboot_netty_websocket.enums;

/**
 * @program: springboot-netty-websocket
 * @description: 与硬件交互枚举
 * @author:
 * @create: 2021-06-14 02:16
 **/
public enum Cmd {
    /**
     * 连接成功
     */
    START("000", "连接成功"),
    /**
     * 消息提醒
     */
    WMESSAGE("001", "消息提醒"),
    /**
     *
     */
    DOWN_START("", ""),
    ;
    private String cmd;
    private String desc;

    Cmd(String cmd, String desc) {
        this.cmd = cmd;
        this.desc = desc;
    }

    public String getCmd() {
        return cmd;
    }

    public String getDesc() {
        return desc;
    }

}
