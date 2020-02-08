package cn.czy15.zyweb.config;

import cn.czy15.zyweb.serializer.MyStringRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Zhuoyu Chen
 * @date 2019/12/27 0027 - 20:24
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        MyStringRedisSerializer myStringRedisSerializer=new MyStringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(myStringRedisSerializer);
        redisTemplate.setValueSerializer(myStringRedisSerializer);
        return redisTemplate;
    }
}
