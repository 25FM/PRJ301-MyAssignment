<%-- 
    Document   : update
    Created on : Nov 2, 2022, 3:33:42 PM
    Author     : MANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Information Student</title>
    </head> 
    <body>
        <form action="update" method="post">
            <input type="hidden" name="id" value="${requestScope.student.id}"/>
            Name:<input type="text" value="${requestScope.student.name}" name="name"/> <br/>
            Gender: <input type="radio" name="gender" value ="male" checked="checked"/> Male
            <input type="radio" name="gender" value="female"/> Female <br/>
            Dob: <input type="date" value="${requestScope.student.dob}" name="dob" /> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
