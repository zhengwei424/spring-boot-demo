package com.zhengwei.security.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings(value= {"unchecked", "rawtypes"})
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;


    /**
     * 缓存基本对象，String、Integer、实体类等
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)  {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public boolean expire (final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @param timeout
     * @param timeUnit 时间单位
     * @return
     */
    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取缓存基本对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getCacheObject(final String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    public boolean delCacheObject(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection
     * @return
     */
    public long deleteCacheObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    public <T> long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    public <T> List<T> getCacheList(final String key) {
        return (List<T>) redisTemplate.opsForList().range(key, 0, -1);
    }

    public <T>BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = dataSet.iterator();

        while (iterator.hasNext()) {
            setOperation.add(iterator.next());
        }
        return setOperation;
    }

    public <T> Set<T> getCacheSet(final String key) {
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    public <T>   void setCacheMap(final String key, final Map<String, T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    public <T> Map<T, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public <T> void setCacheMapValue(final String key, final String hKey,final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    public <T> T getCacheMapValue(final String key,final String hKey) {
        return (T) redisTemplate.opsForHash().get(key, hKey);
    }

    public void deleteCacheMapValue(final String key, final String hKey) {
        redisTemplate.opsForHash().delete(key, hKey);
    }

    /**
     * 获取多个hash中的数据
     * @param key
     * @param hKeys
     * @param <T>
     * @return
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return (List<T>) redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获取缓存的基本对象列表
     * @param pattern
     * @return
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }
}
