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
public class Function5 extends Chromosome{

    private double numGenes;
    public Function5(double tolerance) {
        super(-10, 10, tolerance);
        this.numGenes = 2;
        int geneLengthXi = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance)/Math.log(2));
        this.chromosomeLength = 2 * geneLengthXi;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        this.genes.add(0, new BinaryGene(geneLengthXi));
        this.genes.add(1, new BinaryGene(geneLengthXi));
    } 
    
    public double function(List<Double> x){
        double sum1=0;
        double sum2=0;
        for(int i=1; i<=5; i++){
            sum1+= i*Math.cos((i+1)*x.get(0)+i);
            sum2+= i*Math.cos((i+1)*x.get(1)+i);
        }
      return sum1 * sum2;
    }
    
     @Override
    public void evaluate() {
        fenotype();
        this.fitness = function(this.fenotype); 
    }

    @Override
    public void fenotype() {
        
        double result = getFenotype(this.genes.get(0));
        this.fenotype.add(0, result);
        double result1 = getFenotype(this.genes.get(1));
        this.fenotype.add(1, result1);
    }
    
    private double getFenotype(Gene gene){
        BinaryGene a = (BinaryGene) gene;
        double aDec = a.bin2Dec(gene.getLength());
        double result = this.xmin+(this.xmax-this.xmin)*aDec
                / (Math.pow(2, gene.getLength())-1);
        return result;
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        this.adaptation = cmax - this.fitness; 
        return this.adaptation;
    }

    @Override
    public Chromosome copy() {
        Chromosome c=new Function5(this.tolerance);	
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
