package componentes.navbar;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class Navbar {

    public void toClientes(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/listagem/cliente.listagem.fxml");
    }

    public void toReservas(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "reserva/lsitagem/reserva.listagem.fxml");
    }

    public void toVeiculos(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "veiculo/listagem/veiculo.listagem.fxml");
    }

    public void logout(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "login/login.fxml");
    }
}
