/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.company.dbase;

/**
 *
 * @author Mateo Torres
 */
import java.sql.*;

public class ConexionDb {
    public static Connection MySQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection
                                   //("jdbc:mysql://localhost:3306/bd_tovar","root","admin");
                                   ("jdbc:mysql://node163380-mobileped.jelastic.saveincloud.net:3306/bd_tovar","root","NAOreb45176");
        return c;
    }
}
