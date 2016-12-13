# Neural Network

## Creating the Neural Network object
To create your ```NeuralNetwork``` object is very simple. The most basic constructor takes only 2 parameters: A ```NNSchemeConfig``` and a ```NNConfig``` objects!

The ```NNSchemeConfig``` is responsible for defining how much neurons will be created on the Input Layer, the number of hidden layers, the number of neurons on each hidden layer and the number of output layers.
```java
final int INPUT_NEURONS = 6;
final int HIDDEN_LAYERS = 1;
final int NEURONS_EACH_HIDDEN_LAYER = 6;
final int OUTPUT_NEURONS = 1;
NNSchemeConfig nSchemeConfig = new NNSchemeConfig(INPUT_NEURONS, HIDDEN_LAYERS, NEURONS_EACH_HIDDEN_LAYER, OUTPUT_NEURONS);
```

The ```NNConfig``` is responsible for defining the learning rate, the momentum and the activation function!
```java
final double LEARNING_RATE = 0.7;
final double MOMENTUM = 0.5;
final NNActivationFunction ACT_FUNCTION = NNActivationFunction.SOFT_STEP;
NNConfig nConfig = new NNConfig(LEARNING_RATE, MOMENTUM, ACT_FUNCTION);
```

And finally, to create our ```NeuralNetwork``` object we can simply
```java
NeuralNetwork neuralNetwork = new NeuralNetwork(nSchemeConfig, nConfig);
```
#### Other NeuralNetwork constructor options
But you can also use a ```NNDebug``` and a ```NNSave``` object in the constructor.

The ```NNDebug``` object is used to define if you want debug messages to be shown in the console while training the network and in which rate these messages will appear.
```java
final boolean SHOW_DEBUG = true;
final int DEBUG_FREQUENCY = 1000;
NNDebug nDebug = new NNDebug(SHOW_DEBUG, DEBUG_FREQUENCY);
```
The ```NNSave``` object is used to define if you want to load a neural network from a ```.txt``` file, if you want to save the network in a ```.txt``` file after training and where should the ```.txt``` file be saved/loaded from.
```java
final String PATH_TO_SAVE = "C:\\Users\\lucas\\Desktop\\";
final boolean SAVE_AFTER_TRAIN = false;
final boolean TRY_TO_LOAD = true;
NNSave nSave = new NNSave(PATH_TO_SAVE, SAVE_AFTER_TRAIN, TRY_TO_LOAD);
```
An example of ```NeuralNetwork``` constructor using all these objects would be:
```java
NeuralNetwork neuralNetwork = new NeuralNetwork(nSchemeConfig, nConfig, nDebug, nSave);
```

## Training the Neural Network
To train the network is also pretty simple, we need a Training set and guess how do you create it. Yes a ```NNTrainingSet``` object!

In this example, I will show how to train a network to recognize ```Braille``` symbols!

In the braile language, the letters are written in 6 dots, that can be flat with the paper or outjogged, arranged in 3 lines with 2 dots each. For simplicity sake we will use a vector with 6 positions. The dots flat with the paper will be represented like 0's and the outjogged ones represented like 1's.

**The braille alphabet:**

<img src="http://www.projetoacesso.org.br/site/images/Screen%20Shot%202012-12-06%20at%204.46.41%20PM.png"/>

The inputs (remember that the size of the arrays must be equal to the number of input neurons defined in the ```NNSchemeConfig```!):
```java
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
```

And the outputs for each input (remember that the size of the arrays must be equal to the number of output neurons defined in the ```NNSchemeConfig```!):
```java
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
```
After that we will create the ```NNTrainingSet``` object with the inputs and outputs we just defined.
```java
NNTrainingSet trainingSet = new NNTrainingSet(inputs, outputs);
```
And finally we can train our network with the method ```trainNetwork``` specifying the maximum error the network can have so it can stop training.
```java
final double ERROR_TO_STOP = 0.25;
neuralNetwork.trainNetwork(trainingSet, ERROR_TO_STOP);
```
And after the program runs, you have successfully trained your network!

## Running the network
To run the neural network with a custom input is also veeery simple.

All you need to have is a Input Set (yes, a ```NNInputSet``` object!) with the input you want to run like:
```java
double[] input = {1.0, 1.0, 0.0, 0.0, 0.0, 0.0}; // C
NNInputSet inputSet = new NNInputSet(input);
```

And then simply call the ```runNetwork``` method that will return a list of doubles (```List<Double>```) with all the values of the output neurons!
```java
List<Double> outputs = neuralNetwork.runNetwork(inputSet);
```

And you just successfully created a neural network!
