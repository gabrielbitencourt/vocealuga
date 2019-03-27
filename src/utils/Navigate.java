package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {

    public static void to(ActionEvent event, Class classe, String to, String title) throws IOException {
        Parent view = FXMLLoader.load(classe.getResource("/views/" + to));
        Scene scene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(scene);
        window.show();
    }

    public static void to(ActionEvent event, Class classe, String to) throws IOException {
        Parent view = FXMLLoader.load(classe.getResource("/views/" + to));
        Scene scene = new Scene(view);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
