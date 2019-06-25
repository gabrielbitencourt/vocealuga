package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import database.ConexaoBanco;

public class Cliente {

    public String nome;
    public String sobrenome;
    public String endereco;
    public String cpf;
    public String email;
    public String celular;
    public Date nascimento;

    public Cliente(ResultSet rs) throws SQLException {
        try {
            this.nome = rs.getString("nome");
            this.sobrenome = rs.getString("sobrenome");
            this.endereco = rs.getString("endereco");
            this.cpf = rs.getString("cpf");
            this.email = rs.getString("email");
            this.celular = rs.getString("celular");
            this.nascimento = rs.getDate("nascimento");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public Cliente(String nome, String sobrenome, String endereco, String cpf, String email, String telefone, Date nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.celular = telefone;
        this.nascimento = nascimento;
    }

    public String toString() {
        return this.nome + " " + this.sobrenome;
    }

    public boolean save() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO clientes (nome, sobrenome, endereco, cpf, email, celular, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, this.nome);
        statement.setString(2, this.sobrenome);
        statement.setString(3, this.endereco);
        statement.setString(4, this.cpf);
        statement.setString(5, this.email);
        statement.setString(6, this.celular);
        statement.setDate(7, this.nascimento);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public boolean update() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE clientes SET nome = ?, sobrenome = ?, endereco = ?, email = ?, celular = ? WHERE cpf=?");
        statement.setString(1, this.nome);
        statement.setString(2, this.sobrenome);
        statement.setString(3, this.endereco);
        statement.setString(4, this.email);
        statement.setString(5, this.celular);

        statement.setString(6, this.cpf);
        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public boolean delete() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM clientes WHERE cpf=?");
        statement.setString(1, this.cpf);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public static ArrayList<Cliente> all() throws SQLException {
        ArrayList<Cliente> result = new ArrayList<Cliente>();
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM clientes");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Cliente(rs));
        }
        return result;
    }

    public static Cliente findById(String cpf) throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM clientes WHERE cpf=?");
        statement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        if (rs.first()) {
            return new Cliente(rs);
        }
        return null;

    }
}