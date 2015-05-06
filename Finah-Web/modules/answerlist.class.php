<?php
class AnswerList {
    private $list = 1;
    private $hash;
    private $client;
    private $user;
    private $answerId = array();
    private $questionId = array();
    private $workpoint = array();
    private $listSize;
    private $iterator = 0;
    private $complete = false;
    
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
           if(($this->iterator+1) < $this->listSize ){
            $this->iterator+=1;
           }
       }
       if($action == '-'){
           if(($this->iterator-1) >= 0 ){
            $this->iterator-=1;
           }
       }
   }
   public function setAnswer($answer){
        
          $this->answerId[$this->iterator] = $answer;     
  }
   public function setWorkpoint($workpoint){
        
          $this->workpoint[$this->iterator] = $workpoint;     
  }
  public function checkSubmit(){
      if(count($this->answerId) > $this->iterator){
          return true;
      } else {
          return false;
      }
  }
  public function getList(){
      return $this->list;
  }
  public function getListSize(){
      return $this->listSize;
  }
  public function isComplete(){
      return $this->complete;
  }
  public function getIterator(){
      return $this->iterator;
  }
  public function getAnswerId(){
      return $this->answerId;
  }
}
?>

