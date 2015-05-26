<?php
$_SESSION['end'] = true;
$aL = $_SESSION['answerList'];
$qL = $_SESSION['questionList'];
// overzicht pagina //
$time = $aL->getTime();
$hash = $aL->getHash();

// PREPARE DATA FOR EASY READABLE OUTPUT IN TEMPLATE FILE
$index = 0;
$themes = array_unique($qL->getThemeTitles());
$themeKey = array();
$themeTitle = array();
$themeCount = count($themes);
 foreach($themes as $key => $val){
     array_push($themeKey,$key);
     array_push($themeTitle,$val); 
 }
 array_push($themeKey,53);
// check if hash is present in twice ==> 
?>