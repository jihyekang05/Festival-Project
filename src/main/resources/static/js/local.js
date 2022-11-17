
var data = 0;


function localChk(local) {
    $("#boardList *").remove();
    event.preventDefault();
    data = local;
    $.ajax({
        type: "POST",
        url: "/local",
        data: {local:data},
        success: function(data) {
            var locList = data;
            var str = '<tr>';
            $.each(locList , function(i) {
                str += '<td>' + locList[i].board_addr + '</td><td>' + locList[i].festival_title + '</td><td>' + locList[i].festival_upload_date + '</td></tr>';
            });
            $("#boardList").append(str);
            console.log(data);

//            alert('성공');

        }, error: function() {
            alert('실패');
        }
    });

}

