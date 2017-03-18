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
    public Function4(double tolerance) {
        super(0, Math.PI, tolerance);
        numGenes = 7;
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
        double suma=0;
        for(int i=0; i<numGenes; i++){
            suma+= Math.sin(x.get(i))*Math.pow(Math.sin((i+1)* 
                    Math.pow(x.get(i), 2)) / Math.PI, 20);
        }
      return suma * -1;
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

   
    
}
