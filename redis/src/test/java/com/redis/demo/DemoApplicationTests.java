package com.redis.demo;

import com.redis.demo.entity.Employee;
import com.redis.demo.entity.UserInfo;
import com.redis.demo.redisUtil.JedisPoolUtils;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Autowired
    private RedisTemplate<Object , Object> redisTemplate;








    @Test
    public void testString(){
        UserInfo userInfo = new UserInfo("18701744326" , "besides3" , "1234");
        redisTemplate.opsForValue().set("user2" , userInfo.getNickName());

        String nickNmae =(String) redisTemplate.opsForValue().get("user2");

        System.out.println(nickNmae);

      //  boolean flag = redisTemplate.hasKey("user");
     //   System.out.println(flag);
      //  Assert.assertEquals(true , redisTemplate.hasKey("user"));


    }

    /**
     * redis 存储map
     * redis 只能存入string类型的数据
     * 需要使用json转换
     * */
    @Test
    public void testHash(){
        UserInfo userInfo = new UserInfo("18701744326" , "besides1" , "1234");
        UserInfo userInfo2 = new UserInfo("18701744326" , "besides2" , "1234");
        //将Userinfo 转换为json 否则无法解析
        JSONObject json1 = JSONObject.fromObject(userInfo);
        JSONObject json2 = JSONObject.fromObject(userInfo2);

        Map<String , String> map = new HashMap();
        map.put("user1" , json1.toString());
        map.put("user2" , json2.toString());

        redisTemplate.opsForHash().putAll("map4"  , map);

    }


    @Autowired
    private JedisPoolUtils jedisPoolUtils;


    @Test
    public void testJedis(){
     //   Jedis jedis = new Jedis("127.0.0.1");

        Jedis jedis = jedisPoolUtils.getJedis();

        UserInfo userInfo = new UserInfo("18701744326" , "besides1" , "1234");
        UserInfo userInfo2 = new UserInfo("18701744326" , "besides2" , "1234");

        //将Userinfo 转换为json 否则无法解析
        JSONObject json1 = JSONObject.fromObject(userInfo);
        JSONObject json2 = JSONObject.fromObject(userInfo2);



        jedis.lpush("emps3" , json1.toString());
        jedis.lpush("emps3" , json2.toString());

        List<String> users = jedis.lrange("emps3" , 0 ,10);

        for ( String str : users
             ) {
            System.out.println(str);
        }


    }


    @Autowired
    private EmpService empService;

    @Test
    public void testEmps(){

        List<Employee> emps = empService.allEmps();

        for (Employee emp: emps) {
            System.out.println(emp.getEname());
        }

    }




    @Test
    public void contextLoads() {
    }

}
