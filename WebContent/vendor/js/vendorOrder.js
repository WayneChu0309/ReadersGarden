$(function(){

  $('.cancel_btn').on('click', function(){
	 var that = $(this);
	 var detail_btn = $(this).siblings('button');
	 var act_btn = $(this).siblings('a');
	 var vactid = ($(this).parent().find('td')[6]).innerText;
	 console.log("vactid=" + vactid);
	 var progress_ol = "progress"+vactid;
	  
	$('.pop_bg').show();
	$('#cancel_alert').show();

    $('#confirm_cancel').on('click', function(){

  	  $.ajax({
  		  url: cancelOrder_url,
  		  type: "POST",
  		  data: { "vactid": vactid },
  		  dataType: "json",
  		  success: function(res){
  			  console.log(res);
  			  $('#success_alert').show();
  			  $('#cancel_alert').hide();
  			  that.after(`<p class="canceled_order">✓ 訂單已取消</p>`);
  			  that.remove();
  			  detail_btn.remove();
  			  act_btn.remove();
  			  $(`#${progress_ol}`).remove();
  			  
  		  },
  		  error: function(res){
  			  console.log(res);
  			    $('#failure_alert').show();
  			    $('#cancel_alert').hide();
  		  }
  	  })
    }) 
  })

  $('#not_cancel').on('click', function(){
    $('.pop_bg').hide();
    $('#cancel_alert').hide();
  })

  $('#cancel_success').on('click', function(){
    $('.pop_bg').hide();
    $('#success_alert').hide();
  })
  
  $('#cancel_failure').on('click', function(){
    $('.pop_bg').hide();
    $('#failure_alert').hide();
  })
  
  $('.progress_btn').on('click', function(){
	  $(this).parent().children('ol').toggleClass('-none');
  })
  
  // function: 分割日期 (回傳陣列[yyyy, mm, dd])
  function splitDate(date){
    var date_arr = date.split("-");
    return date_arr;
  }
  
  // function: 計算總天數
  function dateDiff(Date1, Date2){
      var day_arr1 = splitDate(Date1);
      var day_arr2 = splitDate(Date2);
      var dformat1 = new Date(day_arr1[1]+'/'+day_arr1[2]+'/'+day_arr1[0]);
      var dformat2 = new Date(day_arr2[1]+'/'+day_arr2[2]+'/'+day_arr2[0]);
      var cal_days = (Math.abs(dformat2 - dformat1) / 1000 / 60 / 60 / 24);
      return cal_days;
    };
  
  //顯示在畫面上
  var totalday_group = $('.totalday');
  $.each(totalday_group, function(index, item){
	  var rtldate = $(item).prev().children();
	  var rtlstart = rtldate[0].innerText;
	  var rtlend = rtldate[1].innerText;

	  $(item).text(dateDiff(rtlend, rtlstart)+' 天');
	  
  });
})
