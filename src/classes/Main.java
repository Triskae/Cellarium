package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class Main extends Application {

    private Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/mainPanel.fxml")));
        primaryStage.setTitle("Cellarium");
        //primaryStage.setScene(new Scene(root, visualBounds.getWidth() , visualBounds.getHeight()));
        primaryStage.setScene(new Scene(root, 1300 , 900));
        primaryStage.setMaximized(false);
        primaryStage.setMinWidth(1300);
        primaryStage.setMinHeight(900);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("res/img/iconPanneauPrincipal.png"));

    }*/

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/mainPanel.fxml")));
        primaryStage.setTitle("Cellarium");
        primaryStage.setScene(new Scene(root, 1300, 900));
        //primaryStage.setScene(new Scene(root, AnchorPane.USE_COMPUTED_SIZE, AnchorPane.USE_COMPUTED_SIZE));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("res/img/iconPanneauPrincipal.png"));
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}