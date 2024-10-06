package uts.isd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uts.isd.model.AccessLog;
import uts.isd.model.User;
import uts.isd.model.Event;

public class DBManager {
    private Statement st;
    private Connection conn;

    public DBManager(Connection conn) throws SQLException {
        this.conn = conn;
        st = conn.createStatement();
    }

    // --------------------USER DATABASE METHODS--------------------------

     // Method to check if a user exists based on email and password
     public boolean checkUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, email);  // Set the email in the query
        ps.setString(2, password);  // Set the password in the query

        ResultSet rs = ps.executeQuery();

        // Check if the query returned a result
        boolean userExists = rs.next();

        // Close the resources
        rs.close();
        ps.close();

        return userExists;  // Return true if a matching user is found, otherwise false
    }
     // Method to find a user based on email
     public User findUserEmailOnly(String email) throws SQLException {
        String query = "SELECT id, name, email, password, status, role FROM users WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, email); // Set the email in the query

        ResultSet rs = ps.executeQuery();

        User user = null;

        // If the result set has data, create a User object
        if (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String userEmail = rs.getString("email");
            String password = rs.getString("password");
            String status = rs.getString("status");
            String role = rs.getString("role");

            // Create a new User object with the fetched data
            user = new User(name, userEmail, password, id, status, role);
        }

        // Close the resources
        rs.close();
        ps.close();

        return user; // Return the User object (null if not found)
    }
    // Function to find a user based on email and password.
    public User findUser(String email, String password) throws SQLException {
        String fetch = "SELECT * FROM ocms.users WHERE EMAIL = ? AND PASSWORD = ?";
        PreparedStatement pstmt = conn.prepareStatement(fetch);
        pstmt.setString(1, email);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String userName = rs.getString("NAME");
            String userEmail = rs.getString("EMAIL");
            String userPass = rs.getString("PASSWORD");
            String userID = rs.getString("ID");
            String userStatus = rs.getString("STATUS");
            String userRole = rs.getString("ROLE");

            return new User(userName, userEmail, userPass, userID, userStatus, userRole);
        }
        return null;
    }
    public ArrayList<User> fetchUsers() throws SQLException {
        String query = "SELECT id, name, email, password, status, role FROM users";  // Modify based on your table structure
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ArrayList<User> users = new ArrayList<>();

        // Iterate through the result set and create User objects
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String status = rs.getString("status");
            String role = rs.getString("role");

            // Create a new User object
            User user = new User(name, email, password, id , status, role);

            // Add the User object to the list
            users.add(user);
        }

        // Close resources
        rs.close();
        ps.close();

        return users;
    }

    // Create new user
    public void addUser(String name, String email, String password, String ID, String status, String role) throws SQLException {
        String query = "INSERT INTO ocms.users (ID, NAME, EMAIL, PASSWORD, STATUS, ROLE) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, ID);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setString(4, password);
        pstmt.setString(5, status);
        pstmt.setString(6, role);
        pstmt.executeUpdate();
    }

    // Update existing user
    public void updateUser(String name, String email, String password) throws SQLException {
        String query = "UPDATE ocms.users SET NAME = ?, PASSWORD = ? WHERE EMAIL = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.executeUpdate();
    }

    // Admin update user method
    public void adminUpdateUser(String name, String email, String password, String ID, String status, String role) throws SQLException {
        String query = "UPDATE ocms.users SET NAME = ?, PASSWORD = ?, STATUS = ?, ROLE = ? WHERE EMAIL = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, password);
        pstmt.setString(3, status);
        pstmt.setString(4, role);
        pstmt.setString(5, email);
        pstmt.executeUpdate();
    }

    // Delete user
    public void deleteUser(String email) throws SQLException {
        String query = "DELETE FROM ocms.users WHERE EMAIL = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, email);
        pstmt.executeUpdate();
    }






    // --------------------EVENTS DATABASE METHODS--------------------------

    // Add a new event
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO events (id, name, category, description) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, event.getId()); // Explicitly adding ID
        ps.setString(2, event.getName());
        ps.setString(3, event.getCategory());
        ps.setString(4, event.getDescription());
        ps.executeUpdate();
    }

    // Fetch all events
    public ArrayList<Event> fetchEvents() throws SQLException {
        String query = "SELECT * FROM events";
        ResultSet rs = st.executeQuery(query);
        ArrayList<Event> events = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            String description = rs.getString("description");
            events.add(new Event(id, name, category, description));
        }
        return events;
    }
    

    // Get an event by ID
    public Event getEventById(int id) throws SQLException {
        String query = "SELECT * FROM events WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String name = rs.getString("name");
            String category = rs.getString("category");
            String description = rs.getString("description");

            return new Event(id, name, category, description);
        }
        return null;
    }

    // Update an event
    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET name = ?, category = ?, description = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, event.getName());
        ps.setString(2, event.getCategory());
        ps.setString(3, event.getDescription());
        ps.setInt(4, event.getId());
        ps.executeUpdate();
    }

    // Delete an event
    public void deleteEvent(int id) throws SQLException {
        String query = "DELETE FROM events WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    
}
