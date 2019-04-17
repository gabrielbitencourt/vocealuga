package componentes.reserva.listagem.cell;

import componentes.reserva.listagem.ReservaListagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DiaCell {

    ReservaListagem.Day dia;

    @FXML Label diaLabel;

    public void init(ReservaListagem.Day dia) {
        this.dia = dia;
        this.diaLabel.setText(this.dia.day + "");
    }

    public void novaReserva(ActionEvent e) {
        System.out.println("botão " + dia.toString() + " clicado");
    }

}
