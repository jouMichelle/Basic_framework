package com.netty.netty_socektio.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.netty.netty_socektio.utils.NettySocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * socket处理拦截器
 *
 * @author machenike
 */
public class NettySocketHandler implements CommandLineRunner {

    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(NettySocketHandler.class);

    /**
     * 客户端保存用Map
     */
    public static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * 连接数
     */
    public static AtomicInteger onlineCount = new AtomicInteger(0);

    private final static String QUERY_SN = "sn";

    /**
     * 客户端连上socket服务器时执行此事件
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String sn = client.getHandshakeData().getSingleUrlParam(QUERY_SN);
        logger.info("onConnect: [sn:{}]", sn);
        if (sn != null) {
            clientMap.put(sn, client);
            onlineCount.addAndGet(1);
            logger.info("connect success: [sn :{},onlineCount:{}]", sn, onlineCount.get());
        }
    }


    /**
     * 客户端断开socket服务器时执行此事件
     *
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String sn = client.getHandshakeData().getSingleUrlParam(QUERY_SN);
        if (sn != null) {
            clientMap.remove(sn);
            client.disconnect();
            onlineCount.addAndGet(-1);
            logger.info("disconnect success: [sn:{},onlineCount:{}]", sn, onlineCount.get());
        }
    }

    /**
     * @param client
     */
    @OnEvent(value = "message")
    public void onMessage(SocketIOClient client, AckRequest request, Object data) {
        String sn = client.getHandshakeData().getSingleUrlParam(QUERY_SN);
        logger.info("onMessage: [sn:{},data:{}]", sn, data);
        //request.sendAckData("message is revived");
        NettySocketUtil.sendNotice("test message");
        //回发消息
        client.sendEvent("ack", 1);
    }


    @OnEvent(value = "customMessage")
    public void customMessage(SocketIOClient client, AckRequest request, Object data) {
        String sn = client.getHandshakeData().getSingleUrlParam(QUERY_SN);
        logger.info("customMessage: [sn:{},data:{}]", sn, data);
        //System.out.println(commandVO);
        //request.sendAckData("message is revived");
        //NettySocketUtil.sendMessage(sn,"customMessage","ok");
        //回发消息
        client.sendEvent("customMessage", "ok");
    }

    @OnEvent(value = "heartbeat")
    public void heartbeat(SocketIOClient client, AckRequest request, Object data){
        String sn = client.getHandshakeData().getSingleUrlParam(QUERY_SN);
        logger.info("customMessage: [sn:{},data:{}]", sn, data);
        //request.sendAckData("message is revived");
        NettySocketUtil.sendNotice("test customMessage");
        //回发消息
        client.sendEvent("ack", 1);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("socketHandler start-------------------------------");
    }
}
