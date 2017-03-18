/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

import es.ucm.pev.g12p1.chromosome.gene.BinaryGene;
import java.util.LinkedList;

/**
 *
 * @author usuario_local
 */
public class Function1 extends Chromosome{

    public Function1(double tolerance) {
        super(-250, 250, tolerance);
        int geneLength = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance)/Math.log(2));
        this.chromosomeLength = geneLength;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        this.genes.add(0, new BinaryGene(geneLength));
    } 
    
    public double function(double x){
        return -1* Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
    }
    
    @Override
    public void evaluate() {
        fenotype();
        this.fitness = function(this.fenotype.get(0)); 
    }

    @Override
    public void fenotype(){        
        BinaryGene a = (BinaryGene) this.genes.get(0);
        double aDec = a.bin2Dec(chromosomeLength);
        double result = this.xmin+(this.xmax-this.xmin)*aDec
                / (Math.pow(2, this.genes.get(0).getLength())-1);
        this.fenotype.add(0, result);
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        this.adaptation = cmax - this.fitness; 
        return this.adaptation;
    }

}
