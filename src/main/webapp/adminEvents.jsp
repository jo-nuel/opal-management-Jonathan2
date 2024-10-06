<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Event" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("events");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Events</title>
    <link rel="stylesheet" type="text/css" href="stylesheet.css">
    <style>
        /* Styling the add button to match existing styles */
        .addButtonContainer {
            text-align: right;
            margin-bottom: 10px;
        }

        .addButton {
            background-color: #2D86A7;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            font-family: Arial, sans-serif;
        }

        .addButton:hover {
            background-color: #65BFE1;
        }

        /* Table styling */
        .eventTable {
            width: 100%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #87CEEB;
            font-family: Arial, sans-serif;
        }

        .eventTable th, .eventTable td {
            border: 1px solid #2D86A7;
            padding: 10px;
            text-align: left;
        }

        .eventTable th {
            background-color: #2D86A7;
            color: white;
        }

        .actionButton {
            background-color: #2D86A7;
            color: white;
            padding: 5px 10px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-family: Arial, sans-serif;
        }

        .actionButton:hover {
            background-color: #65BFE1;
        }

        .deleteButton {
            background-color: #f44336;
        }

        .deleteButton:hover {
            background-color: #e53935;
        }

        /* Back button styling */
        .backButtonContainer {
            text-align: left;
            margin-top: 20px;
        }

        .backButton {
            background-color: #2D86A7;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            font-family: Arial, sans-serif;
        }

        .backButton:hover {
            background-color: #65BFE1;
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <div class="header">
        <p class="headerText">Manage Events</p>
    </div>
    <br>
    <!-- Add Event Button (Top-right corner) -->
    <div class="addButtonContainer">
        <a href="addEventForm.jsp" class="addButton">Add New Event</a>
    </div>
    <br>

    <!-- Events Table -->
    <table class="eventTable">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="event" items="${events}">
            <tr>
                <td>${event.id}</td>
                <td>${event.name}</td>
                <td>${event.category}</td>
                <td>${event.description}</td>
                <td>
                    <!-- Edit Button -->
                    <a href="EventControllerServlet?action=edit&id=${event.id}" class="actionButton">Edit</a>

                    <!-- Delete Button with Confirmation -->
                    <form action="EventControllerServlet" method="post" style="display:inline;" onsubmit="return confirmDelete(${event.id})">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${event.id}">
                        <button type="submit" class="actionButton deleteButton">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <!-- Go Back Button -->
    <div class="backButtonContainer">
        <a href="adminMain.jsp" class="backButton">Go Back to Admin Menu</a>
    </div>

    <!-- JavaScript for the confirmation popup -->
    <script type="text/javascript">
        function confirmDelete(eventId) {
            return confirm("Are you sure you want to delete event with ID " + eventId + "?");
        }
    </script>

</body>
</html>
