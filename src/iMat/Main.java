package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        /*ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMat.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        primaryStage.setTitle(bundle.getString("application.name"));
        primaryStage.setScene(new Scene(root, 1536, 864));
        primaryStage.show();*/

        /*Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"));
        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1536, 864));
        primaryStage.show();*/

        /*Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();*/

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        primaryStage.setTitle(bundle.getString("application.name"));
        primaryStage.setScene(new Scene(root, 1536, 864));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Model.getInstance().shutDown();
            }
        }));
    }
}
