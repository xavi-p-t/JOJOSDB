package com.project;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControllerInfoLayout {
    @FXML
    private Label desc,subt;
    
    @FXML
    private ImageView imgInf;


    public void setDesc(String desc) {
        this.desc.setText(desc);
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
            e.printStackTrace();
        }
    }
}
