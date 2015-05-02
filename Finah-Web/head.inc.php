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
<script type="text/javascript">
    var Answers = [];
    var inc = 0;
   $(document).ready(function(){
    $("[id^=ChoiceBtn]").click(function(){
        Answers.push($(this).val());
       alert(Answers[inc] + ' ' + inc)
       inc++;
    });
}); 
</script>
   </head>
    <body>

        <div class="container-fluid">
    
<div class="jumbotron">
    <div class="row">
        <div class="col-md-4">
            <img src="@imageSource" />
        </div>
        <div class="col-md-8">
            <h2>Finah web</h2>
            <p>survey site</p>
        </div>
    </div>
</div>

