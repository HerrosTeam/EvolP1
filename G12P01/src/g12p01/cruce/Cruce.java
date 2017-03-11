/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01.cruce;

import g12p01.chromosome.Chromosome;
import g12p01.chromosome.gene.Gene;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author usuario_local
 */
public class Cruce {
    
    public List<Chromosome> cruceMonopunto(Chromosome parent1, Chromosome parent2){
        List<Chromosome> children = new LinkedList();
        int randomNum = ThreadLocalRandom.current().nextInt(1, parent1.getLength());

        List<T> child1 = new LinkedList();
        List<T> child2 = new LinkedList();
        int currentGene=0, sumGeneLengths=0;
        for(int i=0; i<parent1.getLength(); i++){
            if(parent1.getGene(currentGene).getLength() + sumGeneLengths < i){
                sumGeneLengths+= parent1.getGene(currentGene).getLength();
                currentGene++;
            }
            if(i<randomNum){
                child1.add(parent1.getGene(currentGene).getAllele(i-sumGeneLengths));
                child2.add(parent2.getGene(currentGene).getAllele(i-sumGeneLengths));
            }
            else{
                child1.add(parent2.getGene(i));
                child2.add(parent1.getGene(i));
            }
        }
        children.add(new Chromosome(child1));
        children.add(new Chromosome(child2));
        
        return children;
    }
    
    
    public List<Chromosome> mutacionBasica(List<Chromosome> population, double probCruce){
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
