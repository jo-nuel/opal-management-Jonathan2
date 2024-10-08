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

import uts.isd.dao.DBManager;
import uts.isd.model.User;

//Purpose of this controller is to allow customers to register a new customer account
public class RegisterCustomerServlet extends HttpServlet {

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserValidator validator = new UserValidator();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User loggedInUser = (User) session.getAttribute("user");

        // Variables initiated.
        // Email, name and password are gathered from the input fields.
        // ID is a random number between 1 and 99999999
        // Role is assumed to be Customer due to signing up through the customer portal.
        // Status is assumed to be active.
        Random random = new Random();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String ID = Integer.toString(random.nextInt(99999999) + 1);
        String role = "user";
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
        String action = "Registered as a Customer";

        // Customer Registration validation begins.
        // Empty fields and formatting of email, password and name are checked.
        // If a validation fails, the customerRegister.jsp page is reloaded but this
        // time with the corresponding errors and messages.
        if (validator.checkEmptyRegisterCust(email, name, password)) {
            session.setAttribute("emptyError", "Please enter all fields");
            request.getRequestDispatcher("registerCustomer.jsp").include(request, response);
        } else if (!validator.emailFormat(email)) {
            session.setAttribute("emailError", "Your email address must include @");
            request.getRequestDispatcher("registerCustomer.jsp").include(request, response);
        } else if (!validator.passwordFormat(password)) {
            session.setAttribute("passwordError",
                    "Your password must have at least 5 letters and/or numbers and no spaces");
            request.getRequestDispatcher("registerCustomer.jsp").include(request, response);
        } else if (!validator.nameFormat(name)) {
            session.setAttribute("nameError", "Your name must not include numbers");
            request.getRequestDispatcher("registerCustomer.jsp").include(request, response);
        }

        // If validation passes:
        else {
            try {
                // Temp is initiated as a potenital user matching the email supplied.
                User temp = manager.findUserEmailOnly(email);
                // If temp is not null (the query found a matching email) then a createdError is
                // outputted and page is reloaded with error.
                if (temp != null) {
                    session.setAttribute("createdError", "Email already in use.");
                    request.getRequestDispatcher("registerCustomer.jsp").include(request, response);
                }
                // If temp becomes null (query found no matching emails) then a new user is
                // created using the supplied information.
                // Information is then passed onto main through the user variable.
                // Access log is updated.
                else {

                    manager.addUser(name, email, password, ID, status, role);

                    // manager.addAccessLog(email, action, dateString, timeString);

                    // If the logged-in user is an admin, keep the admin session intact
                    if (loggedInUser != null && "admin".equals(loggedInUser.getRole())) {
                        response.sendRedirect("adminMain.jsp"); // Redirect to admin main menu
                    } else {
                        // Normal user registration flow, set the new user in session
                        User newUser = new User(name, email, password, ID, status, role);
                        session.setAttribute("user", newUser);
                        request.getRequestDispatcher("main.jsp").include(request, response);
                    }

                }
            } catch (SQLException | NullPointerException ex) {
                Logger.getLogger(RegisterCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Reset validator for future logins.
        validator.clear(session);
    }
}
