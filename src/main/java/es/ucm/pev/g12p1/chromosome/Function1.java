/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

import es.ucm.pev.g12p1.chromosome.gene.BinaryGene;
import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 *
 * @author usuario_local
 */
public class Function1 extends Chromosome{

    public Function1(double tolerance) {
        super(-250, 250, tolerance);
        this.fenotype = new LinkedList();
        this.tolerance = tolerance;
        this.genes = new LinkedList();
        int geneLength = 1;
        this.genes.add(new BinaryGene(geneLength));
        
    } 
    
    public double function(double x){
        return -1 * Math.abs(x * Math.sin( Math.sqrt( Math.abs(x))));
    }
    
    @Override
    public void evaluate() {
        fenotype();
        this.fitness = function(this.fenotype.get(0)); 
    }

    @Override
    public void fenotype() {
        double result = this.xmin + (this.xmax - this.xmin) * this.genes.get(0);
        this.fenotype.add(result);
    }

}
