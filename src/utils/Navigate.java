package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {

    private static Stage stage;

    public static void to(Class classe, String to, String title) throws IOException {
        Parent view = FXMLLoader.load(classe.getResource("/componentes/" + to));
        Scene scene = new Scene(view, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setTitle(title);
        stage.setScene(scene);
    }

    public static void to(Class classe, String to) throws IOException {
        Parent view = FXMLLoader.load(classe.getResource("/componentes/" + to));
        Scene scene = new Scene(view, stage.getScene().getWidth(), stage.getScene().getHeight());

        stage.setScene(scene);
    }

    public static void setStage(Stage stg) {
        Navigate.stage = stg;
    }

}
