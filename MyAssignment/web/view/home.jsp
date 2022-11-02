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
        <c:if test="${sessionScope.account ne null}">
            Hello ${sessionScope.account.displayname}, click
            <a href="logout">here</a> to logout.
        </c:if>
            <c:if test="${sessionScope.account eq null}">
            click <a href="login">here</a> to login.
        </c:if>
            <br/>
            <select>
                
                <option>View Table</option>
                
            </select>
    </body>
</html>
