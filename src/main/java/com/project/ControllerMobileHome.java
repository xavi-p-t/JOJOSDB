package com.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMobileHome implements Initializable {

    @FXML private VBox yPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            addSection("JOJO's", "/assets/images/jojo.png", "/assets/characters.json");
            addSection("Villanos", "/assets/images/villanos.png", "/assets/consoles.json");
            addSection("Stands", "/assets/images/stands.png", "/assets/games.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(javafx.event.ActionEvent event) {
        UtilsViews.setView("Mobile"); 
    }

    private void addSection(String title, String imagePath, String jsonPath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/listItem.fxml"));
        Parent item = loader.load();
        ControllerListItem itemCtrl = loader.getController();
        itemCtrl.setTitle(title);
        itemCtrl.setImatge(imagePath);

        item.setOnMouseClicked(e -> {
            try {
                ControllerMobileList listCtrl = (ControllerMobileList) UtilsViews.getController("MobileList");
                listCtrl.loadJson(jsonPath);  
                listCtrl.setFXML();           

                UtilsViews.setView("MobileList");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        yPane.getChildren().add(item);
    }
}
