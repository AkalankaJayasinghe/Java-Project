package Controller;

import java.sql.*;
import javax.swing.JOptionPane;

public class database {

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connect Succefully");
            JOptionPane.showMessageDialog(null,"Database Connection ok");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}    

