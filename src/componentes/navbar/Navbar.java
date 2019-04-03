package componentes.navbar;

import utils.Navigate;

import javafx.event.ActionEvent;

import java.io.IOException;

public class Navbar {

    public void acessoHome(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "home/home.fxml");
    }

    public void acessoCliente(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel/cliente.painel.fxml");
    }

    public void acessoVeiculo(ActionEvent event) throws IOException {
        // Navigate.to(event, this.getClass(), "veiculo/painel/veiculo.painel.fxml");
    }

    public void logout(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "login/login.fxml");
    }
}
