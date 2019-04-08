package componentes.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Funcionario;
import utils.Navigate;

import java.io.IOException;

public class Login {

    @FXML TextField nameField;
    @FXML PasswordField passwordField;

    public void login(ActionEvent event) throws IOException {

        try {
            if (Funcionario.authenticate(nameField.getText(), passwordField.getText()) != null) {
                Navigate.to(event, this.getClass(), "cliente/painel/cliente.painel.fxml", "VocêAluga - Painel");
            }
            else {
                /*
                 * TODO - mostrar mensagem de erro
                 */
                Alert alert = new Alert(Alert.AlertType.ERROR, "Usuário ou senha inválidos", ButtonType.CLOSE);
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Usuário ou senha inválidos", ButtonType.CLOSE);
            alert.showAndWait();
        }


    }

}
