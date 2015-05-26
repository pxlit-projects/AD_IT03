<?php
######## SESSION TYPE ##########
if(!isset($_SESSION['report_message'])){
    $_SESSION['report_message'] = "Hmm now how did you get here? Redirecting...";
    $_SESSION['nextUrl'] = HTML_ROOT;
    $_SESSION['report_refresh'] = 5;
}
if(!isset($_SESSION['report_refresh'])){
    $_SESSION['report_refresh'] = 20;
}
########################################
include_once TEMPLATE.'header.php';
include_once TEMPLATE.$thisRequest->handler.'.php';
include_once TEMPLATE.'footer.php';
?>

