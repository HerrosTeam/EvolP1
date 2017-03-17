/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

import es.ucm.pev.g12p1.chromosome.gene.BinaryGene;
import es.ucm.pev.g12p1.chromosome.gene.Gene;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author usuario_local
 */
public class Function3 extends Chromosome{

    private double ymin = 4.1;
    private double ymax = 5.8;
    
    public Function3(double tolerance) {
        super(-3, 12.1, tolerance);
        int chromosomeLengthX = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance));
        int chromosomeLengthY = (int) Math.ceil(Math.log(1+(ymax-ymin)/tolerance));
        this.chromosomeLength = chromosomeLengthX+chromosomeLengthY;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        this.genes.add(new BinaryGene(chromosomeLengthX));
        this.genes.add(new BinaryGene(chromosomeLengthY));
    } 
    
    public double function(List<Double> x){
      return 21.5 + x.get(0) * Math.sin(Math.PI*4*x.get(0))
             + x.get(1) * Math.sin(Math.PI*x.get(1)*20);
    }
    
        @Override
    public void evaluate() {
        fenotype();
        this.fitness = function(this.fenotype); 
    }

    @Override
    public void fenotype() {
        
        double result = getFenotype(this.genes.get(0), xmax, xmin);
        this.fenotype.add(0, result);
        double result1 = getFenotype(this.genes.get(1), ymax, ymin);
        this.fenotype.add(1, result1);
    }
    
    private double getFenotype(Gene gene, double max, double min){
        BinaryGene a = (BinaryGene) gene;
        double aDec = a.bin2Dec(gene.getLength());
        double result = min+(max-min)*aDec
                / (Math.pow(2, gene.getLength())-1);
        return result;
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        this.adaptation = this.fitness + fmin; 
        return this.adaptation;
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
