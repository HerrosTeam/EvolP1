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
 * @author usuario_local
 */
public class Selection {
    //mirar tfg como meter en un hashmap funciones con parametros de entrada
    
    public List<Chromosome> rouletteSelection(List<Chromosome> population, int populationSize){
        List<Chromosome> newPopulation = new LinkedList();
        double probability; //probability of selection
        int survivorPos; //position of survivor
        Random randomNumber =  new Random();
        for(Chromosome c: population){
            probability = randomNumber.nextDouble();
            survivorPos = 0;
            while(probability > population.get(survivorPos).getScoreAccumulated() && 
                    (survivorPos < populationSize)){
                survivorPos++;
            }
            newPopulation.add(population.get(survivorPos));
        }
            
        return newPopulation;
    }
}
