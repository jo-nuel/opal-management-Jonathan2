package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.User;
import uts.isd.dao.DBManager;

//Purpose of this controller is to allow customers to register a new customer account
public class RegisterStaffServlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserValidator validator = new UserValidator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User loggedInUser = (User) session.getAttribute("user"); // Check if current user is an admin

        // Variables are initiated based on the inputs.
        // Key, email, name and password are given by the user.
        // ID is a random number between 1 and 99999999
        // Role is assumed to be staff due to being in the staff creation portal.
        // Status is assumed to be active.
        Random random = new Random();
        String key = request.getParameter("staffPassword");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String ID = Integer.toString(random.nextInt(99999999) + 1);
        String role = "admin";
        String status = "active";

        // Access Log Variables Created
        // Creating Time Variable for Access Log
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = time.format(formatterTime);
        // Creating Date Variable for Access Log
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatterDate);
        // Getting Action Variable for Access Log
        String action = "Registered as a Admin";

        // Staff Registration validation begins.
        // Empty fields and formatting of email, password and name are checked.
        // Staff key is also checked.
        // If a validation fails, the staffRegister.jsp page is reloaded but this time
        // with the corresponding errors and messages.
        if (validator.checkEmptyRegisterStaff(email, password, name, key)) {
            session.setAttribute("emptyError", "Please enter all fields");
            request.getRequestDispatcher("registerStaff.jsp").include(request, response);
        } else if (!validator.emailFormat(email)) {
            session.setAttribute("emailError", "Your email address must include @");
            request.getRequestDispatcher("registerStaff.jsp").include(request, response);
        } else if (!validator.passwordFormat(password)) {
            session.setAttribute("passwordError",
                    "Your password must have at least 5 letters and/or numbers and no spaces");
            request.getRequestDispatcher("registerStaff.jsp").include(request, response);
        } else if (!validator.nameFormat(name)) {
            session.setAttribute("nameError", "Your name must not include numbers");
            request.getRequestDispatcher("registerStaff.jsp").include(request, response);
        } else if (!validator.checkKey(key)) {
            session.setAttribute("keyError", "Staff key is incorrect");
            request.getRequestDispatcher("registerStaff.jsp").include(request, response);
        }

        // If validation passes, the real code begins:
        else {
            try {
                // Temp is initiated as a potenital user matching the email supplied.
                User temp = manager.findUserEmailOnly(email);
                if (temp != null) {
                    session.setAttribute("createdError", "Email already in use.");
                    request.getRequestDispatcher("registerStaff.jsp").include(request, response);
                } else {
                    manager.addUser(name, email, password, ID, status, role);
                    // manager.addAccessLog(email, action, dateString, timeString);

                    // Redirect to admin main menu if an admin is logged in
                    if (loggedInUser != null && "admin".equals(loggedInUser.getRole())) {
                        response.sendRedirect("adminMain.jsp"); // Redirect to admin main menu
                    } else {
                        // Set the new user in session if normal user
                        User newUser = new User(name, email, password, ID, status, role);
                        session.setAttribute("user", newUser);
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    }
                }
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RegisterStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Reset validator for future logins.
        validator.clear(session);
    }
}
