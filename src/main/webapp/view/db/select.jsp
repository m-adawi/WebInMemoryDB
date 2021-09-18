<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Select Records </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <p><font color="red">${error}</font></p>
        <form:form method="POST">
            <table>
                <tr>
                    <td> <form:label path="selectedAttributes"> Attributes to be selected: </form:label> </td>
                    <td> <form:checkboxes items="${attributes}" path="selectedAttributes"/> <td>
                </tr>
                <tr>
                    <td> <form:label path="conditionAttribute"> Filter: </form:label> </td>
                    <td>
                        <form:select path = "conditionAttribute">
                        <form:option value = "NONE" label = "None"/>
                        <form:options items = "${attributes}" />
                        </form:select>

                        <form:select path = "operator">
                        <form:options items = "${operators}" />
                        </form:select>

                        <form:input path = "attributeValue" />
                    </td>
                </tr>
            </table>
            <p><input type="submit" value="Select Record"/></p>
        </form:form>
        <table border = "1" width = "50%">
            <c:forEach var="row" items="${rows}">
                <tr>
                <c:forEach var="cell" items="${row}">
                      <td><c:out value="${cell}" /></td>
                 </c:forEach>
                 </tr>
            </c:forEach>
        </table>
        <jsp:include page="/footer.jsp" />
</body>
</html>