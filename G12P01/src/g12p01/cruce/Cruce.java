/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01.cruce;

import g12p01.chromosome.Chromosome;
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

        Chromosome child1 = parent1.cloneChromosome();
        Chromosome child2 = parent2.cloneChromosome();
        
        //remorremos los alelos de los genes bit a bit
        int currentGene=0, sumGeneLengths=0;
        for(int i=0; i<parent1.getLength(); i++){
            if(i >= parent1.getGene(currentGene).getLength() + sumGeneLengths){
                sumGeneLengths+= parent1.getGene(currentGene).getLength();
                currentGene++;
            }
            if(i >= randomNum){
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
    
    
        public List<Chromosome> cruceMultipunto(Chromosome parent1, Chromosome parent2){
        List<Chromosome> children = new LinkedList();
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);

        Chromosome child1 = parent1.cloneChromosome();
        Chromosome child2 = parent2.cloneChromosome();
        
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
