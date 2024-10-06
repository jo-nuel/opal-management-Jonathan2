<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Delete Account</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <!--Imports -->

        <%
            User user = (User) session.getAttribute("user");
            String emailErr = (String) session.getAttribute("emailErr");
        %>
        <!--End of Imports-->
        <<!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>
        <a href="main.jsp" class="header2Button">MAIN</a>
        <a  class="header2Button">CARDS</a>
        <a  class="header2Button">TOP-UP</a>
        <a  class="header2Button">EVENTS</a>
        <a  class="header2Button">TRAVEL HISTORY</a>
        <a  class="header2Button">TRIP PLANNER</a>

        <a href="logOut.jsp" class="header2Button">LOGOUT</a>
    </div>
        <!--End of IOTBay Header-->
        <!--Start of Content -->
        
        
        
        <h1 class="h1">Are you sure you want to deactivate your account?</h1>
        
        <form action="UserDeleteController" method="post">
            <input type="hidden" id="email" name="email" value="${user.email}" readonly> <br>
            <div class="deleteBox">
                <a href="main.jsp" class="formButton">Cancel</a>
                <input type="submit" value="Delete" class="formButton">
            </div>
        </form>
        

        <!--End of Content -->
    </body>

</html>
