package Controller;

import View.Book;
import View.GoAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    Connection c1 = database.connect();

    public void loginCheck(String username, String password) {

        String sql = "SELECT * FROM `member` WHERE (`MID` = ? OR `MName` = ?) AND `Password` = ? AND `IsDelete` = ?";
        try {
            PreparedStatement pst = c1.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, "false");
            ResultSet r1 = pst.executeQuery();
            if (r1.next()) {
                if ("Admin".equals(r1.getString("MType"))) {
                    new GoAdmin().setVisible(true);
                } 
                else
                {
                    new Book().setVisible(true);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }
}
