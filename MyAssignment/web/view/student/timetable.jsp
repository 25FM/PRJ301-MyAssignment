<%-- 
    Document   : timetable
    Created on : Oct 24, 2022, 3:26:08 PM
    Author     : MANH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timetable for Student</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8" >
                    <h1><span>FPT University Academic Portal</span></h1>
                    <nav><a href="">Home</a></nav>
                </div>
                <div class="col-md-4">
                    <table>
                        <tbody>
                            <tr>
                                <td colspan="2" class="auto-style1"><strong>FAP mobile app (myFAP) is ready at</strong></td>
                            </tr>
                            <tr>
                                <td><a href="https://apps.apple.com/app/id1527723314">
                                        <img src="https://fap.fpt.edu.vn/images/app-store.svg" style="width: 120px; height: 40px" alt="apple store"></a></td>
                                <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                        <img src="https://fap.fpt.edu.vn/images/play-store.svg" style="width: 120px; height: 40px" alt="google store"></a></td>
                            </tr>
                        </tbody>
                    </table>
                    <h2>Activities for ${requestScope.student.name}</h2>
                    <div class="takenote">
                        <p>
                            <b>Note</b>: These activities do not include extra-curriculum activities, such as
                            club activities ...
                        </p>

                        <p>
                            <b>Chú thích</b>: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa,
                            ví dụ như hoạt động câu lạc bộ ...
                        </p>
                        <p>
                            Các phòng bắt đầu bằng AL thuộc tòa nhà Alpha. VD: AL...<br>
                            Các phòng bắt đầu bằng BE thuộc tòa nhà Beta. VD: BE,..<br>
                            Các phòng bắt đầu bằng G thuộc tòa nhà Gamma. VD: G201,...<br>
                            Các phòng tập bằng đầu bằng R thuộc khu vực sân tập Vovinam.<br>
                            Các phòng bắt đầu bằng DE thuộc tòa nhà Delta. VD: DE,..<br>
                            Little UK (LUK) thuộc tầng 5 tòa nhà Delta
                        </p>
                    </div>
                    Student:  <input type="text" readonly="readonly" value="${requestScope.student.getName()}"/>
                    <form action="timetable" method="GET">
                        <input type="hidden" name="stdid" value="${param.stdid}"/>
                        From: <input type="date" name="from" value="${requestScope.from}"/>
                        To: <input type="date" name="to" value="${requestScope.to}"/>
                        <input type="submit" value="View"/> 
                    </form>
                    <table border="1px">
                        <tr>
                            <td></td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td>${d}<br/>${helper.getDayNameofWeek(d)}</td>
                                </c:forEach>
                        </tr>
                        <c:forEach items="${requestScope.slots}" var="slot">
                            <tr>
                                <td>${slot.description}</td>
                                <c:forEach items="${requestScope.dates}" var="d">
                                    <td>
                                        <c:forEach items="${requestScope.sessions}" var="ses">
                                            <c:if test="${helper.compare(ses.date,d) eq 0 and (ses.timeslot.id eq slot.id)}">
                                                <a href="att?id=${ses.id}">${ses.group.name}-${ses.group.subject.name}</a>
                                                <br/>
                                                ${ses.room.name}
                                                <c:if test="${ses.attandated}">
                                                    <img src="" alt=""/>
                                                </c:if>
                                                <c:if test="${!ses.attandated}">
                                                    <img src="" alt=""/>
                                                </c:if>
                                            </c:if>

                                        </c:forEach>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                    </body>
                    </html>
