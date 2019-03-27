package controllers;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class Cliente {

    public void toPainel(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel.fxml");
    }

    public void toCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/cliente.cadastro.fxml");
    }

    public void toCadastroVericulo(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "veiculo/veiculo.cadastro.fxml");
    }
}
