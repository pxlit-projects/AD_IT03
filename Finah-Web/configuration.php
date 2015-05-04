<?php
$host = '81.4.126.109';
$user = 'luke';
$password = 'lukeluke';
$database = 'db_projectx';
$port  = '3306';
$baseLink = '127.0.0.1';


define('PHP_ROOT','');
define('HTML_ROOT','/');
define('IMG_SITE',HTML_ROOT.'/files/site/');

define ('HANDLE',PHP_ROOT.'handlers/');
define ('MOD',PHP_ROOT.'modules/');
define ('PLUGIN',PHP_ROOT.'plugins/');
define ('TEMPLATE',PHP_ROOT.'templates/');
include_once MOD.'questionlist.class.php';
include_once MOD.'answerlist.class.php';


?>

