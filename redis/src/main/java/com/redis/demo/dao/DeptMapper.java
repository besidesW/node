package com.redis.demo.dao;

import com.redis.demo.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {

    public Dept findDeptById(Integer deptid);

    public List<Dept> allDepts();

}
