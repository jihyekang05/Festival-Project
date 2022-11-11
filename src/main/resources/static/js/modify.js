



	function post_delete($postNum){
        //console.log("post num : "+$postNum)
        location.href = "admin/delete/"+$postNum; // admin/delete/7
    };

    function post_modify($postNum){
            //console.log("post num : "+$postNum)
            location.href = "admin/modify/"+$postNum; // admin/delete/7
            //post_existing();

         changeBtnName();
        };

    function post_existing($postNum){

    }

    function changeBtnName()  {
      const btnElement = document.getElementById('btnWrite');
      btnElement.innerText = '새이름!';
    }



