<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" action="${basePath}/emp/add" >
<table>
    <tr>
        <td>
            姓名：
        </td>
        <td>
           <input name="ename" type="text" />
        </td>
    </tr>
    <tr>
        <td>
           生日：
        </td>
        <td>
            <input name="birthday" type="date" />
        </td>
    </tr>
    <tr>
        <td>
            部门：
        </td>
        <td>
            <select name="dept.deptId" >
                <#list dept as d>
                    <option  value="${d.deptId}">${d.dname}</option>
                </#list>
            </select>
        </td>
    </tr>

    <tr>
        <td>
            <input type="submit" value="添加" >
        </td>
        <td>
            <input value="重置" type="reset" />
        </td>
    </tr>

</table>

</form>
<#list dept as d>

</#list>
</body>
</html>