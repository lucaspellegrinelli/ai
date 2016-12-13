package lucaspellegrinelli.ai.neuralnetwork.objects.core;

public class Synapse {
    private double weight;
    private Neuron neuronBack;
    private Neuron neuronFront;
    
    public Synapse(Neuron neuronBack, Neuron neuronFront, double weight){
        this.neuronBack = neuronBack;
        this.neuronFront = neuronFront;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void addWeight(double weight) {
        this.weight += weight;
    }

    public Neuron getNeuronBack() {
        return neuronBack;
    }

    public void setNeuronBack(Neuron neuronBack) {
        this.neuronBack = neuronBack;
    }

    public Neuron getNeuronFront() {
        return neuronFront;
    }

    public void setNeuronFront(Neuron neuronFront) {
        this.neuronFront = neuronFront;
    }
}
