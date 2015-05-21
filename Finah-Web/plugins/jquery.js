
$(document).ready(function () {
    var a;
    var listNo = 1;
    var btnChoice = false;
    var btnWorkpoint = false;
    var btnNavigate = false;
    $("[id^=btnChoice]").click(function () {
        if (btnChoice !== false) {
            btnChoice.toggleClass("btn-info", false);
            btnChoice.toggleClass("btn-default");
        }
        $(this).toggleClass("btn-default", false);
        $(this).toggleClass("btn-info");
        btnChoice = ($(this));

    });
    $("[id^=btnWorkpoint]").click(function () {
        if (btnWorkpoint !== false) {
            btnWorkpoint.toggleClass("btn-info", false);
            btnWorkpoint.toggleClass("btn-default");
        }
        $(this).toggleClass("btn-default", false);
        $(this).toggleClass("btn-info");
        btnWorkpoint = ($(this));
    });
    $("[id^=btnNavigate]").click(function () {
        if ($(this).attr("id") === "btnNavigate_next") {
            if (btnChoice !== false && btnWorkpoint !== false) {
                if (btnNavigate !== false) {
                    btnNavigate.toggleClass("btn-success", false);
                    btnNavigate.toggleClass("btn-default");
                }
                $(this).toggleClass("btn-default", false);
                $(this).toggleClass("btn-success");
                btnNavigate = ($(this));
                var choice = btnChoice.val();
                var workpoint = btnWorkpoint.val();
                var request = $.ajax({
                    type: 'POST',
                    url: 'index.php',
                    data: {"answers": "go",
                        "choice": choice,
                        "workpoint": workpoint,
                        "list": listNo}})
                        .done(function (data) {
                            window.location.href = btnNavigate.val();
                        })
                        .fail(function () {
                            alert("something went wrong when posting your data");
                        });

            } else {
                $("#errorDiv").html("<div class='panel panel-warning'><div class='panel panel-heading'>Waarschuwing</div><div class='panel-body pd0 mg10'>\n\
                <p style='color:#FF0000;'><strong>Om verder te gaan moet je eerst jouw antwoorden aanduiden</strong><p></div></div>");
            }
        }
        if ($(this).attr("id") === "btnNavigate_previous") {
            btnNavigate = ($(this));
            window.location.href = btnNavigate.val();
        }
    });
    var request = $.ajax({
        type: 'GET',
        url: '/send/',
        data: {'a':'1'}})
            .done(function (data) {
               a = jQuery.parseJSON(data);
               //alert(a.iterator + ' ' + a.answers.length);
               //alert(a.answers.length + ' ' + a.iterator);
               if(a.answers.length > a.iterator){
                   var i = a.answers[a.iterator].toString();
                   var btn="btnChoice";
                   var btnName = btn+=i;
                   $("#"+btnName).click();
                   var x = a.workpoints[a.iterator].toString();
                   var btn="btnWorkpoint";
                   var btnName = btn+=x;
                    $("#"+btnName).click();
               }
               
            })
            .fail(function () {
                alert("something went wrong when getting the data");
            }); 
    $("#btnType").click(function () {
        var id;
        var currentLink = $("#homeLink").prop('href');
        if ($(this).text() == "Client") {
            $(this).html("Mantelzorger");
            var id = 3;
        }
        else if ($(this).text() == "Mantelzorger") {
            $(this).html("Client");
            var id = 4;
        }
        var split = currentLink.split('/');
        var newLink = split[0] + '/' + split[1] + '/' + split[2] + '/' + split[3] + '/' + split[4] + '/' + id + '/' + split[6] + '/';
        $("#homeLink").prop('href', newLink);
    });
});

