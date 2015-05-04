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

