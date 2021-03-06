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
public class Arithmetic extends Crossover {

    public Arithmetic() {
    }

    @Override
    public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, int crossPoint) {
        List<Chromosome> children = new LinkedList();

        Chromosome child1 = parent1;
        Chromosome child2 = parent2;
        double alpha = ThreadLocalRandom.current().nextDouble();
        for (int i = 0; i < parent1.getLength(); i++) {
            double child1Gen = alpha * (Double) parent1.getGene(i).getAllele(0) + (1 - alpha) * (Double) parent2.getGene(i).getAllele(0);
            child1.getGene(i).setAllele(0, child1Gen);
            double child2Gen = alpha * (Double) parent2.getGene(i).getAllele(0) + (1 - alpha) * (Double) parent1.getGene(i).getAllele(0);
            child2.getGene(i).setAllele(0, child2Gen);
        }
        
        child1.evaluate();
        child2.evaluate();
        children.add(child1);
        children.add(child2);

        return children;
    }

}
