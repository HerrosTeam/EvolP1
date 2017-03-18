/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.crossover;

/**
 *
 * @author Herros Team
 */
public class CrossoverFactory {

    public static Crossover getCrossoverAlgorithm(String crossoverAlgorithm) {
        switch (crossoverAlgorithm) {
            case "Monopunto":
                return new SinglePoint();
            case "Multipunto":
                return new Uniform();
            case "Aritmético":
                return new Arithmetic();
            case "SBX":
                return new SBX();
            case "Discreto Uniforme":
                return new DiscreteUniform();
            default:
                return new SinglePoint();
        }
    }
}
