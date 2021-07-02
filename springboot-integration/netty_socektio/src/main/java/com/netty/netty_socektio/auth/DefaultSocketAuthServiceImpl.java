package com.netty.netty_socektio.auth;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.netty.netty_socektio.handler.NettySocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 默认认证服务实现
 * @author machenike
 */
public class DefaultSocketAuthServiceImpl implements SocketAuthService {

    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(DefaultSocketAuthServiceImpl.class);

    private final static String QUERY_SN = "sn";

    static Map<String, SocketIOClient> socketMap;

    static{
        socketMap =  NettySocketHandler.clientMap;
    }

    @Override
    public boolean auth(HandshakeData handshakeData) {
        String sn = handshakeData.getSingleUrlParam(QUERY_SN);
        if(sn!=null){
            //若客户端存在
            if(socketMap.get(sn)!=null){
                logger.error("current socket sn - [{}] is repeated",sn);
                //认证失败
                return false;
            }
            logger.info("socket client auth success [sn :{} ]",sn);
            return true;
        }
        logger.error("socket client auth failed [sn:{} ]",sn);
        return false;
    }
}
