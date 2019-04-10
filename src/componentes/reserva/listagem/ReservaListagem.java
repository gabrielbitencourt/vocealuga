package componentes.reserva.listagem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.net.URL;
import java.sql.Date;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ReservaListagem implements Initializable {

	Calendar cal = Calendar.getInstance();

	@FXML TableView monthTable;
	@FXML TableColumn domingoCol;
	@FXML TableColumn segundaCol;
	@FXML TableColumn tercaCol;
	@FXML TableColumn quartaCol;
	@FXML TableColumn quintaCol;
	@FXML TableColumn sextaCol;
	@FXML TableColumn sabadoCol;

	@FXML Label mesLabel;

	public class Day {
		int weekday;
		int monthday;

		public Day(int week, int month) {
			this.monthday = month;
			this.weekday = week;
		}

		@Override
		public String toString() {
			return this.monthday + "";
		}
	}

	public class Week {

		ArrayList<Day> days = new ArrayList<Day>();

		public Week(ArrayList<Day> arr) {
			for (Day day : arr) {
				days.add(day);
			}
		}

		public String getDom() {
			return days.get(0).toString();
		}

		public String getSeg() {
			return days.get(1) != null ? days.get(1).toString() : "la";
		}

		public String getTer() {
			return days.get(2).toString();
		}

		public String getQua() {
			return days.get(3).toString();
		}

		public String getQui() {
			return days.get(4).toString();
		}

		public String getSex() {
			return days.get(5).toString();
		}

		public String getSab() {
			return days.get(6).toString();
		}
	}

	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		domingoCol.setCellValueFactory(new PropertyValueFactory<Week, String>("dom"));
		segundaCol.setCellValueFactory(new PropertyValueFactory<Week, String>("seg"));
		tercaCol.setCellValueFactory(new PropertyValueFactory<Week, String>("ter"));
		quartaCol.setCellValueFactory(new PropertyValueFactory<Week, String>("qua"));
		quintaCol.setCellValueFactory(new PropertyValueFactory<Week, String>("qui"));
		sextaCol.setCellValueFactory(new PropertyValueFactory<Week, String>("sex"));
		sabadoCol.setCellValueFactory(new PropertyValueFactory<Week, String>("sab"));

		Date current = new Date(System.currentTimeMillis());
		cal.setTime(current);
		this.initMes();
	}

	public void initMes() {
		System.out.println(cal);

		monthTable.getItems().clear();
		mesLabel.setText(Month.of(cal.get(Calendar.MONTH) + 1).name());

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int month = cal.get(Calendar.MONTH);

		cal.set(Calendar.DAY_OF_WEEK, 1);
		while (cal.get(Calendar.MONTH) <= month) {

			ArrayList<Day> daysOfWeek = new ArrayList<>();

			int week = cal.get(Calendar.WEEK_OF_YEAR);
			while (cal.get(Calendar.WEEK_OF_YEAR) == week) {

				int weekDay = cal.get(Calendar.DAY_OF_WEEK);
				int monthDay = cal.get(Calendar.DAY_OF_MONTH);

				daysOfWeek.add(new Day(weekDay, monthDay));
				cal.add(Calendar.DAY_OF_MONTH, 1);

			}

			Week weekData = new Week(daysOfWeek);
			monthTable.getItems().add(weekData);

		}
		System.out.println(cal);
		cal.add(Calendar.DAY_OF_YEAR, -28);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(cal);

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
