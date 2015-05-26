<?php

if (isset($_GET['a'])) {

    $arr = array(
        'iterator' => $_SESSION['answerList']->getIterator(),
        'answers' => $_SESSION['answerList']->getAnswerId(),
        'workpoints' => $_SESSION['answerList']->getWorkpoint()
    );
    echo json_encode($arr);
}
