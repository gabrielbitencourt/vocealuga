package componentes.reserva.listagem.cell;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.beans.value.ObservableValue;
import utils.Navigate;
import models.Reserva;
import models.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ArrayList;

public class DiaCell {

    Day dia;

    @FXML Label diaLabel;
    @FXML Button novaBtn;
    @FXML MenuButton retiradasBtn;

    ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    Reserva r = null;

    public void init(Day dia) {
        this.dia = dia;
        this.reservas = this.dia.reservas;
        this.diaLabel.setText(this.dia.day + "");
        if (this.dia.reservas != null && this.dia.reservas.size() > 0) {
            this.retiradasBtn.setText(this.dia.reservas.size() + " retiradas");
            this.retiradasBtn.setVisible(true);
        }
        else {
            this.retiradasBtn.setVisible(false);
        }

        for (Reserva r : this.dia.reservas) {
            MenuItem i = null;
            try {
                i = new MenuItem(Cliente.findById(r.toString()).toString());
                i.setOnAction(event -> {
                    try {
                        Navigate.to(this.getClass(), "reserva/edicao/reserva.edicao.fxml", r, dia);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.retiradasBtn.getItems().add(i);
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, dia.year);
        cal.set(Calendar.MONTH, dia.month);
        cal.set(Calendar.DAY_OF_MONTH, dia.day + 1);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (System.currentTimeMillis() >= cal.getTimeInMillis()) {
            novaBtn.setDisable(true);
        }
        else {
            novaBtn.setDisable(false);
        }
    }

  /*  public void filtrarReservas(String newValue){
        ArrayList <Reserva> filtered = new ArrayList<Reserva>(reservas);

        if (newValue.length() > 0) {
            filtered.removeIf(reserva -> {
                return !reserva.toString().contains(newValue);
            });
            System.out.println(filtered.toString());
        }
        this.retiradasBtn.getItems().clear();
        for (Reserva r : this.reservas) {
            MenuItem i = null;
            try {
                i = new MenuItem(Cliente.findById(r.toString()).toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.retiradasBtn.getItems().add(i);
        }
    }*/

    public void novaReserva(ActionEvent event) {
        try {
            Navigate.to(this.getClass(), "reserva/nova/reserva.nova.fxml", dia);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editarReserva(ActionEvent event) {
        Object r = event.getSource();
        try {
            Navigate.to(this.getClass(), "reserva/edicao/reserva.edicao.fxml", dia, r);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
