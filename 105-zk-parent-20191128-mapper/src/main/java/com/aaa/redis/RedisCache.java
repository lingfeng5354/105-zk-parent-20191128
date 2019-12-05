package com.aaa.redis;

import com.aaa.applicationcontext.GetApplicationContext;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description :  
 * @Author      : cat
 * @exception   : 
 * @CreateDate  : 2019/11/21
 * @Version     : 1.0
 */
public class RedisCache implements Cache {
    private Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final String id;
    private RedisTemplate redisTemplate;
//    缓存数据的有效时间
    private static final long EXPIRE_TIME = 30;
    public RedisCache(String id) {
        if (id==null){
            logger.warn("id is null");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        redisTemplate=getRedisTemplate();
        ValueOperations operations = redisTemplate.opsForValue();
        if (value==null){
            logger.info("value is null");
        }else {
            try {
                operations.set(key.toString(),value,EXPIRE_TIME, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("");
            }
        }
    }

    @Override
    public Object getObject(Object key) {
        System.out.println(123465);
        redisTemplate=getRedisTemplate();
        if (key != null ){
            ValueOperations operations = redisTemplate.opsForValue();
            try {
                Object o = operations.get(key.toString());
                return o;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("");
            }
        }else {
            logger.warn("key is null");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        redisTemplate = getRedisTemplate();
        if (key != null){
            try {
                redisTemplate.delete(key.toString());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("");
            }
        }else {
            logger.warn("key is null");
        }
        return null;
    }

    @Override
    public void clear() {
        redisTemplate=getRedisTemplate();
        Set keys = redisTemplate.keys("*" + id + "*");
        try {
            redisTemplate.delete(keys);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("");
        }
    }

    @Override
    public int getSize() {
        redisTemplate=getRedisTemplate();
        int size = redisTemplate.keys("*").size();
        return size;
    }

    public RedisTemplate getRedisTemplate(){
        if (this.redisTemplate==null){
            this.redisTemplate= GetApplicationContext.getName("redisTemplate");
        }
        return redisTemplate;
    }
}
