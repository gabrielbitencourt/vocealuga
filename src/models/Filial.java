package models;

import database.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Filial {

    public int id;
    public String nome;

    public Filial(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nome = rs.getString("nome");
    }

    public Filial(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public boolean save() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO filiais (nome) VALUES (?)");
        statement.setString(1, this.nome);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public boolean update() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE filiais SET nome = ? WHERE id=?");
        statement.setString(1, this.nome);

        statement.setInt(2, this.id);
        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public boolean delete() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM filiais WHERE id=?");
        statement.setInt(1, this.id);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public static ArrayList<Filial> all() throws SQLException {
        ArrayList<Filial> result = new ArrayList<>();
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM filiais");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Filial(rs));
        }
        return result;
    }

    public static Filial findById(int id) throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM filiais WHERE id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.first()) {
            return new Filial(rs);
        }
        return null;

    }

}
