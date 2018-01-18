package classes;

import controlers.LockerControler;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;

public class Locker extends Pane {

    private LockerControler controller;

    public Locker() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/locker.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return controller = new LockerControler();
            }
        });
        Node view = (Node) fxmlLoader.load();
        getChildren().add(view);
    }

    /*public void setWelcome(String str) {
        controller.textField.setText(str);
    }

    public String getWelcome() {
        return controller.textField.getText();
    }

    public StringProperty welcomeProperty() {
        return controller.textField.textProperty();
    }*/
}
