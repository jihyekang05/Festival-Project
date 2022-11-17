


const member_id_text = document.getElementById("member_id");
const member_nickname_text = document.getElementById("member_nickname");
const member_email_text = document.getElementById("member_email");
const member_addr_text = document.getElementById("member_addr");
const member_birth_text = document.getElementById("member_birth");
const member_category_text = document.getElementById("member_category");


const modify_btn = document.getElementById("modify_btn");

var flag=true;

modify_btn.addEventListener('click', ()=>{

    if(flag){
        // 수정 버튼 눌렀을 때 동작

        member_nickname_text.readOnly=false;
        member_addr_text.readOnly=false;
        member_birth_text.readOnly=false;
        member_category_text.readOnly=false;

        modify_btn.value="저장";
        flag=false;
    }
    else{
        // 변경사항 저장 버튼 눌렀을 때 동작
        
        member_nickname_text.readOnly=true;
        member_addr_text.readOnly=true;
        member_birth_text.readOnly=true;
        member_category_text.readOnly=true;

        
        sendToController();
        modify_btn.value="수정";
        flag=true;
    }

});


function sendToController(){
    

    var nickname = $('#member_nickname').val();
    var addr = $('#member_addr').val();
    var birth = $('#member_birth').val();
    var category = $('#member_category').val();

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