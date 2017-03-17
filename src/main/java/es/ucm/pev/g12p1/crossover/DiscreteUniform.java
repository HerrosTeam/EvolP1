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
public class DiscreteUniform extends Crossover {

    public DiscreteUniform() {
    }

    @Override
    public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, int crossPoint) {
        List<Chromosome> children = new LinkedList();
        Chromosome child1 = parent1;
        Chromosome child2 = parent2;
        double Pi = 0.4;
        for (int i = 0; i < parent1.getLength(); i++) {
            double probability = ThreadLocalRandom.current().nextDouble(0, 1 + 1);
            if (Pi > probability) {
                child1.getGene(i).setAllele(0, parent2.getGene(i).getAllele(0));
                child2.getGene(i).setAllele(0, parent1.getGene(i).getAllele(0));
            }
        }
        children.add(child1);
        children.add(child2);

        return children;
    }
}
