package componentes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import utils.Navigate;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/componentes/login/login.fxml"));
            primaryStage.setTitle("VocêAluga - Login");
            primaryStage.getIcons().add(new Image("assets/icon.png"));
            primaryStage.setScene(new Scene(root));

            primaryStage.setMaximized(true);
            primaryStage.setMinHeight(600);
            primaryStage.setMinWidth(600);

            primaryStage.show();
            Navigate.setStage(primaryStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
