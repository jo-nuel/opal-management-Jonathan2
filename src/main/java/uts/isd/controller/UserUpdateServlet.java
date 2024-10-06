package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.dao.DBManager;
import uts.isd.model.User;

public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");  // Get DBManager from session
        UserValidator validator = new UserValidator();

        // Retrieve the currently logged-in user from the session
        User loggedInUser = (User) session.getAttribute("user");
        // Access log creation
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = time.format(formatterTime);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatterDate);

        String action = "Edited Details";

        // Get user input from the form
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //these arent chnaged so will be retrieved from user
        String email = loggedInUser.getEmail();
        // Reset validator
        validator.clear(session);

        // Validation logic
        if (validator.checkEmptyUpdate(password, name)) {
            session.setAttribute("emptyError", "Please enter all fields");
            request.getRequestDispatcher("userEdit.jsp").include(request, response);
        } else if (!validator.passwordFormat(password)) {
            session.setAttribute("passwordError", "Your password must have at least 5 letters and/or numbers and no spaces");
            request.getRequestDispatcher("userEdit.jsp").include(request, response);
        } else if (!validator.nameFormat(name)) {
            session.setAttribute("nameError", "Your name must not include numbers");
            request.getRequestDispatcher("userEdit.jsp").include(request, response);
        } else {
            try {
                // Update user details in the database using DBManager

                manager.updateUser(name, email, password);
                loggedInUser.setName(name);
                loggedInUser.setPassword(password);
                // Fetch the updated user from the database to reflect in the session
                session.setAttribute("user", loggedInUser);  // Update user in session

                // Create access log entry
               // manager.addAccessLog(email, action, dateString, timeString);
                
                // Redirect to the main page
                request.getRequestDispatcher("main.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(UserUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                session.setAttribute("dbError", "Database error occurred while updating user details.");
                request.getRequestDispatcher("userEdit.jsp").include(request, response);
            }
        }
    }
}
