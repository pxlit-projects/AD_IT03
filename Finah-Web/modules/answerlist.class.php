<?php
class AnswerList {
    private $list = 1;
    private $hash;
    private $answerId = array();
    private $questionId = array();
    private $workpoint = array();
    private $listSize;
    private $iterator = 0;
    private $complete = false;
    private $usertype = 4; // 4 is een patient in db usertype tabel
    private $cTime;
    private $sTime;
    
    function __construct($hash,$list,$usertype,$questionId) {
        $this->hash = $hash;
        $this->list = $list;
        $this->usertype = $usertype;
        $this->questionId =  $questionId;
        $this->listSize = count($this->questionId);
        $time =  new DateTime();
        $this->sTime= $time->getTimestamp();
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
  public function getUserType(){
      return $this->usertype;
  }
  
  
  public function writeToDatabase($connection){
      $n= new DateTime();
      $dt = $n->format('Y-m-d H:i:s');  
      $t = $this->cTime = ($n->getTimestamp()-$this->sTime);
      for ($i=0;$i<$this->listSize;$i++){
          $l = filter_var($this->list,FILTER_SANITIZE_STRING);
          $a = filter_var($this->answerId[$i],FILTER_SANITIZE_STRING);
          $q = filter_var($this->questionId[$i],FILTER_SANITIZE_STRING);
          $w = filter_var($this->workpoint[$i],FILTER_SANITIZE_STRING);
          $h = filter_var($this->hash,FILTER_SANITIZE_STRING);
          $u = filter_var($this->usertype,FILTER_SANITIZE_STRING);
          
          $d = $n->format('Y-m-d H:i:s');    // MySQL datetime format
          $query = "INSERT INTO answerlist (list,answer,question,workpoint,hash,date,usertype,time)"
                  . "VALUES ('$l','$a','$q','$w','$h','$dt','$u','$t')";
          $connection->query($query);
          if($connection->affected_rows == 0){
              echo "llul";
          }
          
      }
  }
}
?>

