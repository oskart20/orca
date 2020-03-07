import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
    // methods for administrative use
    Connection connection = null;
    String urlDB = "";
    String userDB = "";
    String passwordDB = "";

    public void insertTeacherData(String name, String forename, String encryptedPassword, String salt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt=connection.createStatement();
        stmt.executeUpdate(String.format("INSERT INTO User (Name, Forename, Password, Salt) VALUES ('%1$s', '%2$s', '%3$s', '%4$s');", name, forename, encryptedPassword, salt));
        connection.close();
    }

    public void createTeacher(String name, String forename, String userPassword){
        byte[] salt = new byte[0];
        String password = null;
        try {
            salt = PasswordEncryptionService.generateSalt();
            password = PasswordEncryptionService.base64Encode(PasswordEncryptionService.getEncryptedPassword(userPassword, salt));
            insertTeacherData(name, forename, password, PasswordEncryptionService.base64Encode(salt));
            System.out.println(String.format("inserted %1$s %2$s successfully", forename, name));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createPupil(String name, String forename, String grade, String userPassword){
        byte[] salt = new byte[0];
        String password = null;
        try {
            salt = PasswordEncryptionService.generateSalt();
            password = PasswordEncryptionService.base64Encode(PasswordEncryptionService.getEncryptedPassword(userPassword, salt));
            insertPupilData(name, forename, grade, password, PasswordEncryptionService.base64Encode(salt));
            System.out.println(String.format("inserted %1$s %2$s successfully", forename, name));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertPupilData(String name, String forename, String grade, String encryptedPassword, String salt) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urlDB, userDB, passwordDB);
        System.out.println("Connection successful");
        Statement stmt=connection.createStatement();
        stmt.executeUpdate(String.format("INSERT INTO User (Name, Forename, Grade, Password, Salt) VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s');", name, forename, grade, encryptedPassword, salt));
        connection.close();
    }
}
