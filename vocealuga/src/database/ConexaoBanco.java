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
	
	@Deprecated()
	public void executeInsertStatement(String sql, Object[] parametros) throws SQLException {
		try {
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			for (int i = 0; i < parametros.length; i++) {
				Object param = parametros[i];
				if (param instanceof String) {
					statement.setString(i + 1, (String) param);
				}
				else if (param instanceof Integer) {
					statement.setInt(i + 1, (int) param);
				}
				else if (param instanceof java.sql.Date) {
					statement.setDate(i + 1, (java.sql.Date) param);
				}
				
			}
			
			statement.execute();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

}
