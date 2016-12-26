package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.GeneSet;

public class GAResult {
    private final HashMap<String, Double> genesOptimized = new HashMap<>();
    private final int numberOfGenerations;
    
    /**
     * 
     * @param geneSet The best fitness gene set, in other words, the output of the
     * algorithm
     * @param numberOfGenerations The number of generations required to achieve this
     * gene set
     */
    public GAResult(GeneSet geneSet, int numberOfGenerations){
        for (String sectionName : geneSet.getSectionNames()) {
            genesOptimized.put(sectionName, geneSet.getSectionValue(sectionName));
        }
        
        this.numberOfGenerations = numberOfGenerations;
    }
    
    public double getSectionValue(String sectionName){
        return genesOptimized.get(sectionName);
    }
    
    public List<String> getSectionNames(){
        List<String> names = new ArrayList<>();
        names.addAll(genesOptimized.keySet());
        return names;
    }
    
    public int getNumberOfGenerations(){
        return numberOfGenerations;
    }
    
    @Override
    public String toString(){
        String result = "Iterations needed: " + numberOfGenerations + "\n";
        for (String sectionName : getSectionNames()) {
            result += sectionName + ": " + getSectionValue(sectionName) + "\n";
        }
        return result;
    }
}
