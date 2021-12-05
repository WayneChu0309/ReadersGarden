$(function(){


  //==========進階篩選 ==========//
  /* 展開 */
  $('#choose_btn').on('click', function(){
    $(this).addClass('-none');
    $('.side').addClass('side_large');
    $('.choose_div').removeClass('-none');
  })
  /* 收回 */
  $('#side_back').on('click', function(){
    $(this).addClass('-none');
    $('.side').removeClass('side_large');
    $('.choose_div').addClass('-none');
    $('#choose_btn').removeClass('-none');
  })

  $('#area_range').on('mousemove', function(){
    $('#area_v').html($(this).val());
  })

  $('#daycost_range').on('mousemove', function(){
    $('#daycost_v').html($(this).val());
  })

  $('#capacity_range').on('mousemove', function(){
    $('#capacity_v').html($(this).val());
  })
  
  $('#select_btn').on('click', function(){
	  var area = $('#area_v').text();
	  var daycost = $('#daycost_v').text();
	  var capacity = $('#capacity_v').text();
	  $('.s_element').fadeOut(1000, function(){
		  $(this).remove();
	  });
	   
	  $.ajax({
		  url: siteFilter_url,
		  type: "POST",
		  data: {"area": area, "daycost": daycost, "capacity": capacity},
		  dataType: "json",
		  beforeSend: function(res){
			  $('.content').html('<h4 style="font-size: 48px;margin-top:200px;">查詢中</h4>');
		  },
		  success: function(res){
			  console.log(res.length);
			  
			  if (res.length != 0) {
				  $('.content').html("");
				  $.each(res, function(index, item){
					  var price = (item.daycost).toLocaleString();
					  var site_html = 
						  `<div class="s_element" style="display:none;">
							<div class="li_img">
							<img src="SiteImg?ID=${item.siteid}" alt="">
						</div>
						<h5>${item.name}</h5>
						<ul class="site_des">
							<li>場地面積: ${item.area}坪</li>
							<li>容納人數: ${item.capacity}人</li>
							<li class="usage_li">使用建議: 
							</li>
	
							<li>單日報價: $ ${price}/天</li>
						</ul>
						<form method="post" action="${siteServlet_url}">
							<input type="hidden" name="siteid" value="${item.siteid}">
							<input type="hidden" name="action" value="getOne_For_Query"> 
							<button type="submit" name="button" class="query_btn">查詢時間</button>
						</form>
					</div>`;
					  $('.content').append(site_html);
					  $('.s_element').fadeIn();
					  
					  $.ajax({
						  url: siteShowCate_url,
						  type: "POST",
						  data: {"siteid": `${item.siteid}`},
						  dataType: "json",
						  success: function(res){
							  $('.usage_li').html(`使用建議:${res}`);		  	  
						  }
					  })
			  	})	
			  } else {
				  $('.content').html('<h4 style="font-size: 48px;margin-top:200px;">查無結果</h4>');
			  }
		  }  	  
	  })
  })
})
