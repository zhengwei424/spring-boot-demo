package com.zhengwei.security.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.zhengwei.security.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 使用fastjson序列化
 */
@Configuration
public class RedisConfig {

	@Bean
	@SuppressWarnings(value = {"unchecked", "rawtypes"})
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// https://developer.aliyun.com/article/1230034
		// 查询到的redis数据可以反序列化到对应的存储类型
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true); // 这种全局反序列化配置会出现发序列化异常（com.alibaba.fastjson.JSONObject cannot be cast to com.zhengwei.security.domain.LoginUser）
		ParserConfig.getGlobalInstance().addAccept("com.zhengwei");

		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		// key value
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(fastJsonRedisSerializer);

		// hashkey hashvalue
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
