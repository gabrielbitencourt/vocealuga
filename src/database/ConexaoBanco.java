package database;
/**
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author EDS
 *
 */
public class ConexaoBanco {


    public Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/vocealuga?useTimezone=true&serverTimezone=UTC", "root", "");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

}
