/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.mutation;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author PoVALE Team
 */
public class BasicMutation extends Mutation{

    public BasicMutation(double probabilityOfMutation, int populationSize) {
        super(probabilityOfMutation, populationSize);
    }
    
    public List<Chromosome> basicMutation(List<Chromosome> population, double probCruce){
        List<Double> probabilities = new LinkedList();
        for(Chromosome c : population){
            probabilities.add(ThreadLocalRandom.current().nextDouble(0, 1+1));
        }
        
        for(int i=0; i<population.size(); i++){
            if(probabilities.get(i) < probCruce){
            
            }
        }
        return null;
    }
}
