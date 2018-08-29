package com.redis.demo.service.Impl;

import com.redis.demo.dao.DeptMapper;
import com.redis.demo.entity.Dept;
import com.redis.demo.service.DeptService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private Jedis jedis;


    @Override
    public String allDepts() {


        if(jedis.exists("depts")==true){
            return jedis.get("depts");
        }

        List<Dept> depts = deptMapper.allDepts();

        JSONArray jsonDepts = JSONArray.fromObject(depts);

        jedis.set("depts" , jsonDepts.toString());

        return jsonDepts.toString();
    }
}
