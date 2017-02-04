<?php
class GeneSet{
	private $geneMap = [];
	public static $geneSections;
	
	public function __construct() {
		for($i = 0; $i < count(self::$geneSections); $i++){
			$sectionName = self::$geneSections[$i]->getSectionName();
			$minVal = self::$geneSections[$i]->getMinValue();
			$maxVal = self::$geneSections[$i]->getMaxValue();
			
			$this->geneMap[$sectionName] = self::randomInRange($minVal, $maxVal);
		}
    }

	public static function setGeneSections($geneSection){
		self::$geneSections = $geneSection;
	}
	
	public function randomInRange($minVal, $maxVal){
		return $minVal + ((mt_rand() / mt_getrandmax()) * ($maxVal - $minVal));
	}
    
	public function setSectionValue($sectionName, $value){
		$this->geneMap[$sectionName] = $value;
	}
    
    public function getSectionValue($sectionName){
		return $this->geneMap[$sectionName];
	}
    
    public function getSectionCount(){
        return count($this->geneMap);
    }
	
	public function getSectionNames(){
		$names = [];
		
		for($i = 0; $i < count(array_keys($this->geneMap)); $i++){
			$names[$i] = array_keys($this->geneMap)[$i];
		}
		
		return $names;
	}

	public function replaceGeneWithRandomValue($sectionName){
		for($i = 0; $i < count(self::$geneSections); $i++){
			$section = self::$geneSections[$i];
			if(strcmp($section->getSectionName(), $sectionName) == 0){
				$this->geneMap[$sectionName] = self::randomInRange($section->getMinValue(), $section->getMaxValue());
			}
		}
	}
}
?>