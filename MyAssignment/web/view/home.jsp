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
        <link href="../css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${account ne null}">
        <header>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/logofpt.jpg">
        </header>
            <div style="display: flex; justify-content: space-between; margin: 10px 50px">
                <div><h3>Hello ${account.displayname}</h3></div>
                <div style="margin-top: 10px"><h3>Click <a style="text-decoration: none;" href="../logout">here</a> to logout.</h3><br/></div>
            </div>
            <div class="option">


                <h2>Choose your option you want to use</h2>
                <p>Option available with lecturer account</p>
                <ul>
                    <li><a  style="text-decoration: none" href="../lecturer/timetable?lid=1&week=1">Timetable Lecturer</a><br/></li>
                    <li><a  style="text-decoration: none" href="../lecturer/viewattendancereport?grid=1">View Attendance Report for class</a><br/></li>
                    <li><a  style="text-decoration: none" href="../lecturer/takeattendance?grid=1&index=1&lid=1&week=1">Take Attendance</a><br/></li>
                    <li><a  style="text-decoration: none" href="../lecturer/takeattendance/after?grid=1&index=1">After Take Attendance</a><br/></li>
                </ul>
                <p>Option available with student account</p>
                <ul>
                    <li><a  style="text-decoration: none" href="../student/timetable?stdid=1&week=1">Timetable Student</a> <br/></li>
                    <li><a  style="text-decoration: none" href="../student/attendancereport?stdid=1&grid=1">View Attendance Report for Student</a> </li>
                </ul>
            </div>

        </c:if>
        <c:if test="${account eq null }">
            You are not logged in, click <a href="login">here</a> to login.
        </c:if>


    </body>
</html>
