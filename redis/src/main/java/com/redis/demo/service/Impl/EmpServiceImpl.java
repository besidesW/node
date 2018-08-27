package com.redis.demo.service.Impl;

import com.redis.demo.dao.EmpMapper;
import com.redis.demo.entity.Employee;
import com.redis.demo.redisUtil.JedisPoolUtils;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Override
    public List<Employee> allEmps() {

        Jedis jedis =  JedisPoolUtils.getJedis();

        boolean flag = jedisPoolUtils.exist("emps");
        System.out.println(flag);
        List<Employee> es =null;
        if(flag == true){
            System.out.println("进入");
            es = jedisPoolUtils.getList("emps");
            System.out.println("返回");
            return es;
        }
        List<Employee> emps = empMapper.allEmps();

       jedisPoolUtils.setList("emps" , emps);
        return emps;
    }
}
