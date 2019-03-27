package controllers;

import javafx.event.ActionEvent;
import utils.Navigate;

import java.io.IOException;

public class Veiculo {

    public void toPainel(ActionEvent event) throws IOException {
        Navigate.to(event, this.getClass(), "cliente/painel.fxml");
    }

}
