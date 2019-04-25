package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigate {

    public interface NavInit {
        void init(Object... params);
    }

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

    public static void to(Class classe, String to, Object... params) throws IOException {
        FXMLLoader loader = new FXMLLoader(classe.getResource("/componentes/" + to));
        Scene scene = new Scene(loader.load(), stage.getScene().getWidth(), stage.getScene().getHeight());

        stage.setScene(scene);

        NavInit controller = loader.getController();
        controller.init(params);
    }

    public static void setStage(Stage stg) {
        Navigate.stage = stg;
    }

}
