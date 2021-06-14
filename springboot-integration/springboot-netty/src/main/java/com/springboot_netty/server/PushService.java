package com.springboot_netty.server;

/**
 * @program: springboot-netty
 * @description:
 * @author:
 * @create: 2021-06-14 05:07
 **/
public interface PushService {
    /**
     * 推送给指定用户
     * @param userId
     * @param msg
     */
    void pushMsgToOne(String userId,String msg);

    /**
     * 推送给所有用户
     * @param msg
     */
    void pushMsgToAll(String msg);
}
