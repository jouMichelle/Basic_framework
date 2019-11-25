package ai.dongsheng.interceptor;

import org.springframework.stereotype.Component;

import ai.dongsheng.cache.RedisValue;
import ai.dongsheng.common.ErrorCode;
import ai.dongsheng.exception.BaseException;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AuthorizationProvider {
    @Resource
    private RedisValue redisValue;

    public Authorization verify(String authorizationHeader) {
        final String START = "Basic ";
        if (authorizationHeader == null || !authorizationHeader.startsWith(START)) {
            throw new BaseException(ErrorCode.BAD_REQUEST, "Authorization is not Basic");
        }

        byte[] auth = authorizationHeader.substring(START.length()).getBytes(StandardCharsets.UTF_8);
        String content = new String(Base64.getDecoder().decode(auth), StandardCharsets.UTF_8);
        if (content.isEmpty()) {
            throw new BaseException(ErrorCode.BAD_REQUEST, "Authorization is empty");
        }

        int index = content.indexOf(':');
        if (index < 0) {
            throw new BaseException(ErrorCode.BAD_REQUEST, "Authorization's format is error");
        }
        String username = content.substring(0, index);
        String password = content.substring(index + 1);
        String[] parts = username.split(",");
        String timestamp = parts.length > 1 ? parts[1] : null;
        String token = parts.length > 2 ? parts[2] : null;
        Authorization a = Authorization.of(parts[0], timestamp, token, password);

        String appToken = redisValue.get("appId-" + a.appId);
        if(appToken == null){
            throw new BaseException(ErrorCode.UNAUTHORIZED, "Authorization verify error");
        }
        if(appToken.contains("\"")) {
            appToken = appToken.replaceAll("\"", "");
        }

        if (!a.sig.equals(Authorization.sig(a.appId, appToken, a.timestamp, a.token))) {
            throw new BaseException(ErrorCode.UNAUTHORIZED, "Authorization verify error");
        }
        return a;
    }
}
