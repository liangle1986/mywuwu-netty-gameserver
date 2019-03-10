package com.mywuwu.gameserver.core.redisTool;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RedisTool {

    public static String inc(RedisTemplate redisTemplate, String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, Objects.requireNonNull(redisTemplate.getConnectionFactory()));
        Long increment = entityIdCounter.getAndIncrement();

        if (increment.longValue() == 0 && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment.toString();
    }

    /**
     * 获取房间号
     *
     * @param redisTemplate redis对象
     * @param key           生成可kdy
     * @param liveTime      设置有效期
     * @return
     */
    public static String getRoomId(RedisTemplate redisTemplate, String key, long liveTime) {
        String value = inc(redisTemplate, key, liveTime);
        return isNumber(Integer.valueOf(value)) + "";
    }


    /**
     * 现在最大房间数和最小房间号
     * @param number 自增长号
     * @return 6位房间号
     */
    private static int isNumber(int number) {
        int newValue = 0;
        if (number > 100000 && number < 999999) {
            newValue = number;
        } else if (number > 999999) {
            newValue = 100000 + (number - 999999);
            if (newValue > 999999) {
                newValue = isNumber(newValue);
            }
        } else {
            newValue = 1000000 + number;
        }
        return  newValue;
    }
}