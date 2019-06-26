package componentes.navbar;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class Navbar {

    public void toClientes(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/listagem/cliente.listagem.fxml");
    }

    public void toReservas(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "reserva/listagem/reserva.listagem.fxml");
//        Navigate.to(event, this.getClass(), "reserva/listagemAlt/reserva.listagem.alt.fxml");
    }

    public void toVeiculos(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "veiculo/listagem/veiculo.listagem.fxml");
    }
    public void toEntrega(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "reserva/listagem/entrega.fxml");
    }
    

    public void logout(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "login/login.fxml");
    }
}
