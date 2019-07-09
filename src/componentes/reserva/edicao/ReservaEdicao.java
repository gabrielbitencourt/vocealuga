package componentes.reserva.edicao;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReservaEdicao implements NavInit {

    @FXML Label reservaLabel;
    @FXML Label grupoLabel;
    @FXML Label ouLabel;
    @FXML FormattedField retiradaField;
    @FXML DatePicker entregaField;
    @FXML ChoiceBox<Filial> filialSelect;
    @FXML ChoiceBox<Veiculo> veiculoSelect;
    @FXML ChoiceBox<Grupo> grupoSelect;
    @FXML ChoiceBox<Cliente> clienteSelect;

    Reserva reserva;

    public void init(Object... params) {
        Reserva r = (Reserva) params[0];
        this.reserva = r;
        Day dia = (Day) params[1];

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        /*if (cal.get(Calendar.YEAR) == dia.year && cal.get(Calendar.MONTH) == dia.month && cal.get(Calendar.DAY_OF_MONTH) == dia.day) {
            reservaLabel.setText("Aluguel imeadiato");
            grupoSelect.setVisible(false);
            ouLabel.setVisible(false);
            grupoLabel.setVisible(false);
            veiculoSelect.setPrefWidth(veiculoSelect.getWidth() + (grupoSelect.getLayoutX() - veiculoSelect.getLayoutX()));
        }*/


        try {
            entregaField.setValue(r.getEntrega().toLocalDate());
            for (Filial f : Filial.all()) {
                filialSelect.getItems().add(f);
                if (f.id == r.getFilial_id()) {
                    filialSelect.setValue(f);
                }
            }

            // TODO - mostrar apenas veiculos disponiveis
            // veiculos disponiveis sao aqueles que nao estao reservados na data preenchida (campo disabilitado antes do preenchimento da data)
            for (Veiculo v : Veiculo.all()) {
                if( v.placa.equals(r.getVeiculo_id()) ){
                    veiculoSelect.setValue(v);
                } veiculoSelect.getItems().add(v);
            }

            for (Grupo g : Grupo.all()) {
                if(g.id == r.getGrupo_id()) {
                    grupoSelect.setValue(g);
                } grupoSelect.getItems().add(g);
            }

            for (Cliente c : Cliente.all()) {
                if (c.cpf.equals(r.getCliente_id())) {
                    clienteSelect.setValue(c);
                } clienteSelect.getItems().add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*
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
*/
    public void editarReserva(ActionEvent event) {
      //  Date retirada = null;
        Date entrega = null;
        try {
            // TODO (BUG) - data esta voltando um dia do selecionado
        //    retirada = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(retiradaField.getText()).getTime());
//            entrega = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(entregaField.getText()).getTime());
            entrega = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(entregaField.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).getTime());

        } catch (ParseException e) {
            e.printStackTrace();
            // TODO mensagem de erro por causa do formato da data
        }
       // reserva.setRetirada(retirada);
        reserva.setEntrega(entrega);
        reserva.setFilial(filialSelect.getValue());
        reserva.setCliente(clienteSelect.getValue());
        reserva.setVeiculo(veiculoSelect.getValue());
        reserva.setGrupo(grupoSelect.getValue());

        try {
            reserva.update();
            Navigate.to(this.getClass(), "reserva/listagem/reserva.listagem.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
