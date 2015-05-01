<?php
session_start();
include_once 'configuration.php';
include_once 'head.inc.php';
// we gaan met sha1 hashes werken
// vb : 2346ad27d7568ba9896f1b7da6b5991251debdf2
// url aanroepen met 127.0.0.1?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list

$connection = new mysqli($host, $user, $password, $database, $port);
$connection->set_charset("utf8");
if ($connection->connect_error) {
  trigger_error('Database connection failed: '  . $connection->connect_error, E_USER_ERROR);
}
if(isset($_GET['hash']) && isset($_GET['list'])){
    if( ctype_digit($_GET['list']) && ctype_alnum($_GET['hash'])){
    $hash = $connection->real_escape_string($_GET['hash']);
    $list = $connection->real_escape_string($_GET['list']);
    // hash checken op deze in db zit
    $query = "SELECT * FROM hash WHERE string='$hash'";
    $result = $connection->query($query);
    echo $connection->error;
    if($result->num_rows == 1){ 
       include_once 'survey.php';
    }
    }
} else {
    echo '<H3>Geen toegang zonder hash!</H3>';
}
include_once 'foot.inc.php';
?>


