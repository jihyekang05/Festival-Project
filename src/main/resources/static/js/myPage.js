


const memberId_text = document.getElementById("memberId");
const memberNickname_text = document.getElementById("memberNickname");
const memberEmail_text = document.getElementById("memberEmail");
const memberAddr_text = document.getElementById("memberAddr");
const memberBirth_text = document.getElementById("memberBirth");
const memberCategory_text = document.getElementById("memberCategory");


const modify_btn = document.getElementById("modify_btn");

var flag=true;

modify_btn.addEventListener('click', ()=>{

    if(flag){
        // 수정 버튼 눌렀을 때 동작

        memberNickname_text.readOnly=false;
        memberAddr_text.readOnly=false;
        memberBirth_text.readOnly=false;
        memberCategory_text.readOnly=false;

        modify_btn.value="저장";
        flag=false;
    }
    else{
        // 변경사항 저장 버튼 눌렀을 때 동작
        
        memberNickname_text.readOnly=true;
        memberAddr_text.readOnly=true;
        memberBirth_text.readOnly=true;
        memberCategory_text.readOnly=true;

        
        sendToController();
        modify_btn.value="수정";
        flag=true;
    }

});


function sendToController(){
    

    var nickname = $('#memberNickname').val();
    var addr = $('#memberAddr').val();
    var birth = $('#memberBirth').val();
    var category = $('#memberCategory').val();

    console.log(nickname);
    console.log(birth);
    $.ajax({
        type: "POST",
        async:true,
        url: "/member/mypage/modify",
        data:{
            nickname:nickname,
            addr:addr,
            birth:birth,
            category:category
        }

    })
    .done(function(text){
       
        console.log(text);

    })
    .fail(function(){
        
        alert("Ajax Failed!!!!T^T");

    });

};