package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.dao.DBManager;
import uts.isd.model.User;

public class AdminModifyUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) request.getSession().getAttribute("manager");
        User selectedUser = (User)session.getAttribute("userToModify");  // Store user in session

        String name = request.getParameter("name");
        String id = selectedUser.getID();
        String email = selectedUser.getEmail();
        String password = request.getParameter("password");
        String status = request.getParameter("status");
        String role = request.getParameter("role");

        try {
            // Call the DBManager method to update the user details
            manager.adminUpdateUser(name, email, password, id, status, role);  // Assuming this method updates user details
            response.sendRedirect("AdminListUsersServlet");  // After updating, redirect to the user list
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
