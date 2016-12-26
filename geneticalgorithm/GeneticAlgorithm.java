package lucaspellegrinelli.ai.geneticalgorithm;

import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAConfig;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GADebug;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAResult;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GASchemeConfig;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.GeneSet;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAFitnessCalculator;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.Individual;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.Population;

public final class GeneticAlgorithm {
    private final GASchemeConfig schemeConfig;
    private final GAFitnessCalculator fitnessCalc;
    private final GAConfig config;
    private final GADebug debug;
    
    /**
     * 
     * @param schemeConfig The configuration of the population size and the gene
     * @param config Configuration for mutation rate, elitism and tournament selection
     * @param fitnessCalc The method used to calculate the fitness
     */
    public GeneticAlgorithm(GASchemeConfig schemeConfig, GAConfig config, GAFitnessCalculator fitnessCalc){
        GeneSet.setGeneSections(schemeConfig.getGeneConfiguration());
        
        this.schemeConfig = schemeConfig;
        this.config = config;
        this.fitnessCalc = fitnessCalc;
        this.debug = new GADebug(false);
    }
    
    /**
     * 
     * @param schemeConfig The configuration of the population size and the gene
     * @param config Configuration for mutation rate, elitism and tournament selection
     * @param debug Configuration for console debugging
     * @param fitnessCalc The method used to calculate the fitness
     */
    public GeneticAlgorithm(GASchemeConfig schemeConfig, GAConfig config, GADebug debug, GAFitnessCalculator fitnessCalc){
        GeneSet.setGeneSections(schemeConfig.getGeneConfiguration());
        
        this.schemeConfig = schemeConfig;
        this.config = config;
        this.fitnessCalc = fitnessCalc;
        this.debug = debug;
    }
    
    /**
     * 
     * @param minAcceptableFitness The minimum acceptable fitness so the evolving can stop
     * @param maxNumberOfGenerations The number of generations limit, after this generation
     * the evolving will stop
     * @return A GAResult object which contains the best gene set and other information
     */
    public GAResult evolve(double minAcceptableFitness, int maxNumberOfGenerations) {
        GACore core = new GACore(config, fitnessCalc);
        
        Population population = new Population(schemeConfig.getPopulationSize());

        int currentGenerationCount = 0;
        if (debug.isDebug() && currentGenerationCount % debug.getDebugFrequency() == 0) {
            showDebug(currentGenerationCount, core.getFittestIndividual(population).getFitness());
            if (debug.isShowFittestGene()) {
                showGeneSet(core.getFittestIndividual(population));
            }
        }
        
        // Each loop corresponds to one generation
        while (core.getFittestIndividual(population).getFitness() < minAcceptableFitness && currentGenerationCount < maxNumberOfGenerations) {
            currentGenerationCount++;
            
            if(debug.isDebug() && currentGenerationCount % debug.getDebugFrequency() == 0){
                showDebug(currentGenerationCount, core.getFittestIndividual(population).getFitness());
                if(debug.isShowFittestGene()){
                    showGeneSet(core.getFittestIndividual(population));
                }
            }
            
            population = core.evolvePopulation(population);
        }
        
        if (debug.isDebug() && currentGenerationCount < maxNumberOfGenerations) {
            showDebug(currentGenerationCount, core.getFittestIndividual(population).getFitness());
            if (debug.isShowFittestGene()) {
                showGeneSet(core.getFittestIndividual(population));
            }
        }
        
        GAResult result = new GAResult(core.getFittestIndividual(population).getGeneSet(), currentGenerationCount);
        return result;
    }
    
    private void showDebug(int generation, double bestFitness){
        System.out.println("#" + generation + " -> The fittest fitness: " + bestFitness);
    }
    
    private void showGeneSet(Individual i){
        String sections = "Fittest individual gene sections:\n";
        for(String section : i.getGeneSet().getSectionNames()){
            sections += section + ": " + i.getGeneSet().getSectionValue(section) + "\n";
        }
        System.out.println(sections);
    }
}
