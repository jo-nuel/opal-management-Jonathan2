<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="uts.isd.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<User> users = (ArrayList<User>) session.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>List of Users</title>
    <link rel="stylesheet" type="text/css" href="stylesheet.css">
    <style>
        /* Additional table styling, using your existing styles */
        .userTable {
            width: 100%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #87CEEB; /* Light blue background */
        }

        .userTable th, .userTable td {
            border: 1px solid #2D86A7;
            padding: 10px;
            text-align: left;
            font-family: Arial, sans-serif;
        }

        .userTable th {
            background-color: #2D86A7;
            color: white;
        }

        .buttonContainer {
            display: flex;
            justify-content: space-between;
        }

        .actionButton {
            background-color: #2D86A7;
            color: white;
            padding: 8px 16px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-family: Arial, sans-serif;
        }

        .actionButton:hover {
            background-color: #65BFE1;
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <div class="header">
        <p class="headerText">User Management</p>
    </div>

    <h1 class="h1">All Users</h1>
    
    <table class="userTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Status</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.ID}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${user.status}</td>
                    <td>${user.role}</td>
                    <td class="buttonContainer">
                        <!-- Delete Button -->
                        <form action="AdminDeleteConfirmationServlet" method="post" style="display:inline;">
                            <input type="hidden" name="email" value="${user.email}">
                            <button type="submit" class="actionButton">Delete</button>
                        </form>

                        <!-- Modify Button -->
                        <form action="AdminSelectUserServlet" method="get" style="display:inline;">
                            <input type="hidden" name="email" value="${user.email}">
                            <button type="submit" class="actionButton">Modify</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="buttonContainer">
        <a href="adminMain.jsp" class="mainButton">Back to Admin Menu</a>
    </div>
</body>
</html>
