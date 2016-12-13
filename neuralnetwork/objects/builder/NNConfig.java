package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

import lucaspellegrinelli.ai.neuralnetwork.objects.core.NNActivationFunction;

public class NNConfig {
    private double learningRate;
    private double momentum;
    private NNActivationFunction activationFunction;
    
    /**
     * 
     * @param learningRate The learning rate of the neural network
     * @param momentum The momentum of the neural network
     */
    public NNConfig(double learningRate, double momentum){
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.activationFunction = NNActivationFunction.SOFT_STEP;
    }
    
    public NNConfig(double learningRate, double momentum, NNActivationFunction activationFunction){
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.activationFunction = activationFunction;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double getMomentum() {
        return momentum;
    }

    public void setMomentum(double momentum) {
        this.momentum = momentum;
    }

    public NNActivationFunction getFunctions() {
        return activationFunction;
    }

    public void setFunctions(NNActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }
}
