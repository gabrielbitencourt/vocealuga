package componentes.cliente.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
                                Parent view = FXMLLoader.load(getClass().getResource("/componentes/cliente/painel/cliente.painel.fxml"));
                                Scene scene = new Scene(view);
                                Stage window = (Stage) nomeField.getScene().getWindow();
                                window.setScene(scene);
                                window.show();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            // TODO mensagem de sucesso (e perguntar se deseja cadastrar novo)
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO mesangem de erro (que nao deveria acontecer nunca)
        }

    }

}
