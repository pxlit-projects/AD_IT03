<?php
class Answerlist {
    private $list = 1;
    private $hash;
    private $client;
    private $user;
    private $answerId = array();
    private $questionId = array();
    private $workpoint = array();
    private $listSize;
    private $iterator = 0;
    
    function __construct($hash,$list,$user,$client,$questionId) {
        $this->hash = $hash;
        $this->list = $list;
        $this->user = $user;
        $this->client = $client;
        $this->questionId =  $questionId;
        
        $this->listSize = count($this->questionId);
        echo $this->listSize;
        
       
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
       if(ctype_digit("$answer")){
           $answerId[$iterator] = $answer;
           echo $answerId[$iterator];
       }
   }
}
?>

