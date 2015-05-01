<?php
$questionQuery =
"SELECT 
questionlist.list as list,
question.choice,
question.description,
question.theme AS theme_id,
question.title AS question_title,
theme.description AS theme_description,
theme.title AS theme_title,
FROM `questionlist`
JOIN `question` on questionlist.question = question.id
JOIN `theme` on question.theme = theme.id
WHERE list = 1";
$answerQuery = "SELECT * FROM answer WHERE choice=0";

$questionsResult = $connection->query($questionQuery);
$answersResult = $connection->query($answerQuery);
echo $connection->error;
// prepare answer data
$answers = array();
while($aData = $answersResult->fetch_assoc()){
    $answers[$aData['id']] = $aData['title'];
}
// prepare question data
$questions = array();
while ($qData = $questionsResult->fetch_assoc()){
    
}

// output answer data  //
/////////////////////////////////////////////////////////////
echo '<H3>Hoe ervaar ik dit onderdeel?</H3>' . PHP_EOL;
echo '<div class="btn-group btn-group-*" role="group" aria-label="...">'. PHP_EOL;
foreach ($answers as $id => $answer) {
   echo '<div class="btn-group" role="group">' . PHP_EOL;
   echo '<button type="button" class="btn btn-default" id="'.$id.'">'.$answer.'</button>' . PHP_EOL;
   echo '</div>';
}
echo '</div></div>'. PHP_EOL;
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
