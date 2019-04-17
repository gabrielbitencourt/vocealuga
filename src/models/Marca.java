package models;

import database.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Marca {

    public int id;
    public String nome;

    public Marca(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Marca(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nome = rs.getString("nome");
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static ArrayList<Marca> all() throws SQLException {
        ArrayList<Marca> result = new ArrayList<>();
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM marcas");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Marca(rs));
        }
        return result;
    }

    public static Map<Marca, Veiculo> withVeiculos() {
        return null;
    }

}
