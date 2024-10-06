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

public class AdminSelectUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");

        String email = request.getParameter("email");  // Get the email of the selected user
        System.out.println("the email user triyn to modify is " + email);

        try {
            // Retrieve the user based on the email and store it in session as userToModify
            User userToModify = manager.findUserEmailOnly(email);
            System.out.println("the user triyn to modify is " + userToModify.getName());
            session.setAttribute("userToModify", userToModify);  // Store user in session
            response.sendRedirect("adminModifyUser.jsp");  // Redirect to the modify page
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
