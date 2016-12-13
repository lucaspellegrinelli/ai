package lucaspellegrinelli.ai.neuralnetwork.objects.core;


import java.util.ArrayList;
import java.util.List;

public class SynapseLayer {
    private List<Synapse> synapses;
    private NeuronLayer layerBack;
    private NeuronLayer layerFront;
    
    public SynapseLayer(NeuronLayer layerBack, NeuronLayer layerFront){
        this.layerBack = layerBack;
        this.layerFront = layerFront;
        
        synapses = new ArrayList<>();
        
        for(Neuron front : layerFront.getNeurons()){
            for(Neuron back : layerBack.getNeurons()){
                double weight = 0.5 - (Math.random());
                Synapse s = new Synapse(back, front, weight);
                synapses.add(s);
            }
        }
    }
    
    public SynapseLayer(NeuronLayer layerBack, NeuronLayer layerFront, List<Double> weights){
        this.layerBack = layerBack;
        this.layerFront = layerFront;
        
        synapses = new ArrayList<>();
        
        int count = 0;
        for(Neuron front : layerFront.getNeurons()){
            for(Neuron back : layerBack.getNeurons()){
                double weight = weights.get(count++);
                Synapse s = new Synapse(back, front, weight);
                synapses.add(s);
            }
        }
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }

    public void setSynapses(List<Synapse> synapses) {
        this.synapses = synapses;
    }

    public NeuronLayer getLayerBack() {
        return layerBack;
    }

    public void setLayerBack(NeuronLayer layerBack) {
        this.layerBack = layerBack;
    }

    public NeuronLayer getLayerFront() {
        return layerFront;
    }

    public void setLayerFront(NeuronLayer layerFront) {
        this.layerFront = layerFront;
    }
    
    public int getSynapseCount(){
        return synapses.size();
    }
    
    public Synapse findSynapse(Neuron from, Neuron to){
        for(Synapse s : synapses){
            if(s.getNeuronBack().getId() == from.getId() && s.getNeuronFront().getId() == to.getId())
                return s;
        }
        
        return null;
    }
}
