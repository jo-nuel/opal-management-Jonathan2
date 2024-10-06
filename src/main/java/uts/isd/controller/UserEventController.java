package uts.isd.controller;

import uts.isd.dao.DBManager;
import uts.isd.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserEventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager dbManager = (DBManager) session.getAttribute("manager");  // Get DBManager from session

        try {
            ArrayList<Event> userEvents = dbManager.fetchEvents();  // Fetch all events from the database
        session.setAttribute("userEvents", userEvents); 
        request.getRequestDispatcher("userEventView.jsp").forward(request, response);  // Forward to userEventView.jsp

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);  // Handle POST request similarly to GET
    }

    
}
