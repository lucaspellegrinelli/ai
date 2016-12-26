package lucaspellegrinelli.ai.geneticalgorithm.objects.core;

import java.util.ArrayList;
import java.util.List;

public final class Population {
    private final List<Individual> individuals;
    
    public Population(int populationSize){
        this.individuals = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            individuals.add(individual);
        }
    }
    
    public Population(){
        this.individuals = new ArrayList<>();
    }
    
    public void saveIndividual(Individual individual){
        individuals.add(individual);
    }
    
    public int getPopulationCount(){
        return individuals.size();
    }
    
    public Individual getIndividual(int index){
        return individuals.get(index);
    }
}
