package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConexaoBanco;
import utils.BCrypt;

public abstract class Funcionario {

    public static String nome;
    public static String sobrenome;
    public static String usuario;
    public static boolean gerente;
    public static int filial_id;

    private Funcionario(String nome, String sobrenome, String usuario, boolean gerente, int filial_id) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
        this.gerente = gerente;
        this.filial_id = filial_id;
    }

    public static boolean authenticate(String login, String password) throws SQLException, RuntimeException {
        try {
            Connection conn = new ConexaoBanco().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM funcionarios WHERE usuario=?");
            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();
            rs.first();

            if (BCrypt.checkpw(password, rs.getString("senha"))) {
                Funcionario.nome = rs.getString("nome");
                Funcionario.sobrenome = rs.getString("sobrenome");
                Funcionario.usuario = rs.getString("usuario");
                Funcionario.gerente = rs.getBoolean("gerente");
                Funcionario.filial_id = rs.getInt("filial_id");
                return true;
            }
            else {
                rs.close();
                statement.close();
                conn.close();
                throw new RuntimeException("Senha inválida");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public static void logout() {
        Funcionario.nome = null;
        Funcionario.sobrenome = null;
        Funcionario.usuario = null;
        Funcionario.gerente = false;
    }

}
