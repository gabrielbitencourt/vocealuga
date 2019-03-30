package componentes.cliente.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Cliente;
import utils.Navigate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteCadastro {

    @FXML TextField nomeField;
    @FXML TextField sobrenomeField;
    @FXML TextField enderecoField;
    @FXML TextField celularField;
    @FXML TextField cpfField;
    @FXML TextField cnhField;
    @FXML TextField emailField;
    @FXML TextField nascimentoField;

    public void toPainel(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel/cliente.painel.fxml");
    }

    public void cadastrarCliente() {
        Date nascimento = null;
        try {
            nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoField.getText());

        } catch (ParseException e) {
            e.printStackTrace();
            // TODO mensagem de erro por causa do formato da data
        }
        Cliente cliente = new Cliente(nomeField.getText(), sobrenomeField.getText(),
                enderecoField.getText(), cpfField.getText(), cnhField.getText(),
                emailField.getText(), celularField.getText(), nascimento);
        try {
            cliente.save();
            // TODO mensagem de sucesso (e perguntar se deseja cadastrar novo)
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO mesangem de erro (que nao deveria acontecer nunca)
        }

    }

}
