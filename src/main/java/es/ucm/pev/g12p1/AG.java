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
import es.ucm.pev.g12p1.chromosome.Function4Real;
import es.ucm.pev.g12p1.chromosome.Function5;
import es.ucm.pev.g12p1.crossover.Crossover;
import es.ucm.pev.g12p1.elite.Elite;
import es.ucm.pev.g12p1.mutation.BasicMutation;
import es.ucm.pev.g12p1.mutation.Mutation;
import es.ucm.pev.g12p1.selection.Selection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Herros_Team
 */
public class AG {

    private List<Chromosome> population;
    private int populationSize;

    private int currentGeneration;
    private int maxGenerations;

    private Chromosome bestGeneration;
    private Chromosome bestChromosome;
    private int bestPosition;

    private double probCrossover;
    private double probMutation;
    private double tolerance; //tolerancia de representacion

    private Crossover crossover;
    private Selection selection;
    private String function;

    private Random randomNumber;
    private Mutation mutation;

    private boolean maximizar;

    private List<Chromosome> eliteChromosomes;
    private boolean elitism;
    private int elitismPopulation;
    // Position of the best chromosome
    private Elite elite;

    private double average;

    private double evolutionaryPressure;
    
    private FXMLController controller;
    private double[][] graphPoints;
    
    public AG(String function, int populationSize, int max_generations,
            double prob_cross, double prob_mut, double tolerance, int seed, Selection selection, Crossover crossover,
            boolean elitism, FXMLController controller) {
        this.currentGeneration = 0;
        this.function = function;
        this.populationSize = populationSize;
        this.maxGenerations = max_generations;
        this.probCrossover = prob_cross/100;
        this.probMutation = prob_mut/100;
        this.tolerance = tolerance;
        this.randomNumber = (seed == 0 ? new Random() : new Random(seed));
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = new BasicMutation(prob_mut, populationSize);
        this.graphPoints = new double[4][maxGenerations];
        this.elitism = elitism;
        if (elitism) {
            this.elitismPopulation = (int) Math.ceil(this.populationSize / 100.0);
            this.elite = new Elite(this.elitismPopulation);
        }
        this.evolutionaryPressure = 1.5;
        this.controller = controller;
        this.eliteChromosomes = new LinkedList<>();
    }

    public void executeAlgorithm() {
        this.initialize();
        this.evaluate();
        //this.observer.update(this, this);
        while (currentGeneration != maxGenerations) {

            if (elitism) {
                this.eliteChromosomes.clear();
                this.eliteChromosomes.addAll(this.elite.getElite(population));
            }

            this.selection();
            this.crossover();
            this.population = this.mutation.mutate(this.population);

            if (elitism) {
                this.population =this.elite.includeEliteRepWorst(this.population, this.eliteChromosomes);
                this.eliteChromosomes.clear();
            }

            this.evaluate();
            
            this.graphPoints[0][this.currentGeneration]=getGeneration();
            this.graphPoints[1][this.currentGeneration]=getAbsoluteBest();
            this.graphPoints[2][this.currentGeneration]=getGenerationBest();
            this.graphPoints[3][this.currentGeneration]=getGenerationAvg();
            
            controller.generateGraph(graphPoints);
            
            currentGeneration++;
        }
    }

    private void initialize() {
        this.population = new LinkedList();
        for (int i = 0; i < this.populationSize; i++) {
            Chromosome c = createConcreteChromosome();
            c.inicializeChromosome(this.randomNumber);
            c.evaluate();
            population.add(c);
        }
        this.bestChromosome = this.population.get(0).copy();
    }

    public void evaluate() {
        //this.bestChromosome = this.population.get(0);
        this.bestGeneration = this.population.get(0).copy();
        double bestFitness = this.population.get(0).getFitness();
        double sumFitness = 0;
        //this.bestPosition = 0;
        double fmin = bestGeneration.getFitness();
        double cmax = bestGeneration.getFitness();

        for (int i = 0; i < this.populationSize; i++) {
            this.population.get(i).fenotype();
            this.population.get(i).evaluate();
            double currentFitness = this.population.get(i).getFitness();
            sumFitness += currentFitness;

            if (maximizar) {
                if (currentFitness > this.bestGeneration.getFitness()) {
                    bestGeneration = this.population.get(i).copy();
                }
            } else if (!maximizar) {
                if (currentFitness < this.bestGeneration.getFitness()) {
                    bestGeneration = this.population.get(i).copy();
                }
            }

            if (currentFitness < fmin) {
                fmin = currentFitness;
            }
            if (currentFitness > cmax) {
                cmax = currentFitness;
            }
        }
        
        if (maximizar) {
                if (bestGeneration.getFitness() > this.bestChromosome.getFitness()) {
                    bestChromosome = bestGeneration.copy();
                }
            } else if (!maximizar) {
                if (bestGeneration.getFitness() < this.bestChromosome.getFitness()) {
                    bestChromosome = bestGeneration.copy();
                }
            }
        
        this.average = sumFitness / this.populationSize;
        this.adaptation(cmax, fmin);
    }

