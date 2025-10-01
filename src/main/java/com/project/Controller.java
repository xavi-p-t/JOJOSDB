package com.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

    
    @FXML
    private AnchorPane container;
    @FXML
    private VBox yPane = new VBox();
    @FXML
    private ChoiceBox<String> choice;

    String options[] = { "JOJO's", "Villanos", "Stands" };

    private JSONArray jsonInfo;

    

    // Called when the FXML file is loaded
    @Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        // recurso del template (lo usas en setFXML)
        URL resource = this.getClass().getResource("/assets/listItem.fxml");

        // poblar ChoiceBox
        choice.getItems().addAll(options);
        choice.setValue(options[0]);

        // cargar json inicial y pintar la lista
        loadJsonForChoice(choice.getValue());
        setFXML(null);

        // listener: cuando cambie la selección, carga nuevo JSON y refresca la vista
        choice.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) return;
            try {
                loadJsonForChoice(newVal);
                setFXML(null);
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
            opc = "/assets/games.json"; 
            break;
        case "Stands": 
            opc = "/assets/consoles.json"; 
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

    

    @FXML
    private void setFXML(ActionEvent event) throws Exception {

        // Obtenir el recurs del template .fxml
        URL resource = this.getClass().getResource("/assets/listItem.fxml");

        URL resourceInf = this.getClass().getResource("/assets/infoLayout.fxml");
        // Esborrar la llista anterior
        yPane.getChildren().clear();

        // Generar la nova llista a partir de 'jsonInfo'
        for (int i = 0; i < jsonInfo.length(); i++) {
            // Obtenir l'objecte JSON individual (animal)
            JSONObject animal = jsonInfo.getJSONObject(i);

            // Extreure la informació necessària del JSON
            
            String name = animal.getString("name");
            String image = animal.getString("image");

            // Carregar el template de 'listItem.fxml'
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();

            FXMLLoader loaderInf = new FXMLLoader(resourceInf);
            Parent itemTemplateInf = loaderInf.load();
            ControllerInfoLayout infController = loaderInf.getController();



            // Assignar els valors als controls del template
            itemController.setTitle(name);
            
            

            itemController.setImatge("/assets/images/" + image);

            infController.setDesc(image);
            infController.setImatge("/assets/images/" + image);
            

            // Afegir el nou element a 'yPane'
            yPane.getChildren().add(itemTemplate);
        }
    }

}