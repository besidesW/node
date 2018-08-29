<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#--<#assign json = emps />-->
<#list emps as emp>
   姓名： ${emp.ename}
<br/>
生日：
${emp.birthday?datetime}
<br/>
所在部门：
${emp.dept.dname}
<br/>
</#list>
<a href="${basePath}/dept/list" >添加员工</a>
</body>
</html>