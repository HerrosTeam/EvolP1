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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Herros Team
 */
public class AGView implements Observer{
    
    LineChart graph;
    XYChart.Series absoluteBest;
    XYChart.Series generationBest;
    XYChart.Series generationAvg;
    
    private AG AG;

    public AGView(AG AG, LineChart graph) {
        this.AG = AG;
        this.graph = graph;
        this.initializeLineChart();
    }    
    
    private void initializeLineChart(){
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Generación");
        yAxis.setLabel("Evaluación");
        
        absoluteBest = new XYChart.Series();
        absoluteBest.setName("Mejor absoluto");
        generationBest = new XYChart.Series();
        generationBest.setName("Mejor de la generación");
        generationAvg = new XYChart.Series();
        generationAvg.setName("Media de la generación");
        
        this.graph.getData().addAll(absoluteBest, generationBest, generationAvg);
    }

    @Override
    public void update(Observable o, Object arg) {
            absoluteBest.getData().add(new XYChart.Data(this.AG.getGeneration(), ((Double) arg)));
    }
    
    
    
    
}
