<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Confirmation</title>
    </head>
    <body>
        <h1>Are you sure you want to delete this user?</h1>
        <form action="AdminDeleteUserServlet" method="post">
            <input type="hidden" name="email" value="${param.email}">
            <button type="submit">Yes, Delete</button>
        </form>
        <a href="adminListUsers.jsp">Cancel</a>
    </body>
</html>
