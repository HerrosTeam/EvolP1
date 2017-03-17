/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.selection;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Herros Team
 */
public class ProbabilisticTournament extends Selection {

    public ProbabilisticTournament() {
    }

    @Override
    public List<Chromosome> select(List<Chromosome> population) {
        List<Chromosome> newPopulation = new LinkedList();

        double probability = ThreadLocalRandom.current().nextDouble(0, 1 + 1);
        for (int i = 0; i < population.size(); i++) {
            int positionOfSelected;
            double bestFitness;
            int randomPosition = ThreadLocalRandom.current().nextInt(0, population.size() + 1);
            positionOfSelected = randomPosition;
            bestFitness = population.get(randomPosition).getFitness();

            randomPosition = ThreadLocalRandom.current().nextInt(0, population.size() + 1);

            double randomProbability = ThreadLocalRandom.current().nextDouble(0, 1 + 1);
            if (randomProbability > probability) {
                if (bestFitness < population.get(randomPosition).getFitness()) {
                    positionOfSelected = randomPosition;
                }
            } else if (bestFitness > population.get(randomPosition).getFitness()) {
                positionOfSelected = randomPosition;
            }

            newPopulation.add(population.get(positionOfSelected));
        }
        return newPopulation;
    }

}
