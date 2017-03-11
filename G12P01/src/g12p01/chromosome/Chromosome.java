/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01.chromosome;

import g12p01.chromosome.gene.Gene;
import java.util.List;

/**
 *
 * @author usuario_local
 */
public class Chromosome {
    private List<Gene> genes;
    private int max;
    private int min;

    public Chromosome(List<Gene> genes) {
        this.genes = genes;
    }
    
    public Gene getGene(int pos){
        return this.genes.get(pos);
    }
    
    public int getLength(){
        int length = 0;
        for(Gene g: genes){
            length += g.getLength();
        }
        return length;
    }
    
   public double evaluate(){
       return 0;
   }
   
   public double fenotype(){
       return 0;
   }
    
}
