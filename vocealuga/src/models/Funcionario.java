package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConexaoBanco;
import utils.BCrypt;

public class Funcionario {
	
	public String nome;
	public String sobrenome;
	public String usuario;
	
	public Funcionario(String login, char[] passwordcs) throws RuntimeException {
		ConexaoBanco bd = new ConexaoBanco();
		Connection conn = bd.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM funcionarios WHERE usuario=?");
			statement.setString(1, login);
			
			ResultSet rs = statement.executeQuery();
			rs.first();
			
			if (BCrypt.checkpw(new String(passwordcs), rs.getString("senha"))) {
				this.nome = rs.getString("nome");
				this.sobrenome = rs.getString("sobrenome");
				this.usuario = rs.getString("usuario");
				this.nome = rs.getString("senha");
			}
			else {
				rs.close();
				statement.close();
				conn.close();
				throw new RuntimeException("Senha inválida");
			}
			
			rs.close();
			statement.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
