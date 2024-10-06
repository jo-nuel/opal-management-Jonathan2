package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.dao.DBManager;
import uts.isd.model.User;

public class AdminListUsersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        try {
            ArrayList<User> users = manager.fetchUsers();
            session.setAttribute("userList", users);
            response.sendRedirect("adminListUsers.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
