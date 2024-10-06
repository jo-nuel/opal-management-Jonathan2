<%@page import="uts.isd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Main Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
      
        
        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal Management System - Admin Main Menu</p>
        </div>
        <!--End of IOTBay Header-->
        
        <!-- Content-->
        <!-- Content-->
        <div class="buttonContainer">
            <a class="mainButton" href="AdminListUsersServlet">
                <p>View All Users</p>
            </a>
            
            <a class="mainButton" href="registerOption.jsp">
                <p>Register Users or Admins</p>
            </a>

            <!-- New button to go to Events Admin -->
            <a class="mainButton" href="EventControllerServlet?action=list">
                <p>Manage Events</p>
            </a>

            <a class="mainButton" href="logOut.jsp">
                <p>Log Out</p>
            </a>
        </div>
    </body> 
</html>
