
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

            $.post("index.php?",
            {"answers":"go",
             "choice":choice,
             "workpoint":workpoint,
             "list":listNo
            });
        }
  });
   
}); 

