package controlers;

import classes.Cave;
import classes.Locker;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import util.CellariumUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapfoursquare.ParserFoursquare;

public class MainPanelControler implements Initializable {

    @FXML
    public Label ficheBouteilles;
    public Label region;
    public Label appellationtype;
    public Label appellation;
    public Label couleur;
    public Label cepages;
    public Label garde;
    public Label temp;
    public JFXMasonryPane mansoryPane;

    @FXML
    private JFXListView<String> listView;

    @FXML
    private FontAwesomeIconView hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView imgVin;

    @FXML
    private Label lblRegion;

    @FXML
    private Label lblAppellationType;

    @FXML
    private Label lblAppellation;

    @FXML
    private Label lblCouleur;

    @FXML
    private Label lblCepages;

    @FXML
    private Label lblGarde;

    @FXML
    private Label lblTempService;

    @FXML
    private VBox vboxCasiers;

    @FXML
    private HBox hbox1;

    @FXML
    private HBox hbox2;

    @FXML
    private HBox hBox3;

    @FXML
    private StackPane stackPane;

    /*private static Bottle bottle1 = new Bottle("Château Le Mayne", "Bordeau", "AOC", "Lalande de Pomerol", "Blanc", "Merlot, Cabernet Franc", 10, 17.0);
    private static Bottle bottle2 = new Bottle("Château Micardis", "Region 2", "AOC", "Appellation 2", "Rouge", "Merlot", 11, 18.0);*/

    private static ObservableList<String> listVins = FXCollections.observableArrayList("Château Le Mayne", "Château Micardis", "La Cave d'Augustin Florent", "Château l'Hirondelle", "Château Haut-Moulin", "Charlemagne", "Chanturgue", "Châteaugay");
    private static ObservableList<String> listNomBouteilles = FXCollections.observableArrayList();

    private static int nbNodes = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Cave cave = new Cave();

        /*Cave.ajouterBouteille(bottle1);
        Cave.ajouterBouteille(bottle2);*/

        listView.setItems(Cave.getListNomBouteilles());

        try {
            AnchorPane boxMaCave = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/drawer.fxml")));
            drawer.setSidePane(boxMaCave);

        } catch (IOException ex) {
            Logger.getLogger(MainPanelControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void afficherFiche(MouseEvent event) {

        /*String selectedItem = listView.getSelectionModel().getSelectedItem();

        for (int i = 0; i < Cave.getListBottles().size(); i++) {
            if (Cave.getListBottles().get(i).getNom().equals(selectedItem)) {
                tempBottle = Cave.getListBottles().get(i);
                break;
            }
        }

        changerInfos(tempBottle.getRegion(), tempBottle.getAppellationType(), tempBottle.getAppelation(), tempBottle.getCouleur(), tempBottle.getCepages(), tempBottle.getGarde(), tempBottle.getTemperatureService());*/
    }

    @FXML
    void openConnexion(MouseEvent event) throws IOException {
        CellariumUtil.createNewStage("/fxml/login.fxml", "Connexion", false);
    }

    private void changerInfos(String region, String appelationType, String appelation, String couleur, String cepages, int garde, double tempService) {
        lblRegion.setText(region);
        lblAppellationType.setText(appelationType);
        lblAppellation.setText(appelation);
        lblCouleur.setText(couleur);
        lblCepages.setText(cepages);
        lblGarde.setText(garde + " ans");
        lblTempService.setText(tempService + "°C");
    }

    public void openDrawer(MouseEvent event) {
        /*HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);*/



        if (drawer.isShown()) {
            drawer.close();
            drawer.toBack();

            /*hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
            });*/

        } else {
            drawer.toFront();
            drawer.open();
        }
    }

    @FXML
    private void settings(MouseEvent e) throws IOException {

        CellariumUtil.createNewStage("/fxml/settings.fxml", "Parametres", false);
    }

    public void testAddCasier(ActionEvent actionEvent) throws IOException {
        if(nbNodes == 10) {
            CellariumUtil.displayJFXDialog(stackPane, "Attention !", "Vous ne pouvez plus ajouter de casier.", "Okay");
        } else {
            mansoryPane.getChildren().add(new Locker());
            nbNodes++;
        }
    }

    public void testCamera(ActionEvent actionEvent) throws IOException {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null)
        {
            CellariumUtil.displayJFXDialog(stackPane, "Attention !", "Veuillez brancher une webcam.", "Okay");
        }
        else
        {
            CellariumUtil.createNewStage("/fxml/cameraPreview.fxml", "Previsualisation camera", false);

            /*webcam.open();
            BarcodeReaderWebcam reader = new BarcodeReaderWebcam(webcam);

            System.out.println(reader.readBarcodeSafe(true));
            webcam.close();*/
        }
    }

    public void testGeolocalisation(ActionEvent actionEvent) throws IOException, GeoIp2Exception {
        CellariumUtil.createNewStage("/fxml/map.fxml", "Cave aux alentours", false);
    }

    public void testAddBottleLocker(ActionEvent actionEvent) {

    }
}
