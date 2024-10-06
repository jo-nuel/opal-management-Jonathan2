<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Staff Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <!--Imports -->
        <%
            User user = (User) session.getAttribute("user");
            String emptyError = (String) session.getAttribute("emptyError");
            String passwordError = (String) session.getAttribute("passwordError");
            String nameError = (String) session.getAttribute("nameError");
            String phoneError = (String) session.getAttribute("phoneError");
        %>
        <!--End of Imports-->

        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText"></p>
        </div>
        <div class="header2">
            <a href="main.jsp" class="header2Button">MAIN</a>
            <a  class="header2Button">CARDS</a>
            <a  class="header2Button">TOP-UP</a>
            <a  class="header2Button">EVENTS</a>
            <a  class="header2Button">TRAVEL HISTORY</a>
            <a  class="header2Button">TRIP PLANNER</a>

            <a href="logOut.jsp" class="header2Button">LOGOUT</a>
        </div>
        <!--End of IOTBay Header-->

        <!--Content -->
        <div>
            <form class="formBox" action="UserUpdateServlet" method="post">
                <p class="formTitle"> Edit Account </p>

                <!-- Name field -->
                <label for="name" class="formParagraph">NAME</label>
                <br>
                <input type="text" id="name" name="name" value="${user.name}">
                <br><br><br>

                <!-- Password field -->
                <label for="password" class="formParagraph">PASSWORD</label>
                <br>
                <input type="password" id="password" name="password" value="${user.password}">
                <br><br><br>

                
                <!-- Submit and Cancel buttons -->
                <input type="submit" value="Update" class="formButton">
                <br>
                <a href="main.jsp" class="formButton">CANCEL</a>
                
                <!-- Error messages -->
                <p class="formError"><%= (emptyError != null ? emptyError : "") %></p>
                <p class="formError"><%= (passwordError != null ? passwordError : "") %></p>
                <p class="formError"><%= (nameError != null ? nameError : "") %></p>

                <!-- Hidden fields -->
                <input type="hidden" id="email" name="email" value="${user.email}" readonly>
                <input type="hidden" id="ID" name="ID" value="${user.ID}" readonly>
                <input type="hidden" id="status" name="status" value="${user.status}" readonly>
                <input type="hidden" id="role" name="role" value="${user.role}" readonly>
            </form>
        </div>
    </body>
</html>
