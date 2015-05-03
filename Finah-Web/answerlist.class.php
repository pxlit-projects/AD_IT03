<?php
class AnswerList {
    private $list = 1;
    private $hash;
    private $client;
    private $user;
    public $answerId = array();
    public $questionId = array();
    private $workpoint = array();
    public $listSize;
    public $iterator = 0;
    
    function __construct($hash,$list,$user,$client,$questionId) {
        $this->hash = $hash;
        $this->list = $list;
        $this->user = $user;
        $this->client = $client;
        $this->questionId =  $questionId;
        
        $this->listSize = count($this->questionId);
        
        
       
    }
    public function iterate($action){
       if($action == '+'){
           $this->iterator+=1;
       }
       if($action == '-'){
           $this->iterator-=1;
       }
   }
   public function setAnswer($answer){
       
           $this->answerId[$this->iterator] = $answer;
         
  }
}
?>

