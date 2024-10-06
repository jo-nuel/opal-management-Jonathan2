<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Event</title>
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
            <p class="formTitle">ADD NEW EVENT</p>
            
            <!-- Add Event Form -->
            <form action="EventControllerServlet" method="post">
                <input type="hidden" name="action" value="add">

                <!-- Name Field -->
                <label class="formParagraph" for="name">EVENT NAME</label>
                <br>
                <input type="text" id="name" name="name" class="formInput" required>
                <br><br>

                <!-- Category Field -->
                <label class="formParagraph" for="category">CATEGORY</label>
                <br>
                <input type="text" id="category" name="category" class="formInput" required>
                <br><br>

                <!-- Description Field -->
                <label class="formParagraph" for="description">DESCRIPTION</label>
                <br>
                <textarea id="description" name="description" class="formInput" rows="5" required></textarea>
                <br><br>

                <!-- Submit and Go Back Buttons -->
                <input class="formButton" type="submit" value="ADD EVENT">
                <a href="EventControllerServlet?action=list" class="formButton">CANCEL</a>
            </form>
        </div>
        <!-- End of Content -->
        
    </body>
</html>
