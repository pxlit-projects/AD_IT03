<?php
$answerQuery = "SELECT * FROM answer WHERE choice=0";
$answersResult = $connection->query($answerQuery);
/////////////////////////////////////////////////////////////
$answers = array();
while($aData = $answersResult->fetch_assoc()){
    $answers[$aData['id']] = $aData['title'];
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
if(isset($_POST['answers'])){
    $_SESSION['answerList']->setAnswer($_POST['choice']);
     $_SESSION["set"] = TRUE;
}
if(isset($_SESSION["set"])){
    echo 'lio' . '<br>';;
    echo $_SESSION['answerList']->answerId[0] . 'answerid<br>';
 
    echo $_SESSION['answerList']->questionId[0] . 'questionId<br>';
     echo $_SESSION['answerList']->iterator . ' iterator<br>';
    echo count($_SESSION['answerList']->answerId) . 'count<br>';
     echo $_SESSION['answerList']->listSize . ' listSize<br>';
}
if(isset($_GET['go'])){
    if($_GET['go'] == 'next'){
        $_SESSION['questionList']->iterate('+');
        $_SESSION['answerList']->iterate('+');
    
    }
    if($_GET['go'] == 'previous'){
        $_SESSION['questionList']->iterate('-');
        $_SESSION['answerList']->iterate('-');
    }
    
}



//////////////// OUTPUT STARTS HERE /////////////////////////
/////////////////////////////////////////////////////////////
echo '<H4>Thema : '.$_SESSION['questionList']->getThemeTitle().'</H4>'. 
     '<H5> '.$_SESSION['questionList']->getThemeDescription().'</H5>' . PHP_EOL;

echo '<H4>Vraag : '.$_SESSION['questionList']->getQuestionTitle().'</H4>'.
     '<H5> '.$_SESSION['questionList']->getQuestionDescription().'</H5>' . PHP_EOL;

/////////////////////////////////////////////////////////////
echo '<H3>Hoe ervaar ik dit onderdeel?</H3>' . PHP_EOL;
echo '<div class="btn-group btn-group-*" role="group" aria-label="...">'. PHP_EOL;
foreach ($answers as $id => $answer) {
   echo '<div class="btn-group" role="group">' . PHP_EOL;
   echo '<button type="button" class="btn btn-default" id="btnChoice'.$id.'" value="'.$id.'">'.$answer.'</button>' . PHP_EOL;
   echo '</div>';
}
echo '</div>'. PHP_EOL;
/////////////////////////////////////////////////////////////
?>

</br>
<H3>Wilt u hieraan verder werken?</H3>
<div class="btn-group btn-group-*" role="group" aria-label="..." >
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default " id="btnWorkpoint1">Ja</button>
  </div> 
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" id="btnWorkpoint2">Neen</button>
  </div>
</div>
</br></br></br>
<div class="col-sm-100 col-sm-push-100 btn-group" role="group" aria-label="...">
    <a href="?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1&go=previous">
        <button type="button" class="btn btn-default" id="btnNavigate_previous" value="previous">Vorige stelling</button>
    </a>
    <a href="?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1&go=next">
    <button type="button" class="btn btn-default" id="btnNavigate_next" value="next">Volgende stelling</button>
    </a>
</div>

<br><br><br>

<div class="progress progress-striped active">
  <div class="progress-bar" style="width: 25%"></div>
</div>



