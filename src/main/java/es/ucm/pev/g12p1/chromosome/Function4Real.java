/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

import es.ucm.pev.g12p1.chromosome.gene.BinaryGene;
import es.ucm.pev.g12p1.chromosome.gene.Gene;
import es.ucm.pev.g12p1.chromosome.gene.RealGene;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author usuario_local
 */
public class Function4Real extends Chromosome{

    private int numGenes;
    public Function4Real(double tolerance) {
        super(0, Math.PI, tolerance);
        numGenes = 6;
        int geneLength = 1;
        this.chromosomeLength = numGenes*geneLength;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        for(int i=0; i<numGenes; i++){
            this.genes.add(i, new RealGene(geneLength, xmin, xmax));
        }
    } 
    
    public double function(List<Double> x){
        double sum=0;
        for(int i=0; i<numGenes; i++){
            sum+= Math.sin(x.get(i))*Math.pow(Math.sin((i+1)* 
                    Math.pow(x.get(i), 2)) / Math.PI, 20);
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
        RealGene a = (RealGene) gene;
        return a.getRealAllele();
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        this.adaptation = cmax - this.fitness; 
        return this.adaptation;
    }

   
    
}
