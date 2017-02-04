<?php
class GASchemeConfig{
	private $populationSize;
	private $geneConfiguration; // List<GAGeneSection>
	
	public function __construct($populationSize, $geneConfiguration){
		$this->populationSize = $populationSize;
		$this->geneConfiguration = $geneConfiguration;
	}

    public function getPopulationSize() {
        return $this->populationSize;
    }

    public function getGeneConfiguration() {
        return $this->geneConfiguration;
    }
}
?>