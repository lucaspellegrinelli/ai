<?php
class GeneticAlgorithm{
	private $schemeConfig;
    private $fitnessCalc;
    private $config;
    private $debug;
	
	public function __construct(){
		//
	}
	
	public function setSchemeConfig($schemeConfig){
		GeneSet::setGeneSections($schemeConfig->getGeneConfiguration());
		$this->schemeConfig = $schemeConfig;
	}
	
	public function setFitnessCalc($fitnessCalc){
		$this->fitnessCalc = $fitnessCalc;
	}
	
	public function setConfig($config){
		$this->config = $config;
	}
	
	public function setDebug($debug){
		$this->debug = $debug;
	}

    public function evolve($minAcceptableFitness, $maxNumberOfGenerations) {
        $core = new GACore($this->config, $this->fitnessCalc);
        
        $population = new Population($this->schemeConfig->getPopulationSize(), true);

        $currentGenerationCount = 0;
        if ($this->debug->isDebug() && $currentGenerationCount % $this->debug->getDebugFrequency() == 0) {
            self::showDebug($currentGenerationCount, $core->getFittestIndividual($population)->getFitness());
            if ($this->debug->isShowFittestGene()) {
                self::showGeneSet($core->getFittestIndividual($population));
            }
        }
        
        // Each loop corresponds to one generation
        while ($core->getFittestIndividual($population)->getFitness() < $minAcceptableFitness && $currentGenerationCount < $maxNumberOfGenerations) {
            $currentGenerationCount++;
            
            if($this->debug->isDebug() && $currentGenerationCount % $this->debug->getDebugFrequency() == 0){
                self::showDebug($currentGenerationCount, $core->getFittestIndividual($population)->getFitness());
                if($this->debug->isShowFittestGene()){
                    self::showGeneSet($core->getFittestIndividual($population));
                }
            }

            $population = $core->evolvePopulation($population);
        }
        
        if ($this->debug->isDebug() && $currentGenerationCount < $maxNumberOfGenerations) {
            self::showDebug($currentGenerationCount, $core->getFittestIndividual($population)->getFitness());
            if ($this->debug->isShowFittestGene()) {
                self::showGeneSet($core->getFittestIndividual($population));
            }
        }
        
        $result = new GAResult($core->getFittestIndividual($population)->getGeneSet(), $currentGenerationCount);
        return $result;
    }
    
    private function showDebug($generation, $bestFitness){
        echo("#" . $generation . " -> The fittest fitness: " . $bestFitness . "<br/>");
    }
    
    private function showGeneSet($indv){
		//echo "<pre>" . var_export($indv->getGeneSet()->getSectionNames(), true) . "</pre>";
        $sections = "Fittest individual gene sections:<br/>";

		for($i = 0; $i < count($indv->getGeneSet()->getSectionNames()); $i++){
			$section = $indv->getGeneSet()->getSectionNames()[$i];
			$sections .= $section . ": " . $indv->getGeneSet()->getSectionValue($section) . "<br/>";
		}
		
        echo $sections;
    }
}
?>