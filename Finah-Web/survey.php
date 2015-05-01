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
if(isset($_GET['go'])){
    if($_GET['go'] == 'next'){
        $_SESSION['questionList']->iterate('+');
    
    }
    if($_GET['go'] == 'previous'){
        $_SESSION['questionList']->iterate('-');
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
   echo '<button type="button" class="btn btn-default" id="'.$id.'">'.$answer.'</button>' . PHP_EOL;
   echo '</div>';
}
echo '</div>'. PHP_EOL;
/////////////////////////////////////////////////////////////
?>

</br>
<H3>Wilt u hieraan verder werken?</H3>
<div class="btn-group btn-group-*" role="group" aria-label="..." >
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" id="1">Ja</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" id="2">Neen</button>
  </div>
</br></br></br>
<?php 
$linkNext = 'http://'.$baseLink.'/?hash='.$hash.'&list='.$list.'&go=next';
$linkPrevious = 'http://'.$baseLink.'/?hash='.$hash.'&list='.$list.'&go=previous';
echo '<div class="col-sm-100 col-sm-push-100 btn-group btn-group-lg" role="group" aria-label="...">';
echo '<a href="'.$linkPrevious.'"><button type="button" class="btn btn-default">Vorige stelling</button><a>';
echo '<a href="'.$linkNext.'"><button type="button" class="btn btn-default">Volgende stelling</button></a>';
echo '</div></div>';
?>

<br><br><br>

<div class="progress progress-striped active">
  <div class="progress-bar" style="width: 25%"></div>
</div>
