package com.project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class ControllerMobileList {

    @FXML private VBox yPane;
    private JSONArray jsonInfo;

    private String currentJsonPath;

    public void loadJson(String path) throws Exception {
        currentJsonPath = path; 
        try (var in = getClass().getResourceAsStream(path)) {
            String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            jsonInfo = new JSONArray(content);
        }
    }


    public void setFXML() throws Exception {
        URL itemFxml = getClass().getResource("/assets/listItem.fxml");
        URL detailFxml = getClass().getResource("/assets/mobile_detail.fxml");

        yPane.getChildren().clear();

        for (int i = 0; i < jsonInfo.length(); i++) {
            JSONObject obj = jsonInfo.getJSONObject(i);

            String name = obj.getString("name");
            String image = obj.getString("image");
            String subtitle = obj.optString("subt", "");
            String description = obj.optString("inf", "");


            FXMLLoader loader = new FXMLLoader(itemFxml);
            Parent item = loader.load();
            ControllerListItem itemCtrl = loader.getController();
            itemCtrl.setTitle(name);
            itemCtrl.setImatge("/assets/images/" + image);

            item.setOnMouseClicked(e -> {
                try {
                    ControllerMobileDetail infCtrl = (ControllerMobileDetail) UtilsViews.getController("MobileDetail");

                    infCtrl.setName(name);
                    infCtrl.setSubtitle(subtitle);
                    infCtrl.setDesc(description);
                    infCtrl.setImatge("/assets/images/" + image);
                    infCtrl.setJsonPath(currentJsonPath);

                    UtilsViews.setView("MobileDetail");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });



            yPane.getChildren().add(item);
        }
    }

    @FXML
    private void goBack(javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/mobile_home.fxml"));
        Parent homeRoot = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(homeRoot);
    }
}
