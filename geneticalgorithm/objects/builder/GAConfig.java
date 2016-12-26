package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

public class GAConfig {
    private double mutationRate;
    private int tournamentSelectionSize;
    private boolean elitism;
    
    /**
     * 
     * @param mutationRate The probability that an individual will mutate
     */
    public GAConfig(double mutationRate){
        this.mutationRate = mutationRate;
        this.tournamentSelectionSize = 5;
        this.elitism = true;
    }
    
    /**
     * 
     * @param mutationRate The probability that an individual will mutate
     * @param tournamentSelectionSize The number of individuals in the tournament selection
     */
    public GAConfig(double mutationRate, int tournamentSelectionSize){
        this.mutationRate = mutationRate;
        this.tournamentSelectionSize = tournamentSelectionSize;
        this.elitism = true;
    }
    
    /**
     * 
     * @param mutationRate The probability that an individual will mutate
     * @param elitism Will the best individual from a previous generation be used in a newer generation
     */
    public GAConfig(double mutationRate, boolean elitism){
        this.mutationRate = mutationRate;
        this.tournamentSelectionSize = 5;
        this.elitism = elitism;
    }
    
    /**
     * 
     * @param mutationRate The probability that an individual will mutate
     * @param tournamentSelectionSize The number of individuals in the tournament selection
     * @param elitism Will the best individual from a previous generation be used in a newer generation
     */
    public GAConfig(double mutationRate, int tournamentSelectionSize, boolean elitism){
        this.mutationRate = mutationRate;
        this.tournamentSelectionSize = tournamentSelectionSize;
        this.elitism = elitism;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getTournamentSelectionSize() {
        return tournamentSelectionSize;
    }

    public void setTournamentSelectionSize(int tournamentSelectionSize) {
        this.tournamentSelectionSize = tournamentSelectionSize;
    }

    public boolean isElitism() {
        return elitism;
    }

    public void setElitism(boolean elitism) {
        this.elitism = elitism;
    }
}
