package com.example.infrastructure.cache;

import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisValue implements ValueOperations<String, String> {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void set(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);		
	}

	@Override
	public Boolean setIfAbsent(String key, String value) {
		return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
		return null;
	}

	@Override
	public Boolean setIfPresent(String key, String value) {
		return null;
	}

	@Override
	public Boolean setIfPresent(String key, String value, long timeout, TimeUnit unit) {
		return null;
	}

	@Override
	public void multiSet(Map<? extends String, ? extends String> map) {
		stringRedisTemplate.opsForValue().multiSet(map);		
	}

	@Override
	public Boolean multiSetIfAbsent(Map<? extends String, ? extends String> map) {
		return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
	}

	@Override
	public String get(Object key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public String getAndSet(String key, String value) {
		return stringRedisTemplate.opsForValue().getAndSet(key, value);
	}

	@Override
	public List<String> multiGet(Collection<String> keys) {
		return stringRedisTemplate.opsForValue().multiGet(keys);
	}

	@Override
	public Long increment(String key) {
		return stringRedisTemplate.opsForValue().increment(key);
	}

	@Override
	public Long increment(String key, long delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Double increment(String key, double delta) {
		return stringRedisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public Long decrement(String key) {
		//最后一定要带上 *
		Set<String> keys = stringRedisTemplate.keys(key + "*");
		Long delete = stringRedisTemplate.delete(keys);
		return delete;
	}

	@Override
	public Long decrement(String key, long delta) {
		return null;
	}

	@Override
	public Integer append(String key, String value) {
		return stringRedisTemplate.opsForValue().append(key, value);
	}

	@Override
	public String get(String key, long start, long end) {
		return stringRedisTemplate.opsForValue().get(key, start, end);
	}

	@Override
	public void set(String key, String value, long offset) {
		stringRedisTemplate.opsForValue().set(key, value, offset);		
	}

	@Override
	public Long size(String key) {
		return stringRedisTemplate.opsForValue().size(key);
	}

	@Override
	public Boolean setBit(String key, long offset, boolean value) {
		return stringRedisTemplate.opsForValue().setBit(key, offset, value);
	}

	@Override
	public Boolean getBit(String key, long offset) {
		return stringRedisTemplate.opsForValue().getBit(key, offset);
	}

	@Override
	public List<Long> bitField(String key, BitFieldSubCommands subCommands) {
		return null;
	}

	@Override
	public RedisOperations<String, String> getOperations() {
		return stringRedisTemplate.opsForValue().getOperations();
	}

	public Boolean expire(String key,long timeout, TimeUnit unit){
		return stringRedisTemplate.expire(key, timeout, unit);
	}
	
	public Boolean expireAt(String key,Date date){
		return stringRedisTemplate.expireAt(key, date);
	}
	
	public Boolean exists(String key){
		return stringRedisTemplate.hasKey(key);
	}
}
