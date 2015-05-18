<?php
// GET ANSWER FROM ASYNCHRONYMOUS JQUERY POST
if(isset($_POST['answers'])){
    if(isset($_SESSION['answerList'])){
        if($_POST['answers'] == 'go'){
        $a = $_POST['choice'];
        $w = $_POST['workpoint'];
        $l = $_POST['list'];
        if(ctype_digit("$a") && ctype_digit("$w") && ctype_digit("$l")){
            if($_SESSION['answerList']->getList() == $l){
                $_SESSION['answerList']->setAnswer($a);
                $_SESSION['answerList']->setWorkpoint($w);
            }

        }
      } /* else if($_POST['answers'] == 'error'){
          if($_POST['code'] == 1 ) {
              $_SESSION['errorOne'] = true;
          }
          if($_POST['code'] == 2 ) {
              $_SESSION['errorTwo'] = true;
          } 
      } */
    }
}
if(empty($thisRequest->getParams[0])){
    //@Param  - type,errors,data,nextUrl,requestUrl,message
    RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "OOPS");
   } else {
        if ($thisRequest->getParamBool) {
            $getCount = count($thisRequest->getParams);
            $list = $thisRequest->getParams[0];
            $usertype = $thisRequest->getParams[1];
            $hash = $thisRequest->getParams[2];
            if(isset($thisRequest->getParams[3])){$go =  $thisRequest->getParams[3];}
             else {$go = false;}
        /////////////////////////////////////////////////////////////
        if(!isset($_SESSION['standardAnswers'])){
            $answerQuery = "SELECT * FROM answer WHERE choice=0";
            $answersResult = $connection->query($answerQuery);
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
        /////////////////////////////////////////////////////////////
        if(!isset($_SESSION['answerList'])){
            $answerList = new AnswerList($hash, $list,$usertype, $_SESSION['questionList']->getQuestionId('fullArray'));
            $_SESSION['answerList'] = $answerList;

        }
        /////////////////////////////////////////////////////////////
        if($go != false){
            
                if($go == 'next'){
                    if($_SESSION['answerList']->checkSubmit("next")){
                        $_SESSION['questionList']->iterate('+');
                        $_SESSION['answerList']->iterate('+');
                        
                    } else {
                        $_SESION['errorOne'] = true;
                    }
                }
                if($go == 'previous'){
                    if($_SESSION['answerList']->checkSubmit("previous")){
                    $_SESSION['questionList']->iterate('-');
                    $_SESSION['answerList']->iterate('-');
                    
                    }
                }
            // 
            if($go == 'submit'){
                if($_SESSION["questionList"]->getListSize() == count($_SESSION['answerList']->getAnswerId()))
                {
                    // schrijf naar database
                    $_SESSION['answerList']->writeToDatabase($connection);
                    header('location:'.HTML_ROOT . 'end/');
                }
                
            }
          
        }
        /////////////////////////////////////////////////////////////
        //  PREPARE SESSION DATA FOR EASY READABLE OUTPUT
        $qTitle = $_SESSION['questionList']->getQuestionTitle();
        $qDesc =  $_SESSION['questionList']->getQuestionDescription();
        $qNum = count($_SESSION['questionList']->getQuestionId("fullArray"));
        $qCur = $_SESSION['questionList']->getIterator();
        $tTitle = $_SESSION['questionList']->getThemeTitle();
        $tDesc = $_SESSION['questionList']->getThemeDescription();
        $aId = array();
        $aTitle = array();
        $usertype = $_SESSION['answerList']->getUsertype();
        foreach($_SESSION['standardAnswers'] as $key => $val){
            array_push($aId,$key);
            array_push($aTitle,$val); 
       }
        //////////////////////////////////////////////////////////////
        $submit = false;
        if(count($_SESSION['answerList']->getAnswerId()) == $_SESSION["questionList"]->getListSize()){
            $submit = true;
        }
        $set = false;
        if(isset($_SESSION['answerList']->getAnswerId()[$_SESSION['answerList']->getIterator()])){
            $curA = $_SESSION['answerList']->getAnswerId()[$_SESSION['answerList']->getIterator()];
            $set = true;
           
        } 
        
        // ERRORS 
        $errorOne = false;
        if(isset($_SESSION['errorOne'])){
            $errorOne = true;
        } else { $errorOne = false; }
    }
}