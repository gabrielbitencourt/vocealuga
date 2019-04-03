package componentes.cliente.painel;

import models.Cliente;
import utils.Navigate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientePainel implements Initializable {

    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientes = Cliente.all();
            for (Cliente c : clientes) {
                // TODO (front) COLOCAR CLIENTES EM UMA LISTA OU ACCORDION
                System.out.println(c.nome);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("init lista");
    }

    public void toCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

    public void toCadastroVericulo(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "veiculo/cadastro/veiculo.cadastro.fxml");
    }
}
