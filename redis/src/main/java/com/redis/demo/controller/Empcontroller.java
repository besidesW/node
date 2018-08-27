package com.redis.demo.controller;

import com.redis.demo.entity.Employee;
import com.redis.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emp")
public class Empcontroller {

    @Autowired
    private EmpService empService;


    @RequestMapping("list")
    public List<Employee> allemps(){

        List<Employee> emps = empService.allEmps();
        return emps;
    }



}
