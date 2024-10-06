package uts.isd.controller;

import uts.isd.dao.OpalCardDAO;
import uts.isd.model.OpalCard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListOpalCardsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        OpalCardDAO opalCardDAO = (OpalCardDAO) session.getAttribute("opalCardDAO");

        // Retrieve the userID from the session
        String userIDString = (String) session.getAttribute("userID");

        if (userIDString != null) {
            try {
                int userID = Integer.parseInt(userIDString); // Parse userID from String to int

                // Fetch the Opal cards linked to the user
                List<OpalCard> opalCards = opalCardDAO.getCardsByUserId(userID);

                // Set the Opal cards list in the request to display on the JSP page
                request.setAttribute("opalCards", opalCards);
                request.getRequestDispatcher("cardManagement.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
                session.setAttribute("error", "Unable to retrieve Opal cards.");
                response.sendRedirect("error.jsp");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                session.setAttribute("error", "Invalid user ID format.");
                response.sendRedirect("error.jsp");
            }
        } else {
            session.setAttribute("error", "User is not logged in.");
            response.sendRedirect("login.jsp");
        }
    }
}
