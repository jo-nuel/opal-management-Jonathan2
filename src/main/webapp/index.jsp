<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*" %>

<html>
    <head>
        <title>Index</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <!--IOTBay Header-->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>
        <div class="header2">

        </div>
        <!--End of IOTBay Header-->
        
        <!--Start of Content-->
        <div class="centreBox">
            <div>
                <a href="login.jsp" class="indexButton">LOGIN</a>
            </div>
            <div>
                <a href="registerOption.jsp" class="indexButton">REGISTER</a>
            </div>
        </div>
        <!--End of Content-->

        

        <jsp:include page="/ConnServlet" />


    </body>
</html>
