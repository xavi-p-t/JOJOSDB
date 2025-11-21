package com.project;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ControllerInfoLayout {
    @FXML 
    private Label subt,name;
    @FXML 
    private ImageView imgInf;
    @FXML
    private Text desc;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setDesc(String text) {
        desc.setText(text);
        desc.setWrappingWidth(300);
    }

    public void setSubtitle(String subt) {
        this.subt.setText(subt);
    }

    public void setImatge(String imagePath) {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.imgInf.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
        }
    }
}
