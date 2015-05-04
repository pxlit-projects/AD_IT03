<?php

//////////////// OUTPUT STARTS HERE /////////////////////////
/////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////
?>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 id="panel-title" class="panel-title">
            Thema : <?php echo $tTitle;?> <?php echo $tDesc;?>
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h3>
    </div>
    <div class="panel-body">
        <h4><?php echo $qTitle;?> <?php echo $qDesc;?></h4>
        <div class="btn-group btn-group-*" role="group" aria-label="...">
        <?php foreach ($answers as $id => $answer) { ?>
        <div class="btn-group" role="group">
        <button type="button" class="btn btn-default" id="btnChoice<?php echo $id;?>" value="<?php echo $id;?>"><?php echo $answer;?></button>
        </div>
        <?php } ?>
        </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        <h4 id="panel-title" class="panel-title">
            Wilt u hieraan verder werken?
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h4>
    </div>
    <div class="panel-body">
       <div class="btn-group btn-group-*" role="group" aria-label="..." >
        <div class="btn-group" role="group">
          <button type="button" class="btn btn-default " id="btnWorkpoint1">Ja</button>
        </div> 
        <div class="btn-group" role="group">
          <button type="button" class="btn btn-default" id="btnWorkpoint2">Neen</button>
        </div>
      </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-body">
<div class="col-sm-100 col-sm-push-100 btn-group" role="group" aria-label="...">
    <a href="<?php echo HTML_ROOT . 'survey/'.$list.'/'.$hash.'/previous/'; ?>" id="linkPrevious">
        <button type="button" class="btn btn-default" id="btnNavigate_previous" value="previous">Vorige stelling</button>
    </a>
    <a href="<?php echo HTML_ROOT . 'survey/'.$list.'/'.$hash.'/next/'; ?>" id="linkNext">
    <button type="button" class="btn btn-default" id="btnNavigate_next" value="next">Volgende stelling</button>
    </a>
</div>
<div id="errorDiv"></div>
    </div></div>


