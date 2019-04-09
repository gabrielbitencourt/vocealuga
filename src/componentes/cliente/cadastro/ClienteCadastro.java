package componentes.cliente.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Cliente;
import utils.FormattedField;
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
    @FXML FormattedField cpfField;
    @FXML FormattedField cnhField;
    @FXML FormattedField nascimentoField;
    @FXML TextField emailField;
    @FXML FormattedField celularField;

    public void cancelar(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/listagem/cliente.listagem.fxml");
    }

    public void cadastrarCliente(ActionEvent event) {
        Date nascimento = null;
        try {
            nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoField.getText());

        } catch (ParseException e) {
            e.printStackTrace();
            // TODO mensagem de erro por causa do formato da data
        }
        Cliente cliente = new Cliente(nomeField.getText(), sobrenomeField.getText(),
                enderecoField.getText(), cpfField.getPlainText(), cnhField.getPlainText(),
                emailField.getText(), celularField.getPlainText(), nascimento);
        try {
            cliente.save();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cliente cadastrado com sucesso. Deseja cadastrar outro?", ButtonType.NO, ButtonType.YES);
            alert.showAndWait()
                    .ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            nomeField.setText("");
                            sobrenomeField.setText("");
                            enderecoField.setText("");
                            celularField.setText("");
                            cpfField.setText("");
                            cnhField.setText("");
                            emailField.setText("");
                            nascimentoField.setText("");
                        }
                        else if (response == ButtonType.NO) {
                            try {
                                Navigate.to(event, this.getClass(), "cliente/listagem/cliente.listagem.fxml");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO mesangem de erro (que nao deveria acontecer nunca)
        }

    }

}