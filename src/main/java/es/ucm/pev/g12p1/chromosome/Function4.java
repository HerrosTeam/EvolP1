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
public class Function4 extends Chromosome{

    private int numGenes;
    
    public Function4(double tolerance, int numGenes) {
        super(0, Math.PI, tolerance);
        this.numGenes = numGenes;
        int geneLength = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance)/Math.log(2));
        this.chromosomeLength = numGenes * geneLength;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        for(int i=0; i<numGenes; i++){
            this.genes.add(i, new BinaryGene(geneLength));
        }
    } 
    
    public double function(List<Double> x){
        double sum=0;
        for(int i=0; i<numGenes; i++){
            sum+= Math.sin(x.get(i))*Math.pow(Math.sin(((i+1)+1)* 
                    Math.pow(x.get(i), 2) / Math.PI) , 20);
        }
      return sum * -1;
    }
    
    @Override
    public void evaluate() { 
        fenotype();
        this.fitness = function(this.fenotype); 
    }

    @Override
    public void fenotype() {
        for (int i=0; i<numGenes; i++){
            double result = getFenotype(this.genes.get(i));
            this.fenotype.add(i, result);
        }
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
        Chromosome c=new Function4(this.tolerance, this.numGenes);	
        c.genes = new LinkedList();
        for(int i=0; i<this.numGenes; i++){
            c.genes.add(i, this.genes.get(i).copy());
        }
        c.evaluate();
         c.setScore(this.score);
        c.setAccumulatedScore(this.scoreAccumulated);
        c.setEscalation(this.escalation);
        c.setAdaptation(this.adaptation);
        return c;
    }

   
    
}
