package lucaspellegrinelli.ai.neuralnetwork;

import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSchemeConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.SynapseLayer;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.NeuronLayer;
import java.util.ArrayList;
import java.util.List;

public final class NNScheme {
    private final List<NeuronLayer> neuronLayers = new ArrayList<>();
    private final List<SynapseLayer> synapseLayers = new ArrayList<>();
    
    NNScheme(NNSchemeConfig nSchemeConfig){
        for(int i = 0; i < nSchemeConfig.getHiddenLayerCount() + 2; i++){
            NeuronLayer nLayer;
            if(i == 0){
                nLayer = new NeuronLayer(nSchemeConfig.getInputNeuronCount());
            }else if(i == nSchemeConfig.getHiddenLayerCount() + 1){
                nLayer = new NeuronLayer(nSchemeConfig.getOutputNeuronCount());
            }else{
                nLayer = new NeuronLayer(nSchemeConfig.getHiddenLayerNeuronCount());
            }
            addNeuronLayer(nLayer);
        }
        
        for(int i = 0; i < nSchemeConfig.getHiddenLayerCount() + 1; i++){
            SynapseLayer sLayer = new SynapseLayer(getNeuronLayer(i), getNeuronLayer(i + 1));
            addSynapseLayer(sLayer);
        }
    }
    
    public NNScheme(NNSchemeConfig nSchemeConfig, List<Double> synapseWeights, List<Double> neuronBias){
        List<Double> inputBias = neuronBias.subList(0, nSchemeConfig.getInputNeuronCount());
        
        int hiddenStart = nSchemeConfig.getInputNeuronCount();
        List<List<Double>> hiddenBias = new ArrayList<>();
        for(int i = 0; i < nSchemeConfig.getHiddenLayerCount(); i++){
            int start = hiddenStart + nSchemeConfig.getHiddenLayerNeuronCount() * i;
            int end = start + nSchemeConfig.getHiddenLayerNeuronCount();
            List<Double> hiddenLayerBias = neuronBias.subList(start, end);
            hiddenBias.add(hiddenLayerBias);
        }
        
        int outputStart = nSchemeConfig.getInputNeuronCount() + nSchemeConfig.getHiddenLayerCount() * nSchemeConfig.getHiddenLayerNeuronCount();
        int outputEnd = outputStart + nSchemeConfig.getOutputNeuronCount();
        List<Double> outputBias = neuronBias.subList(outputStart, outputEnd);
        
        for(int i = 0; i < nSchemeConfig.getHiddenLayerCount() + 2; i++){
            NeuronLayer nLayer;
            if(i == 0){
                nLayer = new NeuronLayer(inputBias);
            }else if(i == nSchemeConfig.getHiddenLayerCount() + 1){
                nLayer = new NeuronLayer(outputBias);
            }else{
                nLayer = new NeuronLayer(hiddenBias.get(i - 1));
            }
            addNeuronLayer(nLayer);
        }
        
        int synCount = 0;
        for(int i = 0; i < nSchemeConfig.getHiddenLayerCount() + 1; i++){
            int howMuchSyn = getNeuronLayer(i).getNeuronCount() * getNeuronLayer(i + 1).getNeuronCount();
            List<Double> weights = synapseWeights.subList(synCount, howMuchSyn + synCount);
            SynapseLayer sLayer = new SynapseLayer(getNeuronLayer(i), getNeuronLayer(i + 1), weights);
            addSynapseLayer(sLayer);
            synCount += howMuchSyn;
        }
    }
    
    private void addNeuronLayer(NeuronLayer neuronLayer){
        getNeuronLayers().add(neuronLayer);
    }
    
    public NeuronLayer getNeuronLayer(int index){
        return getNeuronLayers().get(index);
    }
    
    public int getNeuronLayerCount(){
        return getNeuronLayers().size();
    }
    
    public NeuronLayer getInputLayer(){
        return getNeuronLayers().get(0);
    }
    
    public NeuronLayer getOutputLayer(){
        return getNeuronLayers().get(getNeuronLayers().size() - 1);
    }
    
    private void addSynapseLayer(SynapseLayer synapseLayer){
        getSynapseLayers().add(synapseLayer);
    }
    
    int getSynapseLayerCount(){
        return getSynapseLayers().size();
    }
    
    SynapseLayer getSynapseLayer(int index){
        return getSynapseLayers().get(index);
    }

    public List<NeuronLayer> getNeuronLayers() {
        return neuronLayers;
    }

    public List<SynapseLayer> getSynapseLayers() {
        return synapseLayers;
    }
}
