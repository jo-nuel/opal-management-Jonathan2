<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.OpalCard" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Opal Cards</title>
    <style>
        /* Embedded CSS for styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }
        .container {
            width: 80%;
            margin: 20px auto;
        }
        h2 {
            text-align: center;
            color: #007cba;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #007cba;
            color: white;
        }
        td {
            text-align: center;
        }
        .button {
            background-color: #007cba;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }
        .button:hover {
            background-color: #005f9a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Your Opal Cards</h2>
        
        <table>
            <thead>
                <tr>
                    <th>Card Number</th>
                    <th>Card Name</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="card" items="${opalCards}">
                    <tr>
                        <td>${card.cardNumber}</td>
                        <td>${card.cardName}</td>
                        <td>${card.cardStatus}</td>
                        <td>
                            <form action="RenameOpalCardServlet" method="post">
                                <input type="hidden" name="cardID" value="${card.cardID}">
                                <input type="text" name="newCardName" placeholder="Rename Card">
                                <input type="submit" value="Rename">
                            </form>
                            <form action="DeleteOpalCardServlet" method="post">
                                <input type="hidden" name="cardID" value="${card.cardID}">
                                <input type="submit" value="Delete" class="button">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="addCard.jsp" class="button">Add New Card</a>
    </div>
</body>
</html>
