<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Account</title>

</head>
<body>
        <jsp:include page="/header.jsp" />
        <form method="POST">
        <table>
            <tr> <td> Old password : </td> <td><input name="oldpass" type="password" /> <font color="red">${fail}</font> </td> </tr>
            <tr> <td> New password : </td> <td><input name="newpass" type="password" /> </td> </tr>
            <tr> <td> Re-enter new password : </td> <td><input name="renewpass" type="password" /> <font color="red">${match}</font></td> </tr>
        </table>
            <input type="submit" value="Submit"/>
        </form>

        <jsp:include page="/footer.jsp" />
</body>
</html>