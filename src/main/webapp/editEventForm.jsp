<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Event" %>
<%
    Event event = (Event) request.getAttribute("event");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Event</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
        <!-- Opal Management System Header -->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>

        <!-- Start of Content -->
        <br>
        <div class="formBox">
            <p class="formTitle">EDIT EVENT</p>
            
            <!-- Edit Event Form -->
            <form action="EventControllerServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${event.id}">

                <!-- Name Field -->
                <label class="formParagraph" for="name">EVENT NAME</label>
                <br>
                <input type="text" id="name" name="name" value="${event.name}">
                <br><br>

                <!-- Category Field -->
                <label class="formParagraph" for="category">CATEGORY</label>
                <br>
                <input type="text" id="category" name="category" value="${event.category}">
                <br><br>

                <!-- Description Field -->
                <label class="formParagraph" for="description">DESCRIPTION</label>
                <br>
                <textarea id="description" name="description">${event.description}</textarea>
                <br><br>

                <!-- Submit and Go Back Buttons -->
                <input class="formButton" type="submit" value="UPDATE EVENT">
                <a href="EventControllerServlet?action=list" class="formButton">CANCEL</a>
            </form>
        </div>
        <!-- End of Content -->
    </body>
</html>
