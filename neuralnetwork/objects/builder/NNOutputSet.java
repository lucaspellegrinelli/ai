package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

import java.util.ArrayList;
import java.util.List;

public class NNOutputSet {
    private List<Double> outputSet = new ArrayList<>();
    
    /**
     * 
     * @param output List of outputs expected from a single NNInputSet
     */
    public NNOutputSet(double... output){
        for(double d : output){
            outputSet.add(d);
        }
    }
    
    /**
     * 
     * @param output List of outputs expected from a single NNInputSet
     */
    public NNOutputSet(List<Double> output){
        this.outputSet = output;
    }

    public List<Double> getOutputSet() {
        return outputSet;
    }

    public void setOutputSet(List<Double> outputSet) {
        this.outputSet = outputSet;
    }
    
    public int getInputSetLength(){
        return outputSet.size();
    }
}
