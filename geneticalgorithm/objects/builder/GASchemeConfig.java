package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

import java.util.List;

public class GASchemeConfig {
    private final int populationSize;
    private final List<GAGeneSection> geneConfiguration;
    
    /**
     * 
     * @param populationSize The number of individuals in each population
     * @param geneConfiguration All the gene sections that a full gene will have
     */
    public GASchemeConfig(int populationSize, List<GAGeneSection> geneConfiguration){
        this.populationSize = populationSize;
        this.geneConfiguration = geneConfiguration;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public List<GAGeneSection> getGeneConfiguration() {
        return geneConfiguration;
    }
}
