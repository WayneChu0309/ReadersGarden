$(function(){
	
	var act_progress;
	
	//change事件: 依分類變換活動的select option
	$('.status_select').on('change', function(){
		var tkstatus = $(this).val();
		
		$.ajax({
			url: orderAllocate_url,
			type: "POST",
			data: {"vendorid": vendorid, "tkstatus": tkstatus},
			dataType: "json",
			beforeSend: function(res){
				var option_str = "";
				option_str += `<option value="">查詢中...</option>`;
				$('.act_select').html(option_str);

			  },
			success: function(res){
				option_str = "";
				if(res.length == 0){
					option_str += `<option value="">無</option>`;
				}
				$.each(res, function(index, item){
					option_str += `<option value="${item.vactid}">${item.name}</option>`;
				})
				$('.act_select').html(option_str);
			}
		})
	})
	
	//click事件: 秀出選擇的活動資訊
	$('#choose_btn').on('click', function(){	
		var vactid = $('.act_select').val();
		console.log(vactid);
		if(vactid == ""){
			return;
		} else {
			
			$("#show_img img").attr('src', `${img_url}`+`${vactid}`);			
			$.ajax({
				url: orderOne_url,
				type: "POST",
				data: {"vactid" : vactid},
				dataType: "json",
				success: function(res){
					$('#show_name').text(res.name);
					$('#show_vactid').text(res.vactid);
					$('#show_cate').text(res.vacttype);
					$('#show_actStart').text(`${res.actstart}`);
					$('#show_actEnd').text(`${res.actend}`);
					$('#show_tkStart').text(`${res.tkstart}`);
					$('#show_tkEnd').text(`${res.tkend}`);
					$('#show_price').text(`${res.price}元`);
					$('#show_content').text(res.actcnt);
					
					act_progress = res.progress;
					check_function();
				}
			})
		}	
	})
	
	//function: 依活動狀態變更$('#more_fuction')
	
	var check_function = function(){
		var today = new Date();
		console.log($('#show_tkStart'));

		var tkstart = transferDate($('#show_tkStart').text());
		var tkend = transferDate($('#show_tkEnd').text());

		//售票中 (顯示連結至售票頁)
//		if(act_progress == 99){
//			$('#more_function').text('我被取消了');
//
//		} else if(act_progress < 3){
//			$('#more_function').text('我還在申請');
//
//		} else if(today > tkstart && today < tkend){
//			$('#more_function').text('我在售票中');
//		//待上架
//		} else if (today < tkstart){
//			$('#more_function').text('我在等上架');
//
//		//已結束
//		} else if (today > tkend){
//			$('#more_function').text('我結束了');
//		}
	}
	
	// function: 轉字串成日期
	  function transferDate(dateStr){
	    var day_arr = dateStr.split("-");
	    var dformat = new Date(day_arr[1]+'/'+day_arr[2]+'/'+day_arr[0]);
	    return dformat;
		}
	
	// function: 單一查詢時, 無法用篩選功能
	  function filterUnable(){
		  
	  }
	  
	//初始觸發
	$('.status_select').change();
	check_function();
	
	//pop彈窗
  $('.cancel_btn').on('click', function(){
    $('.pop_bg').show();
    $('#cancel_alert').show();
  })

  $('#not_cancel').on('click', function(){
    $('.pop_bg').hide();
    $('#cancel_alert').hide();
  })

  $('#confirm_cancel').on('click', function(){
    // $('.pop_bg').hide();
    $('#cancel_alert').hide();
    $('#success_alert').show();
  })

  $('#cancel_success').on('click', function(){
    $('.pop_bg').hide();
    $('#success_alert').hide();
  })
})
