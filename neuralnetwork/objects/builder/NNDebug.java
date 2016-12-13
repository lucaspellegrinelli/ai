package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

public class NNDebug {
    private boolean debug;
    private int debugFrequency;
    
    /**
     * 
     * @param debug Defines if debug messages will be shown in the console during training
     */
    public NNDebug(boolean debug){
        this.debug = debug;
        this.debugFrequency = 1000;
    }
    
    /**
     * 
     * @param debug Defines if debug messages will be shown in the console during training
     * @param debugFrequency While in training, every time 'iterationNumber % debugFrequency == 0' is true
     * the debug message will be shown
     */
    public NNDebug(boolean debug, int debugFrequency){
        this.debug = debug;
        this.debugFrequency = debugFrequency;
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
}
