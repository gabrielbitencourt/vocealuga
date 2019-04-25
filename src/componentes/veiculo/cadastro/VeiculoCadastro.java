package componentes.veiculo.cadastro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import models.*;
import utils.FormattedField;
import utils.Navigate;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VeiculoCadastro implements Initializable {

    ArrayList<Filial> filiais = new ArrayList<>();
    ArrayList<Marca> marcas = new ArrayList<>();
    Map<Integer, ArrayList<Modelo>> modelos = new HashMap<>();

    @FXML ChoiceBox<Marca> marcaSelect;
    @FXML ChoiceBox<Modelo> modeloSelect;
    @FXML FormattedField placaField;
    @FXML FormattedField dataField;
    @FXML ChoiceBox<Filial> filialSelect;
    @FXML TextField kmField;

    public void initialize(URL location, ResourceBundle resources) {
        Button btn = new Button("H");
        btn.setOnAction(this::btnHoje);
        dataField.setRight(btn);
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
                if (f.id == Funcionario.filial_id) {
                    filialSelect.setValue(f);
                }
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

    public void btnHoje(ActionEvent event) {
        this.dataField.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()));
    }

    public void novoVeiculo(ActionEvent event) throws IOException {
        Date compradoEm = null;
        try {
            compradoEm = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(this.dataField.getText()).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Veiculo veiculo = new Veiculo(this.placaField.getText(), Double.valueOf(this.kmField.getText()), compradoEm,
                true, Funcionario.filial_id, this.modeloSelect.getValue().id);
        try {
            veiculo.save();
            Navigate.to(this.getClass(), "veiculo/listagem/veiculo.listagem.fxml");

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO - mensagem de erro
        }
    }
}
