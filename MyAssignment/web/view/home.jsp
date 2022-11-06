<%-- 
    Document   : home
    Created on : Oct 31, 2022, 2:08:30 AM
    Author     : MANH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    <c:if test="${account ne null}">
        Hello ${account.displayname}, click
        <a href="logout">here</a> to logout.
    </c:if>
    <c:if test="${account eq null }">
        Click <a href="login">here</a> to login.
    </c:if>


</body>
</html>
