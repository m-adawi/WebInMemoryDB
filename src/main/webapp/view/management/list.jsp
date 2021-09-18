<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List of users</title>

</head>
<body>
    <jsp:include page="/header.jsp" />

    <p>
    <table border = "1" width = "50%">
    <tr> <td> User name </td> <td> Role </td></tr>
        <c:forEach var="user" items="${users}">
            <tr>
                  <td><c:out value="${user.username}" /></td>
                  <td><c:out value="${user.role}" /></td>
             </tr>
        </c:forEach>
    </table>
    </p>
    <jsp:include page="/footer.jsp" />
</body>
</html>