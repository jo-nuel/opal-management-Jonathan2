

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.User"%>

<!DOCTYPE html>
<html>
     <head>
        <title>Register Option</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>
        
        <!--End of IOTBay Header-->
        
        <!--Start of Content-->
        <div class="centreBox">
            <div>
                <a href="registerStaff.jsp" class="indexButton">STAFF REGISTRATION</a>
            </div>
            <div>
                <a href="registerCustomer.jsp" class="indexButton">CUSTOMER REGISTRATION</a>
            </div>
            <div>
                <% 
                    User loggedInUser = (User) session.getAttribute("user"); // Assuming the logged-in user is stored in session
                    String backPage = "index.jsp"; // Default to index.jsp
                    if (loggedInUser != null && "admin".equals(loggedInUser.getRole())) {
                        backPage = "adminMain.jsp"; // Redirect to admin main menu if the user is admin
                    }
                %>
                <a href="<%= backPage %>" class="indexButton">GO BACK</a>
            </div>
        </div>
        <!--End of Content-->
</html>
