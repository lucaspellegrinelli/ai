<?php
	foreach (glob("objects/*.php") as $filename){
		include $filename;
	}
	
	$POPULATION_SIZE = 10;
	$GENE_SECTIONS = [
		new GAGeneSection("PointAX", 0, 1),
		new GAGeneSection("PointAY", 0, 1),
		new GAGeneSection("PointBX", 0, 1),
		new GAGeneSection("PointBY", 0, 1)
	];
	
	$MUTATION_RATE = 0.015;
	$TOURNAMENT_SIZE = 5;
	$ELITISM = true;
	
	
	class FitnessCalc implements GAFitnessCalculator{
		public function calculateFitness($gene){
			$pointsDeltaX = abs($gene->getSectionValue("PointAX") - $gene->getSectionValue("PointBX"));
			$pointsDeltaY = abs($gene->getSectionValue("PointAY") - $gene->getSectionValue("PointBY"));
			$distanceBetweenPoints = hypot($pointsDeltaX, $pointsDeltaY);
			return $distanceBetweenPoints;
		}
	}
          
	$FITNESS_CALCULATOR = new FitnessCalc();
		      
	$SHOW_DEBUG = true;
	$DEBUG_FREQUENCY = 100;
	$SHOW_FITTEST_INDIVIDUAL_GENE = true;
	
	$MIN_FITNESS_TO_STOP = 1.41;
	$MAX_NUMBER_OF_GENERATIONS = 1000;
        
	$schemeConfig = new GASchemeConfig($POPULATION_SIZE, $GENE_SECTIONS);
	
	$config = new GAConfig();
	$config->setMutationRate($MUTATION_RATE);
	$config->setTournamentSelectionSize($TOURNAMENT_SIZE);
	$config->setElitism($ELITISM);
	
	$debug = new GADebug();
	$debug->setDebug($SHOW_DEBUG);
	$debug->setDebugFrequency($DEBUG_FREQUENCY);
	$debug->setShowFittestGene($SHOW_FITTEST_INDIVIDUAL_GENE);
	
	$geneticAlgorithm = new GeneticAlgorithm();
	$geneticAlgorithm->setSchemeConfig($schemeConfig);
	$geneticAlgorithm->setFitnessCalc($FITNESS_CALCULATOR);
	$geneticAlgorithm->setConfig($config);
	$geneticAlgorithm->setDebug($debug);
	
	
	$result = $geneticAlgorithm->evolve($MIN_FITNESS_TO_STOP, $MAX_NUMBER_OF_GENERATIONS);
?>