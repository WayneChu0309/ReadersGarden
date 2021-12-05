$(function(){

    $('#edit_btn, #return_btn').on('click', function(){
        window.location.href = `${back_url}`;
    })

    $('#apply_btn').on('click', function(){
    	var vendorid = $('#vendorid').val();
    	
    	$.ajax({
    		url: apply_url,
    		type: "POST",
    		data: {"vendorid": vendorid},
    		dataType: "json",
    		success: function(res){
				console.log(res);
    			if(res == "errorMsgs"){
    				console.log("here is errorMsgs");
        		    $('#error').show();
        		    $('#success').hide();

    			} else {
        			$('#new_vactid').text(res);
        		    $('#success').show();
        		    $('#error').hide();

    			}
    			$('.pop_bg').show();
    	        setTimeout(redirect_action, 10000);
    		    webSocket.send(1);
    		}
    	})
    })
    
    var redirect_action = function(){
        window.location.href = `${redirect_url}`;
    }
    
    $('#redirect_click').on('click', function(){
    	redirect_action();
    })
    
    $("html,body").animate({"scrollTop": "650px"}, 500);

})