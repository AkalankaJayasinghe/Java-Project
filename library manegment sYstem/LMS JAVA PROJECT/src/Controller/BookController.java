package Controller;

import Model.BooK;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookController {
         Connection cp = database.connect();
     
     public void addBook(BooK b1){
        String bookName = b1.getBookName();
        String author = b1.getAuthor();
        String publishYear = b1.getPublishYear();

        String sql =  "INSERT INTO `book`(`BName`,`BAuthor`,`BPYear`) VALUES(?,?,?)";
        try {
            PreparedStatement ps = cp.prepareStatement(sql);
            ps.setString(1, bookName);
            ps.setString(2, author);
            ps.setString(3, publishYear);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Cannot Insert Book");
        }
     }
         public void showOnTable(JTable bTable, String bSearch) {

        String query = "SELECT * FROM book  WHERE BookAvailable='false'";

        try {
            PreparedStatement stmt = cp.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Book Name");
            model.addColumn("Author");
            model.addColumn("Year Published");

            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getInt("BID"); 
                row[1] = rs.getString("BName");
                row[2] = rs.getString("BAuthor");
                row[3] = rs.getInt("BPYear");
                model.addRow(row);
            }
            bTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data:1" + e.getMessage());
        }
    }
    public void searchBook(String title, JTable bTable) {
        String query = "SELECT * FROM book WHERE `BookAvailable`='false' AND `BName` LIKE ?";

        try {
            PreparedStatement stmt = cp.prepareStatement(query);

            // Add '%' wildcards around the title before setting it
            stmt.setString(1, "%" + title + "%");

            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Book Name");
            model.addColumn("Author");
            model.addColumn("Year Published");

            while (rs.next()) {
                Object row[] = new Object[4];
                row[0] = rs.getInt("BId");
                row[1] = rs.getString("BName");
                row[2] = rs.getString("BAuthor");
                row[3] = rs.getInt("BPYear");

                model.addRow(row);
            }
                    bTable.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }

    }
     public void searchBook(String title, String author, JTable bTable) {
        String query = "SELECT * FROM book WHERE BookAvailable='false' AND  `BName`  LIKE ? OR `BAuthor` LIKE ?";

        try {
            PreparedStatement stmt = cp.prepareStatement(query);
            stmt.setString(1, "%" + title + "%");
            stmt.setString(2, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Book Name");
            model.addColumn("Author");
            model.addColumn("Year Published");

            while (rs.next()) {
                Object row[] = new Object[4];
                row[0] = rs.getInt("BId"); 
                row[1] = rs.getString("BName");
                row[2] = rs.getString("BAuthor");
                row[3] = rs.getInt("BPYear");

                model.addRow(row);
            }

            bTable.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading data:2 " + e.getMessage());
        }
    }
    public void updateBookDetails(BooK b, JTable bTable, String search, String[] btd) {
        String title = b.getBookName();
        String author = b.getAuthor();
        String year = b.getPublishYear();

        String sqlUpdate = "UPDATE `book` SET `BName`=?, `BAuthor`=?, `BPYear`=? WHERE `BName`=? AND `BAuthor`=? AND `BPYear`=?;";

        try {
            PreparedStatement stmtUpdate = cp.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, title); 
            stmtUpdate.setString(2, author); 
            stmtUpdate.setString(3, year); 
            stmtUpdate.setString(4, btd[0]);
            stmtUpdate.setString(5, btd[1]);
            stmtUpdate.setInt(6, Integer.parseInt(btd[2]));

            int rowsAffected = stmtUpdate.executeUpdate();
            System.out.println(rowsAffected);
            if (0 == rowsAffected) {
                JOptionPane.showMessageDialog(null, "No changes were made to the book details.");
            } else {
                JOptionPane.showMessageDialog(null, "Book details updated successfully.");
          
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating the book: " + e.getMessage());
        }

    }

    public void deleteBook(JTable bTable, String search, String td[]) {

        String sqlDelete = "UPDATE `book` SET `BookAvailable`='true' WHERE `BName` = ?  AND `BAuthor` = ?   AND `BPYear` = ?;";

        try {
            PreparedStatement stmtDelete = cp.prepareStatement(sqlDelete);
            stmtDelete.setString(1, td[0]);
            stmtDelete.setString(2, td[1]);
            stmtDelete.setInt(3, Integer.parseInt(td[2]));

            int rowsAffected = stmtDelete.executeUpdate();
            System.out.println(rowsAffected);
            if (0 == rowsAffected) {

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Deleting the book: " + e.getMessage());
        }
    }
       public String[] getDataFromtable(JTable bTable) {
        int selectedRow = bTable.getSelectedRow();

        String td[] = new String[3];
        if (selectedRow != -1) { // Ensure a row is selected
            td[0] = bTable.getValueAt(selectedRow, 1).toString();
            td[1] = bTable.getValueAt(selectedRow, 2).toString();
            td[2] = bTable.getValueAt(selectedRow, 3).toString();
        }
        return td;
    }

    public void textFieldClear(JTextField txtBName, JTextField txtBAuthor, JTextField txtBYear) {
        txtBName.setText("");
        txtBAuthor.setText("");
        txtBYear.setText("");
    }
}
