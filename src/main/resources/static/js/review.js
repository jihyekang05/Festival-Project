//var review_score = $("#review_score").val();
//var review_text = $("#review_text").val();
//var post_num = $("#post_num").val();


//const review_score = document.querySelector('#review_score');
//const review_text = document.querySelector('#review_text');
const submitBtn = document.querySelector('#review_submit');

//리뷰 등록버튼 눌렀을 때
submitBtn.addEventListener('click' , (e) => {
    e.preventDefault(); //기본 폼 동작 막기

    var review_score = $("#review_score").val();
    var review_text = $("#review_text").val();
    var post_num = $("#post_num").val();
    var review_post_num = $("#review_post_num").val();
    var member_index = $("#member_index").val();
    const reviewData = {review_score,review_text,post_num,review_post_num,member_index}


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

            } catch(err) {
                alert(err);
            }



});







