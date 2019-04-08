package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConexaoBanco;
import utils.BCrypt;

public class Funcionario {

    public String nome;
    public String sobrenome;
    public String usuario;

    public Funcionario(String nome, String sobrenome, String usuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
    }

    public static Funcionario authenticate(String login, String password) throws SQLException, RuntimeException {
        try {
            Connection conn = new ConexaoBanco().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM funcionarios WHERE usuario=?");
            statement.setString(1, login);

            ResultSet rs = statement.executeQuery();
            rs.first();

            if (BCrypt.checkpw(password, rs.getString("senha"))) {
                return new Funcionario(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("usuario"));
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

    public static ArrayList<Funcionario> all() throws SQLException {
        ArrayList<Funcionario> results = new ArrayList<Funcionario>();
        Connection conn = new ConexaoBanco().getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from funcionarios");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                results.add(new Funcionario(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("usuario")));
            }
            return results;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public static Funcionario findById(int id) throws SQLException {
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("SELECT * from funcionarios WHERE id=?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();
            return new Funcionario(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("usuario"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }


}
