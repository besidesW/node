package com.redis.demo;

import com.redis.demo.entity.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    private RedisTemplate<Object , Object> redisTemplate;

    @Test
    public void testRedis(){
        UserInfo userInfo = new UserInfo("18701744326" , "besides3" , "1234");
        redisTemplate.opsForValue().set("user" , userInfo.getNickName());

        String nickNmae =(String) redisTemplate.opsForValue().get("user2");

        System.out.println(nickNmae);

      //  boolean flag = redisTemplate.hasKey("user");
     //   System.out.println(flag);
      //  Assert.assertEquals(true , redisTemplate.hasKey("user"));


    }


    @Test
    public void contextLoads() {
    }

}
