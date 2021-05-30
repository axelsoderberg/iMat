package iMat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class helpController extends AnchorPane {

    @FXML AnchorPane QAAnchorpane;
    @FXML AnchorPane helpAnchorpane;
    @FXML AnchorPane helpPane;
    @FXML StackPane stackPane;
    @FXML AnchorPane guidePane;

    @FXML private Button guide;
    @FXML private Button nextButton;
    @FXML private Button prevButton;
    @FXML private Button resetButton;
    @FXML private Button exitButton;
    @FXML private ImageView g1;
    @FXML private ImageView g2;
    @FXML private ImageView g3;
    @FXML private ImageView g4;
    @FXML private ImageView g5;
    @FXML private ImageView g6;
    @FXML private ImageView g7;
    @FXML private ImageView g8;
    @FXML private ImageView g9;
    @FXML private ImageView g10;
    @FXML private ImageView g11;
    @FXML private ImageView g12;
    @FXML private AnchorPane guideControl;
    @FXML private Rectangle blackBox;


    private int guideIndex = 1;

    public helpController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

    @FXML
    public void closeQA() {
        helpAnchorpane.toFront();
    }

    @FXML
    public void openQA() {
        QAAnchorpane.toFront();
    }

    @FXML
    public void startGuide() {
        helpPane.toFront();
        guidePane.toFront();
        loadPage(1);
    }

    @FXML
    private void nextPage() {
        if (guideIndex < 12)
            loadPage(++guideIndex);
    }

    @FXML
    private void reset() {
        loadPage(1);
    }

    @FXML
    private void exitGuide() {
        guidePane.toBack();
        guideControl.toBack();
        iMatController.getHeader().openHelpView();

    }

    @FXML
    private void prevPage() {
        if (guideIndex > 1)
            loadPage(--guideIndex);
    }

    private void loadPage(int i) {
        switch (i) {
            case 1 -> g1.toFront();
            case 2 -> g2.toFront();
            case 3 -> g3.toFront();
            case 4 -> g4.toFront();
            case 5 -> g5.toFront();
            case 6 -> g6.toFront();
            case 7 -> g7.toFront();
            case 8 -> g8.toFront();
            case 9 -> g9.toFront();
            case 10 -> g10.toFront();
            case 11 -> g11.toFront();
            case 12 -> g12.toFront();
        }
        guideControl.toFront();
    }

}
