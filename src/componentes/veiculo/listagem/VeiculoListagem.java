package componentes.veiculo.listagem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import models.Filial;
import models.Veiculo;
import utils.Navigate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VeiculoListagem implements Initializable {

    Map<Filial, Veiculo> veiculos = new HashMap<>();
    @FXML TreeView veiculosList;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            veiculos = Veiculo.byFilial();
            for (Filial f : veiculos.keySet()) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void novoVeiculo(ActionEvent event) {
        try {
            Navigate.to(this.getClass(), "veiculo/cadastro/veiculo.cadastro.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
