package com.redis.demo.controller;

import com.redis.demo.entity.Employee;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("emp")
public class Empcontroller {

    @Autowired
    private EmpService empService;


    @RequestMapping("list")
    public String allemps(HttpServletRequest request){

       String emps = empService.allEmps();

       //把emps转换为json格式
       JSONArray j = JSONArray.fromObject(emps);
//
//        for (int i = 0; i <j.size() ; i++) {
//            System.out.println(j.get(i));
//        }

        request.setAttribute("emps" ,j);
        return "index";
    }

    @RequestMapping("index2")
    public String toIndex2(){
        return "index2";
    }

}
