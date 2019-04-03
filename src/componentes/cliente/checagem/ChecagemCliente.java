package componentes.cliente.checagem;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class ChecagemCliente {

    public void acessoClintePainel(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel/cliente.painel.fxml");
    }

    public void acessoCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

}
