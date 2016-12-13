package lucaspellegrinelli.ai.neuralnetwork;

import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSchemeConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNDebug;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSave;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNTrainingSet;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNOutputSet;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNInputSet;
import java.util.List;

public final class NeuralNetwork {
    private final NNCore nCore;
    private final NNDebug nDebug;
    private final NNSave nSave;
    
    /**
     * @param nSchemeConfig NNSchemeConfig object to specify the dimensions of the neural network
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     * @param nDebug NNDebug to specify if debugs will be shown in the console and in what rhythm
     */
    public NeuralNetwork(NNSchemeConfig nSchemeConfig, NNConfig nConfig, NNDebug nDebug){
        this.nCore = new NNCore(nSchemeConfig, nConfig);
        this.nDebug = nDebug;
        this.nSave = new NNSave("", false, false);
    }
    
    /**
     * @param nSchemeConfig NNSchemeConfig object to specify the dimensions of the neural network
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     */
    public NeuralNetwork(NNSchemeConfig nSchemeConfig, NNConfig nConfig){
        this.nCore = new NNCore(nSchemeConfig, nConfig);
        this.nDebug = new NNDebug(false, 0);
        this.nSave = new NNSave("", false, false);
    }
    
    /**
     * @param nSchemeConfig NNSchemeConfig object to specify the dimensions of the neural network
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     * @param nDebug NNDebug to specify if debugs will be shown in the console and in what rhythm
     * @param nSave NNSave to specify if the neural network will be loaded from a file, if after training it will
     * be saved and if any of this is going to happen, where the file the class will load from and save to is.
     */
    public NeuralNetwork(NNSchemeConfig nSchemeConfig, NNConfig nConfig, NNDebug nDebug, NNSave nSave){
        if(nSave.loadNeuralNetworkState()){
            this.nCore = new NNCore(nSave.loadNetwork(), nConfig);
        }else{
            this.nCore = new NNCore(nSchemeConfig, nConfig);
        }
        this.nDebug = nDebug;
        this.nSave = nSave;
    }
    
    /**
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     * @param nDebug NNDebug to specify if debugs will be shown in the console and in what rhythm
     * @param nSave NNSave to specify if the neural network will be loaded from a file, if after training it will
     * be saved and if any of this is going to happen, where the file the class will load from and save to is.
     */
    public NeuralNetwork(NNConfig nConfig, NNDebug nDebug, NNSave nSave){
        if(nSave.loadNeuralNetworkState()){
            this.nCore = new NNCore(nSave.loadNetwork(), nConfig);
        }else{
            this.nCore = null;
            System.out.println("You can only use the NeuralNetwork(NNConfig nConfig, "
                    + "NNDebug nDebug, NNSave nSave) constructor "
                    + "if the 'loadNeuralNetworkState' variable in the NNSave object "
                    + "is set to 'true' and a network save file exists in the folder specified."
                    + "\nAborting...");
            System.exit(1);
        }
        this.nDebug = nDebug;
        this.nSave = nSave;
    }
    
    /**
     * @param nSchemeConfig NNSchemeConfig object to specify the dimensions of the neural network
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     * @param nSave NNSave to specify if the neural network will be loaded from a file, if after training it will
     * be saved and if any of this is going to happen, where the file the class will load from and save to is.
     */
    public NeuralNetwork(NNSchemeConfig nSchemeConfig, NNConfig nConfig, NNSave nSave){
        if(nSave.loadNeuralNetworkState()){
            this.nCore = new NNCore(nSave.loadNetwork(), nConfig);
        }else{
            this.nCore = new NNCore(nSchemeConfig, nConfig);
        }
        this.nDebug = new NNDebug(false, 0);
        this.nSave = nSave;
    }
    
    /**
     * @param nConfig NNConfig to specify the Learning Rate, Momentum and Activation Function of the neural network
     * @param nSave NNSave to specify if the neural network will be loaded from a file, if after training it will
     * be saved and if any of this is going to happen, where the file the class will load from and save to is.
     */
    public NeuralNetwork(NNConfig nConfig, NNSave nSave){
        if(nSave.loadNeuralNetworkState()){
            this.nCore = new NNCore(nSave.loadNetwork(), nConfig);
        }else{
            this.nCore = null;
            System.out.println("You can only use the NeuralNetwork(NNConfig nConfig, NNSave nSave) constructor "
                    + "if the 'loadNeuralNetworkState' variable in the NNSave object "
                    + "is set to 'true' and a network save file exists in the folder specified."
                    + "\nAborting...");
            System.exit(1);
        }
        this.nDebug = new NNDebug(false, 0);
        this.nSave = nSave;
    }
    
    public NeuralNetwork(NNSave nSave){
        if(nSave.loadNeuralNetworkState()){
            this.nCore = new NNCore(nSave.loadNetwork(), new NNConfig(0.0, 0.0));
        }else{
            this.nCore = null;
            System.out.println("You can only use the NeuralNetwork(NNConfig nConfig, NNSave nSave) constructor "
                    + "if the 'loadNeuralNetworkState' variable in the NNSave object "
                    + "is set to 'true' and a network save file exists in the folder specified."
                    + "\nAborting...");
            System.exit(1);
        }
        this.nDebug = new NNDebug(false, 0);
        this.nSave = nSave;
    }
    
    /**
     * Run the network on a InputSet
     * @param input NNInputSet object The inputs that will be put in the Input Neurons
     * @return The output from all the Output Neurons
     */
    public List<Double> runNetwork(NNInputSet input){
        return nCore.runNeuralNetwork(input.getInputSet());
    }
    
    /**
     * Trains the network using a TrainingSet
     * @param nTrainingSet NNTrainingSet object the contains various inputs and expected outputs so the network
     * can train
     * @param percErrorToStop double value that specifies the maximum error the network can have
     * so it can stop training.
     */
    public void trainNetwork(NNTrainingSet nTrainingSet, double percErrorToStop){
        int iterationCount = 0;
        while(true){
            for(NNInputSet nInput : nTrainingSet.getTrainingSet().keySet()){
                NNOutputSet nOutput = nTrainingSet.getOutputForInput(nInput);
                
                nCore.trainNetwork(nInput.getInputSet(), nOutput.getOutputSet());
            }
            
            double error = nCore.getNetworkError(nTrainingSet.getLength()) * 100.0;
            if(error <= percErrorToStop){
                if(nDebug.isDebug()){
                    showDebug(iterationCount, error);
                }
                break;
            }else if(nDebug.isDebug() && iterationCount % nDebug.getDebugFrequency() == 0){
                showDebug(iterationCount, error);
            }
            iterationCount++;
        }
        
        if(nSave.saveAfterTrain()){
            nSave.saveNetwork(nCore.getScheme());
        }
    }
    

    private void showDebug(int iteration, double error){
        System.out.println("Iteration #" + iteration + " -> Error: " + error + "%");
    }
}