package models;

import database.ConexaoBanco;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Reserva {

    int id;

    Date retirada;
    Date entrega;

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
        this.filial_id = rs.getInt("filial_id");
        this.cliente_id = rs.getString("cliente_id");
        this.grupo_id = rs.getInt("grupo_id");
        this.veiculo_id = rs.getString("veiculo_id");
    }

    public Reserva(Date retirada, Date entrega, int filial_id, String cliente_id, int grupo_id) {
        this.retirada = retirada;
        this.entrega = entrega;
        this.filial_id = filial_id;
        this.cliente_id = cliente_id;
        this.grupo_id = grupo_id;
    }

    public Reserva(Date retirada, Date entrega, int filial_id, String cliente_id, int grupo_id, String veiculo_id) {
        this.retirada = retirada;
        this.entrega = entrega;
        this.filial_id = filial_id;
        this.cliente_id = cliente_id;
        this.veiculo_id = veiculo_id;
        this.grupo_id = grupo_id;
    }

    public boolean update() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE reservas SET retirada = ?, entrega = ?, filial_id = ?, cliente_id = ?, grupo_id = ?, veiculo_id = ? WHERE id=?");
        statement.setDate(1, this.retirada);
        statement.setDate(2, this.entrega);
        statement.setString(3, Integer.toString(this.filial_id));
        statement.setString(4, this.cliente_id);
        statement.setString(5, Integer.toString(this.grupo_id));
        statement.setString(6, this.veiculo_id);

        statement.setString(7, Integer.toString(this.id));
        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
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
        PreparedStatement statement = conn.prepareStatement("INSERT INTO reservas (retirada, entrega, filial_id, cliente_id, grupo_id, veiculo_id) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setDate(1, this.retirada);
        statement.setDate(2, this.entrega);
        statement.setInt(3, this.filial_id);
        statement.setString(4, this.cliente_id);
        statement.setInt(5, this.grupo_id);
        statement.setString(6, this.veiculo_id);


        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }
    @Override
    public String toString() {
        return this.cliente_id;
    }

    public int getId() {
        return id;
    }

    public int getFilial_id() {
        return filial_id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public String getVeiculo_id() {
        return veiculo_id;
    }

    public int getGrupo_id() {
        return grupo_id;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setRetirada(Date retirada) {
        this.retirada = retirada;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public void setFilial_id(int filial_id) {
        this.filial_id = filial_id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setVeiculo_id(String veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }
}
