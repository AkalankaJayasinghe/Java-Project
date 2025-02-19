package Controller;

import Model.Member;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MemberController {

    Connection cc = database.connect();

    public void addMember(Member m1) {
        String memberName = m1.getName();
        String memberContact = m1.getContactInfo();
        String userType = m1.getMemberType();
        String cardNumber = m1.getCardNumber();
        int expireDate = m1.getExpireDate();

        String sql = "INSERT INTO `member`(`MName`, `MContact`, `MType`, `CardNumber`,`ExpireDate`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cc.prepareStatement(sql);
            pst.setString(1, memberName);
            pst.setString(2, memberContact);
            pst.setString(3, userType);
            pst.setString(4, cardNumber);
            pst.setInt(5, expireDate);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }

    public void updateMemberDetails(Member m, JTable mTable, String mSearch, String mtd[]) {
        String name = m.getName();
        String conNo = m.getContactInfo();
        String type = m.getMemberType();

        String sqlUpdate = "UPDATE `member` SET `MName`=?, `MContact`=?, `MType`=? WHERE `MName`=? AND `MContact`=? AND `MType`=?;";

        try {
            PreparedStatement stmtUpdate = cc.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, name);
            stmtUpdate.setString(2, conNo);
            stmtUpdate.setString(3, type);
            stmtUpdate.setString(4, mtd[0]);
            stmtUpdate.setString(5, mtd[1]);
            stmtUpdate.setString(6, mtd[2]);

            int rowsAffected = stmtUpdate.executeUpdate();
            if (0 == rowsAffected) {
                JOptionPane.showMessageDialog(null, "No changes were made to the member details.");
            } else {
                JOptionPane.showMessageDialog(null, "Member details updated successfully.");
                showOnTable(mTable, mSearch);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating the book: " + e.getMessage());
        }

    }

    public void showOnTable(JTable mTable, String mSearch) {

        String query = "SELECT MID,MName,MContact,CardNumber,ExpireDate,MType FROM member  WHERE IsDelete='false'";

        try {
            PreparedStatement stmt = cc.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Member Name");
            model.addColumn("Contact No");
            model.addColumn("Card Number");
            model.addColumn("Membership Expire Year");
            model.addColumn("Member Type");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("MID");  
                row[1] = rs.getString("MName");
                row[2] = rs.getString("MContact");
                row[3] = rs.getInt("CardNumber");
                row[4] = rs.getInt("ExpireDate");
                row[5] = "User".equals(rs.getString("MType")) ? "User" : "Admin";

                model.addRow(row);
            }
            mTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data:1" + e.getMessage());
        }
    }

    public void searchMember(String name, String id, JTable mTable) {
        String query = "SELECT * FROM member WHERE IsDelete='false' AND  `MName`  LIKE ? OR `MID` LIKE ?";

        try {
            PreparedStatement stmt = cc.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + id + "%");
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Member Name");
            model.addColumn("Contact No");
            model.addColumn("Card Number");
            model.addColumn("Membership Expire Year");
            model.addColumn("Member Type");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getInt("MId");
                row[1] = rs.getString("MName");
                row[2] = rs.getString("MContact");
                row[3] = rs.getInt("CardNumber");
                row[4] = rs.getInt("ExpireDate");
                row[5] = "User".equals(rs.getString("MType")) ? "User" : "Admin";

                model.addRow(row);
            }

            mTable.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data:2 " + e.getMessage());
        }
    }

    public void deleteMember(JTable mTable, String mSearch, String mtd[]) {

        String sqlDelete = "UPDATE `member` SET IsDelete='true' WHERE `MName` = ?  AND `MContact`=? AND `MType`=? ";

        try {
            PreparedStatement stmtDelete = cc.prepareStatement(sqlDelete);
            stmtDelete.setString(1, mtd[0]);
            stmtDelete.setString(2, mtd[1]);
            stmtDelete.setString(3, mtd[2]);

            int rowsAffected = stmtDelete.executeUpdate();
            if (0 == rowsAffected) {

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Deleting the member: " + e.getMessage());
        }

    }

    public String[] getDataFromtable(JTable mTable) {
        int selectedRow = mTable.getSelectedRow();

        String mtd[] = new String[3];
        if (selectedRow != -1) { // Ensure a row is selected
            mtd[0] = mTable.getValueAt(selectedRow, 1).toString();
            mtd[1] = mTable.getValueAt(selectedRow, 2).toString();
            mtd[2] = mTable.getValueAt(selectedRow, 5).toString();
        }
        return mtd;
    }

    public void textFieldClear(JTextField txtMName, JTextField txtMConNo) {
        txtMName.setText("");
        txtMConNo.setText("");
    }

    public String[] displayDetails(int mId) {

        String query = "SELECT MId,MName,MContact,CardNumber,ExpireDate,MType FROM members  WHERE IsDelete='false' And MId=?";

        try {
            PreparedStatement stmt = cc.prepareStatement(query);
            stmt.setInt(1, mId);
            ResultSet rs = stmt.executeQuery();

            String details[] = new String[5];

            while (rs.next()) {
                details[0] = rs.getString("MId");
                details[1] = rs.getString("MName");
                details[2] = rs.getString("MContact");
                details[3] = Integer.toString(rs.getInt("CardNumber"));
                details[4] = Integer.toString(rs.getInt("ExpireDate"));
            }
            return details;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data:1" + e.getMessage());
        }
        return null;
    }

    public void updatePass(String pass, int mId) {
        if (!pass.isEmpty()) {
            if (pass.length() > 2 && pass.length() < 12) {
                String sqlUpdate = "UPDATE `member` SET `mPass`=? WHERE `MId`=? ;";

                try {
                    PreparedStatement stmtUpdate = cc.prepareStatement(sqlUpdate);
                    stmtUpdate.setString(1, pass);
                    stmtUpdate.setInt(2, mId);
                    int rowsAffected = stmtUpdate.executeUpdate();

                    if (0 == rowsAffected) {
                        JOptionPane.showMessageDialog(null, "No changes were made to the member details.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Your Password updated successfully.");
                    }
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error updating " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter 3-12 Charactor Password!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter Valid Password!");

        }

    }
}
