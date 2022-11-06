<%-- 
    Document   : home
    Created on : Oct 31, 2022, 2:08:30 AM
    Author     : MANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
    <c:if test="${session.account == 'null'}">
        Hello ${account.displayname}, click
        <a href="logout">here</a> to logout.
    </c:if>
    <c:if test="${session.account != 'null'}">
        click <a href="login">here</a> to login.
    </c:if>


</body>
</html>
