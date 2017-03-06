package es.ucm.pev.g12p1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private TextField txtPrecision;
    @FXML
    private TextField txtPoblacion;
    @FXML
    private TextField txtIteraciones;
    @FXML
    private TextField txtCruces;
    @FXML
    private TextField txtMutacion;
    @FXML
    private TextField txtSemilla;
    @FXML
    private TextField txtSeleccion1;
    @FXML
    private TextField txtSeleccion2;
    
    @FXML
    private ComboBox cboFuncion;
    @FXML
    private ComboBox cboCruce;
    @FXML
    private ComboBox cboSeleccion;
    
    @FXML
    private void onCopiarButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void onLanzarButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void onRelanzarButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void onCrearAGButton(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboFuncion.getItems().addAll(
                "Función 1",
                "Función 2",
                "Función 3",
                "Función 4",
                "Función 5"
        );
        
        cboCruce.getItems().addAll(
                "Monopunto",
                "Mutación"
        );
        
        cboSeleccion.getItems().addAll(
                "Torneo Estocástico",
                "Ruleta"
        );
    }    
}
