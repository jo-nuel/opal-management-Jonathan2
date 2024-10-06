<%@page import="uts.isd.model.User"%>
<%@page import="uts.isd.model.AccessLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <title>Main</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>


    <body>
        <!-- Imports -->
        <%
            User user = (User) session.getAttribute("user");
        %>
        <!--End of Imports-->
        
        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>
        <div class="header2">
            <a href="main.jsp" class="header2Button">MAIN</a>
            <a href="ListOpalCardsServlet?userID=${user.ID}" class="header2Button">CARDS</a>
            <a  class="header2Button">TOP-UP</a>
            <a href="UserEventController" class="header2Button">EVENTS</a>
            <a  class="header2Button">TRAVEL HISTORY</a>
            <a  class="header2Button">TRIP PLANNER</a>

            <a href="logOut.jsp" class="header2Button">LOGOUT</a>
        </div>
        <!--End of IOTBay Header-->
        
        <!-- Content-->
        
        
        
        <div>
            <br>

            <table class="mainTable">
                <thead>
                <th colspan="2" style="text-align: center;">Account Information</th>
                </thead>
                <tr>
                    <td>Name</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>${user.password}</td>
                </tr>
                <tr>
                    <td>User ID</td>
                    <td>${user.ID}</td>
                </tr>
                <tr>
                    <td>Account Status</td>
                    <td>${user.status}</td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td>${user.role}</td>
                </tr>
             

            </table>

            
            <div class="buttonContainer">
                <a class="mainButton" href="userEdit.jsp">
                    <p>Edit details</p>
                </a>
                
          
                <form action="UserDeleteController" method="post">
                    <input type="hidden" name="email" value="${user.email}">
                    <button type="submit" class="mainButton">
                        <p>Delete account</p>
                    </button>
                </form>
                 <form action="AccessLogServlet">
                    <button type="submit" name="email" value="${user.email}" class="mainButton">   Access Logs </button>
                </form>
                <a class="mainButton" href="logOut.jsp">
                    <p>Log out</p>
                </a>
    
            </div>           

            </form>
        </div>
    </body> 
</html>
