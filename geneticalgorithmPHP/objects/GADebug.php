<?php
class GADebug{
	private $debug;
    private $showFittestGene;
    private $debugFrequency;
	
	public function __construct(){
		//
	}

    public function isDebug() {
        return $this->debug;
    }

    public function setDebug($debug) {
        $this->debug = $debug;
    }

    public function getDebugFrequency() {
        return $this->debugFrequency;
    }

    public function setDebugFrequency($debugFrequency) {
        $this->debugFrequency = $debugFrequency;
    }

    public function isShowFittestGene() {
        return $this->showFittestGene;
    }

    public function setShowFittestGene($showFittestGene) {
        $this->showFittestGene = $showFittestGene;
    }
}
?>