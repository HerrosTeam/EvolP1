/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1;

import es.ucm.pev.g12p1.chromosome.Chromosome;
import es.ucm.pev.g12p1.chromosome.Function1;
import es.ucm.pev.g12p1.chromosome.Function2;
import es.ucm.pev.g12p1.chromosome.Function3;
import es.ucm.pev.g12p1.chromosome.Function4;
import es.ucm.pev.g12p1.chromosome.Function5;
import es.ucm.pev.g12p1.crossover.Crossover;
import es.ucm.pev.g12p1.selection.Selection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class AG {
    
    private List<Chromosome> population;
    private int populationSize;
    
    private int currentGeneration;
    private int maxGenerations;
    
    private Chromosome bestChromosome;
    private int posBestChrom; // Position of the best chromosome
    
    private double probCrossover;
    private double probMutation;
    private double tolerance; //tolerancia de representacion
    
    private Crossover crossover;
    private Selection selection;
    private String function;
    
    private int individualLength; 
    private int numGenes;
    private double eliteSize;
    private int offspringSize;
    private Random randomNumber;

    public AG(String function, int populationSize, int max_generations, double prob_cross, double prob_mut, double tolerance, int seed, int numGenes) {
        this.populationSize = populationSize;
        this.maxGenerations = max_generations;
        this.probCrossover = prob_cross;
        this.probMutation = prob_mut;
        this.tolerance = tolerance;
        this.randomNumber = (seed==0?new Random():new Random(seed));
    }
    
    public double evaluateFunction(/*x*/){//la recibimos
        return 0;
    }
    
    public double fitnessFunction(/* x */){//coincide con la de evaluacion
        return 0;
    }
    
    public double score(/*x*/){
        return 0;
    }
 
    public double accumulatedScore(/*x*/){
        return 0;
    }
    
    //crea cromosomas y los inicializa aleatoriamente 
    private void initialize(){
        this.population = new LinkedList(); 
        for (int i = 0; i < this.populationSize; i++){
            Chromosome c = createChromosome(); //inicializarlo con el problema concreto
            c.inicializeChromosome(this.randomNumber);
        }
    }
    
    public Chromosome AlgoritmoGenetico(){
        /*
        TPoblacion pob;    
        // población
        . . .
        obtener_parametros(parametros); 
        pob = poblacion_inicial(); 
        evaluacion(pob, tam_pob, pos_mejor, sumadaptacion);
        // bucle de evolución
        mientras no se alcanza la condición de terminación hacer{
            selección(pob, parámetros);
            reproduccion(pob, parámetros);
            evaluacion(pob, parámetros, 
            pos_mejor, sumadaptacion); 
        }
        devolver pob[pos_mejor];
        */
        return null;
    }

    private Chromosome createConcreteChromosome() {//crear cromosoma concreto
        switch (this.function) {
        case "funcion1":  
        	return new Function1(this.tolerance);
        case "funcion2":  
        	return new Function2(this.tolerance);
        case "funcion3":  
        	return new Function3(this.tolerance);
        case "funcion4":  
        	return new Function4(this.tolerance);//,this.xi);
       // case "funcion4dec":  
       // 	return new Function4dec(this.tol,this.xi);
        case "funcion5":  
        	return new Function5(this.tolerance);
        default: 
            System.err.println("Error");
    }
    
    
    
    
}
