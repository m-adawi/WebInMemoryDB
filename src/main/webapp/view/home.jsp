<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>${title}</title>

</head>
<body>
        <jsp:include page="/header.jsp" />
        <c:if test="${isAdmin}">
            <a href="management"> Manage users </a>
        </c:if>
        <form method="POST">
            Database query : <input name="query" type="text" size="100"/>
            <input type="submit" value="Submit"/>
        </form>

        <jsp:include page="/footer.jsp" />
</body>
</html>