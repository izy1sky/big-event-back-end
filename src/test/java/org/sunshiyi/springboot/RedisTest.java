package org.sunshiyi.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate template;

    @Test
    public void testSet() {
        // 往redis中存储一个（k, v）
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set("username", "zhangsan");
    }

    @Test
    public void testGet() {
        ValueOperations<String, String> operations = template.opsForValue();
        String username = operations.get("username");
        System.out.println(username);
    }
}
