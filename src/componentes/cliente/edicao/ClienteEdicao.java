package componentes.cliente.edicao;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Cliente;
import utils.Navigate;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ClienteEdicao {

    @FXML TextField nomeField;

    public void toCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

    public void init(Cliente c) {
        nomeField.setText(c.nome);
    }

}
