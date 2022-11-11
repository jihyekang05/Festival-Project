


const id_text =  document.getElementById("login_id");
const pw_text = document.getElementById("login_password");

const login_btn = document.getElementById("login_btn");





login_btn.addEventListener('click', ()=>{


    var login_id = $("login_id");
    var login_password = $("login_password");

    $.ajax({
        type: "POST",
        async:true,
        url: "/login",
        data: {
            login_id: login_id,
            login_password: login_password
        }
    })
    .done(function(text){
        
        if(text=='S'){
            alert("로그인성공");
        }
        else{
            alert("로그인실패");
        }

    })
});