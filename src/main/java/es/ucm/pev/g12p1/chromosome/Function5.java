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
public class Function5 extends Chromosome{

    private double n;
    public Function5(double tolerance) {
        super(-10, 10, tolerance);
        n = 2;
    } 
    
    public double function(List<Double> x){
        double suma1=0;
        double suma2=0;
        for(int i=0; i<5; i++){
            suma1+= i*Math.cos((i+1)*x.get(0)+i);
            suma2+= i*Math.cos((i+1)*x.get(1)+i);
        }
       

      return suma1 * suma2;
    }
    
    @Override
    public void evaluate() {
        
        fenotype();
        this.fitness = function(this.fenotype); 
    }

    @Override
    public void fenotype() {
        for (int i=0; i<n; i++){
            double result = this.xmin + (this.xmax - this.xmin);
            this.fenotype.add(result);
        }
    }

   
    
}
