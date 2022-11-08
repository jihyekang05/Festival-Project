

// DOM 으로 컴포넌트 받아오기
const id = document.getElementById("id");
const id_dup_check = document.getElementById("id_dup_check");
const id_valid_text = document.getElementById("id_valid_text");

const password = document.getElementById("password");
const password_check = document.getElementById("password_check");
const password_valid_text = document.getElementById("password_valid_text");
const password_check_valid_text = document.getElementById("password_check_valid_text");

const nickname = document.getElementById("nickname");
const nickname_dup_check = document.getElementById("nickname_dup_check");
const nickname_valid_text = document.getElementById("nickname_valid_text");

const email = document.getElementById("email");
const email_check = document.getElementById("email_check");
const email_valid_text = document.getElementById("email_valid_text");
const email_check_valid_text = document.getElementById("email_check_valid_text");

const phone = document.getElementById("phone");
const phone_valid_text = document.getElementById("phone_valid_text");


const birth = document.getElementById("birth");
const birth_valid_text = document.getElementById("birth_valid_text");

const submitBtn = document.getElementById("submitForm");
const signupForm = document.signupForm;


const postcode = document.getElementById("postcode");
const detailAddress = document.getElementById("detailAddress");
const detailAddress_valid_text = document.getElementById("detailAddress_valid_text");

const address = document.getElementById("address");
const address_valid_text = document.getElementById("address_valid_text");

const extraAddress = document.getElementById("extraAddress");



// 유효성 검사를 위한 정규식 패턴
const id_pattern = /^[a-zA-Z][0-9a-zA-Z]{4,7}$/;
const pw_pattern = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
const nickname_pattern = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ].{0,5}$/;
const email_pattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

const forms = document.getElementsByClassName('validation-form');




// Vlid 메세지
function validTextChange(flag, validObject, text){


    if(flag){
        validObject.innerHTML = "사용 가능한 "+text+"입니다.";
        validObject.classList.remove('invalidText');
        validObject.classList.add('validText');
    }
    else{
        validObject.innerHTML=text+"를(을) 확인하세요.";
        validObject.classList.remove('validText');
        validObject.classList.add('invalidText');


    }    

}


//////////////////////////////////////////////////////////////////////
// ID 유효성검사 로직

id.addEventListener("keydown", () => {

    id.classList.remove('valid');
    id_dup_check.disabled = false;

    validTextChange(false, id_valid_text, "아이디");
    

});


id.addEventListener("keyup", () => {

    id.classList.remove('valid');
    id_dup_check.disabled = false;
    validTextChange(false, id_valid_text, "아이디");

});


