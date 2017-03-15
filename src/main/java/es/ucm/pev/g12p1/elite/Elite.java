/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.elite;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PoVALE Team
 */
public class Elite {
    
    private List<Chromosome> currentPopulation;
    private int eliteSize;
    private List<Double> worstFitness;
    
    public Elite(int eliteSize) {
        this.eliteSize = eliteSize;
        this.worstFitness = new LinkedList();
    }
    
    public List<Chromosome> getElite(List<Chromosome> currentPopulation){
        
        this.currentPopulation = currentPopulation;
        this.quickSort(0, this.currentPopulation.size()-1);
        for(int i=0; i<this.eliteSize; i++){
            this.worstFitness.add(this.currentPopulation.get(currentPopulation.size()-i-1).getFitness());
        }
        
        return this.currentPopulation.subList(0,this.eliteSize);
    }
    
    private void quickSort(int lo, int hi){
        int i = lo, j = hi;
        double p = this.currentPopulation.get(lo+(hi-lo)/2).getFitness();
        while(i<=j){
            while(this.currentPopulation.get(i).getFitness() < p){
                i++;
            }
            while(this.currentPopulation.get(j).getFitness() > p){
                j--;
            }
            if(i<=j){
                flipChromosomes(i, j);
                i++;
                j--;
            }
        }
        if(lo<j)
            quickSort(lo, j);
        if(i>hi){
            quickSort(i, hi);
        }
    }
 
    private void flipChromosomes(int i, int j){
        Chromosome aux = this.currentPopulation.get(i);
        this.currentPopulation.set(i, this.currentPopulation.get(j));
        this.currentPopulation.set(j, aux);
    }

    public List<Chromosome> includeEliteRandom(List<Chromosome> population, List<Chromosome> eliteChromosomes) {
        for(int i=0; i<eliteChromosomes.size(); i++){
            population.set(i, eliteChromosomes.get(i));
        }
        return population;
    }
    
    //replace worst
    public List<Chromosome> includeEliteRepWorst(List<Chromosome> population, List<Chromosome> eliteChromosomes) {

        boolean end = false;
        for(int i=0; i<this.eliteSize; i++){
            for(int j=0; j<population.size() && !end; j++){
                if(population.get(j).getFitness()== this.worstFitness.get(i)){
                    population.set(j, eliteChromosomes.get(i));
                    end=true;
                }
            }
            end=false;
        }
        return population;
    }
}
