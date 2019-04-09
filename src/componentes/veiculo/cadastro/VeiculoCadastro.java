package componentes.veiculo.cadastro;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class VeiculoCadastro {

    public void toPainel(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/edicao/cliente.edicao.fxml");
    }

}
