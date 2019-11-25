package ai.dongsheng.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.dongsheng.cache.RedisHash;
import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.common.Session;
import ai.dongsheng.exception.BaseException;

public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static final int RESP_SUCC = 200;

    @Resource
    private RedisHash redisHash;

    public String getUserName() {
        String token = Session.get();
        if (token == null) {
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }
        String user = (String) redisHash.get("token_" + token, "username");
        if (user == null) {
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        return user;
    }

    public int getUserId() {
        String token = Session.get();
        if (token == null) {
            logger.error("getUserId Error cause:[ Session not token possible without @Signature annotation]");
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        String user = redisHash.get("token_" + token, "userId");
        if (user == null) {
            logger.error("getUserId Error cause:[{}],rediskey:[{}]", "key nonentity", "token_" + token);
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        return Integer.parseInt(user);
    }

    public Long getLoginUserId() {
        String token = Session.get();
        if (token == null) {
            logger.error("getUserId Error cause:[ Session not token possible without @Signature annotation]");
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        String userId = redisHash.get("token_" + token, "userId");
        if (userId == null) {
            logger.error("getUserId Error cause:[{}],rediskey:[{}]", "key nonentity", "token_" + token);
            throw new BaseException(ErrorCode.UNAUTHORIZED, "token错误");
        }

        return Long.parseLong(userId);
    }

    /**
     * 验证时长，最小为1秒
     *
     * @param duration
     */
    protected void validateDuration(int duration) {
        if (duration <= 0) {
            throw new BaseException(ErrorCode.BAD_REQUEST, "时长最小为1秒");
        }
    }
}
