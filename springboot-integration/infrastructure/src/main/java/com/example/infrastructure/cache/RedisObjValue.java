package com.example.infrastructure.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis Object操作类
 * @author
 *
 */
@Component
public class RedisObjValue implements ValueOperations<String, Object> {
	private static Logger logger = LoggerFactory.getLogger(RedisObjValue.class);
	
	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	@PostConstruct
	public void init() {
		redisTemplate.setKeySerializer(RedisSerializer.string());
		redisTemplate.setHashKeySerializer(RedisSerializer.string());
		logger.info("Initialized RedisObjValue KeySerializer & HashKeySerializer succ...");
	}
	
	@Override
	public void set(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, timeout, unit);		
	}

	@Override
	public Boolean setIfAbsent(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public Boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
		return null;
	}

	@Override
	public Boolean setIfPresent(String key, Object value) {
		return null;
	}

	@Override
	public Boolean setIfPresent(String key, Object value, long timeout, TimeUnit unit) {
		return null;
	}

	@Override
	public void multiSet(Map<? extends String, ? extends Object> map) {
		redisTemplate.opsForValue().multiSet(map);		
	}

	@Override
	public Boolean multiSetIfAbsent(Map<? extends String, ? extends Object> map) {
		return redisTemplate.opsForValue().multiSetIfAbsent(map);
	}

	@Override
	public Object get(Object key) {
		return redisTemplate.opsForValue().get(key);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable>  T get(Object key, Class<T> clz) {
		return (T)redisTemplate.opsForValue().get(key);
	}

	@Override
	public Object getAndSet(String key, Object value) {
		return redisTemplate.opsForValue().getAndSet(key, value);
	}

	@Override
	public List<Object> multiGet(Collection<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Long increment(String key) {
		return null;
	}

	@Override
	public Long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Double increment(String key, double delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Long decrement(String key) {
		return null;
	}

	@Override
	public Long decrement(String key, long delta) {
		return null;
	}

	@Override
	public Integer append(String key, String value) {
		return redisTemplate.opsForValue().append(key, value);
	}

	@Override
	public String get(String key, long start, long end) {
		return redisTemplate.opsForValue().get(key, start, end);
	}

	@Override
	public void set(String key, Object value, long offset) {
		redisTemplate.opsForValue().set(key, value, offset);		
	}

	@Override
	public Long size(String key) {
		return redisTemplate.opsForValue().size(key);
	}

	@Override
	public Boolean setBit(String key, long offset, boolean value) {
		return redisTemplate.opsForValue().setBit(key, offset, value);
	}

	@Override
	public Boolean getBit(String key, long offset) {
		return redisTemplate.opsForValue().getBit(key, offset);
	}

	@Override
	public List<Long> bitField(String key, BitFieldSubCommands subCommands) {
		return null;
	}

	@Override
	public RedisOperations<String, Object> getOperations() {
		return redisTemplate.opsForValue().getOperations();
	}

	public Boolean expire(String key,long timeout, TimeUnit unit){
		return redisTemplate.expire(key, timeout, unit);
	}
	
	public Boolean expireAt(String key,Date date){
		return redisTemplate.expireAt(key, date);
	}
	
	public Boolean exists(String key){
		return redisTemplate.hasKey(key);
	}
}
