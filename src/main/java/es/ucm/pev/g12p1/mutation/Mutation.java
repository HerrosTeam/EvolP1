/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.pev.g12p1.mutation;

/**
 *
 * @author PoVALE Team
 */
public class Mutation {
    private double probabilityOfMutation;
    private int populationSize;
    
    public Mutation(double probabilityOfMutation, int populationSize){
        this.probabilityOfMutation = probabilityOfMutation;
        this.populationSize = populationSize;
    }
    
    public void mutate(){
    
    }
}
