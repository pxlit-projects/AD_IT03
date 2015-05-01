<?php
include_once 'questionlist.class.php';
$answerQuery = "SELECT * FROM answer WHERE choice=0";
$answersResult = $connection->query($answerQuery);
/////////////////////////////////////////////////////////////
$answers = array();
while($aData = $answersResult->fetch_assoc()){
    $answers[$aData['id']] = $aData['title'];
}
/////////////////////////////////////////////////////////////
$questionList = new QuestionList($connection);
$numQuestions = count($questionList->getQuestionId());
$iterator = 1;
/////////////////////////////////////////////////////////////
echo '<H3>Thema : '.$questionList->getThemeTitle($iterator).'</H3>'. 
     '<H5> '.$questionList->getThemeDescription($iterator).'</H5>' . PHP_EOL;

echo '<H2>Vraag : '.$questionList->getQuestionTitle($iterator).
     '<H5> '.$questionList->getQuestionDescription($iterator).'</H5>' . PHP_EOL;

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
<div class="col-sm-100 col-sm-push-100 btn-group btn-group-lg" role="group" aria-label="...">
        <button type="button" class="btn btn-default">Vorige stelling</button>
        <button type="button" class="btn btn-default">Volgende stelling</button>
 </div>
</div>


<br><br><br>

<div class="progress progress-striped active">
  <div class="progress-bar" style="width: 25%"></div>
</div>
