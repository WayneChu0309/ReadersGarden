$(function(){

	//篩選功能
	$('.allselect').on('click', function(){
		$(location).attr('href', vactAll_url);
	})

	$('.statusselect').on('click', function(){	
		if($(this).hasClass('unableselect')){
			return;
		}
		$('#progress_select').toggleClass('-none');
		if(!$('#site_select').hasClass('-none')){
			$('#site_select').addClass('-none');
		}
	})
	
	$('.siteselect').on('click', function(){
		if($(this).hasClass('unableselect')){
			return;
		}
		$('#site_select').toggleClass('-none');
		if(!$('#progress_select').hasClass('-none')){
			$('#progress_select').addClass('-none');
		}
	})
	
	$('#progress_select').on('change', function(){
		var that = $(this);
		var select_value = $(this).val();
		var progress_li = $('.orderitem .progress');
		var ul_size = 0;
		var count = 0;
		console.log(progress_li.parent('ul'));

		if(select_value == -1){
			return;
		}
		
		$('#result_show').text('');
		
		$.each(progress_li, function(index, item){
			if($(item).attr('data-progress') != select_value){				
				$(item).parent('ul').addClass('-none');
				count++;
			} else {
				$(item).parent('ul').removeClass('-none');
			}
			
			ul_size++;
		})
		
		if(count == ul_size){
			$('#result_show').text('查無結果');
		}
	})
	
	$('#site_select').on('change', function(){

		var that = $(this);
		var select_value = $(this).val();
		var site_li = $('.orderitem .siteid');
		var ul_size = 0;
		var count = 0;
			
		if(select_value == -1){
			return;
		}
		
		$('#result_show').text('');

		$.each(site_li, function(index, item){
			if($(item).attr('data-siteid') != select_value){				
				$(item).parent('ul').addClass('-none');
				count++;
			} else {
				$(item).parent('ul').removeClass('-none');
			}
			ul_size++;
		})
		
		if(count == ul_size){
			$('#result_show').text('查無結果');
		}
	})
	
		// 查看活動功能
		$('.check_act').on('click', function(){
			var select_vactid = $(this).parent('li').attr('id');
			console.log(select_vactid);
			var progress_item = $(`ul[data-vactid=${select_vactid}]`).children('.progress');
			console.log(progress_item);
			var progress = $(this).parent('li').next().attr('data-progress');
			console.log("progress:" + progress);
			
			$('#result_maker').show();
			$('#result_zone').text("");
			
			if(progress == 0){
				$.ajax({ //變更進度
					url: vact_progress_url,
					type: "POST",
					data: {"vactid" : select_vactid, "progress": 1},
					dataType: "json",
					success: function(res){
						
						console.log(res);
						
						$.ajax({ //更新畫面上的進度
							url: check_progress_url,
							type: "POST",
							data: {"vactid" : select_vactid},
							dataType: "json",
							success: function(res){
								progress_item.text(res.progressStr);
								progress_item.attr('data-progress', res.progress);
							},
							error: function(res){
								console.log("error");
							}
						})
						
					},
					error: function(res){
						console.log("error");
					}
				})
			}
			
			$.ajax({
				url: vact_check_url,
				type: "POST",
				data: {"vactid": select_vactid},
				dataType: "json",
				success: function(res){
					console.log(res);
					//塞資料
					$('#pop_vactid').text(res.vactid);
					$('#pop_vendorid').text(res.vendorid);
					$('#pop_company').text(res.company);
					$('#pop_addr').text(res.addr);
					$('#pop_taxid').text(res.taxid);
					$('#pop_name').text(res.name);
					$('#pop_tel').text(res.tel);
					
					$('#pop_actname').text(res.actname);
					$('#pop_acttype').text(res.vacttype);
					$('#pop_actstart').text(res.actstart);
					$('#pop_actend').text(res.actend);
					$('#pop_tkstart').text(res.tkstart);
					$('#pop_tkend').text(res.tkend);
					$('#pop_tkprice').text(`${res.price}元`);
					
					$('#pop_actcnt').text(res.actcnt);
					$('#pop_img').attr('src', `${vact_img_url}`+`${select_vactid}`);
					$('#pop_progress').val(res.progress);
					console.log($('#pop_progress').val());
					//跳出視窗
					$('.pop_bg, .pop_item').show();
					
					//顯示審核結果
					if(res.progress <= 1){
						return;
					} else {
						$('#result_maker').hide();
						if(res.progress == 100) {
							$('#result_zone').text(`不通過, 原因: ${res.note}`);
						} else if (res.progress == 99) {
							$('#result_zone').text('訂單已取消');
						} else if (res.progress == 2 || res.progress == 3) {
							$('#result_zone').text('通過');
						}
					}
					
				}
			})
		})
		
		//change事件: 審核切換通過/不通過時, 出現的textarea (不通過要填寫原因)
		$('input[type=radio][name="result"]').on('change', function(){
			
			 switch($(this).val()) {
			    case 'pass':
			    	$('#result_message').hide();
			    	$('#alert_message').hide();
			      break
			    case 'failed':
			    	$('#result_message').val("").show();
			      break
			  }      		
		});
		
		//click事件: 訂單審核動作
		$('#result_submit').on('click', function(){
			var selected_choice= $('input[name=result]:checked').val();
			var select_vactid = $('#pop_vactid').text();
			var progress_item = $(`ul[data-vactid=${select_vactid}]`).children('.progress');
			var progress;
			var vendorid = $('#pop_vendorid').text();
			var result_message = $('#result_message').val().trim();
			
			// 根據審核結果給progress值
			if(selected_choice == null){
				console.log('null');
				return;
			} else if(selected_choice == "failed" && result_message == ""){
				$('#alert_message').show();
				return;
			} else {
				if ($('#pop_progress').val() <= 1) {
					if(selected_choice == 'pass'){
						progress = 2;
					} else {
						progress = 100;
					}
				}
			}
			
			// 用審核結果更新進度
			$.ajax({
				url: vact_progress_url,
				type: "POST",
				data: {"vactid" : select_vactid, "progress": progress},
				dataType: "json",
				success: function(res){
					console.log(res);
					var message; //傳email的訊息內容
					$('#result_maker').hide();
					if(progress == 2){
						message = "通過";
						$('#result_zone').text('通過');
					} else if (progress == 100) {
						message = $('#result_message').val();
						$('#result_zone').text(`不通過: ${result_message}`);
					}	
					
					
					$.ajax({
						url: email_url,
						type: "POST",
						data: {"vendorid": vendorid, "vactid": select_vactid, "result": message},
						dataType: "json",
						success: function(res){
							
						}
						
					})
					
					
					$.ajax({ //更新畫面上的進度
						url: check_progress_url,
						type: "POST",
						data: {"vactid" : select_vactid},
						dataType: "json",
						success: function(res2){
							progress_item.text(res2.progressStr);
							progress_item.attr('data-progress', res2.progress);				
						},
						error: function(res3){
							console.log("error");
						}
					})
				},
				error: function(res){
					console.log("error");
				}
			})
		})
			
		$('#close_btn').on('click', function(){
			$('.pop_bg, .pop_item').hide();
			var select_vactid = $('#pop_vactid').val();
			console.log("select_close"+select_vactid);
			$('input[name="result"]')[0].checked = true; 
			$('#result_message').val("").hide();

		})
		
		$('.change h3').on('click', function(){
			var that = $(this);
			var select_vactid = $(this).parent('li').parent('ul').attr('data-vactid');
			var progress_item = $(this).parent('li').prev();
			console.log(progress_item);
			
			if($(this).hasClass('confirm_progress')){ //確認變更
				
				var select_progress = that.prev().val();
				if(select_progress == -1){
					return;
				}
				
				$.ajax({
					url: vact_progress_url,
					type: "POST",
					data: {"vactid" : select_vactid, "progress": select_progress},
					dataType: "json",
					success: function(res){
						console.log(res);
						that.text('變更').removeClass('confirm_progress');
						that.prev('select').addClass('-none');
						
						$.ajax({ //更新畫面上的進度
							url: check_progress_url,
							type: "POST",
							data: {"vactid" : select_vactid},
							dataType: "json",
							success: function(res){
								console.log(res);
								progress_item.text(res.progressStr);
								progress_item.attr('data-progress', res.progress);
							},
							error: function(res){
								console.log("error");
							}
						})
					},
					error: function(res){
						console.log("error");
					}
				})
				
				
			
			} else { //開始變更 (出現變更選項)
				$(this).prev('select').removeClass('-none');
				$(this).text('確認').addClass('confirm_progress');
			}				
		})
		
		filterUnable();
		
		// function: 單一查詢時, 無法用篩選功能
		  function filterUnable(){
			  var li_item = $('.orderitem');
			  if(li_item.length <= 1){
				  $('.siteselect, .statusselect').addClass('unableselect');
			  }
			  
		  }
		  
	
	
})