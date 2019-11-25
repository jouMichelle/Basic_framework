package ai.dongsheng.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ai.dongsheng.cache.RedisHash;
import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.common.Session;
import ai.dongsheng.exception.BaseException;


@Service
public class BaseService {
	
    @Resource
    private RedisHash redisHash;

    public String getUserName(){
        String token = Session.get();
        if(token == null){
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        String user = (String) redisHash.get("token_"+token, "username");
        if(user == null){
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        return user;
    }

    public int getUserId(){
        String token = Session.get();
        if(token == null){
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        String user = redisHash.get("token_"+token, "userId");
        if(user == null){
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        return Integer.parseInt(user);
    }

}
