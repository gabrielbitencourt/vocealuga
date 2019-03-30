package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import database.ConexaoBanco;

public class Cliente {

    public String nome;
    public String sobrenome;
    public String endereco;
    public String cpf;
    public String cnh;
    public String email;
    public String celular;
    public Date nascimento;

    public Cliente(ResultSet rs) throws SQLException {
        try {
            this.nome = rs.getString("nome");
            this.sobrenome = rs.getString("sobrenome");
            this.endereco = rs.getString("endereco");
            this.cpf = rs.getString("cpf");
            this.cnh = rs.getString("cnh");
            this.email = rs.getString("email");
            this.celular = rs.getString("celular");
            this.nascimento = rs.getDate("nascimento");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public Cliente(String nome, String sobrenome, String endereco, String cpf, String cnh, String email, String telefone, Date nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cnh = cnh;
        this.email = email;
        this.celular = telefone;
        this.nascimento = nascimento;
    }

    public boolean save() throws SQLException {
        Connection conn = new ConexaoBanco().getConnection();
        // BUG - SALVA VALORES NULOS COMO VAZIOS
        // ARRUMAR - GARANTIR QUE TODAS AS PROPRIEDADES DA INSTANCIA TEM UM VALOR
        if (Cliente.findById(this.cpf) != null) {
            PreparedStatement statement = conn.prepareStatement("UPDATE clientes SET nome = ?, sobrenome = ?, endereco = ?, cpf = ?, cnh = ?, email = ?, celular = ?, nascimento = ? WHERE cpf=?");
            statement.setString(1, this.nome);
            statement.setString(2, this.sobrenome);
            statement.setString(3, this.endereco);
            statement.setString(4, this.cpf);
            statement.setString(5, this.cnh);
            statement.setString(6, this.email);
            statement.setString(7, this.celular);

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.nascimento);
            statement.setDate(8, new java.sql.Date(cal.getTimeInMillis()));
            statement.setString(9, this.cpf);
            statement.execute();
        }
        else {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO clientes (nome, sobrenome, endereco, cpf, cnh, email, celular, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, this.nome);
            statement.setString(2, this.sobrenome);
            statement.setString(3, this.endereco);
            statement.setString(4, this.cpf);
            statement.setString(5, this.cnh);
            statement.setString(6, this.email);
            statement.setString(7, this.celular);

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.nascimento);
            statement.setDate(8, new java.sql.Date(cal.getTimeInMillis()));
            statement.execute();
        }
        // BUG - MESMO QUANDO A QUERY NAO EXECUTA ELE RETORNA TRUE
        // ARRUMAR - VERIFICAR RETORNO DA FUNCAO statement.execute() E TRATAR OS CASOS LANCANDO SQLException EM CASO DE ERRO
        conn.close();
        return true;
    }

    public static ArrayList<Cliente> all() throws SQLException {
        ArrayList<Cliente> result = new ArrayList<Cliente>();
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM clientes");
        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            result.add(new Cliente(rs));
        }
        return result;
    }

    public static Cliente findById(String cpf) throws SQLException {
        Connection conn = new ConexaoBanco().getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM clientes WHERE cpf=?");
        statement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        if (rs.first()) {
            return new Cliente(rs);
        }
        return null;

    }

}