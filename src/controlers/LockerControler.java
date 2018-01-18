package controlers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LockerControler implements Initializable {

    @FXML
    private JFXTextField lockerName;
    @FXML
    private Label nbWhite;
    @FXML
    private Label nbRed;
    @FXML
    private Label nbRose;
    @FXML
    private HBox hboxLocker;

    /*public LockerControler(String lockerName) {
        this.lockerName.setText(lockerName);
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");
    }
}
