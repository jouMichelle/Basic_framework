package ai.dongsheng.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisHash implements HashOperations<String, Object, Object>{
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public Long delete(String key, Object... hashKeys) {
		return stringRedisTemplate.opsForHash().delete(key, hashKeys);
	}

	@Override
	public Boolean hasKey(String key, Object hashKey) {
		return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
	}

	@Override
	public String get(String key, Object hashKey) {
		return (String) stringRedisTemplate.opsForHash().get(key, hashKey);
	}

	@Override
	public List<Object> multiGet(String key, Collection<Object> hashKeys) {
		return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
	}

	@Override
	public Long increment(String key, Object hashKey, long delta) {
		return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Double increment(String key, Object hashKey, double delta) {
		return stringRedisTemplate.opsForHash().increment(key, hashKey, delta);
	}

	@Override
	public Set<Object> keys(String key) {
		return stringRedisTemplate.opsForHash().keys(key);
	}

	public Long lengthOfValue(String s, Object o) {
		return null;
	}

	@Override
	public Long size(String key) {
		return stringRedisTemplate.opsForHash().size(key);
	}

	@Override
	public RedisOperations<String, ?> getOperations() {
		return stringRedisTemplate.opsForHash().getOperations();
	}

	@Override
	public void putAll(String key, Map<? extends Object, ? extends Object> m) {
		stringRedisTemplate.opsForHash().putAll(key, m);		
	}

	@Override
	public void put(String key, Object hashKey, Object value) {
		stringRedisTemplate.opsForHash().put(key, hashKey, value);		
	}

	@Override
	public Boolean putIfAbsent(String key, Object hashKey, Object value) {
		return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	@Override
	public List<Object> values(String key) {
		return stringRedisTemplate.opsForHash().values(key);
	}

	@Override
	public Map<Object, Object> entries(String key) {
		return stringRedisTemplate.opsForHash().entries(key);
	}

	@Override
	public Cursor<Entry<Object, Object>> scan(String key, ScanOptions options) {
		return stringRedisTemplate.opsForHash().scan(key, options);
	}
	
	public Boolean delete(String key){
		return stringRedisTemplate.expire(key, 0, TimeUnit.MILLISECONDS);
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
