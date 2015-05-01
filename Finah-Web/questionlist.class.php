<?php
class QuestionList {
    private $list = 1;
    private $questionTitle = array();
    private $questionDescription = array();
    private $questionId = array();
    private $questionChoice = array();
    private $themeId = array();
    private $themeTitle = array();
    private $themeDescription = array();
    
    private $iterator = 0;
    
    
    function __construct($connection ){
        // haal al de vragen van lijst 1 op met hun themas etc
       $questionQuery =
        "SELECT 
        questionlist.list AS list,
        question.id AS question_id,
        question.choice AS  question_choice,
        question.description AS question_description,
        question.theme AS theme_id,
        question.title AS question_title,
        theme.description AS theme_description,
        theme.title AS theme_title
        FROM `questionlist`
        JOIN `question` on questionlist.question = question.id
        JOIN `theme` on question.theme = theme.id
        WHERE list = $this->list";
       
       $questionsResult = $connection->query($questionQuery);
       echo $connection->error;
       // data in array laden
       $index = 0;
       while ($data = $questionsResult->fetch_assoc()){
           $this->questionId[$index] = $data['question_id'];
           $this->questionTitle[$index] = $data['question_title'];
           $this->questionDescription[$index] = $data['question_description'];
           $this->questionChoice[$index] = $data['question_choice'];
           $this->themeId[$index] = $data['theme_id'];
           $this->themeTitle[$index] = $data['theme_title'];
           $this->themeDescription[$index] = $data['theme_description'];
           $index++;
           
       }
   }
   public function iterate($action){
       if($action == '+'){
           $this->iterator+=1;
       }
       if($action == '-'){
           $this->iterator+-1;
       }
   }
            
    public function getQuestionId(){
      return $this->questionId[$this->iterator];   
    }
    public function getQuestionTitle(){
      return $this->questionTitle[$this->iterator];
    }
    public function getQuestionDescription(){
      return $this->questionDescription[$this->iterator];
    }
    public function getQuestionChoice(){
      return $this->questionChoice[$this->iterator]; 
    }
    public function getThemeId(){
       return $this->themeId[$this->iterator]; 
    }
    public function getThemeTitle(){
      return $this->themeTitle[$this->iterator];

    }
    public function getThemeDescription(){
      return $this->themeDescription[$this->iterator];
    }
    public function getIterator(){
      return $this->iterator;
    }

}

