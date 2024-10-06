
<%@page import="uts.isd.model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <div class="formBox">
            <p class="formTitle">LOG IN</p>
            <form action="LoginServlet" method="post">
                <label for="email">Email</label>
                <input type="text" name="email" id="email">
                <br><br>
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
                <br><br>
                <input type="submit" value="LOGIN" class="formButton">
                <a href="index.jsp" class="formButton">GO BACK</a>

            </form>

            <!-- Display error messages -->
            <p class="formError">
                <%= (session.getAttribute("badLoginError") != null) ? session.getAttribute("badLoginError") : "" %>
            </p>
        </div>
    </body>
</html>


       