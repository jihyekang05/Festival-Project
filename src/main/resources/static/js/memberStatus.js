
var data = 0;




function memberState_modify(memberIndex){
               event.preventDefault();
               var member_state_button= document.getElementById("member_state_button");
               var member_state_value = document.getElementById("member_state_value");
               var member_state= document.getElementById("member_state_button").parentNode;
               console.log("memberstate_button's parrentNode"+member_state_button.parentNode);
               console.log("memberstate_button's parrentNode's ID"+member_state_button.parentNode.ID);  


               data = memberIndex;
               console.log("AJAX in "+data);
               console.log("DATA type "+typeof data);
               console.log(member_state_button);
               console.log(member_state_button.value);


                       $.ajax({
                               type: "POST",
                               async:true,
                               url: "/admin/memberStateModify",
                               data: {
                               memberIndex:data
                               }
                        })
                        .done(function(data){
                            var member=data;
//                             console.log(member.memberState)
//                             if(member.memberState=='1'){
//                                             alert("insert SUCCESS");
//                                             member_state_button.value="정지해제";
//
//
//                                         }
//
//                                         else if(member.memberState=='0'){
//                                             member_state_button.value="회원정지";
//                                         }
//                                        else{
////                                             member_state_button.disabled;
//                                        }

                        $('#data').load(location.href+' #data');

                        })
                        .fail(function(data){
                        console.log("Ajax fail");
                        });
                        


        };