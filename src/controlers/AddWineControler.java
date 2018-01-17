package controlers;

import classes.Attribut;
import classes.Couleurs;
import classes.Wine;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import org.controlsfx.control.textfield.TextFields;
import util.CellariumUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddWineControler implements Initializable{

    public JFXComboBox fieldApp;
    public JFXTextField fieldDegre;
    @FXML
    private JFXButton btnLinkBottle;

    @FXML
    private JFXTextField displayBarcode;

    @FXML
    private JFXTextField fieldDomaine;

    @FXML
    private JFXComboBox fieldRegion;

    @FXML
    private JFXComboBox fieldAppellation;

    @FXML
    private JFXComboBox fieldCouleur;

    @FXML
    private JFXTextField fieldCepages;

    @FXML
    private JFXButton buttonValider;

    @FXML
    private Label lblStatus;

    @FXML
    private StackPane stackPaneAddBottle;

    private static String barcode;

    @FXML
    void addWine(ActionEvent event) {
        if (verifChamps()) {
            Wine w = new Wine(
                    fieldDomaine.getText(),
                    fieldAppellation.getValue().toString(),
                    fieldCouleur.getValue().toString(),
                    fieldCepages.getText(),
                    Float.parseFloat(fieldDegre.getText())
            );

            afficherLabel("#388e3c", "La bouteille a bien été ajoutée");

            resetFields(new ActionEvent());
        } else {
            afficherLabel("#d32f2f", "Veuillez remplir tous les champs");
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        fieldDomaine.setText(null);
        fieldRegion.setValue(null);
        fieldApp.setValue(null);
        fieldApp.setDisable(true);
        fieldCouleur.setValue(null);
        fieldCepages.setText(null);
        fieldDegre.setText(null);
    }

    private boolean verifChamps() {
        return !(fieldDomaine.getText().isEmpty()
                || fieldRegion.getValue() == null
                || fieldAppellation.getValue() == null
                || fieldCouleur.getValue() == null
                || fieldCepages.getText() == null);
    }

    private void afficherLabel(String color, String message) {
        lblStatus.setAlignment(Pos.CENTER);
        lblStatus.setTextFill(Paint.valueOf(color));
        lblStatus.setText(message);
        lblStatus.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            System.out.println(Attribut.getAppFromReg("Bordeaux").get(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] possibleAppellation = new String[0];
        String[] possibleCepages = new String[0];
        String[] possibleDomaines = new String[0];
        try {
            possibleAppellation = Attribut.getAppellationSet().toArray(possibleAppellation);
            possibleCepages = Attribut.getCepageSet().toArray(possibleCepages);
            possibleDomaines = Attribut.getDomaineSet().toArray(possibleDomaines);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CellariumUtil.sortClearBOM(possibleAppellation);
        CellariumUtil.sortClearBOM(possibleCepages);
        //TextFields.bindAutoCompletion(fieldAppellation, possibleAppellation);
        TextFields.bindAutoCompletion(fieldCepages, possibleCepages);
        TextFields.bindAutoCompletion(fieldDomaine, possibleDomaines);

        fieldCouleur.getItems().addAll(Couleurs.SORTED_STRING_ARRAY);
        try {
            fieldRegion.getItems().addAll(Attribut.getRegionSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAppellation(ActionEvent actionEvent) throws SQLException {
        String temp = fieldRegion.getValue().toString();
        fieldApp.getItems().clear();
        fieldApp.getItems().addAll(CellariumUtil.sortClearBOM(Attribut.getAppFromReg(temp).toArray(new String[0])));
        fieldApp.disableProperty().setValue(false);

    }


    /*public void setBarcode(String barcode) {
        displayBarcode.setPromptText(barcode);
    }

    public void linkBottleBarCode(ActionEvent actionEvent) {
        if(barcode == null) {
            CellariumUtil.displayJFXDialog(stackPaneAddBottle, "Attention !", "Aucune webcam n'est détectée. Veuillez en brancher une.", "Okay");
        } else {
            CellariumUtil.createWebcamCaptureStage(this);
        }
    }*/
}
