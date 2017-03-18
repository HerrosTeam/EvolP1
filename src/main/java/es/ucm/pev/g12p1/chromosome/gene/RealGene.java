/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome.gene;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author usuario_local
 */
public class RealGene extends Gene{
    private double min;
    private double max;
    
    public RealGene(int geneLength, double min, double max) {
        super(geneLength);
        this.min=min;
        this.max=max;
    }

    @Override
    public void initializeGene(Random randomNumber) {
        this.allele.add(ThreadLocalRandom.current().nextDouble(this.min, this.max));
    }
    
    @Override
    public void mutate(int i) {
        this.allele.set(0, ThreadLocalRandom.current().nextDouble()*(max - min) + min);
    }
    
    public double getRealAllele(){
        return (double) this.allele.get(0);
    }
    
}
