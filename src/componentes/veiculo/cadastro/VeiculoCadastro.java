package componentes.veiculo.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.Filial;
import models.Marca;
import models.Modelo;
import models.Veiculo;
import utils.FormattedField;
import utils.Navigate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VeiculoCadastro implements Initializable {

    ArrayList<Filial> filiais = new ArrayList<>();
    ArrayList<Marca> marcas = new ArrayList<>();
    Map<Integer, ArrayList<Modelo>> modelos = new HashMap<>();

    @FXML ChoiceBox<Marca> marcaSelect;
    @FXML ChoiceBox<Modelo> modeloSelect;
    @FXML TextField placaField;
    @FXML FormattedField dataField;
    @FXML ChoiceBox<Filial> filialSelect;
    @FXML TextField kmField;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.marcas = Marca.all();
            this.modelos = Modelo.byMarca();
            this.filiais = Filial.all();
            this.modeloSelect.setDisable(true);


            for (Marca m : marcas) {
                marcaSelect.getItems().add(m);
            }

            for (Filial f : filiais) {
                this.filialSelect.getItems().add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedMarca() {
        Marca marca = this.marcaSelect.getValue();
        this.modeloSelect.getItems().clear();
        if (marca != null) {
            for (Modelo m : modelos.get(marca.id)) {
                this.modeloSelect.getItems().add(m);
            }
        }
        this.modeloSelect.setDisable(marca == null);
    }

    public void novoVeiculo(ActionEvent event) throws IOException {
    }
}
