package uts.isd.controller;

import uts.isd.dao.OpalCardDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class DeleteOpalCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int cardId = Integer.parseInt(request.getParameter("cardID"));

        OpalCardDAO opalCardDAO = (OpalCardDAO) session.getAttribute("opalCardDAO");

        try {
            opalCardDAO.deleteOpalCard(cardId);
            response.sendRedirect("cardManagement.jsp");

        } catch (SQLException e) {
            session.setAttribute("error", "Database error: Unable to delete Opal card.");
            response.sendRedirect("cardManagement.jsp");
        }
    }
}
