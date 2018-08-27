package com.redis.demo.dao;

import com.redis.demo.entity.Dept;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper {

    public Dept findDeptById(Integer deptid);

}
