
	function post_delete($postNum){
        //console.log("post num : "+$postNum)
        location.href = "admin/delete/"+$postNum; // admin/delete/7
    };

    function post_modify($postNum){
            //console.log("post num : "+$postNum)
            location.href = "admin/modify/"+$postNum; // admin/delete/7
        };