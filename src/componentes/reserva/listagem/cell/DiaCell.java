package componentes.reserva.listagem.cell;

import componentes.reserva.listagem.ReservaListagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.Navigate;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

public class DiaCell {

    ReservaListagem.Day dia;

    @FXML Label diaLabel;
    @FXML Button novaBtn;

    public void init(ReservaListagem.Day dia) {
        this.dia = dia;
        this.diaLabel.setText(this.dia.day + "");

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);

        if (currYear > dia.year || (currYear == dia.year && currMonth > dia.month) || (currYear == dia.year && currMonth == dia.month && cal.get(Calendar.DAY_OF_MONTH) > dia.day)) {
            novaBtn.setDisable(true);
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
