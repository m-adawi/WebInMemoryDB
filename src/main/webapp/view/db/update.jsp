<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Update Records </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <p><font color="red">${error}</font></p>
        <c:if test="${noID}">
            <form method="GET">
                <table>
                    <tr> <td> Record ID :</td><td> <input name="id" type="text"/> </td> </tr>
                </table>
            <p> <input type="submit" value="Submit"/> </p>
            </form>
        </c:if>
        <c:if test="${!noID}">
            <form method="POST">
                <table>
                    <c:forEach var="attribute" items="${attributes}">
                        <tr> <td> ${attribute} :</td><td> <input name="${attribute}" type="text"
                        value="${record.getAttributeFromItsType(attribute).getStrValue()}"/> </td> </tr>
                    </c:forEach>
                </table>
                <input type="submit" value="Update Record"/>
            </form>
        </c:if>
        <p><font color="green">${success}</font></p>
        <jsp:include page="/footer.jsp" />
</body>
</html>