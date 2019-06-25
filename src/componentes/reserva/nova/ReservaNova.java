package componentes.reserva.nova;

import componentes.reserva.listagem.ReservaListagem.Day;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.Callback;
import models.*;
import utils.FormattedField;
import utils.Navigate;
import utils.Navigate.NavInit;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReservaNova implements NavInit {

    public class MotoristaCell extends TableCell<String, String> {

        Field field;
        FormattedField formattedField;

        public MotoristaCell(String fieldName) throws NoSuchFieldException {
            this.field = Motorista.class.getDeclaredField(fieldName);
            this.formattedField = new FormattedField();
            this.formattedField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    this.updateItem(this.getItem(), this.isEmpty());
                }
            });
        }

        public MotoristaCell(String fieldName, String mask) throws NoSuchFieldException {
            this.field = Motorista.class.getDeclaredField(fieldName);
            this.formattedField = new FormattedField(mask);
            this.formattedField.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    this.updateItem(this.getItem(), this.isEmpty());
                }
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();
            this.updateItem(getItem(), isEmpty());
        }

        @Override
        public void commitEdit(String newValue) {
            super.commitEdit(newValue);
            this.updateItem(newValue, isEmpty());

            System.out.println(this.getIndex());
            if (this.field.getName() == "cpf") {
                if (this.getTableView().getItems().size() <= this.getIndex() + 1) {
//                    this.getTableView().getItems().add(new Motorista());
                    this.getTableView().getItems().add(null);
                }
                if (this.formattedField.getPlainText().length() != 11) {
                    System.out.println("cpf inválido");
                }
                else {
                    String cpf = this.formattedField.getPlainText();
                    try {
                        Cliente cliente = Cliente.findById(cpf);
                        if (cliente != null) {
                            System.out.println(cliente);
                        }
                        else {
                            System.out.println("cliente não encontrado");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            this.updateItem(getItem(), isEmpty());
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(this.formattedField.getText());
//            try {
//                this.field.set(this.getTableView().getItems().get(this.getIndex()), this.formattedField.getPlainText());
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            if (isEditing()) {
                setGraphic(this.formattedField);
            }
            else {
                setGraphic(null);
            }
        }
    }

    public class MotoristaTableCell implements Callback<TableColumn<String, String>, TableCell<String, String>> {

        String fieldName;
        String mask;

        public MotoristaTableCell(String fieldName, String mask) {
            this.fieldName = fieldName;
            this.mask = mask;
        }

        public MotoristaTableCell(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public TableCell<String, String> call(TableColumn<String, String> param) {
            try {
                if (mask != null) {
                    return new MotoristaCell(this.fieldName, this.mask);
                }
                return new MotoristaCell(this.fieldName);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    public class MotoristaCellValueFactory implements Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>> {
        @Override public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> features) {
            return new ReadOnlyObjectWrapper(features.getValue());
        }
    }

    @FXML Label reservaLabel;
    @FXML Label grupoLabel;
    @FXML Label ouLabel;
    @FXML FormattedField retiradaField;
    @FXML DatePicker entregaField;
    @FXML ChoiceBox<Filial> filialSelect;
    @FXML ChoiceBox<Veiculo> veiculoSelect;
    @FXML ChoiceBox<Grupo> grupoSelect;
    @FXML ChoiceBox<Cliente> clienteSelect;
    @FXML TableView<Motorista> motoristasTable;
    @FXML TableColumn<String, String> cpfCol;
    @FXML TableColumn nomeCol;
    @FXML TableColumn cnhCol;
    @FXML TableColumn nascimentoCol;
    @FXML TableColumn acoesCol;

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

        motoristasTable.setEditable(true);
        motoristasTable.getItems().add(null);
        cpfCol.setCellValueFactory(new MotoristaCellValueFactory());
        cpfCol.setCellFactory(new MotoristaTableCell("cpf", "###.###.###-##"));

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
