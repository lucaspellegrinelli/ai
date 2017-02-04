<?php
class Population{
	private $individuals = []; //List<Individual>
	
	public function __construct($populationSize, $param){
		if($param){
			for($i = 0; $i < $populationSize; $i++){
				$individual = new Individual();
				$this->individuals[$i] = $individual;
			}
		}else{
			$this->individuals = [];
		}
	}
    
    public function saveIndividual($individual){
		array_push($this->individuals, $individual);
	}
    
    public function getPopulationCount(){
		return count($this->individuals);
	}
    
    public function getIndividual($index){
		return $this->individuals[$index];
	}
}
?>