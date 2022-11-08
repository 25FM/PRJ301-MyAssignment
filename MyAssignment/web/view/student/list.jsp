
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function removeStudent(id)
            {
                var result = confirm("Do you want delete information student with id = " + id + "?");
                if (result)
                    window.location.href = "delete?id=" + id;
            }
        </script>
    </head>
    <body>
        
        <table border = "1px">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Dob</td>
                <td></td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.gender}</td>
                    <td>${s.dob}</td>
                    <td>
                        <a href="update?id=${s.id}">Edit</a>
                    </td>
                    <td>
                        <a href="#" onclick="removeStudent(${s.id})">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="insert">Insert</a>
    </body>
</html>
