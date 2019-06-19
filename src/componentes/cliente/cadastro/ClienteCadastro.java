package componentes.cliente.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import models.Cartao;
import models.Cliente;
import utils.FormattedField;
import utils.Navigate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ClienteCadastro {

    @FXML TextField nomeField;
    @FXML TextField sobrenomeField;
    @FXML TextField enderecoField;
    @FXML FormattedField cpfField;
    @FXML FormattedField nascimentoField;
    @FXML TextField emailField;
    @FXML FormattedField celularField;

    @FXML TextField cardNameField;
    @FXML FormattedField cardNumberField;
    @FXML FormattedField cardCodField;
    @FXML FormattedField cardExpField;

    public void cancelar(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/listagem/cliente.listagem.fxml");
    }

    public void cadastrarCliente(ActionEvent event) {
        Date nascimento = null;
        Date cardExp = null;
        try {
            nascimento = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(nascimentoField.getText()).getTime());
            cardExp = new Date(new SimpleDateFormat("MM/yy").parse(cardExpField.getText()).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            // TODO mensagem de erro por causa do formato da data
        }

        try {
            Cliente cliente = new Cliente(nomeField.getText(), sobrenomeField.getText(),
                    enderecoField.getText(), cpfField.getPlainText(), emailField.getText(),
                    celularField.getPlainText(), nascimento);

            Cartao cartao = new Cartao(cardNameField.getText(), cardNumberField.getPlainText(),
                    cardCodField.getText(), cardExp, cpfField.getPlainText());

            cliente.save();
            cartao.save();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Cliente cadastrado com sucesso. Deseja cadastrar outro?", ButtonType.NO, ButtonType.YES);
            alert.showAndWait()
                    .ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            nomeField.setText("");
                            sobrenomeField.setText("");
                            enderecoField.setText("");
                            celularField.setText("");
                            cpfField.setText("");
                            emailField.setText("");
                            nascimentoField.setText("");
                            cardNameField.setText("");
                            cardNumberField.setText("");
                            cardCodField.setText("");
                            cardExpField.setText("");
                        }
                        else if (response == ButtonType.NO) {
                            try {
                                Navigate.to(this.getClass(), "cliente/listagem/cliente.listagem.fxml");

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
