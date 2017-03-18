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
            this.allele.add(randomNumber.nextInt(2)==1);
        }
    }
    
    public long bin2Dec(int length){
        long result = 0;
            for (int i=0; i< length; i++) {
                boolean a = (boolean) this.allele.get(i);
        	result = result * 2 + (a ? 1 : 0);
        	}
        return result;
    }

    @Override
    public void mutate(int i) {
        boolean allele = (boolean) this.allele.get(i);
        this.allele.set(i, !allele);
    }
}
