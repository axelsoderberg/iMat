package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
        fxmlLoader.setController(new StartController());
        Parent root = fxmlLoader.load();

        //Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"), bundle);

        primaryStage.setTitle(bundle.getString("application.name"));
        primaryStage.setScene(new Scene(root, 1536, 864));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
