<?php
class GAResult{
	private $genesOptimized;
	private $numberOfGenerations;
	
	public function __construct($geneSet, $numberOfGenerations){
		for($i = 0; $i < count($geneSet); $i++){
			$sectionName = $geneSet[$i].getSectionNames();
			array_push($genesOptimized, [$sectionName => $geneSet[$i].getSectionValue($sectionName)]);
		}
		
		$this->numberOfGenerations = $numberOfGenerations;
	}
	
    
    public function getSectionValue($sectionName){
        return $this->genesOptimized[$sectionName];
    }
    
	public function getSectionNames(){
		$names = [];
		
		for($i = 0; $i < count(array_keys($this->genesOptimized)); $i++){
			$names[$i] = array_keys($this->genesOptimized)[$i];
		}
		
		return $names;
	}
    
    public function getNumberOfGenerations(){
        return $this->numberOfGenerations;
    }
}
?>