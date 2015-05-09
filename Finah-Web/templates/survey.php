<?php

//////////////// OUTPUT STARTS HERE /////////////////////////
/////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////
?>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 id="panel-title" class="panel-title">
            <span class="text-left"><strong style="color:#000000;">Thema : <?php echo $tTitle;?> <?php echo $tDesc;?></strong></span>
            <span class="pull-right "><strong style="color:#000000;"><?php echo $qCur+1;?> / <?php echo $qNum;?></strong></span>
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h3>
    </div>
    <div class="panel-body">
        <center><h3><?php echo $qTitle;?> <?php echo $qDesc;?></h3></center>
    </div>
    <div class="panel-body">
        <div class="mg10">
        <div class="row"> 
            <div class="btn-group btn-group-justified "role="group" aria-label="...">
            <div class="col-sm-4 col-xs-12 pd0">
            <button type="button" class="btn btn-default btn-lg btn-block" id="btnChoice1" value="<?php echo $aId[0];?>"><?php echo $aTitle[0];?></button>
            </div><div class="col-sm-4 col-xs-12 pd0">
            <button type="button" class="btn btn-default btn-lg btn-block" id="btnChoice2" value="<?php echo $aId[1];?>"><?php echo $aTitle[1];?></button>
            </div><div class="col-sm-4 col-xs-12 pd0">
            <button type="button" class="btn btn-default btn-lg btn-block" id="btnChoice3" value="<?php echo $aId[2];?>"><?php echo $aTitle[2];?></button>
            </div>
        </div>
        </div><div class="row">
          <div class="btn-group btn-group-justified"role="group" aria-label="...">
              <div class="col-xs-12 col-md-6 pd0">
            <button type="button" class="btn btn-default btn-lg btn-block" id="btnChoice4" value="<?php echo $aId[3];?>"><?php echo $aTitle[3];?></button>
              </div><div class="col-xs-12 col-md-6 pd0">
             <button type="button" class="btn btn-default btn-lg btn-block" id="btnChoice5" value="<?php echo $aId[4];?>"><?php echo $aTitle[4];?></button>
              </div>
          </div>
        </div>
        </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        <h4 id="panel-title" class="panel-title">
            <strong style="color:#000000;">Wilt u hieraan verder werken?</strong>
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h4>
    </div>
    <div class="panel-body pd0 mg10">
       <div class="btn-group btn-group-*" role="group" aria-label="..." >
        <div class="btn-group pd0" role="group">
            <button type="button" class="btn btn-default btn-lg" id="btnWorkpoint1" value="1">Ja</button>
        </div> 
        <div class="btn-group pd0" role="group">
            <button type="button" class="btn btn-default btn-lg" id="btnWorkpoint2" value="0">Neen</button>
        </div>
      </div>
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-body pd0 mg10">
<div class="col-sm-100 col-sm-push-100 btn-group" role="group" aria-label="...">
    <a href="<?php echo HTML_ROOT . 'survey/'.$list.'/'.$hash.'/previous/'; ?>" id="linkPrevious">
        <button type="button" class="btn btn-default btn-lg" id="btnNavigate_previous" value="previous">Vorige stelling</button>
    </a>
    <a href="<?php echo HTML_ROOT . 'survey/'.$list.'/'.$hash.'/next/'; ?>" id="linkNext">
    <button type="button" class="btn btn-default btn-lg" id="btnNavigate_next" value="next">Volgende stelling</button>
    </a>
    <?php if ($submit){?>
    <a href="<?php echo HTML_ROOT . 'survey/'.$list.'/'.$hash.'/submit/'; ?>" id="linkSubmit">
    <button type="button" class="btn btn-success btn-lg" id="btnNavigate_submit" value="next">Versturen</button>
    </a>
    <?php } ?>
</div>
<div id="errorDiv"></div>
    </div></div>


