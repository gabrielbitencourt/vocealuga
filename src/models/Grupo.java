package models;

import database.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Grupo {

    public int id;
    public String nome;

    public Grupo(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.nome = rs.getString("nome");
    }

    public String toString() {
        return this.nome;
    }

    public static ArrayList<Grupo> all() throws SQLException {
        ArrayList<Grupo> result = new ArrayList<>();
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM grupos");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Grupo(rs));
        }
        return result;
    }

}
