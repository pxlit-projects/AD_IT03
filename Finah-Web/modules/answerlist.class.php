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
    private $receivers = 1;
    private $party = 2;
    
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
  public function getQuestionId(){
      return $this->questionId;
  }
  public function getWorkpoint(){
      return $this->workpoint;
  }
  
  
  public function writeToDatabase($connection){
      /*$i = 0;
      $query = "INSERT INTO hash (string,receivers,party) VALUES ('$this->hash','$this->receivers','$this->party')";
      $connection->query($query);
      $query = "SELECT id FROM hash WHERE string='$this->hash'";
      $result->query($query);
      $hashId = $result->fetch_assoc();
      echo $hashId
      //foreach($this->getAnswerId() AS $a){
       //   $query = "INSERT INTO answerlist (list,answer,question,workpoint,hash,client,user"
      */
  }
}
?>

