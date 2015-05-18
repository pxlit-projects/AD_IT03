<?php

$aL = $_SESSION['answerList'];
$qL = $_SESSION['questionList'];
// overzicht pagina //
$time = $aL->getTime();

// PREPARE DATA FOR EASY READABLE OUTPUT IN TEMPLATE FILE
$index = 0;
$themes = array_unique($qL->getThemeTitles());

?>