package com.redis.demo.controller;

import com.redis.demo.service.DeptService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 绝对路径
     * */
    public String basePath(HttpServletRequest request){
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String path = request.getContextPath();
        int port =request.getServerPort();

        String basePath =  scheme + "://" + serverName + ":" + port + path;

        return basePath;
    }

    @RequestMapping(value = "list" , method = RequestMethod.GET)
    public String toPage(HttpServletRequest request){

        String depts = deptService.allDepts();

        JSONArray dept = JSONArray.fromObject(depts);

        request.setAttribute("dept" , dept);
        request.setAttribute("basePath" ,basePath(request));
        return "addEmp";
    }

}
