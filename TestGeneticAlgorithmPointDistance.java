import java.util.Arrays;
import java.util.List;
import lucaspellegrinelli.ai.geneticalgorithm.GeneticAlgorithm;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAConfig;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GADebug;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAGeneSection;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAResult;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GASchemeConfig;
import lucaspellegrinelli.ai.geneticalgorithm.objects.core.GeneSet;
import lucaspellegrinelli.ai.geneticalgorithm.objects.builder.GAFitnessCalculator;

public class TestGeneticAlgorithmPointDistance {
    public static void main(String[] args){
        final int POPULATION_SIZE = 10;
        List<GAGeneSection> GENE_SECTIONS = Arrays.asList(
                new GAGeneSection("PointAX", 0, 1),
                new GAGeneSection("PointAY", 0, 1),
                new GAGeneSection("PointBX", 0, 1),
                new GAGeneSection("PointBY", 0, 1)
        );
        
        final double MUTATION_RATE = 0.015;
        final int TOURNAMENT_SIZE = 5;
        final boolean ELITISM = true;
        
        final GAFitnessCalculator FITNESS_CALCULATOR = new GAFitnessCalculator() {
            @Override
            public double calculateFitness(GeneSet gene) {
                double pointsDeltaX = Math.abs(gene.getSectionValue("PointAX") - gene.getSectionValue("PointBX"));
                double pointsDeltaY = Math.abs(gene.getSectionValue("PointAY") - gene.getSectionValue("PointBY"));
                double distanceBetweenPoints = Math.hypot(pointsDeltaX, pointsDeltaY);
                return distanceBetweenPoints;
            }
        };
        
        final boolean SHOW_DEBUG = true;
        final int DEBUG_FREQUENCY = 100;
        final boolean SHOW_FITTEST_INDIVIDUAL_GENE = true;
        
        final double MIN_FITNESS_TO_STOP = 1.41;
        final int MAX_NUMBER_OF_GENERATIONS = 1000;
        
        GASchemeConfig schemeConfig = new GASchemeConfig(POPULATION_SIZE, GENE_SECTIONS);
        GAConfig config = new GAConfig(MUTATION_RATE, TOURNAMENT_SIZE, ELITISM);
        GADebug debug = new GADebug(SHOW_DEBUG, DEBUG_FREQUENCY, SHOW_FITTEST_INDIVIDUAL_GENE);
        
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(schemeConfig, config, debug, FITNESS_CALCULATOR);
        GAResult result = geneticAlgorithm.evolve(MIN_FITNESS_TO_STOP, MAX_NUMBER_OF_GENERATIONS);
        
        System.out.println();
        System.out.println(result);
    }
}
