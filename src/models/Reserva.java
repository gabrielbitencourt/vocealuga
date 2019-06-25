package models;

import database.ConexaoBanco;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Reserva {

    int id;

    Date retirada;
	Date entrega;
	
	String status;

    Filial filial;
    int filial_id;

    Cliente cliente;
    String cliente_id;

    Veiculo veiculo;
    String veiculo_id;

    Grupo grupo;
    int grupo_id;

    public Reserva(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.retirada = rs.getDate("retirada");
		this.entrega = rs.getDate("entrega");
		this.cliente_id = rs.getString("status");
        this.filial_id = rs.getInt("filial_id");
        this.cliente_id = rs.getString("cliente_id");
        this.grupo_id = rs.getInt("grupo_id");
        this.veiculo_id = rs.getString("veiculo_id");
    }

    public Reserva(Date retirada, Date entrega, int filial_id, String cliente_id, int grupo_id) {
        this.retirada = retirada;
		this.entrega = entrega;
		this.status = "reservado";
        this.filial_id = filial_id;
        this.cliente_id = cliente_id;
        this.grupo_id = grupo_id;
    }

    public Reserva(Date retirada, Date entrega, int filial_id, String cliente_id, int grupo_id, String veiculo_id) {
        this.retirada = retirada;
		this.entrega = entrega;
		this.status = "reservado";
        this.filial_id = filial_id;
        this.cliente_id = cliente_id;
        this.veiculo_id = veiculo_id;
        this.grupo_id = grupo_id;
    }

    public static Map<Integer, Map<Integer, Map<Integer, ArrayList<Reserva>>>> all() throws SQLException {
        Map<Integer, Map<Integer, Map<Integer, ArrayList<Reserva>>>> results = new HashMap<>();
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservas ORDER BY reservas.retirada ASC");
        ResultSet rs = statement.executeQuery();

        Calendar cal = Calendar.getInstance();
        while (rs.next()) {
            cal.setTime(rs.getDate("retirada"));

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            if (results.get(year) == null) {
                results.put(year, new HashMap<>());
            }
            if (results.get(year).get(month) == null) {
                results.get(year).put(month, new HashMap<>());
            }
            if (results.get(year).get(month).get(day) == null) {
                results.get(year).get(month).put(day, new ArrayList<>());
            }
            results.get(year).get(month).get(day).add(new Reserva(rs));
        }
        return results;
    }

    public boolean save() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO reservas (retirada, entrega, status, filial_id, cliente_id, grupo_id, veiculo_id) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setDate(1, this.retirada);
        statement.setDate(2, this.entrega);
        statement.setString(3, this.status);
        statement.setInt(4, this.filial_id);
        statement.setString(5, this.cliente_id);
        statement.setInt(6, this.grupo_id);
        statement.setString(7, this.veiculo_id);


        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

}
