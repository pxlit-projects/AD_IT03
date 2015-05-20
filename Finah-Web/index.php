<?php
// nieuwe structuur om leesbare urls te voorzien
// aanroepen met http://127.0.0.1/survey/1/4/2346ad27d7568ba9896f1b7da6b5991251debdf2/
include_once 'configuration.php';
include_once 'request.php';
session_start();

$thisRequest = new Request();
///////////////////////////////
if ($thisRequest->getBool) {
    $connection = new mysqli($host, $user, $password, $database, $port);
    $connection->set_charset("utf8");
    
    include_once HANDLE . $thisRequest->handler.'.php';
    //////////// DISPLAY DATA ///////////////
    if($thisRequest->handler != 'send'){
    include_once TEMPLATE . 'header.php';
    include_once TEMPLATE . $thisRequest->handler . '.php';
    include_once TEMPLATE . 'footer.php';
    }
    
} else {
    RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "OOPS");
    }

?>


