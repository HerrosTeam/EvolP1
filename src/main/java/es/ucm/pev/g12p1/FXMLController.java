package es.ucm.pev.g12p1;

import es.ucm.pev.g12p1.crossover.Crossover;
import es.ucm.pev.g12p1.crossover.CrossoverFactory;
import es.ucm.pev.g12p1.selection.Selection;
import es.ucm.pev.g12p1.selection.SelectionFactory;
import javafx.scene.control.CheckBox;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    private List<AGView> currentAGViews;
    
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
    private LineChart lineChart;
    
    @FXML
    private javafx.scene.control.CheckBox chbElitism;

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
        String function = cboFuncion.getSelectionModel().getSelectedItem().toString();
        int populationSize = Integer.parseInt(txtPoblacion.getText());
        int max_generations = Integer.parseInt(txtIteraciones.getText());
        double prob_cross = Double.parseDouble(txtCruces.getText());
        double prob_mut = Double.parseDouble(txtMutacion.getText());
        double tolerance = Double.parseDouble(txtPrecision.getText());
        int seed = Integer.parseInt(txtIteraciones.getText());
        Selection selection = SelectionFactory.getSelectionAlgorithm(cboSeleccion.getSelectionModel().getSelectedItem().toString());
        Crossover crossover = CrossoverFactory.getCrossoverAlgorithm(cboCruce.getSelectionModel().getSelectedItem().toString());
        boolean elitism = chbElitism.isSelected();

        AG newAG = new AG(function, populationSize, max_generations, prob_cross, 
                prob_mut, tolerance, seed, selection, crossover, elitism);
        
        //crear nuevo Tab, con nuevo graph, con nuevo AG
        
        
        newAG.executeAlgorithm();
    }

    @FXML
    private void onEliminarButton(ActionEvent event) {
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
        cboFuncion.getSelectionModel().selectFirst();

        cboCruce.getItems().addAll(
                "Monopunto",
                "Multipunto",
                "Aritmético",
                "SBX",
                "Discreto Uniforme"
        );
        cboCruce.getSelectionModel().selectFirst();

        cboSeleccion.getItems().addAll(
                "Ruleta",
                "Estocástico Universal",
                "Truncamiento",
                "Torneo Determinista",
                "Torneo Probabilístico"
        );
        cboSeleccion.getSelectionModel().selectFirst();
        
    }
}
