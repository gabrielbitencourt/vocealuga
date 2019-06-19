package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Motorista {

    public String nome;
    public Date nascimento;
    public String cnh;

    public Motorista(String nome, Date nascimento, String cnh) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.cnh = cnh;
    }

    public Motorista(Cliente cliente, String cnh) {
        this.nome = cliente.nome + ' ' + cliente.sobrenome;
        this.nascimento = cliente.nascimento;
        this.cnh = cnh;
    }

    public Motorista(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome_completo");
        this.nascimento = rs.getDate("nascimento");
        this.cnh = rs.getString("cnh");
    }

}
