package componentes.veiculo.edicao;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Veiculo;

public class VeiculoEdicao {

    @FXML Label modeloLabel;
    @FXML Label marcaLabel;
    Veiculo veiculo;



    public void init(Veiculo v) {
        this.veiculo = v;
        modeloLabel.setText(v.modelo.nome);
    }

}
