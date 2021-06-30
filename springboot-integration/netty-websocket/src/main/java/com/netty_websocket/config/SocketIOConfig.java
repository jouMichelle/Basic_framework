package com.netty_websocket.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author:
 * @create: 2021-06-28 19:17
 **/
@Slf4j
@Configuration
public class SocketIOConfig {
    @Value("${socketio.host}")
    private String host;
    @Value("${socketio.port}")
    private Integer port;
    @Value("${socketio.bossCount}")
    private int bossCount;
    @Value("${socketio.workCount}")
    private int workCount;
    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;
    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;
    @Value("${socketio.pingTimeout}")
    private int pingTimeout;
    @Value("${socketio.pingInterval}")
    private int pingInterval;

    // 设置 socket 服务器的属性
    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        // 关闭nagle算法，默认false
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        configuration.setHostname(host);
        configuration.setPort(port);
        configuration.setBossThreads(bossCount);
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        configuration.setUpgradeTimeout(upgradeTimeout);
        configuration.setPingTimeout(pingTimeout);
        configuration.setPingInterval(pingInterval);
        // 鉴权管理
        configuration.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                // 可以使用如下代码获取用户密码信息
                String sn = data.getSingleUrlParam("sn");
                log.info("sn:{}", sn);

                // 如果认证不通过会返回一个Socket.EVENT_CONNECT_ERROR事件
                return true;
            }
        });
        return new SocketIOServer(configuration);
    }
}
