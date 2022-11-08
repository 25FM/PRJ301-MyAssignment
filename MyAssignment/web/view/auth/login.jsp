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
        <link href="./css/login.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <div class="login">
            <div class="form">
                <h3>FPT University Academic Portal</h3>
                <form action="login" method="post">
                    username: <input type="text" name="username" /> <br/>
                    password: <input type="password" name="password" /> <br/>
                    <input class="submit" type="submit" value="Login" /> 
                </form>
                <div class="mess">
                    ${mess}<br>
                    <c:if test="${mess == 'Login successfull!'}">
                        Go to <a href="./view/home.jsp">Home</a> now
                    </c:if>
                </div>
            </div>
            <div class="img"><img src="img/2.jpg" alt=""></div>
        </div>

    </body>
</html>
