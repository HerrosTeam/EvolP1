package es.ucm.pev.g12p1;

import es.ucm.pev.g12p1.crossover.Crossover;
import es.ucm.pev.g12p1.crossover.CrossoverFactory;
import es.ucm.pev.g12p1.selection.Selection;
import es.ucm.pev.g12p1.selection.SelectionFactory;
import java.awt.Dimension;
import javafx.scene.control.CheckBox;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;


public class FXMLController implements Initializable {

    private Plot2DPanel plot;
    
    private int count;
    
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
    private TabPane tabPane;
    
     @FXML
    private javafx.scene.control.CheckBox chbElitism;

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
                prob_mut, tolerance, seed, selection, crossover, elitism, this);
        
        
        Tab tab = new Tab();
        tab.setText("AG "+ count);
        
        final SwingNode swingNode = new SwingNode();
        JPanel graphPanel = new JPanel();
        plot = new Plot2DPanel();
	plot.addLegend("SOUTH");
        plot.setPreferredSize(new Dimension(610, 460));
	graphPanel.add(plot);
        swingNode.setContent(graphPanel);
        
        Pane pane = new Pane();
        pane.getChildren().add(swingNode);
         
        tab.setContent(pane);
        tabPane.getTabs().add(tab);
        count++;
        newAG.executeAlgorithm();
        
    }
    
    public void generateGraph(double[][] graphPoints){
        plot.removeAllPlots();
    	plot.addLinePlot("Mejor absoluto", graphPoints[0], graphPoints[1]);
        plot.addLinePlot("Mejor de la generación", graphPoints[0], graphPoints[2]);
    	plot.addLinePlot("Media de la generación", graphPoints[0], graphPoints[3]);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        count=1;
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
