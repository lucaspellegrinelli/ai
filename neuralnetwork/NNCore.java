package lucaspellegrinelli.ai.neuralnetwork;

import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSchemeConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.Neuron;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.Synapse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class NNCore {
    private double globalNetworkError;
    
    private final NNConfig nConfig;

    private final NNScheme nScheme;
    
    /*
    * For output layer:
    *   LastErrorMargin += ExpectedOutput - NeuronOutput
    * For hidden/input layer:
    *   LastErrorMargin += SynapseForwardeWeight * ErrorDeltaNextNeuron
    */
    private final HashMap<Neuron, Double> lastErrorMargin = new HashMap<>();
    
    // BiasDelta = (LearningRate * ErrorDelta) + (Momentum * BiasDelta)
    // Makes part of Neural Network Memory
    private final HashMap<Neuron, Double> allBiasDeltas = new HashMap<>();
    
    // WeightDelta = (LearningRate * Delta) + (Momentum * WeightDelta)
    // Makes part of Neural Network Memory
    private final HashMap<Synapse, Double> allWeightDeltas = new HashMap<>();

    NNCore(NNSchemeConfig nSchemeConfig, NNConfig nConfig) {
        this.nConfig = nConfig;
        this.nScheme = new NNScheme(nSchemeConfig);
    }
    
    NNCore(NNScheme nScheme, NNConfig nConfig) {
        this.nConfig = nConfig;
        this.nScheme = nScheme;
    }

    List<Double> runNeuralNetwork(List<Double> input) {
        if(input.size() < nScheme.getInputLayer().getNeuronCount()){
            Logger.getLogger(NNCore.class.getName()).log(Level.WARNING, "The input provided has less values than the number of input neurons.\nAborting task...");
            return new ArrayList<>();
        }else if(input.size() > nScheme.getInputLayer().getNeuronCount()){
            Logger.getLogger(NNCore.class.getName()).log(Level.WARNING, "The input provided has more values than the number of input neurons.\nClass will use some of the values");
        }
        
        // Coloca os inputs dados como outputs dos neuronios da camada de input
        for (int i = 0; i < nScheme.getInputLayer().getNeuronCount(); i++) {
            nScheme.getInputLayer().getNeurons().get(i).setOutput(input.get(i));
        }
        
        // Calculando o output de cada neuronio da camada hidden e output
        for(int i = 1; i < nScheme.getNeuronLayerCount(); i++){
            for(int k = 0; k < nScheme.getNeuronLayer(i).getNeurons().size(); k++){
                double sum = nScheme.getNeuronLayer(i).getNeurons().get(k).getBias();

                for (int j = 0; j < nScheme.getNeuronLayer(i - 1).getNeuronCount(); j++) {
                    int synapseIndex = k * nScheme.getNeuronLayer(i - 1).getNeuronCount() + j;

                    sum += nScheme.getNeuronLayer(i - 1).getNeurons().get(j).getOutput() *
                            nScheme.getSynapseLayer(i - 1).getSynapses().get(synapseIndex).getWeight();
                }

                nScheme.getNeuronLayer(i).getNeurons().get(k).calculateAndSetOutput(sum, nConfig.getFunctions());
            }
        }

        // Pegando o output de cada neuronio da camada output
        List<Double> outputNeuronsOutputForReturn = new ArrayList<>();
        
        for(int i = 0; i < nScheme.getOutputLayer().getNeuronCount(); i++){
            outputNeuronsOutputForReturn.add(nScheme.getOutputLayer().getNeurons().get(i).getOutput());
        }

        return outputNeuronsOutputForReturn;
    }

    private void calculateErrorsAndDeltas(List<Double> expectedOutput) {       
        lastErrorMargin.clear();
        
        // Calcula o erro para a camada de output
        for(int i = 0; i < nScheme.getOutputLayer().getNeuronCount(); i++){
            double error = expectedOutput.get(i) - nScheme.getOutputLayer().getNeurons().get(i).getOutput();
            addLastErrorMargin(nScheme.getOutputLayer().getNeurons().get(i), error);
            
            globalNetworkError += Math.pow(error, 2);
        }
        
        // Calcula o erro para a camada hidden e input
        for(int layer = nScheme.getNeuronLayerCount() - 1; layer > 0; layer--){
            for(int i = 0; i < nScheme.getNeuronLayer(layer).getNeuronCount(); i++){
                for(int j = 0; j < nScheme.getNeuronLayer(layer - 1).getNeuronCount(); j++){
                    int synapseIndex = i * nScheme.getNeuronLayer(layer - 1).getNeuronCount() + j;
                    double error = nScheme.getSynapseLayer(layer - 1).getSynapses().get(synapseIndex).getWeight() *
                            getErrorDelta(nScheme.getNeuronLayer(layer).getNeurons().get(i));

                    addLastErrorMargin(nScheme.getNeuronLayer(layer - 1).getNeurons().get(j), error);
                }
            }
        }
    }

    protected void trainNetwork(List<Double> input, List<Double> expectedOutput) {
        runNeuralNetwork(input);
        
        // Calcula o erro e os coloca nos hashMaps
        calculateErrorsAndDeltas(expectedOutput);
        
        // Corrige todos os weights das sinapses
        for(int layer = 0; layer < nScheme.getSynapseLayerCount(); layer++){
            for(int i = 0; i < nScheme.getSynapseLayer(layer).getSynapseCount(); i++){           
                double delta = getErrorDelta(nScheme.getSynapseLayer(layer).getSynapses().get(i).getNeuronFront()) *
                        nScheme.getSynapseLayer(layer).getSynapses().get(i).getNeuronBack().getOutput();

                double weightDelta = (nConfig.getLearningRate() * delta) +
                        (nConfig.getMomentum() * getWeightDelta(nScheme.getSynapseLayer(layer).getSynapses().get(i)));

                setWeightDelta(nScheme.getSynapseLayer(layer).getSynapses().get(i), weightDelta);

                double weight = getWeightDelta(nScheme.getSynapseLayer(layer).getSynapses().get(i));
                nScheme.getSynapseLayer(layer).getSynapses().get(i).addWeight(weight);
            }
        }
        
        // Atualiza as bias dos neuronios da camada hidden-output
        for(int layer = 1; layer < nScheme.getNeuronLayerCount(); layer++){ // int i = 1 pois não queremos a primeira layer (input)
            for (int i = 0; i < nScheme.getNeuronLayer(layer).getNeuronCount(); i++) {
                double biasDelta = (nConfig.getLearningRate() * getErrorDelta(nScheme.getNeuronLayer(layer).getNeurons().get(i))) +
                        (nConfig.getMomentum() * getBiasDelta(nScheme.getNeuronLayer(layer).getNeurons().get(i)));

                setBiasDelta(nScheme.getNeuronLayer(layer).getNeurons().get(i), biasDelta);

                nScheme.getNeuronLayer(layer).getNeurons().get(i).addBias(biasDelta);
            }
        }
    }
    
    private double getWeightDelta(Synapse s){
        if(allWeightDeltas.containsKey(s)){
            return allWeightDeltas.get(s);
        }else{
            return 0.0;
        }
    }
    
    private void setWeightDelta(Synapse s, double d){
        if(allWeightDeltas.containsKey(s)){
            allWeightDeltas.replace(s, d);
        }else{
            allWeightDeltas.put(s, d);
        }
    }
    
    private void addLastErrorMargin(Neuron n, double val){
        if(lastErrorMargin.containsKey(n)){
            double previousValue = lastErrorMargin.get(n);
            lastErrorMargin.replace(n, previousValue + val);
        }else{
            lastErrorMargin.put(n, val);
        }
    }
    
    private double getLastErrorMargin(Neuron n){
        if(lastErrorMargin.containsKey(n)){
            return lastErrorMargin.get(n);
        }else{
            return 0.0;
        }
    }
    
    private void setBiasDelta(Neuron n, double val){
        if(allBiasDeltas.containsKey(n)){
            allBiasDeltas.replace(n, val);
        }else{
            allBiasDeltas.put(n, val);
        }
    }
    
    private double getBiasDelta(Neuron n){
        if(allBiasDeltas.containsKey(n)){
            return allBiasDeltas.get(n);
        }else{
            return 0.0;
        }
    }
    
    private double getErrorDelta(Neuron n){
        return getLastErrorMargin(n) * nConfig.getFunctions().derivativeActivationFunction(n.getRawOutput());
    }
    
    double getNetworkError(int completeTrainSetLength) {
        double error = Math.sqrt(globalNetworkError / (completeTrainSetLength * nScheme.getOutputLayer().getNeuronCount()));
        globalNetworkError = 0;
        return error;
    }
    
    NNScheme getScheme(){
        return nScheme;
    }
}
