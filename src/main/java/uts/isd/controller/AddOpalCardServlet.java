package uts.isd.controller;

import uts.isd.model.OpalCard;
import uts.isd.dao.OpalCardDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AddOpalCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Fetch request parameters
        String cardNumber = request.getParameter("cardNumber");
        String cardName = request.getParameter("cardName");
        String cardSecurityCode = request.getParameter("cardSecurityCode"); // Fetch security code
        String cardStatus = "active"; // Set card status by default to active

        // Retrieve the user ID from session
        String userIDString = (String) session.getAttribute("userID");

        // Error handling in case userID is null or not available
        if (userIDString == null) {
            session.setAttribute("error", "User is not logged in.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Parse userID from String to Integer
        int userID = Integer.parseInt(userIDString);

        OpalCardDAO opalCardDAO = (OpalCardDAO) session.getAttribute("opalCardDAO");

        try {
            // Create a new OpalCard object (cardID will be set automatically)
            OpalCard newCard = new OpalCard(0, cardNumber, cardName, cardSecurityCode, cardStatus, userID);
            opalCardDAO.addOpalCard(newCard); // Insert the card into the DB
            System.out.println("Card successfully added");
            // Redirect to the card management page after adding the card
            response.sendRedirect("ListOpalCardsServlet");

        } catch (SQLException e) {
            session.setAttribute("error", "Database error: Unable to add the Opal card.");
            response.sendRedirect("addCard.jsp");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid User ID.");
            response.sendRedirect("login.jsp");
        }
    }
}
