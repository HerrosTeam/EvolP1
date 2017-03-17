/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;

import java.util.List;

/**
 *
 * @author usuario_local
 */
public class Function3 extends Chromosome{

    //incompleta
    public Function3(double tolerance) {
        super(-3, 12, tolerance);
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
        double result1 = this.xmin + (this.xmax - this.xmin);
        this.fenotype.add(result1);
        double result2 = this.xmin + (this.xmax - this.xmin);
        this.fenotype.add(result2);
    }

    @Override
    public double getAdaptation(double cmax, double fmin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
