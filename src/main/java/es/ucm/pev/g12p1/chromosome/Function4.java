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
public class Function4 extends Chromosome{

    private double n;
    public Function4(double tolerance) {
        super(0, Math.PI, tolerance);
        n = 7;
    } 
    
    public double function(List<Double> x){
        double suma=0;
        for(int i=0; i<n; i++){
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
        for (int i=0; i<n; i++){
            double result = this.xmin + (this.xmax - this.xmin);
            this.fenotype.add(result);
        }
    }

   
    
}
