<?php
if(!isset($_SESSION['hash'])){
    $randomHash = sha1(microtime(true).mt_rand(10000,90000));
    $query = "SELECT * FROM hash WHERE string ='$randomHash'";
    $hashResult = $connection->query($query);
    if($hashResult->num_rows == 1){
       //@Param  - type,errors,data,nextUrl,requestUrl,message
           RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "GENERATED HASH NOT UNIQUE, RETRY"); 
    } else {
        $_SESSION['hash'] = $randomHash;
    }
  
}
// prepare data for easy readable template file.
$gHash = $_SESSION['hash'];