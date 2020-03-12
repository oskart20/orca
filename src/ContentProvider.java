import javax.swing.table.DefaultTableModel;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Vector;

public class ContentProvider {
    String urlDB = "";
    String userDB = "";
    String passwordDB = "";

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    /*public static Vector<Vector<Object>> correctedTableData(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return data;
    }*/

    private byte[] getPassword(String name, String forename, String type) throws ClassNotFoundException, SQLException {
        byte[] password = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT Password FROM %1$s WHERE Name='%2$s' AND Forename='%3$s';", type, name, forename));
        password = PasswordEncryptionService.base64Decode(rs.getString("Password"));
        connection.close();
        return password;
    }

    private byte[] getSalt(String name, String forename, String type) throws ClassNotFoundException, SQLException {
        byte[] salt = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT Salt FROM %1$s WHERE Name='%2$s' AND Forename='%3$s';", type, name, forename));
        salt = PasswordEncryptionService.base64Decode(rs.getString("Salt"));
        connection.close();
        return salt;
    }

    public boolean singInUser(String password, String name, String forename, String type) {
        try {
            return PasswordEncryptionService.authenticate(password, getPassword(name, forename, type), getSalt(name, forename, type));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getTestData(String id, String language) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("SELECT %1$s FROM Tests WHERE ID='%2$s';", language, id));
        connection.close();
        return rs;
    }

    public void insertTestData(String id, Object[][] data) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        String query = "INSERT INTO Tests(English, German) values(?,?)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        for (int i = 0; i < data.length; i++) {
            pstmt.setString(1, (String) data[0][i]);
            pstmt.setString(2, (String) data[1][i]);
            pstmt.addBatch();
        }
        // execute the batch
        int[] updateCounts = pstmt.executeBatch();
        checkUpdateCounts(updateCounts);
        // since there were no errors, commit
        connection.commit();
        connection.close();
    }

    public static void checkUpdateCounts(int[] updateCounts) {
        for (int updateCount : updateCounts) {
            if (updateCount >= 0) {
                System.out.println("OK; updateCount=" + updateCount);
            } else if (updateCount == Statement.SUCCESS_NO_INFO) {
                System.out.println("OK; updateCount=Statement.SUCCESS_NO_INFO");
            } else if (updateCount == Statement.EXECUTE_FAILED) {
                System.out.println("Failure; updateCount=Statement.EXECUTE_FAILED");
            }
        }
    }
}
