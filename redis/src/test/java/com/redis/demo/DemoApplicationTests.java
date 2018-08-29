package com.redis.demo;

import com.redis.demo.entity.Employee;
import com.redis.demo.entity.UserInfo;
import com.redis.demo.redisUtil.JedisPoolUtils;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

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


    @Autowired
    private Jedis jedis;

    @Test
    public void testString2(){


        /**
         * 如果使用windows 版本的redis
         * 需要设置 cmd的编码
         * 否则redis 无法显示中文
         * chcp 65001 cmd设置utf-8 编码
         * */
            jedis.set("test" , "就阿卡");
            String str = jedis.get("test");
            System.out.println(str);

    }



        @Test
    public void contextLoads() {
    }

}
