package componentes.cliente.edicao;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import models.Cliente;
import utils.FormattedField;
import utils.Navigate;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ClienteEdicao {

    Cliente cliente;
    TitledPane tp;

    @FXML TextField nomeField;
    @FXML TextField sobrenomeField;
    @FXML TextField enderecoField;
    @FXML FormattedField cpfField;
    @FXML FormattedField nascimentoField;
    @FXML TextField emailField;
    @FXML FormattedField celularField;

    public void toCadastroCliente(ActionEvent event) throws IOException {
        Navigate.to(this.getClass(), "cliente/cadastro/cliente.cadastro.fxml");
    }

    public void init(Cliente c, TitledPane tp) {
        this.cliente = c;
        this.tp = tp;

        nomeField.setText(c.nome);
        sobrenomeField.setText(c.sobrenome);
        enderecoField.setText(c.endereco);
        celularField.setPlainText(c.celular);
        cpfField.setPlainText(c.cpf);
        emailField.setText(c.email);

        String data = "";
        ArrayList<String> dataList = new ArrayList<>(Arrays.asList(c.nascimento.toString().split("-")));
        Collections.reverse(dataList);
        for (String d : dataList) {
            data = data + d;
        }

        nascimentoField.setPlainText(data);
    }

    public void atualizarCliente() {
        cliente.nome = this.nomeField.getText();
        cliente.sobrenome = this.sobrenomeField.getText();
        cliente.endereco = this.enderecoField.getText();
        cliente.celular = this.celularField.getPlainText();
        cliente.email = this.emailField.getText();
        try {
            cliente.update();
            tp.setText(cliente.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO mensagem de erro
        }
    }

    public void deletarCliente() {
        try {
            cliente.delete();
            Accordion ac = (Accordion) tp.getParent();
            ac.getPanes().remove(this.tp);


        } catch (Exception e) {
            e.printStackTrace();
            // TODO mostrar mensagem de erro (nunca deveria acontecer)
        }
    }

}
