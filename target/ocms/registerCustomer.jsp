
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*" %>
<!DOCTYPE html>


        
<html>
    <head>
        <title>Customer Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>


        <!--Imports-->
        <%
            String emptyError = (String) session.getAttribute("emptyError");
            String emailError = (String) session.getAttribute("emailError");
            String nameError = (String) session.getAttribute("nameError");
            String passwordError = (String) session.getAttribute("passwordError");
            String createdError = (String) session.getAttribute("createdError");
            String phoneError = (String) session.getAttribute("phoneError");
        %>
        <!--End of Imports-->
        
        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal management system</p>
        </div>
        
        <!--End of IOTBay Header-->
        <br>
        <!--Start of Content-->
        <div class="formBox">
            <p class="formTitle">CUSTOMER REGISTRATION</p>
            <form action="RegisterCustomerServlet" method="post">
                <label class="formParagraph" for="email">CUSTOMER EMAIL</label>
                <br>
                <input type="text" id="email" name="email">
                <br>   
                <br>
                <br>   
                <label class="formParagraph" for="name">CUSTOMER NAME</label>
                <br>
                <input type="text" id="name" name="name">
                <br>   
                <br>
                <br>
                <label class="formParagraph" for="password">CUSTOMER PASSWORD</label>
                <br>
                <input type="password" id="password" name="password">
                <br>   
                <br>

                <br>
                <br>
                <input class="formButton" type="submit" value="SUBMIT">
                
                <a href="registerOption.jsp" class="formButton">GO BACK</a>
                <p class="formError"><%=(emptyError != null ? emptyError : "")%> </p>
                <p class="formError"><%=(emailError != null ? emailError : "")%> </p>
                <p class="formError"><%=(nameError != null ? nameError : "")%> </p>
                <p class="formError"><%=(passwordError != null ? passwordError : "")%> </p>
                <p class="formError"><%=(createdError != null ? createdError : "")%> </p>
            </form>
        </div>
        <!--End of Content-->
    </body>
</html>
