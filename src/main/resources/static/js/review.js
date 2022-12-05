const submitBtn = document.querySelector('#review_submit');

//리뷰 등록버튼 눌렀을 때
submitBtn.addEventListener('click' , (e) => {
    
    Swal.fire({
        title: '리뷰 등록하기',
        text: '작성하신 내용을 등록할까요?',
        showDenyButton: true,
        confirmButtonText: '확인',
        denyButtonText: `취소`,
    }).then((result) => {
        if (result.isConfirmed) {
         
            e.preventDefault(); //기본 폼 동작 막기
            var reviewScore = $("#reviewScore").val();
            var reviewText = $("#reviewText").val();
            var postNum = $("#postNum").val();
            var review_num = $("#reviewPostNum").val();
            var memberIndex = $("#memberIndex").val();
        
        
            
        //    if(review_score>5 || review_score<0 || !review_score) {
        
        
            if(reviewScore>5 || reviewScore<1 || !reviewScore) {
                Swal.fire({
                    icon: 'error',
                    title: '',
                    text: '1~5사이의 숫자를 입력해주세요.',
                    confirmButtonText: '확인'
                });
            } else {
                var star= '';
                for(i=0;i<reviewScore;i++) {
                    star += '<i class="fa-solid fa-star"></i>';
                }
                const reviewData = {reviewScore,reviewText,postNum,review_num,memberIndex,star};
                console.log(reviewData);
                console.log(reviewData.star)
                try {
                    res = fetch('/festival/review',
                    {
                        method:'POST',
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(reviewData)
                    });
                    Swal.fire({
                        title: '정상적으로 등록되었습니다!',
                        confirmButtonText: '확인',
                    });                    var str = '<tr>';
        //                  str += '<td>' +  reviewData.reviewScore + '</td><td>' + reviewData.reviewText +'</td><td>' + reviewData.memberIndex+'</td></tr>';
                            // str += '<td>' +reviewData.star+ '</td><td>' + reviewData.reviewText +'</td><td>' + reviewData.memberIndex+'</td></tr>';
                            str += '<td>' +reviewData.star+ '</td><td>' + reviewData.reviewText +'</td><td>' + reviewData.memberIndex+'</td></tr>';
                          $("#reviewList").append(str);
        
                }catch(err) {
                    alert(err);
                }
            }
        

        }
    });
});