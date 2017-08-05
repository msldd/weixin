<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户组管理</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/wtag/add">添加用户组</a>&nbsp;&nbsp;
<a href="<%=request.getContextPath() %>/wtag/list">用户组列表</a>&nbsp;&nbsp;
<table width="900" align="center" border="1">
    <tr>
        <td>ID</td>
        <td>name</td>
        <td>count</td>
    </tr>
    <c:forEach items="${datas}" var="wg">
        <tr>
            <td>${wg.id }</td>
            <td>${wg.name }</td>
            <td>${wg.count}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>