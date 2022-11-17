



	function post_delete($postNum){
        //console.log("post num : "+$postNum)
        location.href = "admin/delete/"+$postNum; // admin/delete/7
    }

    function post_modify($postNum){
            //console.log("post num : "+$postNum)
         location.href = "admin/modify/"+$postNum; // admin/delete/7
            //post_existing();

         changeBtnName();
    }

    function notice_delete($postNum){
        console.log("post num : "+$postNum)
        location.href = "noticeManagement/delete_notice/"+$postNum; // admin/delete/7
    }

    function changeBtnName($postNum)  {
        console.log("123")
      const btnElement
        = document.getElementById('btn123');

      btnElement.innerText = "공지해제!";
    }


