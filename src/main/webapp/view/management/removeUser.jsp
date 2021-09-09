<html>
<head>
<title>User Management </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <p><font color="green">${success}</font></p>
        <p><font color="red">${fail}</font></p>
        <form method="POST">
            User name: <input name="username" type="text" />
        <input type="submit" value="Delete user"/>
        </form>
        <jsp:include page="/footer.jsp" />
</body>
</html>