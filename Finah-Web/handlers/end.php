<?php

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
 /* include_once MOD.'pdfcrowd.php';

try
{   
    // create an API client instance
    $client = new Pdfcrowd("synbitz", "e65167f8f55eb04fd07c1b118b0af34a");

    // convert a web page and store the generated PDF into a $pdf variable
    $pdf = $client->convertURI(HTML_ROOT . 'end/',fopen("finah-survey-$hash.pdf", 'wb'));

    // set HTTP response headers
    header("Content-Type: application/pdf");
    header("Cache-Control: max-age=0");
    header("Accept-Ranges: none");
    header("Content-Disposition: attachment; filename=\"finah-rapport.pdf\"");

    // send the generated PDF 
    echo $pdf;
}
catch(PdfcrowdException $why)
{
    echo "Pdfcrowd Error: " . $why;
}
  * *
  */
?>