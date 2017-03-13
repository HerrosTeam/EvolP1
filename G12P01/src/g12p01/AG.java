/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01;

import g12p01.chromosome.Chromosome;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class AG {
    
    private List<Chromosome> population; // poblacion
    private int populationSize; //tamaño poblacion
    private int max_generations; // num maximo de generaciones
    private Chromosome bestChrom;
    private int PosBestChrom; // Position of the best chromosome
    private double prob_cruce; // probabilidad de cruce
    private double prob_mut; //probabilidad de mutación
    private double tol; //tolerancia de representacion
    
    private int individualLength; 
    private int numGenes;
    private double eliteSize;
    private int offspringSize;
    private Random randomNumber;

    public AG(String function, int populationSize, int max_generations, double prob_cruce, double prob_mut, double tol, int seed, int numGenes) {
        this.populationSize = populationSize;
        this.max_generations = max_generations;
        this.prob_cruce = prob_cruce;
        this.prob_mut = prob_mut;
        this.tol = tol;
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

    private Chromosome createChromosome() {//crear cromosoma concreto
        return null;
    }
    
    
    
    
}
