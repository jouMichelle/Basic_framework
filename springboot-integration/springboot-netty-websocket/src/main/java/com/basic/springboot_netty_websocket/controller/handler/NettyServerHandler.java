package com.basic.springboot_netty_websocket.controller.handler;

import com.basic.springboot_netty_websocket.enums.Cmd;
import com.basic.springboot_netty_websocket.utils.json.JsonUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: springboot-netty-websocket
 * @description: netty服务端处理器
 * @author:
 * @create: 2021-06-14 02:59
 **/
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private ServerChannelCache cache;
    private static final String dataKey = "test=";

    @Data
    public static class ChannelCache {
    }


    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("通道连接已打开，ID->{}......", channel.id().asLongText());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            Channel channel = ctx.channel();
            WebSocketServerProtocolHandler.HandshakeComplete handshakeComplete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            String requestUri = handshakeComplete.requestUri();
            requestUri = URLDecoder.decode(requestUri, "UTF-8");
            log.info("HANDSHAKE_COMPLETE，ID->{}，URI->{}", channel.id().asLongText(), requestUri);
            String socketKey = requestUri.substring(requestUri.lastIndexOf(dataKey) + dataKey.length());
            if (socketKey.length() > 0) {
                cache.add(socketKey, channel);
                this.send(channel, Cmd.DOWN_START, null);
            } else {
                channel.disconnect();
                ctx.close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("通道连接已断开，ID->{}，用户ID->{}......", channel.id().asLongText(), cache.getCacheId(channel));
        cache.remove(channel);
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        log.error("连接出现异常，ID->{}，用户ID->{}，异常->{}......", channel.id().asLongText(), cache.getCacheId(channel), cause.getMessage(), cause);
        cache.remove(channel);
        ctx.close();
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        try {
            log.info("接收到客户端发送的消息：{}", msg.text());
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtils.toString(Collections.singletonMap("cmd", "100"))));
        } catch (Exception e) {
            log.error("消息处理异常：{}", e.getMessage(), e);
        }
    }

    public void send(Cmd cmd, String id, Object obj) {
        HashMap<String, Channel> channels = cache.get(id);
        if (channels == null) {
            return;
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("cmd", cmd.getCmd());
        data.put("data", obj);
        String msg = JsonUtils.toString(data);
        log.info("服务器下发消息: {}", msg);
        channels.values().forEach(channel -> {
            channel.writeAndFlush(new TextWebSocketFrame(msg));
        });
    }

    public void send(Channel channel, Cmd cmd, Object obj) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("cmd", cmd.getCmd());
        data.put("data", obj);
        String msg = JsonUtils.toString(data);
        log.info("服务器下发消息: {}", msg);
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

}
