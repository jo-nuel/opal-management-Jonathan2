package uts.isd.controller;

import uts.isd.dao.DBManager;
import uts.isd.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        DBManager dbManager = (DBManager) session.getAttribute("manager");  // Get DBManager from session

        if (action == null) {
            action = "list";  // Default action is to list events
        }

        try {
            switch (action) {
                case "list":
                    listEvents(request, response, dbManager);
                    break;
                case "edit":
                    showEditForm(request, response, dbManager);
                    break;
                default:
                    listEvents(request, response, dbManager);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        DBManager dbManager = (DBManager) session.getAttribute("manager");  // Get DBManager from session

        if (action == null) {
            action = "list";  // Default action is to list events
        }

        try {
            switch (action) {
                case "add":
                    addEvent(request, response, dbManager);
                    break;
                case "update":
                    updateEvent(request, response, dbManager);
                    break;
                case "delete":  
                    deleteEvent(request, response, dbManager);
                    break;
                default:
                    listEvents(request, response, dbManager);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listEvents(HttpServletRequest request, HttpServletResponse response, DBManager dbManager) throws ServletException, IOException, SQLException {
        ArrayList<Event> events = dbManager.fetchEvents();  // Get all events from DB
       // request.setAttribute("events", events);
        HttpSession session = request.getSession();
        session.setAttribute("events", events);

        request.getRequestDispatcher("adminEvents.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, DBManager dbManager) throws ServletException, IOException, SQLException {
        int eventId = Integer.parseInt(request.getParameter("id"));
        Event event = dbManager.getEventById(eventId);  // Get event by ID from DB
        request.setAttribute("event", event);
        request.getRequestDispatcher("editEventForm.jsp").forward(request, response);
    }

    private void addEvent(HttpServletRequest request, HttpServletResponse response, DBManager dbManager) throws IOException, SQLException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        Event newEvent = new Event(0, name, category, description);  //  ID is auto-incremented in DB
        dbManager.addEvent(newEvent);  // Add the new event to the DB
        response.sendRedirect("EventControllerServlet?action=list");
    }

    private void updateEvent(HttpServletRequest request, HttpServletResponse response, DBManager dbManager) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String description = request.getParameter("description");

        Event event = new Event(id, name, category, description);
        dbManager.updateEvent(event);  // Update event in DB
        response.sendRedirect("EventControllerServlet?action=list");
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response, DBManager dbManager) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        dbManager.deleteEvent(id);  // Delete event from DB
        response.sendRedirect("EventControllerServlet?action=list");
    }
}
