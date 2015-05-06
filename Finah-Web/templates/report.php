<div class="panel panel-warning">
    <div class="panel-heading">
        <h4 id="panel-title" class="panel-title">
            <?php echo $_SESSION['report_message']; ?>
            <a class="anchorjs-link" href="#panel-title">
            <span class="anchorjs-icon"></span>
        </a>
        </h4>
    </div>
    <div class="panel-body">
        <div>
            <?php if(!empty($_SESSION['nextUrl'])){?>
    <div><a href="<?=$_SESSION['nextUrl']?>">CONTINUE...</a></div>
    <?php }?>
        </div>
    </div>
</div>