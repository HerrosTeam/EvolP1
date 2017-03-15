/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome.gene;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author usuario_local
 */
public class BinaryGene extends Gene{
    
    public BinaryGene(int geneLength) {
        super(geneLength);
        this.allele = new LinkedList();
        
    }

    @Override
    public void initializeGene(Random randomNumber) {
        for(int i=0; i < this.getLength(); i++){
            this.allele.add(randomNumber.nextBoolean());
        }
    }
    
    public int bin2Dec(int length){
        if(length < 2){
            return length;
        }
        else{ 
            return (length % 10) + 2 * bin2Dec(length/10);
        }
    }

    @Override
    public void mutate(int i) {
        boolean allele = (boolean) this.allele.get(i);
        this.allele.set(i, !allele);
    }
}
