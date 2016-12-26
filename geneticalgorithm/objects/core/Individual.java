package lucaspellegrinelli.ai.geneticalgorithm.objects.core;

public final class Individual {
    private final GeneSet geneSet;
    private double fitness;
    
    public Individual(){
        this.geneSet = new GeneSet();
    }
    
    public GeneSet getGeneSet(){
        return geneSet;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    public void mutate(double mutationRate){
        for (int i = 0; i < geneSet.getSectionCount(); i++) {
            if (Math.random() <= mutationRate) {
                String gene = getGeneSet().getSectionNames().get(i);
                getGeneSet().replaceGeneWithRandomValue(gene);
            }
        }
    }
    
    @Override
    public String toString(){
        String result = "";
        for (String sectionName : geneSet.getSectionNames()) {
            result += sectionName + ": " + geneSet.getSectionValue(sectionName) + "\n";
        }
        return result;
    }
}
