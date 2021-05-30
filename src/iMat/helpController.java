package iMat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class helpController extends AnchorPane {

    @FXML AnchorPane QAAnchorpane;
    @FXML AnchorPane helpAnchorpane;
    @FXML private Button guide;

    @FXML AnchorPane g1;
    @FXML AnchorPane g2;
    @FXML AnchorPane g3;
    @FXML AnchorPane g4;
    @FXML AnchorPane g5;
    @FXML AnchorPane g6;
    @FXML AnchorPane g7;
    @FXML AnchorPane g8;
    @FXML AnchorPane g9;
    @FXML AnchorPane g10;
    @FXML AnchorPane g11;
    @FXML AnchorPane g12;
    @FXML AnchorPane g13;
    @FXML AnchorPane g14;
    @FXML AnchorPane g15;
    @FXML AnchorPane g16;


    private int guideIndex = 0;

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
        loadPage(1);

    }

    private void nextPage() {
        loadPage(++guideIndex);
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
            case 13 -> g13.toFront();
            case 14 -> g14.toFront();
            case 15 -> g15.toFront();
            case 16 -> g16.toFront();
        }
    }

}
