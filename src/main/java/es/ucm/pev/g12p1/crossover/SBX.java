/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.crossover;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Herros Team
 */
public class SBX extends Crossover {

    public SBX() {
    }

    @Override
    public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, int crossPoint) {
        List<Chromosome> children = new LinkedList();

        Chromosome child1 = parent1;
        Chromosome child2 = parent2;
        double alpha = ThreadLocalRandom.current().nextDouble(0, 1);
        double beta;
        int n = 1;//recomended value = 1 || 2
        if(alpha<0.5){
            beta = 2*Math.pow(alpha, 1/(n+1));
        }else{
            beta = Math.pow(1/(2*(1-alpha)), 1/(n+1));
        }
        
        for (int i = 0; i < parent1.getLength(); i++) {
            double child1Gen = 0.5 * ( ((Double) parent1.getGene(i).getAllele(0) + (Double) parent2.getGene(i).getAllele(0)) 
                    - beta * (((Double) parent2.getGene(i).getAllele(0) - (Double) parent1.getGene(i).getAllele(0))));
            child2.getGene(i).setAllele(0, child1Gen);
            double child2Gen = 0.5 * ( ((Double) parent1.getGene(i).getAllele(0) + (Double) parent2.getGene(i).getAllele(0)) 
                    + beta * (((Double) parent2.getGene(i).getAllele(0) - (Double) parent1.getGene(i).getAllele(0))));
            child2.getGene(i).setAllele(0, child2Gen);
        }
        children.add(child1);
        children.add(child2);

        return children;
        
    }
}
