package componentes.reserva.listagem.cell;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.Navigate;

import java.io.IOException;
import java.util.Calendar;

public class DiaCell {

    Day dia;

    @FXML Label diaLabel;
    @FXML Button novaBtn;
    @FXML Button retiradasBtn;

    public void init(Day dia) {
        this.dia = dia;
        this.diaLabel.setText(this.dia.day + "");
        if (this.dia.reservas != null && this.dia.reservas.size() > 0) {
            this.retiradasBtn.setText(this.dia.reservas.size() + " retiradas");
            this.retiradasBtn.setVisible(true);
        }
        else {
            this.retiradasBtn.setVisible(false);
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

    public void novaReserva(ActionEvent event) {
        try {
            Navigate.to(this.getClass(), "reserva/nova/reserva.nova.fxml", dia);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
