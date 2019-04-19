package componentes.veiculo.listagem;

import componentes.veiculo.edicao.VeiculoEdicao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import models.Funcionario;
import models.Veiculo;
import utils.Navigate;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VeiculoListagem implements Initializable {

    ArrayList<Veiculo> veiculos = new ArrayList<>();
    @FXML Accordion veiculosLista;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            veiculos = Veiculo.ofFilial(Funcionario.filial_id);
            if (veiculos.size() < 1) {
                // TODO - mostrar mensagem que nao tem veiculos naquela filial
            }
            else {
                for (Veiculo v : veiculos) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/componentes/veiculo/edicao/veiculo.edicao.fxml"));
                        TitledPane tp = new TitledPane(v.placa/* + " - " + v.modelo.nome*/, loader.load());
                        veiculosLista.getPanes().add(tp);

                        VeiculoEdicao controller = loader.getController();
                        controller.init(v);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
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
