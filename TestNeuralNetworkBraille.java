import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNTrainingSet;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNInputSet;
import lucaspellegrinelli.ai.neuralnetwork.NeuralNetwork;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNDebug;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSchemeConfig;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNSave;
import lucaspellegrinelli.ai.neuralnetwork.objects.builder.NNConfig;
import java.util.List;
import lucaspellegrinelli.ai.neuralnetwork.objects.core.NNActivationFunction;

public class TestNeuralNetworkBraille {
    public static void main(String[] args){
        final int INPUT_NEURONS = 6;
        final int HIDDEN_LAYERS = 1;
        final int NEURONS_EACH_HIDDEN_LAYER = 6;
        final int OUTPUT_NEURONS = 1;
        
        final double LEARNING_RATE = 0.7;
        final double MOMENTUM = 0.5;
        final NNActivationFunction ACT_FUNCTION = NNActivationFunction.GAUSSIAN;
        
        final double ERROR_TO_STOP = 0.25;
        
        final boolean SHOW_DEBUG = true;
        final int DEBUG_FREQUENCY = 1000;
        
        final String PATH_TO_SAVE = "C:\\Users\\lucas\\Desktop\\";
        final boolean SAVE_AFTER_TRAIN = false;
        final boolean TRY_TO_LOAD = true;
        
        NNSchemeConfig nSchemeConfig = new NNSchemeConfig(INPUT_NEURONS, HIDDEN_LAYERS, NEURONS_EACH_HIDDEN_LAYER, OUTPUT_NEURONS);
        NNConfig nConfig = new NNConfig(LEARNING_RATE, MOMENTUM, ACT_FUNCTION);
        NNDebug nDebug = new NNDebug(SHOW_DEBUG, DEBUG_FREQUENCY);
        NNSave nSave = new NNSave(PATH_TO_SAVE, SAVE_AFTER_TRAIN, TRY_TO_LOAD);
        
        NeuralNetwork neuralNetwork = new NeuralNetwork(nSchemeConfig, nConfig, nDebug, nSave);
        
        // Braile Example
        double[][] inputs = {
            {1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // A
            {1.0, 0.0, 1.0, 0.0, 0.0, 0.0}, // B
            {1.0, 1.0, 0.0, 0.0, 0.0, 0.0}, // C
            {1.0, 1.0, 0.0, 1.0, 0.0, 0.0}, // D
            {1.0, 0.0, 0.0, 1.0, 0.0, 0.0}, // E
            {1.0, 1.0, 1.0, 0.0, 0.0, 0.0}, // F
            {1.0, 1.0, 1.0, 1.0, 0.0, 0.0}, // G
            {1.0, 0.0, 1.0, 1.0, 0.0, 0.0}, // H
            {0.0, 1.0, 1.0, 0.0, 0.0, 0.0}, // I
            {0.0, 1.0, 1.0, 1.0, 0.0, 0.0}, // J
            {1.0, 0.0, 0.0, 0.0, 1.0, 0.0}, // K
            {1.0, 0.0, 1.0, 0.0, 1.0, 0.0}, // L
            {1.0, 1.0, 0.0, 0.0, 1.0, 0.0}, // M
            {1.0, 1.0, 0.0, 1.0, 1.0, 0.0}, // N
            {1.0, 0.0, 0.0, 1.0, 1.0, 0.0}, // O
            {1.0, 1.0, 1.0, 0.0, 1.0, 0.0}, // P
            {1.0, 1.0, 1.0, 1.0, 1.0, 0.0}, // Q
            {1.0, 0.0, 1.0, 1.0, 1.0, 0.0}, // R
            {0.0, 1.0, 1.0, 0.0, 1.0, 0.0}, // S
            {0.0, 1.0, 1.0, 1.0, 1.0, 0.0}, // T
            {1.0, 0.0, 0.0, 0.0, 1.0, 1.0}, // U
            {1.0, 0.0, 1.0, 0.0, 1.0, 1.0}, // V
            {0.0, 1.0, 1.0, 1.0, 0.0, 1.0}, // W
            {1.0, 1.0, 0.0, 0.0, 1.0, 1.0}, // X
            {1.0, 1.0, 0.0, 1.0, 1.0, 1.0}, // Y
            {1.0, 0.0, 0.0, 1.0, 1.0, 1.0} // Z
        };
        
        double[][] outputs = {
            {(((int)'A') - 64) / 26.0},
            {(((int)'B') - 64) / 26.0},
            {(((int)'C') - 64) / 26.0},
            {(((int)'D') - 64) / 26.0},
            {(((int)'E') - 64) / 26.0},
            {(((int)'F') - 64) / 26.0},
            {(((int)'G') - 64) / 26.0},
            {(((int)'H') - 64) / 26.0},
            {(((int)'I') - 64) / 26.0},
            {(((int)'J') - 64) / 26.0},
            {(((int)'K') - 64) / 26.0},
            {(((int)'L') - 64) / 26.0},
            {(((int)'M') - 64) / 26.0},
            {(((int)'N') - 64) / 26.0},
            {(((int)'O') - 64) / 26.0},
            {(((int)'P') - 64) / 26.0},
            {(((int)'Q') - 64) / 26.0},
            {(((int)'R') - 64) / 26.0},
            {(((int)'S') - 64) / 26.0},
            {(((int)'T') - 64) / 26.0},
            {(((int)'U') - 64) / 26.0},
            {(((int)'V') - 64) / 26.0},
            {(((int)'W') - 64) / 26.0},
            {(((int)'X') - 64) / 26.0},
            {(((int)'Y') - 64) / 26.0},
            {(((int)'Z') - 64) / 26.0},
        };
        
        System.out.println("Before training:");
        testInputs(neuralNetwork, inputs);
        System.out.println("");
        
        if(!TRY_TO_LOAD){
            NNTrainingSet trainingSet = new NNTrainingSet(inputs, outputs);
            neuralNetwork.trainNetwork(trainingSet, ERROR_TO_STOP);

            System.out.println("After training:");
            testInputs(neuralNetwork, inputs);
            System.out.println("");
        }
    }
    
    private static void testInputs(NeuralNetwork neuralNetwork, double[][] inputs){
        for(int i = 0; i < inputs.length; i++){
            List<Double> outputsCalculated = neuralNetwork.runNetwork(new NNInputSet(inputs[i]));
            for(int j = 0; j < inputs[0].length; j++){
                if(j >= inputs[i].length) continue;
                System.out.print(inputs[i][j] + "  ");
            }
            System.out.println("=  " + ((char)(Math.round(outputsCalculated.get(0) * 26.0) + 64)));
        }
    }
}
