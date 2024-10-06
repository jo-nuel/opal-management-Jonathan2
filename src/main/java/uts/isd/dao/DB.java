
package uts.isd.dao;

import java.sql.Connection;

//import java.sql.Connection;

public abstract class DB {

    protected String URL = "jdbc:mysql://localhost:3306/ocms";// replace this string with your jdbc:derby local host url
    protected String db = "ocms";// name of the database
    protected String dbuser = "root";// db root user
    protected String dbpass = "useruser"; // db root password
    protected String driver = "com.mysql.cj.jdbc.Driver";
    protected Connection conn; // connection null-instance to be initialized in sub-classes

}
