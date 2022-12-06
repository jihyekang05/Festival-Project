var pageNum = 1;
//새로고침 했을 때 페이지 맨 위로
window.onload = function() {
    setTimeout (function() {
    scrollTo(0,0);
    },100);
}

$(window).scroll(function() {
	if($(window).scrollTop() + $(window).height() == $(document).height()) {

	//bottom에 왔을 때 ajax로 다음 데이터 받아오기
		$.ajax({
            type: 'POST',
            url: "/categoryfestival/scroll",
            data: {
            page: pageNum, // current Page
//            size: 6, // max page size(수정해야함)
            },
          dataType: 'text'
        }).done(function (result) {
            pageNum++;
            let json =  JSON.parse(result);
            if(json.content.length > 0 ){
            var str = '';
            $.each(json.content , function(i) {
                            str += "<div class='col-md-4'>\
                                        <div class='card' style='width: 100%;' >\
                                           <img width='180' height='300' src='/assets/img/image/"+ json.content[i].contentImage+"' class='card-img-top' alt='...'>\
                                           <div class='card-body'>\
                                             <h5 class='festivalTitle'>"+json.content[i].festivalTitle+"</h5>\
                                             <p>조회수</p>\
                                             <p class='festival-text'>"+ json.content[i].contentViews +"</p>\
                                             <a href='/festival/"+ json.content[i].postNum +"' class='btn btn-primary'>자세히보기</a>\
                                           </div>\
                                         </div>\
                                       </div>"
                        });
                        $("#post_list").append(str);
            }
        }).fail(function(data, textStatus, errorThrown){
                     console.log(data);
          });
    }
});

