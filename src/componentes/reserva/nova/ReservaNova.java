package componentes.reserva.nova;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import models.*;
import utils.FormattedField;
import utils.Navigate.NavInit;

import java.sql.SQLException;
import java.util.Calendar;

public class ReservaNova implements NavInit {

    @FXML Label reservaLabel;
    @FXML Label grupoLabel;
    @FXML Label ouLabel;
    @FXML FormattedField retirada;
    @FXML FormattedField devolucao;
    @FXML ChoiceBox<Filial> filiais;
    @FXML ChoiceBox<Veiculo> veiculos;
    @FXML ChoiceBox<Grupo> grupos;
    @FXML ChoiceBox<Cliente> clientes;

    public void init(Object... params) {
        Day dia = (Day) params[0];
        retirada.setText(dia.toString());

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        if (cal.get(Calendar.YEAR) == dia.year && cal.get(Calendar.MONTH) == dia.month && cal.get(Calendar.DAY_OF_MONTH) == dia.day) {
            reservaLabel.setText("Aluguel imeadiato");
            grupos.setVisible(false);
            ouLabel.setVisible(false);
            grupoLabel.setVisible(false);
            veiculos.setPrefWidth(veiculos.getWidth() + (grupos.getLayoutX() - veiculos.getLayoutX()));
        }

        try {
            for (Filial f : Filial.all()) {
                filiais.getItems().add(f);
                if (f.id == Funcionario.filial_id) {
                    filiais.setValue(f);
                }
            }

            for (Veiculo v : Veiculo.all()) {
                veiculos.getItems().add(v);
            }

            for (Grupo g : Grupo.all()) {
                grupos.getItems().add(g);
            }

            for (Cliente c : Cliente.all()) {
                clientes.getItems().add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedVeiculo() {
        if (this.veiculos.getValue() != null) {
            this.grupos.setValue(null);
        }
    }

    public void selectedGrupo() {
        if (this.grupos.getValue() != null) {
            this.veiculos.setValue(null);
        }
    }

    public void novaReserva(ActionEvent event) {

    }


}