    public void adaptation(double cmax, double fmin) {
        //desplazamiento de la adaptación
        cmax *= 1.05;
        if (cmax >= 0) {
            cmax *= 1.05;
        } else {
            cmax *= 0.95;
        }
        fmin = Math.abs(fmin);

        double sumAdaptations = 0;

        for (int i = 0; i < this.populationSize; i++) {
            sumAdaptations += this.population.get(i).getAdaptation(cmax, fmin);
        }

        double avgAdaptations = sumAdaptations / this.populationSize;
        double a = ((this.evolutionaryPressure - 1) * avgAdaptations) / (this.bestGeneration.getAdaptation(cmax, fmin) - avgAdaptations);
        double b = (1 - a) * avgAdaptations;
        double sumEscalation = 0;
        for (int i = 0; i < this.populationSize; i++) {
            double escalation = (a * this.population.get(i).getAdaptation(cmax, fmin)) + b;
            this.population.get(i).setEscalation(escalation);
            sumEscalation += escalation;
        }

        double score = 0;
        double accumulatedScore = 0;

        for (int i = 0; i < this.populationSize; i++) {//calcular puntuaciones y puntuaciones acumuladas
            score = this.population.get(i).getEscalation() / sumEscalation;
            accumulatedScore += score;
            this.population.get(i).setScore(score);
            this.population.get(i).setAccumulatedScore(accumulatedScore);
        }
    }

    public void selection() {
        this.population = this.selection.select(this.population);
    }

    public void crossover() {
        int[] sel_cross = new int[this.populationSize];
        int num_sel_cross = 0;
        double prob;

        for (int i = 0; i < this.populationSize; i++) {
            prob = Math.random();
            if (prob < this.probCrossover) {
                sel_cross[num_sel_cross] = i;
                num_sel_cross++;
            }
        }

        if ((num_sel_cross % 2) == 1) {
            num_sel_cross--;
        }

        for (int j = 0; j < num_sel_cross; j += 2) {
            Chromosome parent1 = population.get(sel_cross[j]).copy();
            Chromosome parent2 = population.get(sel_cross[j + 1]).copy();
            int cross_point = ThreadLocalRandom.current().nextInt(0, this.population.get(0).getLength()-1);
            List<Chromosome> children = this.crossover.crossover(parent1, parent2, cross_point);
            children.get(0).fenotype();
            children.get(1).fenotype();
            children.get(0).evaluate();
            children.get(1).evaluate();
            population.set(sel_cross[j], children.get(0).copy());
            population.set(sel_cross[j + 1], children.get(1).copy());
        }
    }

    private Chromosome createConcreteChromosome() {
        switch (this.function) {
            case "Función 1":
                this.maximizar = false;
                return new Function1(this.tolerance);
            case "Función 2":
                this.maximizar = true;
                return new Function2(this.tolerance);
            case "Función 3":
                this.maximizar = true;
                return new Function3(this.tolerance);
            case "Función 4":
                this.maximizar = true;
                return new Function4(this.tolerance);
            case "Función 4 reales":
                this.maximizar = true;
                return new Function4Real(this.tolerance);
            case "Función 5":
                this.maximizar = true;
                return new Function5(this.tolerance);
            default:
                System.err.println("Error");
                return null;
        }
    }

    public int getGeneration() {
        return this.currentGeneration;
    }
    
    public double getAbsoluteBest(){
        return this.bestChromosome.getFitness();
    }
    
    public double getGenerationBest(){
        return this.bestGeneration.getFitness();
    }
    
    public double getGenerationAvg(){
        return this.average;
    }
    
}
 
