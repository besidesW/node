<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#assign json = emps />
<#list json as emp>
    ${emp.ename}
</#list>

</body>
</html>