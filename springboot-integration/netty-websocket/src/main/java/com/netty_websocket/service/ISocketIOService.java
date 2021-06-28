package com.netty_websocket.service;

/**
 * @description:
 * @author:
 * @create: 2021-06-28 19:19
 **/
public interface ISocketIOService {
    /**
     * 启动服务
     */
    void start();
    /**
     * 停止服务
     */
    void stop();
    /**
     * 推送消息給指定用户
     * @param userId 客户端唯一标识
     * @param msgContent 消息内容
     */
    void pushMessageToUser(String userId, String msgContent);
}
