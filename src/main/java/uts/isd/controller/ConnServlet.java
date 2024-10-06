/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.dao.*;

public class ConnServlet extends HttpServlet {
    private DBConnector db;
    private DBManager manager;
    private OpalCardDAO opalCardDAO;
    private SavedTripDAO savedTripDAO;
    private Connection conn;

    @Override // Create and instance of DBConnector for the deployment session
    public void init() {
        try {
            db = new DBConnector();
            System.out.println("connection is working");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override // Add the DBConnector, DBManager, Connection instances to the session

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("manager") == null) {
            try {
                conn = db.openConnection();
                manager = new DBManager(conn);
                session.setAttribute("manager", manager); // Store DBManager in session
                System.out.println("manager is set and isnt null");

                opalCardDAO = new OpalCardDAO(conn);
                savedTripDAO = new SavedTripDAO(conn);
                session.setAttribute("opalCardDAO", opalCardDAO);
                session.setAttribute("savedTripDAO", savedTripDAO);

            } catch (SQLException ex) {
                Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Use sendRedirect to avoid forwarding loops
        response.sendRedirect("index.jsp");
    }

    @Override // Destroy the servlet and release the resources of the application (terminate
              // also the db connection)

    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
