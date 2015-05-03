<?php
if(isset($_POST['answers'])){
    // not save yet need to filter post var
    $_SESSION['answerList']->setAnswer($_POST['choice']);
}
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
if(isset($_GET['go'])){
    if($_SESSION['answerList']->checkSubmit()){
        if($_GET['go'] == 'next'){
            $_SESSION['questionList']->iterate('+');
            $_SESSION['answerList']->iterate('+');

        }
        if($_GET['go'] == 'previous'){
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
//////////////// OUTPUT STARTS HERE /////////////////////////
/////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////
?>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 id="panel-title" class="panel-title">
            Thema : <?php echo $tTitle;?> <?php echo $tDesc;?>
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h3>
    </div>
    <div class="panel-body">
        <h4><?php echo $qTitle;?> <?php echo $qDesc;?></h4>
        <div class="btn-group btn-group-*" role="group" aria-label="...">
        <?php foreach ($answers as $id => $answer) { ?>
        <div class="btn-group" role="group">
        <button type="button" class="btn btn-default" id="btnChoice<?php echo $id;?>" value="<?php echo $id;?>"><?php echo $answer;?></button>
        </div>
        <?php } ?>
        </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        <h4 id="panel-title" class="panel-title">
            Wilt u hieraan verder werken?
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h4>
    </div>
    <div class="panel-body">
       <div class="btn-group btn-group-*" role="group" aria-label="..." >
        <div class="btn-group" role="group">
          <button type="button" class="btn btn-default " id="btnWorkpoint1">Ja</button>
        </div> 
        <div class="btn-group" role="group">
          <button type="button" class="btn btn-default" id="btnWorkpoint2">Neen</button>
        </div>
      </div>
    </div>
</div>
<div class="col-sm-100 col-sm-push-100 btn-group" role="group" aria-label="...">
    <a href="?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1&go=previous" id="linkNext">
        <button type="button" class="btn btn-default" id="btnNavigate_previous" value="previous">Vorige stelling</button>
    </a>
    <a href="?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1&go=next" id="linkPrevious">
    <button type="button" class="btn btn-default" id="btnNavigate_next" value="next">Volgende stelling</button>
    </a>
</div>
<div id="errorDiv"></div>


