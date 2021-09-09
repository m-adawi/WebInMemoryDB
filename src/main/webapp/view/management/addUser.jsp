<html>
<head>
<title>User Management </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <p><font color="red">${used}</font></p>
        <p><font color="green">${success}</font></p>
        <form method="POST">
        <table>
            <tr>
                <td>
                    User type:
                </td>
                <td>
                    <select name="userType">
                        <option value="READER"> Reader </option>
                        <option value="WRITER"> Writer </option>
                        <option value="ADMIN"> Admin </option>
                    </select>
                </td>
            </tr>
            <tr> <td> User name:</td><td> <input name="username" type="text" /> </td>
            <tr> <td> Password : </td> <td><input name="password" type="password" /> </td>
        </table>
        <input type="submit" value="Add user"/>
        </form>
        <jsp:include page="/footer.jsp" />
</body>
</html>