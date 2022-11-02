<%-- 
    Document   : login
    Created on : Oct 31, 2022, 1:57:56 AM
    Author     : MANH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
    </head>
    <body>
        <form action="login" method="post">
            username: <input type="text" name="username" /> <br/>
            password: <input type="password" name="password" /> <br/>
            <input type="submit" value="Login" /> 
        </form>
        <c:if test="${account != null}">
        </c:if>
</body>
</html>
