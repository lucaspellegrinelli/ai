package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

import java.util.ArrayList;
import java.util.List;

public class NNInputSet {
    private List<Double> inputSet = new ArrayList<>();
    
    /**
     * 
     * @param input List of inputs of a single test (each value will be put in a Input Neuron)
     */
    public NNInputSet(double... input){
        for(double d : input){
            inputSet.add(d);
        }
    }
    
    /**
     * 
     * @param input List of inputs of a single test (each value will be put in a Input Neuron)
     */
    public NNInputSet(List<Double> input){
        this.inputSet = input;
    }

    public List<Double> getInputSet() {
        return inputSet;
    }

    public void setInputSet(List<Double> inputSet) {
        this.inputSet = inputSet;
    }
    
    public int getInputSetLength(){
        return inputSet.size();
    }
}
