package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

public class NNSchemeConfig {
    private int inputNeuronCount;
    private int hiddenLayerCount;
    private int hiddenLayerNeuronCount;
    private int outputNeuronCount;
    
    /**
     * 
     * @param inputNeuronCount Number of neurons in the Input Layer
     * @param hiddenLayerCount Number of hidden layers
     * @param hiddenLayerNeuronCount Number of neurons in each hidden layer
     * @param outputNeuronCount  Number of neurons in the Output Layer
     */
    public NNSchemeConfig(int inputNeuronCount, int hiddenLayerCount, int hiddenLayerNeuronCount, int outputNeuronCount){
        this.inputNeuronCount = inputNeuronCount;
        this.hiddenLayerCount = hiddenLayerCount;
        this.hiddenLayerNeuronCount = hiddenLayerNeuronCount;
        this.outputNeuronCount = outputNeuronCount;
    }

    public int getInputNeuronCount() {
        return inputNeuronCount;
    }

    public void setInputNeuronCount(int inputNeuronCount) {
        this.inputNeuronCount = inputNeuronCount;
    }

    public int getHiddenLayerCount() {
        return hiddenLayerCount;
    }

    public void setHiddenLayerCount(int hiddenLayerCount) {
        this.hiddenLayerCount = hiddenLayerCount;
    }

    public int getHiddenLayerNeuronCount() {
        return hiddenLayerNeuronCount;
    }

    public void setHiddenLayerNeuronCount(int hiddenLayerNeuronCount) {
        this.hiddenLayerNeuronCount = hiddenLayerNeuronCount;
    }

    public int getOutputNeuronCount() {
        return outputNeuronCount;
    }

    public void setOutputNeuronCount(int outputNeuronCount) {
        this.outputNeuronCount = outputNeuronCount;
    }
}
