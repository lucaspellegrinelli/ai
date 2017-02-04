<?php
require_once "GeneSet.php";

class Individual{
	private $geneSet;
	private $fitness;
	
	public function __construct() {
       $this->geneSet = new GeneSet();
    }
	
	public function getGeneSet(){
		return $this->geneSet;
	}
	
	public function getFitness(){
		return $this->fitness;
	}
	
	public function setFitness($fitness){
		$this->fitness = $fitness;
	}
	
	public function mutate($mutationRate){
		for($i = 0; $i < $this->geneSet->getSectionCount(); $i++){
			$rand = mt_rand() / mt_getrandmax();
			if($rand < $mutationRate){
				$gene = self::getGeneSet()->getSectionNames()[$i];
				self::getGeneSet()->replaceGeneWithRandomValue($gene);
			}
		}
	}
}
?>