<?php

class Request {

    public $handler;
    public $requestString;
    public $getParams;
    public $getBool = false;
    public $getParamBool = false;
    public $getErr;
    
    function __CONSTRUCT() {
        if (empty($_GET['request'])) {
            $this->handler = 'home';
            $this->getBool = true;
        } else {
            $this->requestString = $_GET['request'];
            $request = explode('/', $this->requestString, 2);
            $this->handler = $request[0];
            $this->checkHandler();
            if (!empty($request[1])) {
                $this->getParams = explode('/', $request[1]);
                $this->checkParams();
            }
        }
    }

    private function checkHandler() {
        $loc = "";
        if (!ctype_alpha($this->handler)) {
            $this->getBool = false;
        } else {
            $loc = HANDLE . '/' . $this->handler . '.php';
        }
        if (!file_exists($loc)) {
            $this->getBool = false;
        } else {
            $this->getBool = true;
        }
    }

    private function checkParams() {
        $acceptSet = '0123456789abcdefghijklmnopqrstuvwxyz-|';
        $getParamBools = array();
        $i = 0;
        foreach ($this->getParams as $param) {
            if (empty($param)) {
                unset($this->getParams[$i]);
            } else {
                if (strlen($param) != strspn($param, $acceptSet)) {
                    array_push($getParamBools, false);
                } else {
                    array_push($getParamBools, true);
                }
            }
            $i++;
        }
        if (!in_array(false, $getParamBools)) {
            $this->getParamBool = true;
        }
    }
}


?>


