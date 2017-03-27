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
        int geneLengthX = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance)/Math.log(2));
        int geneLengthY = (int) Math.ceil(Math.log(1+(ymax-ymin)/tolerance)/Math.log(2));
        this.chromosomeLength = geneLengthX+geneLengthY;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        this.genes.add(0, new BinaryGene(geneLengthX));
        this.genes.add(1, new BinaryGene(geneLengthY));
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
    public Chromosome copy() {
        Chromosome c=new Function3(this.tolerance);	
        c.genes = new LinkedList();
        c.genes.add(0, this.genes.get(0).copy());
        c.genes.add(1, this.genes.get(1).copy());
        c.evaluate();
         c.setScore(this.score);
        c.setAccumulatedScore(this.scoreAccumulated);
        c.setEscalation(this.escalation);
        c.setAdaptation(this.adaptation);
        return c;
    }

    
}
