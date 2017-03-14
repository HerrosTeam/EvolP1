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
 * @author PoVALE Team
 */
public class TwoPoint extends Crossover{
    
     @Override
     public List<Chromosome> crossover(Chromosome parent1, Chromosome parent2, int crossPoint){
        List<Chromosome> children = new LinkedList();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);

        Chromosome child1 = parent1;
        Chromosome child2 = parent2;
        
        //remorremos los alelos de los genes bit a bit
        int currentGene=0, sumGeneLengths=0;
        for(int i=0; i<parent1.getLength(); i++){
            if(i >= parent1.getGene(currentGene).getLength() + sumGeneLengths){
                sumGeneLengths+= parent1.getGene(currentGene).getLength();
                currentGene++;
            }
            randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            if(randomNum==1){
                child1.getGene(currentGene).setAllele(i-sumGeneLengths, 
                        parent2.getGene(currentGene).getAllele(i-sumGeneLengths));
                child2.getGene(currentGene).setAllele(i-sumGeneLengths, 
                        parent1.getGene(currentGene).getAllele(i-sumGeneLengths));
            }
        }
        children.add(child1);
        children.add(child2);
        
        return children;
    }
}
