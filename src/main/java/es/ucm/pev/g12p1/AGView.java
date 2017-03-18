/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Herros Team
 */
public class AGView {

    private static LineChart graph;
    private static XYChart.Series absoluteBest;
    private static XYChart.Series generationBest;
    private static XYChart.Series generationAvg;
    private static ObservableList<Number> aB;
    private static ObservableList<Number> gB;
    private static ObservableList<Number> gA;

    private AG ag;

    public AGView(AG ag, LineChart graph) {
        this.ag = ag;
        this.graph = graph;
        this.initializeLineChart();
    }

    private void initializeLineChart() {

        graph.getXAxis().setAutoRanging(false);
        graph.getYAxis().setAutoRanging(false);
        graph.getXAxis().setAnimated(false);
        graph.getYAxis().setAnimated(false);
        
        absoluteBest = new XYChart.Series();
        absoluteBest.setName("Mejor absoluto");
        generationBest = new XYChart.Series();
        generationBest.setName("Mejor de la generación");
        generationAvg = new XYChart.Series();
        generationAvg.setName("Media de la generación");
        
        this.graph.getData().addAll(absoluteBest, generationBest, generationAvg);

    }
    
    public void update(){
        absoluteBest.getData().add(new XYChart.Data(ag.getGeneration(), ag.getAbsoluteBest()));
        generationBest.getData().add(new XYChart.Data(ag.getGeneration(), ag.getGenerationBest()));
        generationAvg.getData().add(new XYChart.Data(ag.getGeneration(), ag.getGenerationAvg()));
    }
}
        
        