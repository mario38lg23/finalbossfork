package com.rodasfiti.controllers;

import com.rodasfiti.App;
import com.rodasfiti.interfaces.Observer;
import com.rodasfiti.model.*;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class mainVista implements Observer {
    @FXML
    private AnchorPane contenedorPrincipal;
    @FXML
    private Slider puntosVida;
    @FXML
    private Slider puntoSalud;
    @FXML
    private Slider puntosAtaque;
    @FXML
    private ImageView fotoPersonaje;
    @FXML
    private ImageView fotoVida;
    @FXML
    private ImageView fotoSalud;
    @FXML
    private ImageView fotoAtaque;
    @FXML
    private ImageView fotoAtributos;
    @FXML
    private ProgressIndicator porcentajeAtributos;
    @FXML
    private Button botonEmpezar;
    @FXML
    private TextField nombrePersonaje;
    @Override
    public void onChange() {
        
    }


    
}
