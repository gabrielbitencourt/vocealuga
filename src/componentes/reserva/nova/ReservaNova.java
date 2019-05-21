package componentes.reserva.nova;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import models.*;
import utils.FormattedField;
import utils.Navigate;
import utils.Navigate.NavInit;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReservaNova implements NavInit {

    @FXML Label reservaLabel;
    @FXML Label grupoLabel;
    @FXML Label ouLabel;
    @FXML FormattedField retiradaField;
    @FXML DatePicker entregaField;
    @FXML ChoiceBox<Filial> filialSelect;
    @FXML ChoiceBox<Veiculo> veiculoSelect;
    @FXML ChoiceBox<Grupo> grupoSelect;
    @FXML ChoiceBox<Cliente> clienteSelect;

    public void init(Object... params) {
        Day dia = (Day) params[0];
        retiradaField.setText(dia.toString());

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        if (cal.get(Calendar.YEAR) == dia.year && cal.get(Calendar.MONTH) == dia.month && cal.get(Calendar.DAY_OF_MONTH) == dia.day) {
            reservaLabel.setText("Aluguel imeadiato");
            grupoSelect.setVisible(false);
            ouLabel.setVisible(false);
            grupoLabel.setVisible(false);
            veiculoSelect.setPrefWidth(veiculoSelect.getWidth() + (grupoSelect.getLayoutX() - veiculoSelect.getLayoutX()));
        }

        try {
            for (Filial f : Filial.all()) {
                filialSelect.getItems().add(f);
                if (f.id == Funcionario.filial_id) {
                    filialSelect.setValue(f);
                }
            }

            // TODO - mostrar apenas veiculos disponiveis
            // veiculos disponiveis sao aqueles que nao estao reservados na data preenchida (campo disabilitado antes do preenchimento da data)
            for (Veiculo v : Veiculo.all()) {
                veiculoSelect.getItems().add(v);
            }

            for (Grupo g : Grupo.all()) {
                grupoSelect.getItems().add(g);
            }

            for (Cliente c : Cliente.all()) {
                clienteSelect.getItems().add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectedVeiculo() {
        if (this.veiculoSelect.getValue() != null) {
            this.grupoSelect.setValue(null);
        }
    }

    public void selectedGrupo() {
        if (this.grupoSelect.getValue() != null) {
            this.veiculoSelect.setValue(null);
        }
    }

    public void novaReserva(ActionEvent event) {
        Date retirada = null;
        Date entrega = null;
        try {
            // TODO (BUG) - data esta voltando um dia do selecionado
            retirada = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(retiradaField.getText()).getTime());
//            entrega = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(entregaField.getText()).getTime());
            entrega = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(entregaField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            // TODO mensagem de erro por causa do formato da data
        }

        Reserva nova;
        if (this.grupoSelect.getValue() != null) {
            nova = new Reserva(retirada, entrega, filialSelect.getValue().id, clienteSelect.getValue().cpf, this.grupoSelect.getValue().id);
        }
        else {
            nova = new Reserva(retirada, entrega, filialSelect.getValue().id, clienteSelect.getValue().cpf, this.veiculoSelect.getValue().modelo.grupo_id, this.veiculoSelect.getValue().placa);
        }

        try {
            nova.save();
            Navigate.to(this.getClass(), "reserva/listagem/reserva.listagem.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
