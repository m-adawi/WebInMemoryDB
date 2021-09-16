<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Delete Records </title>
</head>
<body>
        <jsp:include page="/header.jsp" />
        <form method="POST">
        <table>
            <tr> <td> Record ID :</td><td> <input name="id" type="text"/> </td> </tr>
        </table>
        <p> <input type="submit" value="Delete Record"/> </p>
        </form>
        <p><font color="red">${error}</font></p>
        <p><font color="green">${success}</font></p>
        <jsp:include page="/footer.jsp" />
</body>
</html>