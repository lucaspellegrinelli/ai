package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

import java.util.HashMap;
import java.util.List;

public class NNTrainingSet {
    private HashMap<NNInputSet, NNOutputSet> trainingSet = new HashMap<>();
    
    /**
     * 
     * @param trainingSet The list of inputs and expected outputs for each input
     */
    public NNTrainingSet(HashMap<NNInputSet, NNOutputSet> trainingSet){
        this.trainingSet = trainingSet;
    }
    
    /**
     * 
     * @param inputs List of inputs
     * @param outputs List of expected outputs for each input
     */
    public NNTrainingSet(List<NNInputSet> inputs, List<NNOutputSet> outputs){
        if(inputs.size() != outputs.size()){
            System.out.println("The number of inputs is different than the number of outputs!\nUsing those inputs that have a paired output");
        }
        
        for(int i = 0; i < Math.min(inputs.size(), outputs.size()); i++){
            trainingSet.put(inputs.get(i), outputs.get(i));
        }
    }
    
    /**
     * 
     * @param inputs List of inputs
     * @param outputs List of expected outputs for each input
     */
    public NNTrainingSet(NNInputSet[] inputs, NNOutputSet[] outputs){
        if(inputs.length != outputs.length){
            System.out.println("The number of inputs is different than the number of outputs!\nUsing those inputs that have a paired output");
        }
        
        for(int i = 0; i < Math.min(inputs.length, outputs.length); i++){
            trainingSet.put(inputs[i], outputs[i]);
        }
    }
    
    /**
     * 
     * @param input List of inputs
     * @param output List of expected outputs for each input
     */
    public NNTrainingSet(double[][] input, double[][] output){
        if(input.length != output.length){
            System.out.println("The number of inputs is different than the number of outputs!\nUsing those inputs that have a paired output");
        }
        
        for(int i = 0; i < Math.min(input.length, output.length); i++){
            NNInputSet nInput = new NNInputSet(input[i]);
            NNOutputSet nOutput = new NNOutputSet(output[i]);
            trainingSet.put(nInput, nOutput);
        }
    }

    public HashMap<NNInputSet, NNOutputSet> getTrainingSet() {
        return trainingSet;
    }
    
    public void addToTrainingSet(HashMap<NNInputSet, NNOutputSet> trainingSet){
        this.trainingSet.putAll(trainingSet);
    }
    
    public void addToTrainingSet(List<NNInputSet> inputs, List<NNOutputSet> outputs){
        for(int i = 0; i < inputs.size(); i++){
            trainingSet.put(inputs.get(i), outputs.get(i));
        }
    }
    
    public void addToTrainingSet(NNInputSet[] inputs, NNOutputSet[] outputs){
        for(int i = 0; i < inputs.length; i++){
            trainingSet.put(inputs[i], outputs[i]);
        }
    }
    
    public void addToTrainingSet(double[][] input, double[][] output){
        for(int i = 0; i < input.length; i++){
            NNInputSet nInput = new NNInputSet(input[i]);
            NNOutputSet nOutput = new NNOutputSet(output[i]);
            trainingSet.put(nInput, nOutput);
        }
    }
    
    public int getLength(){
        return trainingSet.size();
    }
    
    public NNOutputSet getOutputForInput(NNInputSet nInput){
        return trainingSet.get(nInput);
    }
}
