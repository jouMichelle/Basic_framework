package com.netty.netty_socektio.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: socketio配置类
 * @author:
 * @create: 2021-06-30 11:09
 **/
@Slf4j
@Configuration
public class SocketIOConfig {

    /**
     * host 地址
     */
    @Value("${socketio.host}")
    private String host;
    /**
     * 监听端口
     */
    @Value("${socketio.port}")
    private Integer port;
    /**
     * socket连接数大小（如只监听一个端口boss线程组为1即可）
     */
    @Value("${socketio.bossCount}")
    private int bossCount;
    /**
     * 连接数大小
     */
    @Value("${socketio.workCount}")
    private int workCount;
    /**
     * 允许客户请求
     */
    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;
    /**
     * 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
     */
    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;
    /**
     * Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
     */
    @Value("${socketio.pingTimeout}")
    private int pingTimeout;
    /**
     * Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
     */
    @Value("${socketio.pingInterval}")
    private int pingInterval;
    /**
     * 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
     */
    @Value("${socketio.maxFramePayloadLength}")
    private int maxFramePayloadLength;
    /**
     * 设置http交互最大内容长度
     */
    @Value("${socketio.maxHttpContentLength}")
    private int maxHttpContentLength;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        // 配置端口
        config.setPort(port);
        // 开启Socket端口复用
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setSoLinger(0);
        socketConfig.setReuseAddress(true);
        config.setSocketConfig(socketConfig);
        // 连接数大小
        config.setWorkerThreads(workCount);
        // 允许客户请求
        config.setAllowCustomRequests(allowCustomRequests);
        // 协议升级超时时间(毫秒)，默认10秒，HTTP握手升级为ws协议超时时间
        config.setUpgradeTimeout(upgradeTimeout);
        // Ping消息超时时间(毫秒)，默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        config.setPingTimeout(pingTimeout);
        // Ping消息间隔(毫秒)，默认25秒。客户端向服务器发送一条心跳消息间隔
        config.setPingInterval(pingInterval);
        // 设置HTTP交互最大内容长度
        config.setMaxHttpContentLength(maxHttpContentLength);
        // 设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
        config.setMaxFramePayloadLength(maxFramePayloadLength);
        // 设置是否可以跨域访问
        config.setOrigin("*");
        // 鉴权管理
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                // 可以使用如下代码获取用户密码信息
                String sn = data.getSingleUrlParam("sn");
                log.info("sn:{}", sn);

                // 如果认证不通过会返回一个Socket.EVENT_CONNECT_ERROR事件
                return false;
            }
        });
        SocketIOServer socketIOServer = new SocketIOServer(config);
        //socketIOServer.addListeners(eventListenner);
        //socketIOServer.start();
        return socketIOServer;

    }
}
