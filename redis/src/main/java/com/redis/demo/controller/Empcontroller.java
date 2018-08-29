package com.redis.demo.controller;

import com.redis.demo.entity.Employee;
import com.redis.demo.service.EmpService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

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

        request.setAttribute("basePath" , empService.basePath());
        request.setAttribute("emps" ,j);
        return "index";
    }

    @RequestMapping("add")
    public String toIndex2(Employee emp , HttpServletRequest request){

        System.out.println(emp.getBirthday()+","+emp.getEname()+","+emp.getDept().getDname());


        return "redirect:/emp/list";
    }

}
