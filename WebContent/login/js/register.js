$(document).ready(function() {
	$('#regiser2').click(function() {
		var mname = $('#mname').val();
		var memail = $('#memail').val();
		var mphoneNumber = $('#mphoneNumber').val();
		var maddress = $('#maddress').val();
		var mID = $('#mID').val();
		var mpassword = $('#mpassword').val();
	 
            $.ajax({
                type: "POST",//方法
                url: "RegisterServlet2" ,//表單接收url
                data: $('#form2').serialize(),
                success: function (data) {
                	if (data == "True") {
    					window.location.href = `${home}`;
    				} else {

    					Swal.fire({
    						title : '帳號或密碼錯誤!',
//    						showClass : {
//    							popup : 'animate__animated animate__fadeInDown'
//    						},
//    						hideClass : {
//    							popup : 'animate__animated animate__fadeOutUp'
//    						}
    					})
    				}

    			}
    		})
	})
	
	
});
