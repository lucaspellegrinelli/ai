<?php
class GAGeneSection{
	private $sectionName;
	private $minValue;
	private $maxValue;
	
	public function __construct($sectionName, $minValue, $maxValue){
		$this->sectionName = $sectionName;
		$this->minValue = $minValue;
		$this->maxValue = $maxValue;
	}
	
	public function getSectionName(){
		return $this->sectionName;
	}

	public function setSectionName($sectionName){
		$this->sectionName = $sectionName;
	}

    public function getMinValue(){
		return $this->minValue;
	}
	
    public function setMinValue($minValue){
		$this->minValue = $minValue;
	}

    public function getMaxValue(){
		return $this->maxValue;
	}
	
	public function setMaxValue($maxValue){
		$this->maxValue = $maxValue;
	}
}
?>