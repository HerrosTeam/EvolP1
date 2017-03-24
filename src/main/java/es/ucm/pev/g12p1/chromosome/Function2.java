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
public class Function2 extends Chromosome{

    public Function2(double tolerance) {
        super(-512, 512, tolerance);
        int geneLengthXi = (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerance)/Math.log(2));
        this.chromosomeLength = 2 * geneLengthXi;
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        this.genes.add(0, new BinaryGene(geneLengthXi));
        this.genes.add(1, new BinaryGene(geneLengthXi));
    } 
    
    public double function(List<Double> x){
      return -1 * (x.get(1)+47) 
              * Math.sin(Math.sqrt(Math.abs(x.get(1) + x.get(0)/2 + 47)))
              - x.get(0) * Math.sin(Math.sqrt(Math.abs(x.get(0)-(x.get(1)+47))));
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
        Chromosome c=new Function2(this.tolerance);	
        c.genes = new LinkedList();
        c.genes.add(0, this.genes.get(0).copy());
        c.genes.add(1, this.genes.get(1).copy());
        c.evaluate();
        return c;
    }

}
