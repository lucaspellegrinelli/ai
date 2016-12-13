package lucaspellegrinelli.ai.neuralnetwork.objects.core;

import java.util.concurrent.atomic.AtomicInteger;

public class Neuron {
    private final int id;
    private double output;
    private double rawOutput;
    private double bias;
    
    private static final AtomicInteger UNIQUE_ID = new AtomicInteger();
    
    public Neuron(double bias){
        this.id = UNIQUE_ID.getAndIncrement();
        this.bias = bias;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }
    
    public void calculateAndSetOutput(double output, NNActivationFunction activationFunction){
        this.output = activationFunction.activationFunction(output);
        this.rawOutput = output;
    }
    
    public double getRawOutput(){
        return rawOutput;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
    
    public void addBias(double bias) {
        this.bias += bias;
    }

    public int getId() {
        return id;
    }
}
