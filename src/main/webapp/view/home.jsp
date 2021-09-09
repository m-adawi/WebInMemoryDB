
<html>
<head>
<title>${title}</title>

</head>
<body>
        <a href="management"> Manage users </a>
        <form method="POST">
            Database query : <input name="query" type="text" size="100"/>
            <input type="submit" value="Submit"/>
        </form>
        <jsp:include page="/footer.jsp" />
</body>
</html>