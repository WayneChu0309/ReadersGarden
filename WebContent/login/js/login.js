$(document).ready(function() {
	$('#login').click(function() {
		var email = $('#email_input').val();
		var password = $('#password_input').val();

		$.ajax({
			type : "POST",
			url : "LoginServlet2",

			data : $('#form1').serialize(),
			success : function(data) {
				if (data == "True") {
					window.location.href = `${home}`;
				} else {

					Swal.fire({
						title : '帳號或密碼錯誤!',
//						showClass : {
//							popup : 'animate__animated animate__fadeInDown'
//						},
//						hideClass : {
//							popup : 'animate__animated animate__fadeOutUp'
//						}
					})
				}

			}

		})
	})
	
	
});
