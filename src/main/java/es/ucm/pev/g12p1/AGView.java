/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1;

import java.util.List;

/**
 *
 * @author Herros Team
 */
public class AGView {

    List<Double> generationAverage;
    List<Double> generationBest;
    List<Double> absoluteBest;

    public AGView(List<Double> generationAverage, List<Double> generationBest, List<Double> absoluteBest) {
        this.generationAverage = generationAverage;
        this.generationBest = generationBest;
        this.absoluteBest = absoluteBest;
    }

    public List<Double> getGenerationAverage() {
        return generationAverage;
    }

    public List<Double> getGenerationBest() {
        return generationBest;
    }

    public List<Double> getAbsoluteBest() {
        return absoluteBest;
    }
}

        
        