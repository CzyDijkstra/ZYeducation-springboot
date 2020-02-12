package cn.czy15.zyweb.thread;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 10:10
 */
public class MyRedisTemplate  extends RedisTemplate {
    @Override
    public void restore(Object key, byte[] value, long timeToLive, TimeUnit unit) {

    }
}
