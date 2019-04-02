package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    public void acessoSample(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }

    public void acessoHome(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }

    public void acessoCenaCadastroUsuario(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("clienteCadastro.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }

    public void acessoChecagemCliente(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("checagemCliente.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }

    public void acessoCenaCadastroVeiculo(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("veiculoCadastro.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }

    public void acesso(ActionEvent event) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    }
   /*
    public void selector(int index) throws IOException{
        switch(index){
            case 0:
                trocadorCena("sample.fxml");
                break;
            case 1:
                trocadorCena("clienteCadastro.fxml");
                break;
            case 2:
                trocadorCena("veiculoCadastro.fxml");
                break;
        }
    }
    public void trocadorCena(String file) throws IOException {
        Parent loggedInParent = FXMLLoader.load(getClass().getResource(file);
        Scene loggedInScene = new Scene(loggedInParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loggedInScene);
        window.show();
    } */
}
