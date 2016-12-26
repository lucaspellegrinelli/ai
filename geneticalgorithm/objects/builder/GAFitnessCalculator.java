package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

import lucaspellegrinelli.ai.geneticalgorithm.objects.core.GeneSet;

public interface GAFitnessCalculator {
    double calculateFitness(GeneSet gene);
}
