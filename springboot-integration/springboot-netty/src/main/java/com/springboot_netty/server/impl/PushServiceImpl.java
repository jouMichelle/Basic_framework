package com.springboot_netty.server.impl;

import com.springboot_netty.config.NettyConfig;
import com.springboot_netty.server.PushService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: springboot-netty
 * @description:
 * @author:
 * @create: 2021-06-14 05:11
 **/
@Service
public class PushServiceImpl implements PushService {
    /**
     * 推送给指定用户
     *
     * @param userId
     * @param msg
     */
    @Override
    public void pushMsgToOne(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    /**
     * 推送给所有用户
     *
     * @param msg
     */
    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
