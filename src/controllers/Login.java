package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Funcionario;
import utils.Navigate;

import java.io.IOException;

public class Login {

    @FXML TextField nameField;
    @FXML PasswordField passwordField;

    public void login(ActionEvent event) throws IOException {

        try {
            if (Funcionario.authenticate(nameField.getText(), passwordField.getText()) != null) {
                Navigate.to(event, this.getClass(), "cliente/painel.fxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
