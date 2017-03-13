/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01.chromosome.gene;

import java.util.List;
import java.util.Random;

/**
 *
 * @author usuario_local
 */
public class BinaryGene extends Gene<Boolean>{
    
    public BinaryGene(List<Boolean> allele) {
        super(allele);
    }

    @Override
    public void initializeGene(Random randomNumber) {
        for(int i=0; i < this.getLength(); i++){
            this.setAllele(i, randomNumber.nextBoolean());
        }
    }
}
