//const jejuBtn = document.querySelector('#jeju');
//
//jejuBtn.addEventListener('click' , (e) => {
//    e.preventDefault(); //기본 폼 동작 막기
//
//
//
//});

//$("#jeju").on("click", function(e) {
//    e.preventDefault();
//    console.log('a');
//})

//function jeju() {
//    console.log('a');
//}
var data = 0;

//$('#jeju').click(function(e) {
//    e.preventDefault();
//    data=1;
//    $.ajax({
//        type: "POST",
//        url: "/abc",
//        data: {local:data},
//        success: function() {
//            alert('성공');
//            //location.reload();
//        }, error: function() {
//            alert('실패');
//        }
//    });
//});

function localChk(local) {
    $("#boardList *").remove();
    event.preventDefault();
    data = local;
//    console.log(data);
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

//$.ajax({
//	url : "/local",
//	type : 'post',
//	data : {
//
//	},
//	success : function(data) { // controllor에서 list를 return 받았음
//    			console.log(data);
//     },
//	error : function() {
//		alert("error");
//	}
//});