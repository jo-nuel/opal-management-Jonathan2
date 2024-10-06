<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Opal Card</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Embedded CSS */
        .formContainer {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 10px;
            box-shadow: 0px 0px 10px #ccc;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #2D86A7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #005f9a;
        }
    </style>
</head>
<body>
    <div class="header">
        <p class="headerText">Add New Opal Card</p>
    </div>

    <div class="container">
        <div class="formContainer">
            <form action="AddOpalCardServlet" method="post">
                <label for="cardNumber">Card Number:</label>
                <input type="text" id="cardNumber" name="cardNumber" required>

                <label for="securityCode">Security Code:</label>
                <input type="text" id="securityCode" name="cardSecurityCode" required>

                <label for="cardName">Card Nickname:</label>
                <input type="text" id="cardName" name="cardName">

                <button type="submit">Add Card</button>
            </form>
        </div>
    </div>
</body>
</html>
