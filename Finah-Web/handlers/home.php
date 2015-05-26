<?php
if(!isset($_SESSION['hash'])){
    
    $randomHash = sha1(microtime(true).mt_rand(10000,90000));
    $query = "SELECT * FROM answerlist WHERE hash='$randomHash'";
    $hashResult = $connection->query($query);
    if($hashResult->num_rows == 0){
        $_SESSION['hash'] = $randomHash;
    } else {
         //@Param  - type,errors,data,nextUrl,requestUrl,message
           RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "GENERATED HASH NOT UNIQUE, RETRY"); 
    }
  
}
// prepare data for easy readable template file.
$gHash = $_SESSION['hash'];