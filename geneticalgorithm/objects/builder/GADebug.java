package lucaspellegrinelli.ai.geneticalgorithm.objects.builder;

public class GADebug {
    private boolean debug;
    private boolean showFittestGene;
    private int debugFrequency;
    
    /**
     * 
     * @param debug Defines if debug messages will be shown in the console during training
     */
    public GADebug(boolean debug){
        this.debug = debug;
        this.debugFrequency = 100;
    }
    
    /**
     * 
     * @param debug Defines if debug messages will be shown in the console during training
     * @param debugFrequency While in training, every time 'iterationNumber % debugFrequency == 0' is true
     * the debug message will be shown
     */
    public GADebug(boolean debug, int debugFrequency){
        this.debug = debug;
        this.debugFrequency = debugFrequency;
        this.showFittestGene = false;
    }
    
    /**
     * 
     * @param debug Defines if debug messages will be shown in the console during training
     * @param debugFrequency While in training, every time 'iterationNumber % debugFrequency == 0' is true
     * the debug message will be shown
     * @param showFittestGene When showing the usual debug (the current iteration plus the fittest fitness),
     * it will also be shown all the genome sections and its values from the fittest individual
     */
    public GADebug(boolean debug, int debugFrequency, boolean showFittestGene){
        this.debug = debug;
        this.debugFrequency = debugFrequency;
        this.showFittestGene = showFittestGene;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getDebugFrequency() {
        return debugFrequency;
    }

    public void setDebugFrequency(int debugFrequency) {
        this.debugFrequency = debugFrequency;
    }

    public boolean isShowFittestGene() {
        return showFittestGene;
    }

    public void setShowFittestGene(boolean showFittestGene) {
        this.showFittestGene = showFittestGene;
    }
}