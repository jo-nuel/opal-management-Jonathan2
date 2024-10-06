<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.User"%>
<%
    User userToModify = (User) session.getAttribute("userToModify");  // Get the selected user to modify
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify User</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <div class="formBox">
            <form action="AdminModifyUserServlet" method="post">
                <p class="formTitle"> Edit User Details </p>

                <!-- Email field (Visible but not editable) -->
                <label for="email" class="formParagraph">EMAIL</label>
                <br>
                <input type="text" id="email" name="email" value="${userToModify.email}" readonly>
                <br><br><br>

                <!-- ID field (Visible but not editable) -->
                <label for="id" class="formParagraph">ID</label>
                <br>
                <input type="text" id="id" name="id" value="${userToModify.ID}" readonly>
                <br><br><br>

                <!-- Name field (Editable) -->
                <label for="name" class="formParagraph">NAME</label>
                <br>
                <input type="text" id="name" name="name" value="${userToModify.name}">
                <br><br><br>

                <!-- Password field (Editable) -->
                <label for="password" class="formParagraph">PASSWORD</label>
                <br>
                <input type="password" id="password" name="password" value="${userToModify.password}">
                <br><br><br>

                <!-- Status field (Editable) -->
                <label for="status" class="formParagraph">STATUS</label>
                <br>
                <input type="text" id="status" name="status" value="${userToModify.status}">
                <br><br><br>

                <!-- Role field (Editable) -->
                <label for="role" class="formParagraph">ROLE</label>
                <br>
                <input type="text" id="role" name="role" value="${userToModify.role}">
                <br><br><br>

                <!-- Submit and Cancel buttons -->
                <input type="submit" value="Update" class="formButton">
                <br>
                <a href="adminListUsers.jsp" class="formButton">Cancel</a>
            </form>
        </div>
    </body>
</html>
