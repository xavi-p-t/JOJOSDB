package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

    @FXML
    private VBox yPane;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private AnchorPane infoPanel; // 👉 panel derecho

    String options[] = { "JOJO's", "Villanos", "Stands" };
    private JSONArray jsonInfo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            choice.getItems().addAll(options);
            choice.setValue(options[0]);

            loadJsonForChoice(choice.getValue());
            setFXML();

            choice.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal == null) return;
                try {
                    loadJsonForChoice(newVal);
                    setFXML();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadJsonForChoice(String option) throws Exception {
        String opc;
        switch (option) {
            case "JOJO's": 
                opc = "/assets/characters.json"; 
                break;
            case "Villanos": 
                opc = "/assets/consoles.json"; 
                break;
            case "Stands": 
                opc = "/assets/games.json"; 
                break;
            default: 
                opc = "/assets/characters.json"; 
                break;
        }

        try (java.io.InputStream in = getClass().getResourceAsStream(opc)) {
            if (in == null) throw new RuntimeException("Recurso no encontrado: " + opc);
            String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            jsonInfo = new JSONArray(content);
        }
    }

    private void setFXML() throws Exception {
        URL resource = this.getClass().getResource("/assets/listItem.fxml");
        URL resourceInf = this.getClass().getResource("/assets/infoLayout.fxml");

        yPane.getChildren().clear();

        for (int i = 0; i < jsonInfo.length(); i++) {
            JSONObject obj = jsonInfo.getJSONObject(i);

            String name = obj.getString("name");
            String image = obj.getString("image");
            String subtitle = obj.optString("subt", "");
            String description = obj.optString("inf", "Sin descripción");


            // 👉 cargar item de lista
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();
            itemController.setTitle(name);
            itemController.setImatge("/assets/images/" + image);

            // 👉 cargar layout de detalle
            FXMLLoader loaderInf = new FXMLLoader(resourceInf);
            Parent itemTemplateInf = loaderInf.load();
            ControllerInfoLayout infController = loaderInf.getController();
            infController.setName(name);
            infController.setDesc(description);
            infController.setSubtitle(subtitle);
            infController.setImatge("/assets/images/" + image);

            // 👉 evento de clic: mostrar detalle en panel derecho
            itemTemplate.setOnMouseClicked(e -> {
                infoPanel.getChildren().clear();
                infoPanel.getChildren().add(itemTemplateInf);
            });

            yPane.getChildren().add(itemTemplate);
        }
    }
}
