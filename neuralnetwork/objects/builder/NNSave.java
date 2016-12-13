package lucaspellegrinelli.ai.neuralnetwork.objects.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lucaspellegrinelli.ai.neuralnetwork.NNScheme;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.Neuron;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.NeuronLayer;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.Synapse;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.SynapseLayer;
import lucaspellegrinelli.ai.neuralnetwork.util.FileManager;

public class NNSave {
    private String folderToSaveIn;
    private boolean saveAfterTrain;
    private boolean loadNeuralNetworkState;
    private String fileName;
    
    private final String SECTION_SEPARATOR = System.getProperty("line.separator");
    
    private static final String DEFAULT_FILE_NAME = "NeuralNetworkSave";
    private static final String FILE_EXTENSION = ".txt";
    
    /**
     * 
     * @param folderToSaveIn Path to the folder the save file will be saved/read from
     * @param saveAfterTrain Defines if after training the network will be saved in a file
     * @param loadNeuralNetworkState Defines if the network will be loaded from a file instead of created again
     */
    public NNSave(String folderToSaveIn, boolean saveAfterTrain, boolean loadNeuralNetworkState){
        setFolderToSaveIn(folderToSaveIn);
        this.saveAfterTrain = saveAfterTrain;
        this.loadNeuralNetworkState = loadNeuralNetworkState;
        this.fileName = DEFAULT_FILE_NAME;
    }
    
    /**
     * 
     * @param folderToSaveIn Path to the folder the save file will be saved/read from
     * @param fileName The name of the file that will be created/loaded
     * @param saveAfterTrain Defines if after training the network will be saved in a file
     * @param loadNeuralNetworkState  Defines if the network will be loaded from a file instead of created again
     */
    public NNSave(String folderToSaveIn, String fileName, boolean saveAfterTrain, boolean loadNeuralNetworkState){
        setFolderToSaveIn(folderToSaveIn);
        this.saveAfterTrain = saveAfterTrain;
        this.loadNeuralNetworkState = loadNeuralNetworkState;
        this.fileName = fileName;
    }

    public String getFolderToSaveIn() {
        return folderToSaveIn;
    }

    public final void setFolderToSaveIn(String folderToSaveIn) {
        folderToSaveIn = folderToSaveIn.replace("/", "\\");
        if(folderToSaveIn.endsWith("\\")){
            this.folderToSaveIn = folderToSaveIn;
        }else{
            this.folderToSaveIn = folderToSaveIn + "\\";
        }
    }
    
    /**
     * 
     * @param nScheme Saves the network in a file
     */
    public void saveNetwork(NNScheme nScheme){
        String scheme = schemeToString(nScheme) + SECTION_SEPARATOR;
        String weights = synapseLayersToString(nScheme) + SECTION_SEPARATOR;
        String bias = neuronLayersToString(nScheme);
        
        FileManager.saveFile(folderToSaveIn + fileName + FILE_EXTENSION, scheme + weights + bias);
    }
    
    /**
     * 
     * @return The Neural network scheme loaded from the file
     */
    public NNScheme loadNetwork(){
        String content = FileManager.readFile(folderToSaveIn + fileName + FILE_EXTENSION);
        String[] lineSplit = content.split(SECTION_SEPARATOR);
        String schemeContent = removeFirstLastCharacter(lineSplit[0]);
        String weightContent = removeFirstLastCharacter(lineSplit[1]);
        String biasContent = removeFirstLastCharacter(lineSplit[2]);
        
        String[] schemeValues = schemeContent.split(",");
        int inputNeurons = Integer.parseInt(schemeValues[0]);
        int hiddenLayers = Integer.parseInt(schemeValues[1]);
        int hiddenNeuronsPerLayer = Integer.parseInt(schemeValues[2]);
        int outputNeurons = Integer.parseInt(schemeValues[3]);
        
        String[] weightValues = weightContent.split(",");
        List<Double> weightConvertedValues = new ArrayList<>();
        for(String s : weightValues){
            weightConvertedValues.add(Double.parseDouble(s));
        }
        
        String[] biasValues = biasContent.split(",");
        List<Double> biasConvertedValues = new ArrayList<>();
        for(String s : biasValues){
            biasConvertedValues.add(Double.parseDouble(s));
        }
        
        int biasExpected = inputNeurons + hiddenLayers * hiddenNeuronsPerLayer + outputNeurons;
        if(biasValues.length != biasExpected){
            Logger.getLogger(NNSave.class.getName()).log(Level.WARNING, "File corrupted while loading.\nNumber of bias found in the file ({0}) does not match the number of neurons defined in the file ({1})\nAborting...", new Object[]{biasValues.length, biasExpected});
            System.exit(1);
        }
        
        int synapsesExpected = (inputNeurons * hiddenNeuronsPerLayer) + ((hiddenLayers - 1) * (int)Math.pow(hiddenNeuronsPerLayer, 2)) + (outputNeurons * hiddenNeuronsPerLayer);
        if(weightValues.length != synapsesExpected){
            Logger.getLogger(NNSave.class.getName()).log(Level.WARNING, "File corrupted while loading.\nNumber of synapses found in the file ({0}) does not match the number of synapses expected ({1})\nAborting...", new Object[]{weightValues.length, synapsesExpected});
            System.exit(1);
        }
        
        NNSchemeConfig nSchemeConfig = new NNSchemeConfig(inputNeurons, hiddenLayers, hiddenNeuronsPerLayer, outputNeurons);
        return new NNScheme(nSchemeConfig, weightConvertedValues, biasConvertedValues);
    }
    
    private String schemeToString(NNScheme nScheme){
        int inputNeurons = nScheme.getInputLayer().getNeuronCount();
        int hiddenLayers = nScheme.getNeuronLayerCount() - 2;
        int hiddenNeuronsPerLayer = nScheme.getNeuronLayer(1).getNeuronCount();
        int outputNeurons = nScheme.getOutputLayer().getNeuronCount();
        
        return "[" + inputNeurons + "," + hiddenLayers + "," + hiddenNeuronsPerLayer + "," + outputNeurons + "]";
    }
    
    private String synapseLayersToString(NNScheme nScheme){
        String out = "[";
        for(SynapseLayer synLayer : nScheme.getSynapseLayers()){
            for (Synapse s : synLayer.getSynapses()) {
                out += s.getWeight() + ",";
            }
        }
        
        return removeLastCharacter(out) + "]";
    }
    
    private String neuronLayersToString(NNScheme nScheme){
        String out = "[";
        for(NeuronLayer neuronLayer : nScheme.getNeuronLayers()){
            for (Neuron n : neuronLayer.getNeurons()) {
                out += n.getBias() + ",";
            }
        }
        
        return removeLastCharacter(out) + "]";
    }
    
    private String removeLastCharacter(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    
    private String removeFirstLastCharacter(String str) {
        if (str != null && str.length() > 1) {
            str = str.substring(1, str.length() - 1);
        }
        return str;
    }

    public boolean saveAfterTrain() {
        return saveAfterTrain;
    }

    public void setSaveAfterTrain(boolean saveAfterTrain) {
        this.saveAfterTrain = saveAfterTrain;
    }

    public boolean loadNeuralNetworkState() {
        File f = new File(folderToSaveIn + "NeuralNetworkSave.txt");
        return loadNeuralNetworkState && f.exists() && !f.isDirectory();
    }

    public void setLoadNeuralNetworkState(boolean loadNeuralNetworkState) {
        this.loadNeuralNetworkState = loadNeuralNetworkState;
    }
    
}
