# Genetic Algorithm

## The example definition
In this whole explanation text we will be using a common example that follows:
```
The aim of the algorithm is to determine 2D positions (x, y) to 2 points in a 1x1 unit square trying to make
them as most far apart from each other as possible.
```

## Creating your genetic algorithm object

To create your ```GeneticAlgorithm``` object is simple. The most simple constructor has 3 parameters: a ```GASchemeConfig```, a ```GAConfig```
and a ```GAFitnessCalculator```.

The ```GASchemeConfig``` is used to determine how many individuals will be created in each population and how the gene of each individual
will be. This gene configuration is a list of "Gene Sections", used as a ```List<GAGeneSection>``` object.

The ```GAGeneSection``` is nothing more than a part of the gene as the name suggests. It has a ```name```, a ```minValue``` and a ```maxValue```.
An individual can have as much gene sections as you want. This ideia will be better understood with an example.

```java
List<GAGeneSection> GENE_SECTIONS = Arrays.asList(
        new GAGeneSection("PointAX", 0, 1), // The x value of the point A can have values from 0 to 1 since the square is 1x1 unit.
        new GAGeneSection("PointAY", 0, 1), // The y value of the point A can have values from 0 to 1 since the square is 1x1 unit.
        new GAGeneSection("PointBX", 0, 1), // The x value of the point B can have values from 0 to 1 since the square is 1x1 unit.
        new GAGeneSection("PointBY", 0, 1) // The y value of the point B can have values from 0 to 1 since the square is 1x1 unit.
);
```
In this code, we created a gene that contains 4 sections, each section has a name (PointAX, PointAY...) a minimum value and a maximum value.
These are the genes that will be changed by the algorithm aiming to fit the fitness function defined next.

With the ```List<GAGeneSection>``` we can create our ```GASchemeConfig``` object:
```java
final int POPULATION_SIZE = 10;
GASchemeConfig schemeConfig = new GASchemeConfig(POPULATION_SIZE, GENE_SECTIONS);
```

The next object we need to create is the ```GAConfig``` object. It has many different constructors, we will be using the most complete one,
the one that takes the ```mutation rate```, the ```tournament selection size``` and the ```elitism``` boolean.

```java
final double MUTATION_RATE = 0.015;
final int TOURNAMENT_SIZE = 5;
final boolean ELITISM = true;
GAConfig config = new GAConfig(MUTATION_RATE, TOURNAMENT_SIZE, ELITISM);
```

Finally, the last object is a ```GAFitnessCalculator``` object that is responsible for defining how the fitness calculation will be.

In my case the fitness function was simple the 2D distance from one point to another. (The fitness function must always follow the ideia
that the greater the fitnessi, the better it is).

```java
final GAFitnessCalculator FITNESS_CALCULATOR = new GAFitnessCalculator() {
    @Override
    public double calculateFitness(GeneSet gene) {
        double pointsDeltaX = Math.abs(gene.getSectionValue("PointAX") - gene.getSectionValue("PointBX")); // Delta X
        double pointsDeltaY = Math.abs(gene.getSectionValue("PointAY") - gene.getSectionValue("PointBY")); // Delta X
        double distanceBetweenPoints = Math.hypot(pointsDeltaX, pointsDeltaY); // Hipotenuse = sqrt(deltaX^2 + deltaY^2)
        return distanceBetweenPoints;
    }
};
```

Now what's left is to create the ```GeneticAlgorithm``` object:
```java
GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(schemeConfig, config, FITNESS_CALCULATOR);
```

### Other GeneticAlgorithm constructor options
You can also use a ```GADebug``` object in the constructor. It is responsible for defining how and when the algorithm should print debug/
progress messages in the console.

The object takes 1, 2 or 3 that defines if there will be any debug messages at all, the frequency of the messages and if at each message,
the gene of the fittest individual should be printed as well.
```java
final boolean SHOW_DEBUG = true;
final int DEBUG_FREQUENCY = 100; // Optional
final boolean SHOW_FITTEST_INDIVIDUAL_GENE = true; // Optional
GADebug debug = new GADebug(SHOW_DEBUG, DEBUG_FREQUENCY, SHOW_FITTEST_INDIVIDUAL_GENE);
```
And if you choose to create the ```GADebug``` object, the ```GeneticAlgorithm``` constructor will be like:
```java
GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(schemeConfig, config, debug, FITNESS_CALCULATOR);
```

## Evolving the generations
To make the algorithm actually work, we need to evolve the generations right? Yes. And it is veeery simple.

All you need to do is define 2 variables controlling the minimum acceptable fitness so the algorithm can stop and a limit to the generation
count so the algorithm won't run forever if the minimum acceptable fitness is never achieved.

After that just call the ```evolve``` method in the ```GeneticAlgorithm``` object saving the result in a ```GAResult``` variable and
you are done!

```java
GAResult result = geneticAlgorithm.evolve(MIN_FITNESS_TO_STOP, MAX_NUMBER_OF_GENERATIONS);
```

From the ```GAResult``` object, you can get the values of each gene section of the fittest individual using the ```getSectionValue(String sectionName)``` method
and the number of generation the algorithm took to create this individual using the ```getNumberOfGenerations()``` method.

```java
int numberOfGenerationItTook = result.getNumberOfGenerations();
double pointAXValue = result.getSectionValue("PointAX");
double pointAYValue = result.getSectionValue("PointAY");
double pointBXValue = result.getSectionValue("PointBX");
double pointBYValue = result.getSectionValue("PointBY");
```
If everything goes as planned, the fitness of the fittest individual should be around ```sqrt(2) ~ 1.4``` because the longest distance
from one point to another in a square is its diagonal and since the diagonal of a 1 unit square is sqrt(2), the fitness should be around that.

The section values should be around 0.99 and 0.01 meaning that the points are close to the sides of the square, which makes sense!
