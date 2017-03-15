/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.selection;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author PoVALE Team
 */
public class Truncation extends Selection{
    private List<Chromosome> population;
    @Override
    public List<Chromosome> select(List<Chromosome> population) {
        List<Chromosome> newPopulation = new LinkedList();
        double trunc = 0.5; //trunc = x ->[0.1, 0.5]
        double timesSelected = (Integer)1/trunc;
        this.population = population;
        quickSort(0, population.size());
        
        for(int i=0; i<this.population.size()*trunc; i++){
            for(int j=0; j<timesSelected; j++){
                newPopulation.add(this.population.get(i));
            }
        }
        return newPopulation;
    }
    
    private void quickSort(int lo, int hi){
        int i = lo, j = hi;
        double p = this.population.get(lo+(hi-lo)/2).getFitness();
        while(i<=j){
            while(this.population.get(i).getFitness() < p){
                i++;
            }
            while(this.population.get(j).getFitness() > p){
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
        Chromosome aux = this.population.get(i);
        this.population.set(i, this.population.get(j));
        this.population.set(j, aux);
    }
    
}