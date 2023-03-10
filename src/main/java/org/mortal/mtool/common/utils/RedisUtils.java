package org.mortal.mtool.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2023/3/10 17:21
 * @description redis处理
 */
@Component
public class RedisUtils {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private final static String prefix = "MORTAL:";

    /**
     * 指定缓存过期时间
     *
     * @param key  键
     * @param time 失效时间(秒)
     * @return 结果
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存过期时间
     *
     * @param key 键
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(prefix + key);
    }

    /**
     * @param key
     * @return
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(prefix + key);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        return set(key, value, -1L);
    }

    /**
     * @param key
     * @param value
     * @param expire 过期时间(秒)
     * @return
     */
    public boolean set(String key, Object value, long expire) {
        try {
            if (expire > 0) {
                redisTemplate.opsForValue().set(prefix + key, value, expire, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(prefix + key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key
     * @param increment
     * @return
     */
    public long incr(String key, long increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(prefix + key, increment);
    }

    /**
     * 递减
     *
     * @param key
     * @param increment
     * @return
     */
    public long decr(String key, long increment) {
        if (increment < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(prefix + key, -increment);
    }


}
