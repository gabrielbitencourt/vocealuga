package models;

import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;

public class Veiculo {

    public String placa;
    public Double km;
    public Date comprado;
    public boolean disponivel;

    public Modelo modelo;
    public int modelo_id;

    public Filial filial;
    public int filial_id;

    public Veiculo(ResultSet rs) throws SQLException {
        try {
            this.placa = rs.getString("placa");
            this.km = rs.getDouble("km");
            this.comprado = rs.getDate("comprado");
            this.disponivel = rs.getBoolean("disponivel");
            this.filial_id = rs.getInt("filial_id");
            this.modelo_id = rs.getInt("modelo_id");
            this.modelo = new Modelo(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public Veiculo(String placa, Double km, Date comprado, boolean disponivel, int filial_id, int modelo_id) {
        this.placa = placa;
        this.km = km;
        this.comprado = comprado;
        this.disponivel = disponivel;
        this.filial_id = filial_id;
        this.modelo_id = modelo_id;
    }
    
    public String toString() {
        return this.placa;
    }

    public void save() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO veiculos (placa, km, disponivel, comprado, filial_id, modelo_id) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, this.placa);
        statement.setDouble(2, this.km);
        statement.setBoolean(3, this.disponivel);
        statement.setDate(4, this.comprado);
        statement.setInt(5, this.filial_id);
        statement.setInt(6, this.modelo_id);

        statement.execute();
        statement.close();
        conn.close();
    }

    public boolean update() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE veiculos SET km = ?, disponivel = ?, filial_id = ? WHERE placa=?");
        statement.setDouble(1, this.km);
        statement.setBoolean(2, this.disponivel);
        statement.setInt(3, this.filial_id);

        statement.setString(4, this.placa);
        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public boolean delete() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM veiculos WHERE placa=?");
        statement.setString(1, this.placa);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

    public static ArrayList<Veiculo> all() throws SQLException {
        ArrayList<Veiculo> result = new ArrayList<>();
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM veiculos LEFT JOIN modelos ON veiculos.modelo_id = modelos.id");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Veiculo(rs));
        }
        return result;
    }

    public static ArrayList<Veiculo> ofFilial(int filial_id) throws SQLException {
        ArrayList<Veiculo> result = new ArrayList<>();
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM veiculos LEFT JOIN modelos ON veiculos.modelo_id = modelos.id WHERE veiculos.filial_id = ?");
        statement.setInt(1, filial_id);

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Veiculo(rs));
        }

        return result;

    }

    public static Veiculo findById(String placa) throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM veiculos LEFT JOIN modelos ON veiculos.modelo_id = modelos.id WHERE veiculos.placa=? ");
        statement.setString(1, placa);
        ResultSet rs = statement.executeQuery();
        if (rs.first()) {
            return new Veiculo(rs);
        }
        return null;

    }
}
