<?php
session_start();
include_once 'head.inc.php';
// we gaan met sha1 hashes werken
// vb : 2346ad27d7568ba9896f1b7da6b5991251debdf2
// url aanroepen met 127.0.0.1?hash=2346ad27d7568ba9896f1b7da6b5991251debdf2&list=1
if(isset($_GET['hash']) && isset($_GET['list'])){
    $hash = $_GET['hash'];
    $list = $_GET['list'];
    if(count_chars($_GET['hash']) == 40){
        include_once 'survey.php';
    }
} else {
    echo '<H3>Geen toegang zonder hash!</H3>';
}
include_once 'foot.inc.php';
?>


