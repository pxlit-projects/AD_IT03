
   $(document).ready(function(){
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
   $("[id^=btnNavigate_next]").click(function(){
       
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

            $.post("index.php?",
            {"answers":"go",
             "choice":choice,
             "workpoint":workpoint,
             "list":listNo
            });
        }
  });
  $("#btnType").click(function(){
       var id;
       var currentLink = $("#homeLink").prop('href');
     if($(this).text()=="Client"){
          $(this).html("Mantelzorger");
          var id = 3;
      }
      else if($(this).text()=="Mantelzorger"){
          $(this).html("Client");
          var id = 4;
      }
       var split = currentLink.split('/');
       var newLink = split[0] + '/' + split[1] + '/' + split[2] + '/' + split[3]+ '/' + split[4] + '/' + id + '/' + split[6] + '/';
       $("#homeLink").prop('href',newLink);
  });
}); 

