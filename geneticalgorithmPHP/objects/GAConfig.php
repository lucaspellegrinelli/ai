<?php
class GAConfig{
	private $mutationRate;
	private $tournamentSelectionSize;
	private $elitism;

	public function __construct(){
		//
	}
	
    public function getMutationRate() {
        return $this->mutationRate;
    }

    public function setMutationRate($mutationRate) {
        $this->mutationRate = $mutationRate;
    }

    public function getTournamentSelectionSize() {
        return $this->tournamentSelectionSize;
    }

    public function setTournamentSelectionSize($tournamentSelectionSize) {
        $this->tournamentSelectionSize = $tournamentSelectionSize;
    }

    public function isElitism() {
        return $this->elitism;
    }

    public function setElitism($elitism) {
        $this->elitism = $elitism;
    }
}
?>