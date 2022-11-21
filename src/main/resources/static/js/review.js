const submitBtn = document.querySelector('#review_submit');

//리뷰 등록버튼 눌렀을 때
submitBtn.addEventListener('click' , (e) => {
    e.preventDefault(); //기본 폼 동작 막기
    var reviewScore = $("#reviewScore").val();
    var reviewText = $("#reviewText").val();
    var postNum = $("#postNum").val();
    var review_num = $("#review_postNum").val();
    var memberIndex = $("#memberIndex").val();


//    if(review_score>5 || review_score<0 || !review_score) {


    if(reviewScore>5 || reviewScore<0 || !reviewScore) {

        alert('0부터5사이의 숫자를 입력하시오');
    } else {
        const reviewData = {reviewScore,reviewText,postNum,review_num,memberIndex};
        try {
            res = fetch('/festival/review',
            {
                method:'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(reviewData)
            });
            alert('리뷰가 정상적으로 등록되었습니다');
            var str = '<tr>';
                  str += '<td>' + reviewData.reviewScore + '</td><td>' + reviewData.reviewText +'</td><td>' + reviewData.memberIndex+'</td></tr>';

                  $("#reviewList").append(str);

        }catch(err) {
            alert(err);
        }
    }



});