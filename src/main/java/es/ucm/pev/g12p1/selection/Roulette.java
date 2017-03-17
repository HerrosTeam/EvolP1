/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.selection;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Herros Team
 */
public class Roulette extends Selection {

    public Roulette() {
    }

    @Override
    public List<Chromosome> select(List<Chromosome> population) {
        List<Chromosome> newPopulation = new LinkedList();
        double probability;
        int survivorPos;
        Random randomNumber = new Random();
        for (Chromosome c : population) {
            probability = randomNumber.nextDouble();
            survivorPos = 0;
            while (probability > population.get(survivorPos).getScoreAccumulated()
                    && (survivorPos < population.size())) {
                survivorPos++;
            }
            newPopulation.add(population.get(survivorPos));
        }

        return newPopulation;
    }

}
