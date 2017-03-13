/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome.gene;

import java.util.List;
import java.util.Random;

/**
 *
 * @author usuario_local
 */
public class RealGene extends Gene<Double>{
    
    public RealGene(List<Double> allele) {
        super(allele);
    }

    @Override
    public void initializeGene(Random randomNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
