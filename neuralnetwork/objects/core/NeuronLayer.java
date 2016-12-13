package lucaspellegrinelli.ai.neuralnetwork.objects.core;


import java.util.ArrayList;
import java.util.List;

public class NeuronLayer {
    private List<Neuron> neurons;
    
    public NeuronLayer(int neuronCount){
        neurons = new ArrayList<>();
        
        for(int i = 0; i < neuronCount; i++){
            double bias = 0.5 - (Math.random());
            Neuron n = new Neuron(bias);
            neurons.add(n);
        }
    }
    
    public NeuronLayer(List<Double> neuronBias){
        neurons = new ArrayList<>();
        
        for(int i = 0; i < neuronBias.size(); i++){
            Neuron n = new Neuron(neuronBias.get(i));
            neurons.add(n);
        }
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }
    
    public int getNeuronCount(){
        return neurons.size();
    }
}
