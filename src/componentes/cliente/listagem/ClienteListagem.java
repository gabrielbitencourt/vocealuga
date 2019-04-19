package componentes.cliente.listagem;

import componentes.cliente.edicao.ClienteEdicao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.InputEvent;
import models.Cliente;
import utils.FormattedField;
import utils.Navigate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ClienteListagem implements Initializable {

    @FXML Accordion clientesLista;
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    @FXML FormattedField cpfFilter;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientes = Cliente.all();
            if (clientes.size() < 1) {
                // TODO - mostrar label com mensagem que não há nenhum cliente
            }
            else {
                for (Cliente c : clientes) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/componentes/cliente/edicao/cliente.edicao.fxml"));
                        TitledPane tp = new TitledPane(c.toString(), loader.load());
                        clientesLista.getPanes().add(tp);

                        ClienteEdicao controller = loader.getController();
                        controller.init(c, tp);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                cpfFilter.plainTextProperty().addListener(this::filtrarCpf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void filtrarCpf(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        ArrayList<Cliente> filtered = new ArrayList<>(clientes);

        if (newValue.length() > 0) {
            filtered.removeIf(cliente -> {
                return !cliente.cpf.contains(newValue);
            });
            System.out.println(filtered.toString());
        }
        clientesLista.getPanes().clear();
        for (Cliente c : filtered) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/componentes/cliente/edicao/cliente.edicao.fxml"));
                TitledPane tp = new TitledPane(c.toString(), loader.load());
                clientesLista.getPanes().add(tp);

                ClienteEdicao controller = loader.getController();
                controller.init(c, tp);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void acessoClintePainel(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/edicao/cliente.edicao.fxml");
    }

    public void acessoCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

}
