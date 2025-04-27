package com.rodasfiti.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

import com.rodasfiti.App;

public class VistaJuego implements Initializable {
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(
                new Image(App.class.getResource("src/main/resources/com/rodafiti/images/math.png").toExternalForm()));

    }
}