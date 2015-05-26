<?php

class AnswerList {

    private $list = 1;
    private $hash;
    private $answerId = array();
    private $questionId = array();
    private $workpoint = array();
    private $listSize;
    private $iterator = 0;
    private $complete = false;
    private $usertype = 4; // 4 is een patient in db usertype tabel
    private $cTime;
    private $sTime;
    private $time;

    function __construct($hash, $list, $usertype, $questionId) {
        $this->hash = $hash;
        $this->list = $list;
        $this->usertype = $usertype;
        $this->questionId = $questionId;
        $this->listSize = count($this->questionId);
        $time = new DateTime();
        $this->sTime = $time->getTimestamp();
    }

    public function iterate($action) {
        if ($action == '+') {
            if (($this->iterator + 1) < $this->listSize) {
                $this->iterator+=1;
            }
        }
        if ($action == '-') {
            if (($this->iterator - 1) >= 0) {
                $this->iterator-=1;
            }
        }
        if (ctype_digit("$action")) {
            $this->iterator = $action;
        }
    }

    public function setAnswer($answer) {

        $this->answerId[$this->iterator] = $answer;
    }

    public function setWorkpoint($workpoint) {

        $this->workpoint[$this->iterator] = $workpoint;
    }

    public function checkSubmit($check) {
        if ($check == 'next') {
            if (count($this->answerId) > $this->iterator) {
                return true;
            } else {
                return false;
            }
        }
        if ($check == 'previous') {
            return true;
        }
    }

    public function getList() {
        return $this->list;
    }

    public function getListSize() {
        return $this->listSize;
    }

    public function isComplete() {
        return $this->complete;
    }

    public function getIterator() {
        return $this->iterator;
    }

    public function getAnswerId() {
        return $this->answerId;
    }

    public function getQuestionId() {
        return $this->questionId;
    }

    public function getWorkpoint() {
        return $this->workpoint;
    }

    public function getUserType() {
        return $this->usertype;
    }

    public function getTime() {
        return $this->time;
    }

    public function getHash() {
        return $this->time;
    }

    public function writeToDatabase($connection) {
        if ($connection != 'api') {
            // SCHRIJF WEG VIA DIRECT DATABASE
            $n = new DateTime();
            $dt = $n->format('Y-m-d H:i:s');
            $t = $this->cTime = ($n->getTimestamp() - $this->sTime);
            $this->time = $t;
            for ($i = 0; $i < $this->listSize; $i++) {
                $l = filter_var($this->list, FILTER_SANITIZE_STRING);
                $a = filter_var($this->answerId[$i], FILTER_SANITIZE_STRING);
                $q = filter_var($this->questionId[$i], FILTER_SANITIZE_STRING);
                $w = filter_var($this->workpoint[$i], FILTER_SANITIZE_STRING);
                $h = filter_var($this->hash, FILTER_SANITIZE_STRING);
                $u = filter_var($this->usertype, FILTER_SANITIZE_STRING);

                $d = $n->format('Y-m-d H:i:s');    // MySQL datetime format
                $query = "INSERT INTO answerlist (list,answer,question,workpoint,hash,date,usertype,time)"
                        . "VALUES ('$l','$a','$q','$w','$h','$dt','$u','$t')";
                $connection->query($query);
                if ($connection->affected_rows == 0) {
                    
                }
            }
        } else {
            // SCHRIJF WEG VIA WEB API
            $n = new DateTime();
            $dt = $n->format('Y-m-d H:i:s');
            $t = $this->cTime = ($n->getTimestamp() - $this->sTime);
            $this->time = $t;
            for ($i = 0; $i < $this->listSize; $i++) {
                $l = filter_var($this->list, FILTER_SANITIZE_STRING);
                $a = filter_var($this->answerId[$i], FILTER_SANITIZE_STRING);
                $q = filter_var($this->questionId[$i], FILTER_SANITIZE_STRING);
                $w = filter_var($this->workpoint[$i], FILTER_SANITIZE_STRING);
                $h = filter_var($this->hash, FILTER_SANITIZE_STRING);
                $u = filter_var($this->usertype, FILTER_SANITIZE_STRING);

                $url = 'http://finah-webapi-appdevit03.azurewebsites.net/api/answerlist/';

                //Initiate cURL.
                $ch = curl_init($url);

                //The JSON data.
                $jsonData = array(
                    'list' => $l,
                    'answer' => $a,
                    'question' => $q,
                    'workpoint' => $w,
                    'hash' => $h,
                    'date' => $dt,
                    'usertype' => $u,
                    'time' => $t,
                );
                $jsonDataEncoded = json_encode($jsonData);
                curl_setopt($ch, CURLOPT_POST, 1);
                curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonDataEncoded);
                curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
                curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
                $result = curl_exec($ch);
                 
           }
        }
    }
    function process($method){
        if($method=='api'){
            // [{"id":2,"hash":"34466a893f951e4ae18b64ff9d4b32701978944c","status":1,"user":3,"date":"2015-05-21T00:00:00"}]
            $url = 'http://finah-webapi-appdevit03.azurewebsites.net/api/hashes/GetHashByHash/'.$this->hash.'/'; 
            $json = file_get_contents($url);
            $hash = json_decode($json, TRUE);
            if(count($hash)==1){
                $urlx = 'http://finah-webapi-appdevit03.azurewebsites.net/api/answerlist/GetAnswerlistByHash/'.$this->hash.'/';
                $jsonx = file_get_contents($urlx);
                $resx = json_decode($jsonx, TRUE);
                if(count($resx)==0 || count($resx) == $this->listSize){
                    $this->writeToDatabase('api');
                   if(count($resx) == $this->listSize){
                   $updateUrl = 'http://finah-webapi-appdevit03.azurewebsites.net/api/hashes/' . $hash[0]['id'] .'/';
                       $ch = curl_init($updateUrl);
                        $hash[0]['status'] = 1;
                        str_replace('T',' ', $hash[0]['date']);
                        $jsonDataEncoded = json_encode($hash[0]);
                        
                        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
                        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
                        curl_setopt($ch, CURLOPT_POSTFIELDS,$jsonDataEncoded);
                        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
                        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
                        $result = curl_exec($ch);
                       
                    } 
                }
            }
        }
    }
}
?>

