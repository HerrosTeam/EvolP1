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
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author PoVALE Team
 */
public class DeterministicTournament extends Selection{

    @Override
    public List<Chromosome> select(List<Chromosome> population) {
        List<Chromosome> newPopulation = new LinkedList();
  
        for(int i=0; i<population.size(); i++){
            int positionOfBest;
            double bestFitness;
            int random = ThreadLocalRandom.current().nextInt(0, population.size() + 1);
            positionOfBest = random;
            bestFitness = population.get(random).getFitness();
            
            random = ThreadLocalRandom.current().nextInt(0, population.size() + 1);
            if(bestFitness < population.get(random).getFitness())
                positionOfBest = random;

            newPopulation.add(population.get(positionOfBest));
        }
        return newPopulation;
    }
    
}
