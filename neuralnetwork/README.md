# Neural Network

## Creating the Neural Network object
To create your ```NeuralNetwork``` object is very simple. The most basic constructor take only 2 parameters: A ```NNSchemeConfig``` and a ```NNConfig``` objects!
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

### To be continued....
