package com.example.infrastructure.cache;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisList implements ListOperations<String, String> {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<String> range(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key,start,end);
    }

    @Override
    public void trim(String key, long start, long end) {
        stringRedisTemplate.opsForList().trim(key,start,end);
    }

    @Override
    public Long size(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    @Override
    public Long leftPush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key,value);
    }

    @Override
    public Long leftPushAll(String key, String... values) {
        return stringRedisTemplate.opsForList().leftPushAll(key,values);
    }

    @Override
    public Long leftPushAll(String key, Collection<String> values) {
        return stringRedisTemplate.opsForList().leftPushAll(key,values);
    }

    @Override
    public Long leftPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().leftPushIfPresent(key,value);
    }

    @Override
    public Long leftPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().leftPush(key,pivot,value);
    }

    @Override
    public Long rightPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public Long rightPushAll(String key, String... values) {
        return stringRedisTemplate.opsForList().rightPushAll(key,values);
    }

    @Override
    public Long rightPushAll(String key, Collection<String> values) {
        return stringRedisTemplate.opsForList().rightPushAll(key,values);
    }

    @Override
    public Long rightPushIfPresent(String key, String value) {
        return stringRedisTemplate.opsForList().rightPushIfPresent(key,value);
    }

    @Override
    public Long rightPush(String key, String pivot, String value) {
        return stringRedisTemplate.opsForList().rightPush(key,pivot,value);
    }

    @Override
    public void set(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key,index,value);
    }

    @Override
    public Long remove(String key, long count, Object value) {
        return stringRedisTemplate.opsForList().remove(key,count,value);
    }

    @Override
    public String index(String key, long index) {
        return stringRedisTemplate.opsForList().index(key,index);
    }

    @Override
    public Long indexOf(String s, String s2) {
        return null;
    }

    @Override
    public Long lastIndexOf(String s, String s2) {
        return null;
    }

    @Override
    public String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    @Override
    public String leftPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().leftPop(key,timeout,unit);
    }

    @Override
    public String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    @Override
    public String rightPop(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPop(key,timeout,unit);
    }

    @Override
    public String rightPopAndLeftPush(String sourceKey, String destinationKey) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey,destinationKey);
    }

    @Override
    public String rightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return stringRedisTemplate.opsForList().rightPopAndLeftPush(sourceKey,destinationKey,timeout,unit);
    }

    @Override
    public RedisOperations<String, String> getOperations() {
        return stringRedisTemplate.opsForList().getOperations();
    }


}
