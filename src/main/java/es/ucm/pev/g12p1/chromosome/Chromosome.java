/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.chromosome;


import es.ucm.pev.g12p1.chromosome.gene.Gene;
import java.util.List;
import java.util.Random;

/**
 *
 * @author usuario_local
 */
public abstract class Chromosome {
    protected List<Gene> genes;
    
    protected List<Double> fenotype;
    protected double fitness; //funcion de evaluacion fitness
    protected double score; //puntuacion relativa (aptitud/suma)
    protected double scoreAccumulated; //puntuacion acumulada para seleccion
    private String tipo; //tipo de cromosoma
    
    //extremos del intervalo para los valores del dominio
    protected double xmin, xmax;
    protected int chromosomeLength; //longitud del cromosoma
    protected double tolerance;
    protected double adaptation;
    
    public Chromosome(double min, double max, double tolerance) {
        this.xmin = min;
        this.xmax = max;
        this.tolerance = tolerance;
    }
   
    
    public List<? extends Gene> getGenes(){
        return this.genes;
    }
    
    public Gene getGene(int pos){
        return this.genes.get(pos);
    }
    
    public int getLength(){
        int length = 0;
        for(Gene g: genes){
            length += g.getLength();
        }
        return length;
    }
    
    public double getFitness(){
        return fitness;
    }
    
   public abstract void evaluate();
   
   public abstract void  fenotype();// fenotype =...

    public void inicializeChromosome(Random randomNumber){
        for(int i=0; i<this.genes.size(); i++){
            this.genes.get(i).initializeGene(randomNumber);
        }
    }

    public double getScoreAccumulated() {
        return this.scoreAccumulated;
    }
  
    public abstract double getAdaptation(double cmax, double fmin);

    public void setEscalation(double escalation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setScore(double puntuacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAccumulatedScore(double puntuacionA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getEscalation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
