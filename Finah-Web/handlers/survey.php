<?php
// GET ANSWER FROM ASYNCHRONYMOUS JQUERY POST
if(isset($_POST['answers'])){
    if(isset($_SESSION['answerList'])){
        $a = $_POST['choice'];
        $w = $_POST['workpoint'];
        $l = $_POST['list'];
        if(ctype_digit("$a") && ctype_digit("$w") && ctype_digit("$l")){
            if($_SESSION['answerList']->getList() == $l){
                $_SESSION['answerList']->setAnswer($a);
                $_SESSION['answerList']->setWorkpoint($w);
            }

        }
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
            $answerList = new AnswerList($hash, $list, 4, 5, $_SESSION['questionList']->getQuestionId('fullArray'));
            $_SESSION['answerList'] = $answerList;

        }
        /////////////////////////////////////////////////////////////
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
            //echo $_SESSION['answerList']->getIterator();
            //echo $_SESSION['questionList']->getIterator();
        }
        /////////////////////////////////////////////////////////////
        //  PREPARE SESSION DATA FOR EASY READABLE OUTPUT
        $qTitle = $_SESSION['questionList']->getQuestionTitle();
        $qDesc =  $_SESSION['questionList']->getQuestionDescription();
        $qNum = count($_SESSION['questionList']->getQuestionId("fullArray"));
        $qCur = $_SESSION['questionList']->getIterator();
        $tTitle = $_SESSION['questionList']->getThemeTitle();
        $tDesc = $_SESSION['questionList']->getThemeDescription();
        $answers = $_SESSION['standardAnswers'];
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
    }
}