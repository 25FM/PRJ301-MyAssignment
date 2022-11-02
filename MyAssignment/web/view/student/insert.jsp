<%-- 
    Document   : insert
    Created on : Oct 31, 2022, 4:45:33 PM
    Author     : MANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insert" method="POST">
            Id:<input type="text" name="id"/> <br/>
            Name:<input type="text" name="name"/> <br/>
            Gender: <input type="radio" name="gender" value ="male" checked="checked"/> Male
            <input type="radio" name="gender" value="female"/> Female <br/>
            Dob: <input type="date" name="dob" /> <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
