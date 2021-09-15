<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SQL Command</title>

</head>
<body>
    <jsp:include page="/header.jsp" />
    <form method="POST">
        Database query : <input name="query" type="text" size="100" value="${sqlcommand}"/>
        <input type="submit" value="Submit"/>
    </form>
    ${message}
    <p>
    <table border = "1" width = "50%">
        <c:forEach var="row" items="${rows}">
            <tr>
            <c:forEach var="cell" items="${row}">
                  <td><c:out value="${cell}" /></td>
             </c:forEach>
             </tr>
        </c:forEach>
    </table>
    </p>
    <jsp:include page="/footer.jsp" />
</body>
</html>