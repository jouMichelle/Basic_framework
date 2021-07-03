package com.netty.netty_socektio.utils;


import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @description:
 * @author:
 * @create: 2021-07-02 10:06
 **/
@Slf4j
public class ChatClient {
    //options.transports = new String[]{"websocket"};
    public static void main(String[] args) {
        String url = "http://47.119.204.247:9000?sn=111";
        //String url = "http://192.168.0.129:9000?sn=111";
        try {
            IO.Options options = new IO.Options();
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);
            //监听自定义msg事件
            socket.on("message", objects -> System.out.println("client: 收到msg->" + Arrays.toString(objects)));
            //监听自定义订阅事件
            socket.on("customMessage", objects -> System.out.println("client: " + "订阅成功，收到反馈->" + Arrays.toString(objects)));
            socket.on(Socket.EVENT_CONNECT, objects -> {
                socket.emit("sub", "我是訂閲對象");
                System.out.println("client: " + "连接成功");
            });
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.connect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
