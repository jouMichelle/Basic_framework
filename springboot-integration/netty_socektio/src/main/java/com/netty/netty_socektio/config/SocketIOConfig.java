package com.netty.netty_socektio.config;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.netty.netty_socektio.auth.SocketAuthListener;
import com.netty.netty_socektio.handler.NettySocketHandler;
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
        //在默认情况下,当调用close关闭socke的使用,close会立即返回,
        // 但是,如果send buffer中还有数据,系统会试着先把send buffer中的数据发送出去,然后close才返回.
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        // 设置主机名，默认是0.0.0.0
        configuration.setHostname(host);
        // 设置监听端口
        configuration.setPort(port);
        configuration.setBossThreads(bossCount);
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
        configuration.setUpgradeTimeout(upgradeTimeout);
        // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
        configuration.setPingTimeout(pingTimeout);
        // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
        configuration.setPingInterval(pingInterval);
        //初始化认证监听器
        AuthorizationListener SocketAuthListener = new SocketAuthListener();
        //设置认证监听器
        configuration.setAuthorizationListener(SocketAuthListener);
        SocketIOServer server = new SocketIOServer(configuration);
        //启动socket服务
        server.start();
        return server;

    }
    /**
     *用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
     *
     **/
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }

    /**
     * 注入socket处理拦截器
     * @return
     */
    @Bean
    public NettySocketHandler nettySocketHandler(){
       return new NettySocketHandler();
    }

}
