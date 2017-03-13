/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

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
    } 
    
    public double function(double x){
        return Math.abs(x * Math.sin( Math.sqrt( Math.abs(x)))) * -1;
    }
    
    @Override
    public void evaluate() {
        fenotype();
        this.fitness = function(this.fenotype.get(0)); 
    }

    @Override
    public void fenotype() {
        double result = this.xmin + (this.xmax - this.xmin);
        this.fenotype.add(result);
    }

}
