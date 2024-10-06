package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;
import uts.isd.dao.DBManager;



//UserDeleteController is 2/2 Servlets used to update details. UserDeleteController changes status.
public class UserDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        String email = request.getParameter("email");  // Get the email from the hidden input
        try {
            manager.deleteUser(email);
            session.invalidate();
            response.sendRedirect("index.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(UserDeleteController.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("dbError", "A database error occurred while deleting the account.");     
        }

        
    }

}



