package componentes.reserva.listagem;

import componentes.reserva.listagem.cell.DiaCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import models.Reserva;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class ReservaListagem implements Initializable {

	Calendar cal = Calendar.getInstance();
	Map<Integer, Map<Integer, Map<Integer, ArrayList<Reserva>>>> reservas;
	{
		try {
			reservas = Reserva.all();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML TableView monthTable;
	@FXML TableColumn<Week, Week> domingoCol;
	@FXML TableColumn<Week, Week> segundaCol;
	@FXML TableColumn<Week, Week> tercaCol;
	@FXML TableColumn<Week, Week> quartaCol;
	@FXML TableColumn<Week, Week> quintaCol;
	@FXML TableColumn<Week, Week> sextaCol;
	@FXML TableColumn<Week, Week> sabadoCol;

	@FXML Label mesLabel;

	public class Day {
		public int weekday;
		public int day;
		public int month;
		public int year;
		public ArrayList<Reserva> reservas;

		public Day(int week, int day, int month, int year, ArrayList<Reserva> reservas) {
			this.weekday = week;
			this.day = day;
			this.month = month;
			this.year = year;
			this.reservas = reservas;
		}

		@Override
		public String toString() {
			return this.day + "/" + (this.month + 1) + "/" + this.year;
		}
	}
	public class Week {

		Map<Integer, Day> days = new HashMap<>();

		public Week(ArrayList<Day> arr) {
			for (Day day : arr) {
				days.put(day.weekday, day);
			}
		}
	}

	public class DayCellFactory implements Callback<TableColumn<Week, Week>, TableCell<Week, Week>> {

		int day;

		public DayCellFactory(int day) {
			this.day = day;
		}

		@Override
		public TableCell<Week, Week> call(TableColumn<Week, Week> col) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("./cell/dia.cell.fxml"));
			try {
				final Node node = loader.load();
				return new TableCell<Week, Week>() {

					@Override
					public void updateItem(final Week week, boolean empty) {
						if (week != null && week.days != null && week.days.get(day) != null) {
							super.updateItem(week, false);

							setGraphic(node);
							DiaCell controller = loader.getController();
							controller.init(week.days.get(day));

						} else {
							super.updateItem(week, true);
							setGraphic(null);
						}
					}
				};

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	}
	public class DayCellValueFactory implements Callback<CellDataFeatures<Week, Week>, ObservableValue<Week>> {
		@Override public ObservableValue<Week> call(CellDataFeatures<Week, Week> features) {
			return new ReadOnlyObjectWrapper(features.getValue());
		}
	}

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
//		try {
//			reservas = Reserva.all();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		monthTable.getSelectionModel().setCellSelectionEnabled(true);

		domingoCol.setCellValueFactory(new DayCellValueFactory());
		segundaCol.setCellValueFactory(new DayCellValueFactory());
		tercaCol.setCellValueFactory(new DayCellValueFactory());
		quartaCol.setCellValueFactory(new DayCellValueFactory());
		quintaCol.setCellValueFactory(new DayCellValueFactory());
		sextaCol.setCellValueFactory(new DayCellValueFactory());
		sabadoCol.setCellValueFactory(new DayCellValueFactory());

		domingoCol.setCellFactory(new DayCellFactory(1));
		segundaCol.setCellFactory(new DayCellFactory(2));
		tercaCol.setCellFactory(new DayCellFactory(3));
		quartaCol.setCellFactory(new DayCellFactory(4));
		quintaCol.setCellFactory(new DayCellFactory(5));
		sextaCol.setCellFactory(new DayCellFactory(6));
		sabadoCol.setCellFactory(new DayCellFactory(7));

		Date current = new Date(System.currentTimeMillis());
		cal.setTime(current);
		cal.setMinimalDaysInFirstWeek(1);
		this.initMes();
	}

	public void initMes() {
		monthTable.getItems().clear();
		mesLabel.setText(cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.YEAR));

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);

		while (cal.get(Calendar.MONTH) == month) {

			ArrayList<Day> daysOfWeek = new ArrayList<>();

			while (true) {
				ArrayList<Reserva> reservasDoDia = new ArrayList<>();
				if (reservas.containsKey(cal.get(Calendar.YEAR)) && reservas.get(cal.get(Calendar.YEAR)).containsKey(cal.get(Calendar.MONTH)) && reservas.get(cal.get(Calendar.YEAR)).get(cal.get(Calendar.MONTH)).containsKey(cal.get(Calendar.DAY_OF_MONTH))) {
					reservasDoDia = reservas.get(cal.get(Calendar.YEAR)).get(cal.get(Calendar.MONTH)).get(cal.get(Calendar.DAY_OF_MONTH));
				}


				daysOfWeek.add(new Day(cal.get(Calendar.DAY_OF_WEEK), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), reservasDoDia));
				cal.add(Calendar.DAY_OF_MONTH, 1);
				if (cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.MONTH) > month) {
					break;
				}
			}

			Week weekData = new Week(daysOfWeek);
			monthTable.getItems().add(weekData);

		}
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
	}

	public void nextMes(ActionEvent e) {
		cal.add(Calendar.MONTH, 1);
		this.initMes();
	}

	public void prevMes(ActionEvent e) {
		cal.add(Calendar.MONTH, -1);
		this.initMes();
	}

}
