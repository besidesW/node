package com.redis.demo.dao;

import com.redis.demo.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmpMapper {

    public List<Employee> allEmps();
    public int insertIntoEmp(Employee emp);




}
