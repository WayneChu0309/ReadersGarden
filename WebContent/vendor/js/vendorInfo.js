$(function(){
	
  	if(locate == "password"){
  		$('#info_edit, #password_edit').toggleClass('edit_active');
  		$('#info_edit_zone').hide();
  		$('#password_edit_zone').show();
  	}
  	
  $('#info_edit').on('click', function(){
    if($(this).hasClass('edit_active')){
      return;
    } else{
      $(this).toggleClass('edit_active');
      $('#password_edit').toggleClass('edit_active');
      $('#info_edit_zone').show();
      $('#password_edit_zone').hide();
    }

  })

  $('#password_edit').on('click', function(){
    if($(this).hasClass('edit_active')){
      return;
    } else{
      $(this).toggleClass('edit_active');
      $('#info_edit').toggleClass('edit_active');
      $('#password_edit_zone').show();
      $('#info_edit_zone').hide();
    }
  })
  
  $('#confirm_edit').on('click', function(){
	  $('#info_btn').click();
  })
  
})
