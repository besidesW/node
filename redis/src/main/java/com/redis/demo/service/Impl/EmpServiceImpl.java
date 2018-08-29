package com.redis.demo.service.Impl;

import com.redis.demo.dao.EmpMapper;
import com.redis.demo.entity.Employee;
import com.redis.demo.redisUtil.JedisPoolUtils;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private Jedis jedis;

    @Override
    public String allEmps() {

        boolean flag = jedis.exists("emps");
        if(flag == true ){

            System.out.println("service");
            String emp = jedis.hget("emps" , "emps"+1);
           // emp.split(",");

           return emp;
        }
        List<Employee> emps = empMapper.allEmps();
        JSONArray jsonList = JSONArray.fromObject(emps);
        jedis.hset("emps" , "emps"+1 , jsonList.toString());

        return jsonList.toString();
    }
}