// ID 중복 체크 이벤트 리스너
id_dup_check.addEventListener('click', function (event) {

    console.log("id : "+id.value)

    pattern_check = false;
    dup_check = false;
    
    // Ajax POST 로 ID 중복체크
    $.ajax({
        url: "/iddupcheck",
        async: true,
        data: {member_id : id.value},
        method: "POST",
        dataType: "text"
    })

        .done(function (text) {
            // Ajax 통신 성공했을 경우
            
            console.log(text);
            console.log("AJAX SUCCESS");
            dup_check = true;

            if(text=='S'){
                dup_check = true;
            }
            else{
                dup_check = false;
            }


            if (id_pattern.test(id.value)) {
                pattern_check = true;
            }

            else {
                pattern_check = false;
            }


            console.log("patter_check: " + pattern_check);
            console.log("dup_check: " + dup_check);

            if (pattern_check && dup_check) {
                $("#id_valid_msg").textContent="중복체크 완료";
                Swal.fire({
                    title: '유효한 아이디입니다.',
                    text: '이 아이디를 사용하시겠습니까?',
                    showDenyButton: true,
                    confirmButtonText: '사용',
                    denyButtonText: `취소`,
                }).then((result) => {
                    if (result.isConfirmed) {
                        id.classList.add('valid');
                        Swal.fire('저장되었습니다.', '', 'success');
                        id_dup_check.disabled = true;
                        validTextChange(true, id_valid_text, "아이디");

        
                    }
                    else {
                        id.value = '';
                        id.focus();
                    }
                });
            }
            else {
                Swal.fire({
                    icon: 'error',
                    title: '사용 불가능한 아이디입니다.',
                    text: '다른 아이디를 입력하세요.',
                });

                id.value = '';
                id.focus();
                validTextChange(false, id_valid_text, "아이디");


            }

        })

        .fail(function (xhr, status, errorThrown) {
            // alert("아이디 사용 불가능");
            console.log("AJAX FAIL");
            console.log("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+errorThrown);
            // 실패했을 경우
        })
});

//////////////////////////////////////////////////////////////////////////
// PW 유효성 검사 로직
password.addEventListener("keyup", () => {

    if (password_check.value == password.value) {
        password_check.classList.add('valid');
        validTextChange(true, password_check_valid_text, "비밀번호");

    }
    else {
        password_check.classList.remove('valid');
        validTextChange(false, password_check_valid_text, "비밀번호 확인");
    }

    if (pw_pattern.test(password.value)) {
        password.classList.add('valid');
        validTextChange(true, password_valid_text, "비밀번호");
    }
    else {
        password.classList.remove('valid');
        validTextChange(false, password_valid_text, "비밀번호");

    }
});


password.addEventListener("keydown", () => {
    if (password_check.value == password.value) {
        password_check.classList.add('valid');
        validTextChange(true, password_check_valid_text, "비밀번호");

    }
    else {
        password_check.classList.remove('valid');
        validTextChange(false, password_check_valid_text, "비밀번호 확인");
    }
    
    if (pw_pattern.test(password.value)) {
        password.classList.add('valid');
        validTextChange(true, password_valid_text, "비밀번호");

    }
    else {
        password.classList.remove('valid');
        validTextChange(false, password_valid_text, "비밀번호");

    }
});


//////////////////////////////////////////////////////////////////////////
// PW check 유효성 검사 로직

password_check.addEventListener("keyup", () => {
    if (password_check.value == password.value) {
        password_check.classList.add('valid');
        validTextChange(true, password_check_valid_text, "비밀번호");

    }
    else {
        password_check.classList.remove('valid');
        validTextChange(false, password_check_valid_text, "비밀번호 확인");
    }
});

password_check.addEventListener("keydown", () => {
    if (password_check.value == password.value) {
        password_check.classList.add('valid');
        validTextChange(true, password_check_valid_text, "비밀번호");

    }
    else {
        password_check.classList.remove('valid');
        validTextChange(false, password_check_valid_text, "비밀번호 확인");

    }
});


//////////////////////////////////////////////////////////////////////////
// nickname 유효성 검사 로직


nickname.addEventListener("keydown", () => {

    nickname.classList.remove('valid');
    nickname_dup_check.disabled = false;

    validTextChange(false, nickname_valid_text, "닉네임");
    

});


nickname.addEventListener("keyup", () => {

    nickname.classList.remove('valid');
    nickname_dup_check.disabled = false;
    validTextChange(false, nickname_valid_text, "닉네임");

});


nickname_dup_check.addEventListener("click", () => {


    pattern_check = false;
    dup_check = false;



    $.ajax({
        url: "/nicknamedupcheck",
        async: true,
        data: {member_nickname : nickname.value},
        method: "POST",
        dataType: "text"
    })

        .done(function (text) {
            // Ajax 통신 성공했을 경우
            
            console.log(text);
            console.log("AJAX SUCCESS");
            
            
            if(text=='S'){
                dup_check = true;
            }
            else{
                dup_check = false;
            }

            if (nickname_pattern.test(nickname.value)) {
                pattern_check = true;
            }

            else {
                pattern_check = false;
            }
            console.log(pattern_check);
            console.log(dup_check);

            if (pattern_check && dup_check) {

                Swal.fire({
                    title: '유효한 닉네임입니다.',
                    text: '이 닉네임을 사용하시겠습니까?',
                    showDenyButton: true,
                    confirmButtonText: '사용',
                    denyButtonText: `취소`,
                }).then((result) => {
                    if (result.isConfirmed) {
                        nickname.classList.add('valid');
                        Swal.fire('저장되었습니다.', '', 'success');
                        nickname_dup_check.disabled = true;
                        validTextChange(true, nickname_valid_text, "닉네임");

                    }
                    else {
                        nickname.value = '';
                        nickname.focus();
                    }
                });
            }

            else {
                Swal.fire({
                    icon: 'error',
                    title: '닉네임이 유효하지 않습니다.',
                    text: '닉네임을 확인하세요',
                });
        
                nickname.value = '';
                nickname.focus();
                validTextChange(false, nickname_valid_text, "닉네임");

            }

        })

        .fail(function (xhr, status, errorThrown) {
            console.log("AJAX FAIL");
            console.log("code:"+xhr.status+"\n"+"message:"+xhr.responseText+"\n"+"error:"+errorThrown);
        })
});

nickname.addEventListener("keydown", () => {

    nickname.classList.remove('valid');
    nickname_dup_check.disabled = false;

});

//////////////////////////////////////////////////////////
// email 유효성 검증 로직

email.addEventListener("keyup", () => {
    if (email_check.value == email.value) {
        email_check.classList.add('valid');
        console.log("valid");
        console.log(email_check.value);
        validTextChange(true, email_check_valid_text, "이메일 확인");

    }
    else {
        email_check.classList.remove('valid');
        validTextChange(false, email_check_valid_text, "이메일 확인");

    }
    if (email_pattern.test(email.value)) {
        email.classList.add('valid');
        console.log("valid");
        console.log(email.value);
        email_check.classList.remove('valid');
        validTextChange(true, email_valid_text, "이메일");

    }
    else {
        email.classList.remove('valid');
        validTextChange(false, email_valid_text, "이메일");

    }
});


email.addEventListener("keydown", () => {
    if (email_check.value == email.value) {
        email_check.classList.add('valid');
        console.log("valid");
        console.log(email_check.value);
        validTextChange(true, email_check_valid_text, "이메일 확인");

    }
    else {
        email_check.classList.remove('valid');
        validTextChange(false, email_check_valid_text, "이메일 확인");

    }
    if (email_pattern.test(email.value)) {
        email.classList.add('valid');
        console.log("valid");
        console.log(email.value);
        email_check.classList.remove('valid');
        validTextChange(true, email_valid_text, "이메일");


    }
    else {
        email.classList.remove('valid');
        validTextChange(false, email_valid_text, "이메일");

    }
});


//////////////////////////////////////////////////////////
// email_check 유효성 검증 로직

email_check.addEventListener("keyup", () => {
    if (email_check.value == email.value) {
        email_check.classList.add('valid');
        console.log("valid");
        console.log(email_check.value);
        validTextChange(true, email_check_valid_text, "이메일 확인");

    }
    else {
        email_check.classList.remove('valid');
        validTextChange(false, email_check_valid_text, "이메일 확인");

    }
});

email_check.addEventListener("keydown", () => {
    if (email_check.value == email.value) {
        email_check.classList.add('valid');
        console.log("valid");
        console.log(email_check.value);
        validTextChange(true, email_check_valid_text, "이메일 확인");

    }
    else {
        email_check.classList.remove('valid');
        validTextChange(false, email_check_valid_text, "이메일 확인");

    }
});



///////////////////////////////////////////////////
// 주소 유효성 검증 로직


// detailAddress.addEventListener("focusout", () => {
//     if (detailAddress.value != '') {
//         detailAddress.classList.add('valid');
//         validTextChange(true, detailAddress_valid_text, "상세주소");

//     }
//     else {
//         detailAddress.classList.remove('valid');
//         validTextChange(false, detailAddress_valid_text, "상세주소");

//     }
// });



///////////////////////////////////////////////////
// submit 정의

let validFlag = false;



function validCheck(){
    if(address.value!=''){
        address.classList.add('valid');
        validTextChange(true, address_valid_text, "주소");

    }
    else{
        address.classList.remove('valid');
        validTextChange(false, address_valid_text, "주소");
    }

    validCnt = document.getElementsByClassName("valid");

    // valid 개수 카운팅 후 flag

    if(validCnt.length>=7){
    // valid 개수 맞으면
        validFlag=true;
    }
    else{
    // valid 개수 적으면
        validFlag=false;
    }
}

submitBtn.addEventListener("click", (event) => {
    

    
    // 주소 유효성 검증

    validCheck();


    if (validFlag) {

        Swal.fire({
            title: '회원가입이 가능합니다.',
            text: '회원가입을 진행할까요??',
            showDenyButton: true,
            confirmButtonText: '확인',
            denyButtonText: `취소`,
        }).then((result) => {
            if (result.isConfirmed) {
                // document.getElementById("signUpForm").submit();
                $("#signUpForm").submit();
            }
            else {
                
            }
        });
        
    }
    else {

        Swal.fire({
            icon: 'error',
            title: '회원가입 실패',
            text: '필수항목을 입력해주세요.',
        });
    }

});