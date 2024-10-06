/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uts.isd.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import uts.isd.dao.DBConnector;
import uts.isd.dao.DBManager;
import uts.isd.model.User;

public class TestUserDB {
    private static Scanner in = new Scanner(System.in);
    private DBConnector connector;
    private Connection conn;
    private DBManager db;

    public static void main(String[] args) throws SQLException {
        (new TestUserDB()).runQueries();
    }

    public TestUserDB() {
        try {
            connector = new DBConnector();
            conn = connector.openConnection();
            db = new DBManager(conn);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private char readChoice() {
        System.out.print("Operation CRUDS or * to exit: ");
        return in.nextLine().charAt(0);
    }

    void runQueries() throws SQLException {
        char c;
        while ((c = readChoice()) != '*') {
            switch (c) {
                case 'C':
                    testAdd();
                    break;
                case 'R':
                    testRead();
                    break;
                case 'U':
                    testUpdate();
                    break;
                case 'D':
                    testDelete();
                    break;

                // case 'L':

                // testAccessLog();
                // break;
                case 'S':
                    showAll();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }

    private void testAdd() {
        System.out.print("User email: ");
        String email = in.nextLine();
        System.out.print("User name: ");
        String name = in.nextLine();
        System.out.print("User password: ");
        String password = in.nextLine();
        System.out.print("User ID: ");
        String ID = in.nextLine();
        System.out.print("User role: ");
        String role = in.nextLine();
        System.out.print("User Status: ");
        String status = in.nextLine();

        try {
            db.addUser(name, email, password, ID, status, role);
        } catch (SQLException ex) {
            Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User is added to the database.");
    }

    private void testRead() throws SQLException {
        System.out.print("User email: ");
        String email = in.nextLine();
        System.out.print("User password: ");
        String password = in.nextLine();
        User user = db.findUser(email, password);
        if (user != null) {
            System.out.println("User " + user.getName() + "exists in the database.");
        } else {
            System.out.println("User does not exist.");
        }
    }

    private void testUpdate() {
        System.out.print("User email: ");
        String email = in.nextLine();
        System.out.print("User password: ");
        String password = in.nextLine();

        try {
            if (db.checkUser(email, password)) {
                System.out.print("User name: ");
                String name = in.nextLine();

                db.updateUser(name, email, password);
            } else {
                System.out.println("User does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void testDelete() {
        System.out.print("User email: ");
        String email = in.nextLine();
        System.out.print("User password: ");
        String password = in.nextLine();

        try {
            if (db.checkUser(email, password)) {
                db.deleteUser(email);
            } else {
                System.out.println("User does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAll() {
        try {
            ArrayList<User> users = db.fetchUsers();
            System.out.println("Students Table:");
            users.stream().forEach((user) -> {
                System.out.printf("%-20s %-30s %-20s %-10s\n", user.getName(), user.getEmail(), user.getPassword(),
                        user.getID(), user.getStatus(), user.getRole());
            });
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * private void testAccessLog(){
     * Date date = Calendar.getInstance().getTime();
     * DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
     * String stringDate = dateFormat.format(date);
     * 
     * System.out.print("Time: ");
     * String time = in.nextLine();
     * System.out.print("Action: ");
     * String action = in.nextLine();
     * System.out.print("Email: ");
     * String email = in.nextLine();
     * 
     * 
     * try {
     * db.addAccessLog(stringDate, time, action, email);
     * } catch (SQLException ex) {
     * Logger.getLogger(TestUserDB.class.getName()).log(Level.SEVERE, null, ex);
     * }
     * System.out.println("Access Log added");
     */

}
