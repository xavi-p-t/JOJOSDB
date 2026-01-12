package com.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerMobileHome {

    @FXML private VBox yPane;
    @FXML private StackPane rootPane; // contenedor principal

    @FXML
    public void initialize() {
        // Crear tres ítems como en la lista de desktop
        createItem("JOJO's", "/assets/characters.json");
        createItem("Villanos", "/assets/consoles.json");
        createItem("Stands", "/assets/games.json");
    }

    private void createItem(String title, String jsonFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/listItem.fxml"));
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();
            itemController.setTitle(title);
            itemController.setImatge("/assets/images/icon_section.png"); // icono genérico

            // Al pulsar, cargar la vista de lista
            itemTemplate.setOnMouseClicked(e -> {
                try {
                    UtilsViews.setViewAnimating("MobileList");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            yPane.getChildren().add(itemTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
