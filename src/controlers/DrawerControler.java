package controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DrawerControler {

    @FXML
    public void openAddBottleBDD(MouseEvent mouseEvent) throws IOException {
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/addWine.fxml"));
        primaryStage.setTitle("Connexion");
        primaryStage.setScene(new Scene(root, AnchorPane.USE_COMPUTED_SIZE, AnchorPane.USE_COMPUTED_SIZE));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
