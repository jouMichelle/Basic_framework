package ai.dongsheng.starter.util;

import java.util.List;

import javax.annotation.Resource;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import ai.dongsheng.cache.RedisHash;
import ai.dongsheng.cache.RedisList;
import ai.dongsheng.cache.RedisObjValue;
import ai.dongsheng.cache.RedisValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Resource
    private RedisList redisList;
    
    @Resource
    private RedisHash redisHash;
    
    @Resource
    private RedisValue redisValue;	
    
    @Resource
    private RedisObjValue redisObjValue;
    
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisList(){
    	System.out.println("测试RedisList");
    	long pushNums = redisList.leftPushAll("hsx_list", "1","2","3","4","结束");
    	System.out.println("leftPushAll nums: "+pushNums);
    	List<String> items = redisList.range("hsx_list", 0, -1);
    	System.out.println(items);
    }
    
    @Test
    public void testRedisHash(){
    	System.out.println("测试RedisHash");
    	
    	String key="hsx_hash";
    	redisHash.put(key, "id", "1001");
    	redisHash.put(key, "name", "张三");
    	redisHash.put(key, "address", "深圳市南山区");

    	List<Object> result = redisHash.multiGet(key, Arrays.asList(new String[] {"id","name","address"}));
    	System.out.println(result);
    }
    
    @Test
    public void testRedisValue(){
    	System.out.println("测试RedisValue");
    	redisValue.set("hsx_value", "哈哈");
    	String result = redisValue.get("hsx_value");
    	System.out.println(result);
    }
    
    @Test
    public void testStringRedisTemplate(){
    	System.out.println("测试StringRedisTemplate");
    	stringRedisTemplate.opsForValue().set("hsx", "hsx123455,你好!");
    	String val = stringRedisTemplate.opsForValue().get("hsx");
    	System.out.println(val);
    }

  
}
