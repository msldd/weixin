<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<a href="add">添加</a><a href="publishMenu">发布</a>
<table width="900" align="center" border="1">
    <tr>
        <td>ID</td>
        <td>name</td>
        <td>key</td>
        <td>url</td>
        <td>content</td>
        <td>type</td>
        <td>pid</td>
    </tr>
    <c:forEach var="menu" items="${menus}">
        <tr>
            <td>${menu.id }</td>
            <td>${menu.name }[<a href="update/${menu.id }">更新</a>&nbsp;<a href="delete/${menu.id }">删除</a>]</td>
            <td>${menu.menuKey}</td>
            <td>${menu.url }</td>
            <Td>${menu.content }</Td>
            <td>${menu.type }[${menu.respType }]</td>
            <td>${menu.pid}</td>
        </tr>
    </c:forEach>
</table>
<div>
    <c:forEach var="wmd" items="${wmds }">
        <div>
                ${wmd.name }--->${wmd.type }-->${wmd.key }--->${wmd.id }
            <br/>
            &nbsp;
            <c:forEach items="${wmd.sub_button }" var="sbm">
                ${sbm.name }--->${sbm.type }-->${sbm.key }--->${sbm.id }
                <br/>&nbsp;
            </c:forEach>
        </div>
    </c:forEach>
</div>
</body>
</html>