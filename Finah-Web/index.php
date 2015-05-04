<?php
// nieuwe structuur om leesbare urls te voorzien
// aanroepen met http://127.0.0.1/survey/1/2346ad27d7568ba9896f1b7da6b5991251debdf2/
// hash check moet opnieuw gecode worden
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
    include_once TEMPLATE . 'header.php';
    include_once TEMPLATE . $thisRequest->handler . '.php';
    include_once TEMPLATE . 'footer.php';
    
} else {
    RegActionSes("failed_get_request", false, false, HTML_ROOT, HTML_ROOT . '/' . $thisRequest->requestString, "OOPS");
    }


// we gaan met sha1 hashes werken
// vb : 2346ad27d7568ba9896f1b7da6b5991251debdf2
// url aanroepen met 127.0.0.1?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1


/* if(isset($_GET['hash']) && isset($_GET['list'])){
    if( ctype_digit($_GET['list']) && ctype_alnum($_GET['hash'])){
    $hash = $connection->real_escape_string($_GET['hash']);
    $list = $connection->real_escape_string($_GET['list']);
    // hash checken op deze in db zit
    $query = "SELECT * FROM hash WHERE string='$hash'";
    $result = $connection->query($query);
    echo $connection->error;
    if($result->num_rows == 1){ 
       include_once 'head.inc.php';
       include_once 'survey.php';
       include_once 'foot.inc.php';
    }
    }
}
 * 
 */
?>


