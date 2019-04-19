package models;

import database.ConexaoBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Modelo {

    public int id;
    public String nome;

    public Marca marca;
    public int marca_id;

    public Grupo grupo;
    public int grupo_id;

    public Modelo(String nome, int ano, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }

    public Modelo(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nome = rs.getString("nome");

        this.marca_id = rs.getInt("marca_id");
        this.grupo_id = rs.getInt("grupo_id");
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static Map<Integer, ArrayList<Modelo>> byMarca() throws SQLException {
        Map<Integer, ArrayList<Modelo>> result = new HashMap<>();
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "SELECT modelos.id, modelos.nome, modelos.grupo_id, modelos.marca_id, marcas.nome AS marca, grupos.nome AS grupo FROM modelos " +
                "LEFT JOIN marcas ON modelos.marca_id = marcas.id " +
                "LEFT JOIN grupos ON modelos.grupo_id = grupos.id");
        ResultSet rs = statement.executeQuery();


        while (rs.next()) {
            if (result.get(rs.getInt("marca_id")) != null) {
                result.get(rs.getInt("marca_id")).add(new Modelo(rs));
            }
            else {
                ArrayList<Modelo> arr = new ArrayList<>();
                arr.add(new Modelo(rs));
                result.put(rs.getInt("marca_id"), arr);
            }
        }

        return result;
    }

}
