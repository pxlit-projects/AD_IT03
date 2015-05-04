<?php
if(isset($_POST['answers'])){
        if(isset($_SESSION[answerList])){
            $_SESSION['answerList']->setAnswer($_POST['choice']);
        }
        
}
if(empty($thisRequest->getParams[0])){
   //@Param  - type,errors,data,nextUrl,requestUrl,message
       
    RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "OOPS");
   } else {
    if ($thisRequest->getParamBool) {
        $getCount = count($thisRequest->getParams);
        
            $list = $thisRequest->getParams[0];
            $hash = $thisRequest->getParams[1];
            if(isset($thisRequest->getParams[2])){$go =  $thisRequest->getParams[2];}
            else {$go = false;}
            
        
        // parameters are set , process
    
    
    if(!isset($_SESSION['standardAnswers'])){
        $answerQuery = "SELECT * FROM answer WHERE choice=0";
        $answersResult = $connection->query($answerQuery);
        /////////////////////////////////////////////////////////////
        $answers = array();
        while($aData = $answersResult->fetch_assoc()){
            $answers[$aData['id']] = $aData['title'];

        }
        $_SESSION['standardAnswers'] = $answers;
    }
    /////////////////////////////////////////////////////////////
    if(!isset($_SESSION['questionList'])){
        $questionList = new QuestionList($connection);
        $_SESSION['questionList'] = $questionList;

    }
    if(!isset($_SESSION['answerList'])){
        $answerList = new AnswerList($hash, $list, 4, 5, $_SESSION['questionList']->getQuestionId('fullArray'));
        $_SESSION['answerList'] = $answerList;

    }
    if($go != false){
        if($_SESSION['answerList']->checkSubmit()){
          
            if($go == 'next'){
                $_SESSION['questionList']->iterate('+');
                $_SESSION['answerList']->iterate('+');
            }
            if($go == 'previous'){
                $_SESSION['questionList']->iterate('-');
                $_SESSION['answerList']->iterate('-');
            }
        }
    }

    //  PREPARE SESSION DATA FOR EASY READABLE OUTPUT
    $qTitle = $_SESSION['questionList']->getQuestionTitle();
    $qDesc =  $_SESSION['questionList']->getQuestionDescription();
    $tTitle = $_SESSION['questionList']->getThemeTitle();
    $tDesc = $_SESSION['questionList']->getThemeDescription();
    $answers = $_SESSION['standardAnswers'];
        
    
    }
   }