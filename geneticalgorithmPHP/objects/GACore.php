<?php
class GACore{
	private $config; // GAConfig
	private $fitnessCalc; // GAFitnessCalculator
	
	public function __construct($config, $fitnessCalc){
		$this->config = $config;
		$this->fitnessCalc = $fitnessCalc;
	}
	
	public function evolvePopulation($population){ // Returns Population
		$newPopulation = new Population(null, false);
		$elitismOffset = 0;
		
		if($this->config->isElitism()){
			$elitismOffset = 1;
			$newPopulation->saveIndividual(self::getFittestIndividual($population));
		}
		
		for ($i = $elitismOffset; $i < $population->getPopulationCount(); $i++) {
            $firstIndv = self::tournamentSelection($population);
            $secndIndv = self::tournamentSelection($population);
            $newIndiv = self::crossoverIndividuals($firstIndv, $secndIndv);
            $newPopulation->saveIndividual($newIndiv);
        }
		
		for ($i = $elitismOffset; $i < $newPopulation->getPopulationCount(); $i++) {
            $newPopulation->getIndividual($i)->mutate($this->config->getMutationRate());
        }

        return $newPopulation;
	}
	

    
    public function getFittestIndividual($population) {
        $fittestFitness = $this->fitnessCalc->calculateFitness($population->getIndividual(0)->getGeneSet());
        $population->getIndividual(0)->setFitness($fittestFitness);
        $fittest = $population->getIndividual(0);

        for ($i = 0; $i < $population->getPopulationCount(); $i++) {
            $currentIndFitness = $this->fitnessCalc->calculateFitness($population->getIndividual($i)->getGeneSet());
            $population->getIndividual($i)->setFitness($currentIndFitness);

            if ($fittestFitness <= $currentIndFitness) {
                $fittest = $population->getIndividual($i);
                $fittestFitness = $currentIndFitness;
            }
        }

        return $fittest;
    }

    private function tournamentSelection($population) {
        $tournamentPopulation = new Population(null, false);
        
        if($this->config->getTournamentSelectionSize() > 0){
            for ($i = 0; $i < $this->config->getTournamentSelectionSize(); $i++) {
                $index = intval((mt_rand() / mt_getrandmax()) * $population->getPopulationCount());
                $tournamentPopulation->saveIndividual($population->getIndividual($index));
            }
        }else{
            $tournamentPopulation->saveIndividual(self::getFittestIndividual($population));
        }

        $fittest = self::getFittestIndividual($tournamentPopulation);
        return $fittest;
    }
    
    private function crossoverIndividuals($firstIndividual, $secondIndividual) {
        $crossOvered = new Individual();

		for($i = 0; $i < count($firstIndividual->getGeneSet()->getSectionNames()); $i++){
			$geneSection = $firstIndividual->getGeneSet()->getSectionNames()[$i];
			
			$newSectionValue;
			
			if ((mt_rand() / mt_getrandmax()) <= 0.5) {
                $newSectionValue = $firstIndividual->getGeneSet()->getSectionValue($geneSection);
            } else {
                $newSectionValue = $secondIndividual->getGeneSet()->getSectionValue($geneSection);
            }
			
			$crossOvered->getGeneSet()->setSectionValue($geneSection, $newSectionValue);
		}

        return $crossOvered;
    }
}
?>