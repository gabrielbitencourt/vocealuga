package componentes.navbar;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class Navbar {

    public void toClientes(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel/cliente.painel.fxml");
    }

    public void toReservas(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "reserva/painel/reservas.painel.fxml");
    }

    public void toVeiculos(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "veiculo/painel/veiculo.painel.fxml");
    }

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
