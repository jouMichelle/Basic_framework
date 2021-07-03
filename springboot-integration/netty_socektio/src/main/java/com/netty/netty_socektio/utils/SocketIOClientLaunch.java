package com.netty.netty_socektio.utils;



import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * @description:
 * @author:
 * @create: 2021-07-02 10:30
 **/
@Slf4j
public class SocketIOClientLaunch {
    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
        //String url = "http://127.0.0.1:9000";
        String url = "http://192.168.0.129:9000?sn=1";
        //String url = "http://47.119.204.247:9000";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            //final Socket socket = IO.socket(url + "?sn=1", options);
            //final Socket socket = IO.socket(url + "?clientId=1", options);
            final Socket socket = IO.socket(url, options);
            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));

            //监听自定义msg事件
            socket.on("message", objects -> System.out.println("client: 收到msg->" + Arrays.toString(objects)));

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("customMessage", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));

            socket.on(Socket.EVENT_CONNECT, objects -> {
                socket.emit("sub", "我是訂閲對象");
                System.out.println("client: " + "连接成功");
            });
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_TIMEOUT, objects -> System.out.println("client: " + "连接超时"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.connect();

            while (true) {
                Thread.sleep(3000);
                socket.emit("customMessage",  "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
