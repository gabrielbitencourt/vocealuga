package componentes.cliente.listagem;

import componentes.cliente.edicao.ClienteEdicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import models.Cliente;
import utils.Navigate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClienteListagem implements Initializable {

    @FXML
    Accordion clientesLista;
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientes = Cliente.all();
            if (clientes.size() < 1) {
                // TODO - mostrar label com mensagem que não há nenhum cliente
            }
            else {
                for (Cliente c : clientes) {
                    TitledPane tp = new TitledPane();
                    tp.setText(c.toString());

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/componentes/cliente/edicao/cliente.edicao.fxml"));
                        tp.setContent(loader.load());
                        clientesLista.getPanes().add(tp);

                        ClienteEdicao controller = loader.getController();
                        controller.init(c, tp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void acessoClintePainel(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/edicao/cliente.edicao.fxml");
    }

    public void acessoCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

}
