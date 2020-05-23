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
