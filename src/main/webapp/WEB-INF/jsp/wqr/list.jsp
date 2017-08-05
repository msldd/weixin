<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>二维码管理</title>
</head>
<body>
<a href="<%=request.getContextPath() %>/wqr/add">添加固定二维码</a>&nbsp;&nbsp;
<a href="<%=request.getContextPath() %>/wqr/list/base">固定二维码列表</a>&nbsp;&nbsp;
<a href="<%=request.getContextPath() %>/wqr/list/temp">临时二维码列表</a>
<table width="900" align="center" border="1">
    <tr>
        <td>ID</td>
        <td>name</td>
        <td>status</td>
        <td>type</td>
        <td>snum</td>
        <td>qrData</td>
        <td>msg</td>
        <td>qr</td>
    </tr>
    <c:forEach items="${datas}" var="wq">
        <tr>
            <td>${wq.id }</td>
            <td>${wq.name }</td>
            <td>${wq.status}</td>
            <td>${wq.type }</td>
            <td>${wq.snum }</td>
            <Td>${wq.qrData }</Td>
            <td>${wq.msg }</td>
            <td><img width="120" src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${wq.ticket }"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>