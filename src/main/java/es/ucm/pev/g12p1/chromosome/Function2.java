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
public class Function2 extends Chromosome{

    public Function2(double tolerance) {
        super(-512, 512, tolerance);
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
        double result1 = this.xmin + (this.xmax - this.xmin);
        this.fenotype.add(result1);
        double result2 = this.xmin + (this.xmax - this.xmin);
        this.fenotype.add(result2);
    }

   
    
}
