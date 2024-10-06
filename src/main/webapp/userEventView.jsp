<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Event" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Event> events = (ArrayList<Event>) request.getAttribute("userEvents");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Available Events</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <style>
            .container {
                width: 90%; /* Make the container wider */
                margin: 0 auto;
            }

            .mainTable {
                width: 100%; /* Set table width to 100% */
                margin: 20px 0;
                border-collapse: collapse; /* Remove gaps between cells */
            }

            .mainTable th, .mainTable td {
                border: 1px solid #2D86A7;
                padding: 12px;
                text-align: left;
                font-size: 18px;
            }

            .mainTable th {
                background-color: #2D86A7;
                color: white;
            }

            .mainTable td {
                background-color: #87CEEB;
            }

            .buttonContainer {
                text-align: center;
                margin-top: 20px;
            }

            .mainButton {
                padding: 10px 30px; /* Normal size for the button */
                font-size: 18px;
            }
        </style>
    </head>
    <body>

        <!-- Opal Management System Header -->
        <div class="header">
            <p class="headerText">Opal Management System</p>
        </div>

        <!-- Start of Content -->
        <div class="container">
            <h1 class="h1">Available Events</h1>

            <!-- Event Table -->
            <table class="mainTable">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Event Name</th>
                        <th>Category</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="event" items="${userEvents}">
                        <tr>
                            <td>${event.id}</td>
                            <td>${event.name}</td>
                            <td>${event.category}</td>
                            <td>${event.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Back Button -->
            <div class="buttonContainer">
                <a href="main.jsp" class="mainButton">Back to Main Menu</a>
            </div>
        </div>

        <!-- End of Content -->
    </body>
</html>
