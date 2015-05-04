<?php
?> 
   <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="/css/normalize.css">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<link type="text/css" href="/bootstrap/css/jumbotron.css" rel="stylesheet">
<script type="text/javascript">
    function getUrlParameter(sParam)
    {
        var sPageURL = window.location.search.substring(1);
        var sURLVariables = sPageURL.split('&');
        for (var i = 0; i < sURLVariables.length; i++) 
        {
            var sParameterName = sURLVariables[i].split('=');
            if (sParameterName[0] == sParam) 
            {
                return sParameterName[1];
            }
        }
    }
    
   $(document).ready(function(){
    var hashString = getUrlParameter('hash');
    var listNo = 1;
    var btnChoice = false; 
    var btnWorkpoint = false;
    var btnNavigate = false;
   $("[id^=btnChoice]").click(function(){
        if(btnChoice !== false){
            btnChoice.toggleClass("btn-info",false);
            btnChoice.toggleClass("btn-default");  
        }
        $(this).toggleClass("btn-default",false);
        $(this).toggleClass("btn-info");
        btnChoice = ($(this));
        
        
   });
   $("[id^=btnWorkpoint]").click(function(){
       if(btnWorkpoint !== false){
            btnWorkpoint.toggleClass("btn-info",false);
            btnWorkpoint.toggleClass("btn-default");  
        }
        $(this).toggleClass("btn-default",false);
        $(this).toggleClass("btn-info");
        btnWorkpoint = ($(this));
   });
   $("[id^=btnNavigate]").click(function(){
       if(btnChoice !== false && btnWorkpoint !== false ){
       if(btnNavigate !== false){
            btnNavigate.toggleClass("btn-success",false);
            btnNavigate.toggleClass("btn-default");  
        }
        
        $(this).toggleClass("btn-default",false);
        $(this).toggleClass("btn-success");
        btnNavigate = ($(this)); 
        
            var choice = btnChoice.val();
            var workpoint = btnWorkpoint.val();

            $.post("index.php?hash="+hashString+"&list="+listNo,
            {"answers":"go",
             "choice":choice,
             "workpoint":workpoint,
             "hash":hashString,
             "list":listNo,
            });
        }
  });
   
}); 
</script>
   </head>
    <body>

        <div class="container">
    
<nav class="navbar navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">FINAH WEB SURVEY</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
              &nbsp;
        </div>
      </div>
    </nav>

 
    <div class="jumbotron">
        
        &nbsp;
    </div>

