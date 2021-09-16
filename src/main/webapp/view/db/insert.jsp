<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Insert Records </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <p><font color="red">${error}</font></p>
        <form method="POST">
        <table>
            <c:forEach var="attribute" items="${attributes}">
                <tr> <td> ${attribute} :</td><td> <input name="${attribute}" type="text"/> </td> </tr>
            </c:forEach>
        </table>
        <input type="submit" value="Insert Record"/>
        </form>
        <p><font color="green">${success}</font></p>
        <jsp:include page="/footer.jsp" />
</body>
</html>