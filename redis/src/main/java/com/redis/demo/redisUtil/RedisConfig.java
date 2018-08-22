package com.redis.demo.redisUtil;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.demo.entity.RedisObject;
import com.redis.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }


//
//
//    @Bean
//    public RedisTemplate<String , Object> redisSerializerObj(RedisConnectionFactory redisConnectionFactory){
//
//        RedisTemplate<String , Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        //使用Jackson2JsonRedisSerializer 替换默认序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.setVisibility(PropertyAccessor.ALL , JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//        //value，key 序列化规则
//   //    redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }


}
