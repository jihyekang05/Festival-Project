


const id_text = document.getElementById("login_id");
const pw_text = document.getElementById("login_password");

const login_btn = document.getElementById("login_btn");

const logout_btn = document.getElementById("logout_btn");

const my_page_btn = document.getElementById("my_page_btn");


const admin_login_check = document.getElementById("admin_login_check");



function loginClick(){
    var login_id = $("#login_id").val();
    var login_password = $("#login_password").val();

    if(admin_login_check.checked){
        $.ajax({
            type: "POST",
            async: true,
            url: "/member/adminlogin",
            data: {
                login_id: login_id,
                login_password: login_password
            }
        })
            .done(function (text) {
    
                if (text == "S") {
                    Swal.fire({
                        title: '로그인 성공',
                        confirmButtonText: '확인',
                    }).then(() => {
                        location.reload();
                    });
                }
                else {
                    Swal.fire({
                        icon: 'error',
                        title: '로그인 실패',
                        text: '아이디 혹은 비밀번호를 확인하세요.',
                        confirmButtonText: '확인',
                    });
                }
            })
            .fail(function () {
                alert("Ajax 실패");
            });
    }
    else{
        $.ajax({
            type: "POST",
            async: true,
            url: "/member/login",
            data: {
                login_id: login_id,
                login_password: login_password
            }
        })
            .done(function (text) {
    
                if (text == "S") {
                    Swal.fire({
                        title: '로그인 성공',
                        confirmButtonText: '확인',
                    }).then(() => {
                        location.reload();
                    });
                }
                else {
                    Swal.fire({
                        icon: 'error',
                        title: '로그인 실패',
                        text: '아이디 혹은 비밀번호를 확인하세요.',
                        confirmButtonText: '확인',
                    });
                }
            })
            .fail(function () {
                alert("Ajax 실패");
    
            });
    }
    
};

function logoutClick(){
    Swal.fire({
        title: '로그아웃 하시겠습니까?',
        text: '진짜루..?',
        showDenyButton: true,
        confirmButtonText: '확인',
        denyButtonText: `취소`,
    }).then((result) => {
        if (result.isConfirmed) {
            
            $.ajax({
                type: "POST",
                async:true,
                url:"/member/logout",
            })
            
            .done(function (text){
                console.log(text);
                Swal.fire({
                    title: '로그아웃 성공',
                    confirmButtonText: '확인',
                })
                .then(() => {
                    location.reload();
                });
            });
        }
    });
};

