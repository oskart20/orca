import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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

    private byte[] getPassword(String name, String forename, String type) throws ClassNotFoundException, SQLException {
        byte[] password = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt=connection.createStatement();
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
        Statement stmt=connection.createStatement();
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



}
