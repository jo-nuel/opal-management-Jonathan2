package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uts.isd.dao.DBManager;

public class AdminDeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager manager = (DBManager) request.getSession().getAttribute("manager");
        String email = request.getParameter("email");
        try {
            manager.deleteUser(email);
            response.sendRedirect("AdminListUsersServlet");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
