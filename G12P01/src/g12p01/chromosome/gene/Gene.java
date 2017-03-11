/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g12p01.chromosome.gene;

import java.util.List;

/**
 *
 * @author usuario_local
 */
public abstract class Gene<T> {
    
    private List<T> allele;

    public Gene(List<T> allele) {
        this.allele = allele;
    }

    public int getLength(){
        return allele.size();
    }
    
    public T getAllele(int pos){
        return allele.get(pos);
    }
}
