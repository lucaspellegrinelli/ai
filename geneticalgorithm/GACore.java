package lucaspellegrinelli.ai.geneticalgorithm;

import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAConfig;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAFitnessCalculator;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.Individual;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.Population;

final class GACore {
    private final GAConfig config;
    private final GAFitnessCalculator fitnessCalc;
    
    GACore(GAConfig config, GAFitnessCalculator fitnessCalc){
        this.config = config;
        this.fitnessCalc = fitnessCalc;
    }
    
    public Population evolvePopulation(Population population){
        Population newPopulation = new Population();

        if (config.isElitism()) {
            newPopulation.saveIndividual(getFittestIndividual(population));
        }

        int elitismOffset = config.isElitism() ? 1 : 0;

        for (int i = elitismOffset; i < population.getPopulationCount(); i++) {
            Individual firstIndv = tournamentSelection(population);
            Individual secndIndv = tournamentSelection(population);
            Individual newIndiv = crossoverIndividuals(firstIndv, secndIndv);
            newPopulation.saveIndividual(newIndiv);
        }

        for (int i = elitismOffset; i < newPopulation.getPopulationCount(); i++) {
            newPopulation.getIndividual(i).mutate(config.getMutationRate());
        }

        return newPopulation;
    }
    
    Individual getFittestIndividual(Population population) {
        double fittestFitness = fitnessCalc.calculateFitness(population.getIndividual(0).getGeneSet());
        population.getIndividual(0).setFitness(fittestFitness);
        Individual fittest = population.getIndividual(0);

        for (int i = 0; i < population.getPopulationCount(); i++) {
            double currentIndFitness = fitnessCalc.calculateFitness(population.getIndividual(i).getGeneSet());
            population.getIndividual(i).setFitness(currentIndFitness);

            if (fittestFitness <= currentIndFitness) {
                fittest = population.getIndividual(i);
                fittestFitness = currentIndFitness;
            }
        }
        return fittest;
    }

    private Individual tournamentSelection(Population population) {
        Population tournamentPopulation = new Population();
        
        if(config.getTournamentSelectionSize() > 0){
            for (int i = 0; i < config.getTournamentSelectionSize(); i++) {
                int index = (int) (Math.random() * population.getPopulationCount());
                tournamentPopulation.saveIndividual(population.getIndividual(index));
            }
        }else{
            tournamentPopulation.saveIndividual(getFittestIndividual(population));
        }

        Individual fittest = getFittestIndividual(tournamentPopulation);
        return fittest;
    }
    
    private Individual crossoverIndividuals(Individual firstIndividual, Individual secondIndividual) {
        Individual crossOvered = new Individual();

        for (String geneSection : firstIndividual.getGeneSet().getSectionNames()) {
            double newSectionValue;

            if (Math.random() <= 0.5) {
                newSectionValue = firstIndividual.getGeneSet().getSectionValue(geneSection);
            } else {
                newSectionValue = secondIndividual.getGeneSet().getSectionValue(geneSection);
            }

            crossOvered.getGeneSet().setSectionValue(geneSection, newSectionValue);
        }

        return crossOvered;
    }
}
