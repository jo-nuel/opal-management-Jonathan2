<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.OpalCard"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Opal Cards</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Embedded CSS */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #2D86A7;
            text-align: center;
        }
        th {
            background-color: #007cba;
            color: white;
        }
        td {
            background-color: #f9f9f9;
        }
        .actionButton {
            padding: 10px;
            background-color: #2D86A7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 5px;
        }
        .actionButton:hover {
            background-color: #65BFE1;
        }
        .buttonContainer {
            text-align: center;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="header">
        <p class="headerText">Manage Your Opal Cards</p>
    </div>

    <div class="container">
        <h3>Your Linked Cards</h3>
        <table>
            <thead>
                <tr>
                    <th>Card Number</th>
                    <th>Card Nickname</th>
                    <th>Balance</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="card" items="${sessionScope.cards}">
                    <tr>
                        <td>${card.cardNumber}</td>
                        <td>${card.cardName}</td>
                        <td>${card.balance}</td>
                        <td>${card.cardStatus}</td>
                        <td>
                            <form action="RenameOpalCardServlet" method="post" style="display:inline;">
                                <input type="hidden" name="cardID" value="${card.cardID}">
                                <input type="text" name="newCardName" placeholder="Rename card">
                                <button type="submit" class="actionButton">Rename</button>
                            </form>
                            <form action="DeleteOpalCardServlet" method="post" style="display:inline;">
                                <input type="hidden" name="cardID" value="${card.cardID}">
                                <button type="submit" class="actionButton">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="buttonContainer">
            <a href="addCard.jsp" class="mainButton">Add New Opal Card</a>
        </div>
    </div>
</body>
</html>
