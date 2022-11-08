
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/timetable.css"/>


        <script>
            function changeWeek() {
                var x = document.getElementById("weeks").value;
                window.location.href = '?stdid=' +${requestScope.student.getId()} + '&week=' + x;
            }
        </script>
    </head>
    <body>
        <header>
            <nav><a style="text-decoration: none; color: purple" href="../view/home.jsp">Home</a></nav>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/logofpt.jpg">
        </header>

        <h2>Activities for ${requestScope.student.name} ${requestScope.student.getCode()}</h2>
        <div class="note">
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
        <div class="timetable">
            <table style="width: 100%">
                <tr><th rowspan="2">
                        YEAR
                        <select>
                            <option>2022</option>
                        </select><br>
                        WEEK
                        <select id="weeks" onchange="changeWeek();">
                            <c:forEach items="${requestScope.weeks}" var="w" >
                                <option value="${w.getIndexWeekOfYear()}" 
                                        <c:if test="${w.getIndexWeekOfYear() eq requestScope.indexCurrentWeek}" >
                                            selected
                                        </c:if>

                                        > ${w.toString()}</option>
                            </c:forEach>

                        </select>

                    </th>
                    <th>MON</th>
                    <th>TUE</th>
                    <th>WED</th>
                    <th>THU</th>
                    <th>FRI</th>
                    <th>SAT</th>
                    <th>SUN</th>
                </tr>
                <tr>
                    <c:forEach items="${requestScope.daysOfWeek}" var="d">
                        <th>${d}</th>
                        </c:forEach>
                </tr>
                <c:forEach items="${requestScope.slots}" var="slot">
                    <tr>

                        <td>Slot ${slot.id}</td>
                        <c:forEach items="${requestScope.currentWeek.getDayList()}" var="day" >
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${(ses.getDate().compareTo(day) eq 0 ) and(ses.getTimeslot().getId() eq slot.getId()) }">
                                        ${ses.getGroup().getName()}<br>
                                        at ${ses.getRoom().getName()}<br>

                                        <c:if test="${ses.getAttandances().get(0).isPresent()}">
                                            <abbr class="attended" title="${requestScope.student.name} had attended this activity">attended</abbr><br>
                                        </c:if>

                                        <c:if test="${!ses.getAttandances().get(0).isPresent()}">
                                            <abbr class="absent" title="${requestScope.student.name} had NOT attended this activity ">absent</abbr><br>
                                        </c:if>
                                        <c:if test="${ses.getAttandances().get(0).isPresent() eq null}">
                                            Not yet<br>
                                        </c:if>
                                        ${ses. getTimeslot().getDescription()}<br>  

                                    </c:if>

                                </c:forEach>
                            </td>
                        </c:forEach>



                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
