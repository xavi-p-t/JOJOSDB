package com.project;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.Objects;

public class ControllerMobileDetail {

    @FXML private ImageView imgInf;
    @FXML private Text name;
    @FXML private Text subt;
    @FXML private Text desc;

    private String jsonPath; // para volver a la lista correcta

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setSubtitle(String subt) {
        this.subt.setText(subt);
    }

    public void setDesc(String desc) {
        this.desc.setText(desc);
    }

    public void setImatge(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(imagePath)
            ));
            this.imgInf.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image asset: " + imagePath);
        }
    }

    public void setJsonPath(String path) {
        this.jsonPath = path;
    }

    @FXML
    private void goBack(javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/mobile_list.fxml"));
        Parent listRoot = loader.load();

        ControllerMobileList listCtrl = loader.getController();
        listCtrl.loadJson(jsonPath);
        listCtrl.setFXML();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(listRoot);
    }
}
