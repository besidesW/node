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

    /**
     * redis 中读取的数据都是string 类型的，所以直接返回string类的数据
     * */
    @Override
    public String allEmps() {

        //判断是否redis 中存在 emps
        boolean flag = jedis.exists("emps");
        //如果存在则直接返回string
        if(flag == true ){
            String emp = jedis.hget("emps" , "emps"+1);
           // emp.split(",");
           return emp;
        }
        //不存在则去数据库中读取
        List<Employee> emps = empMapper.allEmps();
        //遍历emps 设置时间格式，否则json自带的时间格式需要序列化操作
        for (Employee emp : emps ) {
            emp.setBirthday(emp.getBirth().toLocaleString());
           // System.out.println(emp.getBirthday());
        }

        JSONArray jsonList = JSONArray.fromObject(emps);
        //把读取的数据保存在redis 中，以便下次直接调用，而非再次调用数据库
        jedis.hset("emps" , "emps"+1 , jsonList.toString());



        return jsonList.toString();
    }
}
