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
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import org.w3c.dom.Element;

/**
 *
 * @author Herros_Team
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

    public AG(String function, int populationSize, int max_generations,
            double prob_cross, double prob_mut, double tolerance, int seed,
            int numGenes, Selection selection, Crossover crossover) {
        this.populationSize = populationSize;
        this.maxGenerations = max_generations;
        this.probCrossover = prob_cross;
        this.probMutation = prob_mut;
        this.tolerance = tolerance;
        this.randomNumber = (seed == 0 ? new Random() : new Random(seed));
        this.selection = selection;
        this.crossover = crossover;
    }

    public void executeAlgorithm() {
        this.initialize();
        this.evaluate();

        while (currentGeneration != maxGenerations) {
            this.selection();
            this.crossover();
            //this.mutate();
            this.evaluate();
            currentGeneration++;
        }
        //generar grafica
        /*
            selección(pob, parámetros);
            reproduccion(pob, parámetros);
            evaluacion(pob, parámetros, 
            pos_mejor, sumadaptacion); 
        }
        devolver pob[pos_mejor];*/

    }

    private void initialize() {
        this.population = new LinkedList();
        for (int i = 0; i < this.populationSize; i++) {
            Chromosome c = createConcreteChromosome();
            c.inicializeChromosome(this.randomNumber);
            population.add(c);
        }
    }

    public double evaluate(/*x*/) {//la recibimos
        return 0;
    }

    public void selection() {
        this.population = this.selection.select(this.population);
    }

    public void crossover() {
        int[] sel_cross = new int[this.populationSize];
        int num_sel_cross = 0;
        double prob;
        
        for (int i=0; i < this.populationSize; i++) {
            prob = Math.random();
            if (prob < this.probCrossover) {
                sel_cross[num_sel_cross] = i;
                num_sel_cross++;
            }
        }
        
        if ((num_sel_cross % 2) == 1) {
            num_sel_cross--;
        }

        int cross_point = ThreadLocalRandom.current().nextInt(0, this.population.get(0).getLength() + 1);
         
        for (int j=0; j<num_sel_cross; j+=2) {
            Chromosome parent1 = population.get(sel_cross[j]);
            Chromosome parent2 = population.get(sel_cross[j+1]);
            List<Chromosome> children = this.crossover.crossover(parent1, parent2, cross_point);
            
            population.set(sel_cross[j],children.get(0));
            population.set(sel_cross[j+1], children.get(1));
        }
    }

    public double score(/*x*/) {
        return 0;
    }

    public double accumulatedScore(/*x*/) {
        return 0;
    }

    private Chromosome createConcreteChromosome() {
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
                return null;
        }
    }

}
