package models;

import database.ConexaoBanco;

import java.sql.*;

public class Cartao {

    String titular;
    String numero;
    String codigo;
    Date expiraEm;

    Cliente cliente;
    String cliente_id;

    public Cartao(String titular, String numero, String codigo, Date expiraEm, String cliente_id) {
        this.titular = titular;
        this.numero = numero;
        this.codigo = codigo;
        this.expiraEm = expiraEm;
        this.cliente_id = cliente_id;
    }

    public Cartao(ResultSet rs) throws SQLException {
        this.titular = rs.getString("titular");
        this.numero = rs.getString("numero");
        this.codigo = rs.getString("codigo");
        this.expiraEm = rs.getDate("expiraEm");
        this.cliente_id = rs.getString("cliente_id");
    }

    public boolean save() throws SQLException {
        Connection conn = ConexaoBanco.getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT INTO cartoes (titular, numero, codigo, expiracao, cliente_id) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, this.titular);
        statement.setString(2, this.numero);
        statement.setString(3, this.codigo);
        statement.setDate(4, this.expiraEm);
        statement.setString(5, this.cliente_id);

        boolean executed = statement.execute();
        statement.close();
        conn.close();
        return executed;
    }

}
