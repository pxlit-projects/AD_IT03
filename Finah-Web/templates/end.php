<div class="panel panel-info">
    <div class="panel panel-heading">
        <span style="color:#000000;" class="text-left"><strong>Einde van bevraging!</strong></span>
        <span style="color:#000000;" class="pull-right"><strong>Tijd: <?php echo ($time/60);?>Min.</strong></span>
    </div>
    <div class="panel panel-body">
        <p>Bedankt voor het invullen van de vragenlijst. Hieronder uw antwoorden:</p>
        
        <?php 
        for($i=0; $i<$themeCount;$i++) {
            
            echo '<h3>'.$themeTitle[$i] . '</h3><br>';
            $x = $i;
            $sKey = $themeKey[$x];
            $nKey = $themeKey[$x+1];
            $z = 0;
            while($z < ($nKey - $sKey)){
                $key= $z+$sKey;
                $qL->iterate(($key));
                $answerId = $aL->getAnswerId()[($key)];
                if($aL->getWorkpoint()[($key)] == 1){
                    $workpoint = '<strong style="color:orange;">Ja</strong>';
                } else {
                     $workpoint = '<strong style="color:green;">Nee</strong>';
                }
                echo '<strong>'.$qL->getQuestionTitle(). '</strong><br>';
                echo $_SESSION['standardAnswers'][$answerId] .'</br><span style="font-size:11px;"> werkpunt: '.$workpoint .'</span><br>';
                echo '<hr>';
                $z++;
            }
        }
 
        ?>
    </div>
</div>

