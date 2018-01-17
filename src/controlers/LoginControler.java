package controlers;

import bdd.Connexion;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;
import classes.User;

import static util.CellariumUtil.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LoginControler {

    private static int status = 0;
    @FXML
    private Label lblStatus;
    @FXML
    private JFXTextField textUsr;
    @FXML
    private JFXPasswordField textPwd;

    public static int getStatus() {
        return status;
    }

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        Boolean tmp = User.login(textUsr.getText(), textPwd.getText());
        if (tmp) {
            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
            displayLabel(lblStatus, Pos.CENTER, Paint.valueOf("#388e3c"), "Connexion réussie");
            exec.schedule(new Runnable() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            createNewStageWithBaseSize("/fxml/mainPanel.fxml", "Cellarium", 1300, 900, true);
                            Node source = (Node) event.getSource();
                            Window theStage = source.getScene().getWindow();
                            theStage.hide();
                        }
                    });
                }
            }, 1, TimeUnit.SECONDS);


        } else {
            displayLabel(lblStatus, Pos.CENTER, Paint.valueOf("#d32f2f"), "L'e-mail ou le mot de passe est incorrect");
        }
    }

    @FXML
    public void addMember(ActionEvent ae) throws IOException {

        Node source = (Node) ae.getSource();
        Window theStage = source.getScene().getWindow();
        theStage.hide();

        createNewStage("/fxml/addMember.fxml", "Inscription", false);
    }
}
