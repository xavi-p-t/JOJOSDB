package com.project;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ControllerMobileDetail {

    @FXML private ImageView imgInf;
    @FXML private Text name, subt, desc;
    @FXML private StackPane rootPane;

    public void setData(String name, String subt, String desc, String imagePath) {
        this.name.setText(name);
        this.subt.setText(subt);
        this.desc.setText(desc);
        this.imgInf.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath))));
    }

    public void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/mobile_list.fxml"));
        Parent listView = loader.load();
        rootPane.getChildren().setAll(listView);
    }
}
