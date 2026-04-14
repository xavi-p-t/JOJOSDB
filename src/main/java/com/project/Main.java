package com.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    final int WINDOW_WIDTH = 850;
    final int WINDOW_HEIGHT = 650;
    final int MIN_WIDTH = 400;
    final int MIN_HEIGHT = 500;

        @Override
        public void start(Stage stage) throws Exception {

            // Carrega la vista inicial des del fitxer FXML
            UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
            UtilsViews.addView(getClass(), "Desktop", "/assets/layout.fxml");
            UtilsViews.addView(getClass(), "Mobile", "/assets/mobile_home.fxml");
            UtilsViews.addView(getClass(), "MobileList", "/assets/mobile_list.fxml");
            UtilsViews.addView(getClass(), "MobileDetail", "/assets/mobile_detail.fxml");
            Scene scene = new Scene(UtilsViews.parentContainer);

            stage.setScene(scene);
            stage.setTitle("JavaFX App");
            stage.setMinWidth(MIN_WIDTH);
            stage.setWidth(WINDOW_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setHeight(WINDOW_HEIGHT);
            stage.show();
            
            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                String activeView = UtilsViews.getActiveView();
                boolean isMobileWidth = newVal.doubleValue() < 600;

                if (isMobileWidth) {
                    // Si se hace pequeño y estamos en Desktop, pasamos a la Home de Mobile
                    if ("Desktop".equals(activeView)) {
                        UtilsViews.setView("Mobile");
                    }
                } else {
                    // Si se hace grande y estamos en alguna pantalla de Mobile, pasamos a Desktop
                    if (activeView != null && activeView.startsWith("Mobile")) {
                        UtilsViews.setView("Desktop");
                    }
                }
            });

            // Afegeix una icona només si no és un Mac
            if (!System.getProperty("os.name").contains("Mac")) {
                Image icon = new Image("file:icons/icon.png");
                stage.getIcons().add(icon);
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
