<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#assign json = emps />
<#list json as emp>
   姓名： ${emp.ename}
<br/>
生日：
${emp.birthday?datetime}

</#list>

</body>
</html>