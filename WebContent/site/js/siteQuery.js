$(function(){

//======================= Step1 選取場地 ==========================//
	
	$('#site_select_rwd, #site_select').on('change', function(){
		
		// 清除試算表
		clear_btn.click();
		
	      // 帶入場地資訊
		var siteid = $(this).val();
		$('#site_select_rwd').val(siteid);
		$('#site_select').val(siteid);
		$('.site_img').attr("src", `SiteImg?ID=${siteid}`);		
		$('#siteid_input').val(siteid);
		
		$.ajax({
			url: showDes_url,
			type: "POST",
			data: {"siteid": siteid},
			dataType: "json",
			success: function(res){
				console.log(res);
				$('.site_name').text(res.name);
				$('.site_area').text(res.area);
				$('.site_capacity').text(res.capacity);
				$('.site_daycost').text((res.daycost).toLocaleString());
				$('#rnt_daycost').text(res.daycost);  // 用來直接計算總金額
				
				//帶入form表單
				$('#site_input').val(res.name);
				$('#daycost_input').val(res.daycost);
				
				// 建議活動
				$.ajax({
					url: showCate_url,
					type: "POST",
					data: {"siteid": siteid},
					dataType: "json",
					success: function(res){
						$('.acttype').text(res);
						setCalender(my_month, my_year);

					}
				})
			}		
		})
	})
	
	
	// click事件: site_btn (移動到step2)
	$('#site_btn').on('click', function(){
	  window.scrollTo(0, 950);
	  prevNotsee();
	  clear_btn.click();
	})
	
	 $('#site_btn_rwd').on('click', function(){
      window.scrollTo(0, 737);
	  prevNotsee();
	  clear_btn.click();

    })
	
//======================= Step2 選取日期 ==========================//
  // 月份
  var leapDayInMonth = [31,29,31,30,31,30,31,31,30,31,30,31];
  var dayInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];
  
  // 當前日期
  var my_date = new Date();
  console.log(my_date);
  var my_year = my_date.getFullYear();
  var my_month = my_date.getMonth()+1;
  var my_day = my_date.getDate();

  // 抓取月曆元素
  var cal_element = document.getElementsByClassName("day-list")[0];
  var prev = document.getElementById("prev");
  var next = document.getElementById("next");
  var cmonth = document.getElementById("month_cal");
  var cyear = document.getElementById("year_cal");
  var month_select = document.getElementById("month_select");
  var year_select = document.getElementById('year_select');
  var change_btn = document.getElementById('change_btn');
  var select_start = document.getElementById('select_start');
  var select_end = document.getElementById('select_end');
  var clear_btn = document.getElementById('clear_btn');
  var day_btn = document.getElementById('day_btn');
    
  //======================================== 函數宣告 ===========================================//

  // function: 獲取某年某月的第一天是禮拜幾
  function dayStart(month, year){
    var firstDate = new Date(year, month-1, 1);
    // console.log(firstDate.getDay());
    return (firstDate.getDay());
  }

    // function: 獲取某月的總天數
  function daysMonth(month, year){

    //判斷為閏年
    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
      // console.log(leapDayInMonth[month-1]);
      return leapDayInMonth[month-1];

      //判斷為平年
    } else {
      // console.log(dayInMonth[month-1]);
      return dayInMonth[month-1];
    }
  }

  // function: 獲取某月的最後一天是禮拜幾
  function dayEnd(month, year){
    var totalDays = daysMonth(month, year);
    var lastDate = new Date(year, month-1, totalDays);
    // console.log(lastDate.getDay());
    return (lastDate.getDay());
  }

  // function: 日期動態放入月曆
  function setCalender(month, year){
    // 當前年月
    var startDay = dayStart(month, year);
    var endDay = dayEnd(month, year);
    var totalDay = daysMonth(month, year);
    // 前一個月 尾數天
    var totalDay_prev;

    if(parseInt(month) == 1){
      totalDay_prev = daysMonth(12, year-1);
    } else {
      totalDay_prev = daysMonth(month-1, year);
    }
      totalDay_prev = totalDay_prev - startDay + 1;

    // 下一個月 頭幾天
    var count = 1;
    //準備li字串
    var dayStr = "";
    var siteid = $('#siteid_input').val();
    
    //資料庫抓已預定日期
    $.ajax({
    	url: showOccupied_url,
    	type: "POST",
    	data: {"siteid": siteid, "year": year, "month": month},
    	dataType: "json",
    	success: function(res){
    		
    		 // startDay前加入節點
    	    for(let i = 0; i < startDay; i++){
    	      dayStr += `<li class="unable">${totalDay_prev++}</li>`;
    	    }
    		
    		let occupied = false;
    		
    		// 迴圈放入日期 (已預定: class="occupied")
    	    for(let i = 1; i <= totalDay; i++){
    	    	occupied = false;
    	    	let prev_day = false;
	    		if(year == my_year && month == my_month && i < my_day){
	    			dayStr += `<li data-day="${i}" class="unable cal_el">${i}</li>`;
	    			prev_day = true;
	    		}
	    		
    	    	for(let j = 0; j < res.length; j++){

    	    		 if (i == res[j] && prev_day == false){
    	    	    	dayStr += `<li data-day="${i}" class="occupied cal_el">${i}</li>`;
    	    	    	occupied = true;
    	    		 }
    	    	}
    	    	if(!occupied && prev_day == false){
        	    	dayStr += `<li class="cal_el" data-day="${i}">${i}</li>`;
    	    	}	
    	    }
    	    
    	    // endDay後也加入節點
    	    for(let i = endDay; i < 6; i++){
    	      dayStr += `<li class="unable">${count++}</li>`;
    	    }

    	    cal_element.innerHTML = dayStr;
    	    
    	    //填入標題年月
    	    cmonth.innerHTML = month;
    	    cyear.innerHTML = year;
    	    
    	    checkAvailablePeriod();
    	}
    })
    

    }

    // function: 把已選start日期放回月曆
    function setCalender_start(year, month, day){
        // 當前年月
      var startDay = dayStart(month, year);
      var endDay = dayEnd(month, year);
      var totalDay = daysMonth(month, year);
      // 前一個月 尾數天
      var totalDay_prev;

      if(parseInt(month) == 1){
        totalDay_prev = daysMonth(12, year-1);
      } else {
        totalDay_prev = daysMonth(month-1, year);
      }
        totalDay_prev = totalDay_prev - startDay + 1;

      // 下一個月 頭幾天
      var count = 1;
      //準備li字串
      var dayStr = "";
      var siteid = $('#siteid_input').val();
      
      $.ajax({
    	url: showOccupied_url,
    	type: "POST",
    	data: {"siteid": siteid, "year": year, "month": month},
    	dataType: "json",
    	success: function(res){
    		
    		// startDay前加入節點
    	      for(let i = 0; i < startDay; i++){
    	        dayStr += `<li class="unable">${totalDay_prev++}</li>`;
    	      }

    	      var left_day = totalDay - day;
    	      var count_day = 1;

    	      // 被選擇start day前addClass('unable')
    	      for(let i = 1; i < day; i++){
    	        dayStr += `<li data-day="${i}" class="unable">${i}</li>`;
    	        count_day++;
    	      }

    	      // 被選擇start day: addClass('checked')
    	      dayStr += `<li data-day="${parseInt(day)}" class="checked">${parseInt(day)}</li>`;
    	      count_day++;
    	      
    	      
    	      // 迴圈放入剩餘日期
    	      var occupied = false;
    	      
    	      for(let i = count_day; i <= totalDay; i++){
    	    	  occupied = false;
    	    	  for(let j = 0; j < res.length; j++){
    	    		  if(i == res[j]){
    	      	        dayStr += `<li data-day="${i}" class="occupied">${i}</li>`;
    	      	        occupied = true;
    	    		  }
    	    	  }
    	    	  if(!occupied){
    	    	        dayStr += `<li data-day="${i}">${i}</li>`;
    	    	  }
    	      }

    	      // endDay後也加入節點
    	      for(let i = endDay; i < 6; i++){
    	        dayStr += `<li class="unable">${count++}</li>`;
    	      }

    	      cal_element.innerHTML = dayStr;
    	      
    	      //填入標題年月
    	      cmonth.innerHTML = month;
    	      cyear.innerHTML = year;
    	      checkAvailablePeriod();
    	}
      })
    }

    // function: 根據當前日期改變select option
    function changeOption(month, year){
      var this_year = my_year;
      var this_month = my_month;
      var mon_str = "";
      var year_str = "";
      //年份
      for(let i = 0; i < 5 ; i++){
        year_str += `<option value="${this_year}">${this_year++}年</option>`;
      }
      year_select.innerHTML = year_str;
      $('#year_select_rwd').html(year_str);

      //月份
      if(year_select.value == this_year){
        for(let i = my_month; i <= 12 ; i++){
          mon_str += `<option value="${this_month}">${this_month++}月</option>`;
        }
      } else {
          for(let i = 1; this_month <= 12 ; this_month++){
           mon_str += `<option value="${this_month}">${this_month}月</option>`;
        }
      }
      month_select.innerHTML = mon_str;
      $('#month_select_rwd').html(mon_str);

    }

    //function: 切換上一個月
    function prevAction(){
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);
      if(cmonth_text == 1){
        cyear_text -= 1;
        cmonth_text = 12;
        setCalender(cmonth_text, cyear_text);        
      } else {
        setCalender(cmonth_text-1, cyear_text);
      }
    }

    //function: 切換下一個月
    function nextAction(){
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);
      
      if($('.next').hasClass('next_notsee')){
    	  return;
      }
      
      if(cmonth_text == 12){
        cyear_text += 1;
        cmonth_text = 1;
        setCalender(cmonth_text, cyear_text);
      } else {
        setCalender(cmonth_text+1, cyear_text);
      }
    }

    // function: 讓prev消失
    function prevNotsee(){
      $('.prev').addClass('prev_notsee');
    }

    // function: 讓prev出現
    function prevSee(){
      $('.prev').removeClass('prev_notsee');
    }

    // function: 讓next消失
    function nextNotsee(){
      $('.next').addClass('next_notsee');
    }

    // function: 讓next出現
    function nextSee(){
      $('.next').removeClass('next_notsee');
    }

    // function: 分割日期 (回傳陣列[yyyy, mm, dd])
    function splitDate(date){
      var date_arr = date.split("-");
      return date_arr;
    }
    // function: 計算天數 (含換算格式)
    function dateDiff(Date1, Date2){
      var day_arr1 = splitDate(Date1);
      var day_arr2 = splitDate(Date2);
      var dformat1 = new Date(day_arr1[1]+'/'+day_arr1[2]+'/'+day_arr1[0]);
      var dformat2 = new Date(day_arr2[1]+'/'+day_arr2[2]+'/'+day_arr2[0]);
      var cal_days = (Math.abs(dformat2 - dformat1) / 1000 / 60 / 60 / 24);
      return cal_days;
    };
   
    // function: 日期格式(補零)
    function formatTime(time){
      return time < 10 ? `0${time}` : time;
    }

    // function: 轉為YYYY-MM-DD日期格式 (網頁上放的日期)
    function setDate(year, month, day){
      return `${year}-${formatTime(month)}-${formatTime(day)}`;
    }

    // function: RWD版 抓select option的日期去變更月曆
    function changeCalenderRWD(){
      let year_input = $('#year_select_rwd').val();
      let month_input = $('#month_select_rwd').val();
      
      if($('#select_start').hasClass('selected')){
        clear_btn.click();
        setCalender(month_input, year_input);
      } else {
        if(year_input == my_year && month_input == my_month){
          prevNotsee();
          setCalender(month_input, year_input);
        } else {
          prevSee();
          setCalender(month_input, year_input);
        }
      }
    }
    
    //function: 檢查可選擇區間
    function checkAvailablePeriod(){  	
    	let start_arr = splitDate($('#select_start').text());
    	let start_year = parseInt(start_arr[0]);
    	let start_month = parseInt(start_arr[1]);
    	let start_day = parseInt(start_arr[2]);
    	let year_cal = $('#year_cal').text();
    	let month_cal = $('#month_cal').text();
    	let occupied_period = false;
    	nextSee();
    	
    	console.log(start_arr);
    	console.log(month_cal);
    	console.log(year_cal);
    	
    	//start day在本年月
    	if(start_year == year_cal && start_month == month_cal){
        	let start_li = $(`li[data-day='${start_day}']`);
            $.each(start_li.nextAll(), function(index, item){
            	  if($(item).hasClass('occupied')){
            		  occupied_period = true;
            	  } else {
            		  if(occupied_period){
                		  $(item).addClass('unable');
                	  }     
            	  }
              })
        //月份 > start day
    	} else if (start_year == year_cal && start_month < month_cal){
        	let start_li = $('li[data-day="1"]'); 
    		console.log(start_li.nextAll());

    		$.each(start_li.nextAll(), function(index, item){
          	  if($(item).hasClass('occupied')){
        		  occupied_period = true;
        		  
        	  } else {
        		  if(occupied_period && item.hasAttribute('data-day')){
        			  $(item).addClass('unable');
            	  }     
        	  }
    		})
    	} else if (year_cal > start_year){
    		let start_li = $('li[data-day="1"]');
    		console.log(start_li.nextAll());
    		$.each(start_li.nextAll(), function(index, item){
            if($(item).hasClass('occupied')){
            	occupied_period = true;
          		  
          	} else {
          		if(occupied_period && item.hasAttribute('data-day')){
          			$(item).addClass('unable');
              	}     
          	 }
      	})
    }
    	
    if(occupied_period){
    	nextNotsee();
    }
    	
    	
    }
    

 //======================================== 事件綁定 =========================================//

    // change事件: 當年份改變, 月份跟著改變 + 變更月曆
    year_select.addEventListener('change', function(){
      var mon_str = "";
      var this_year = my_year;
      var this_month = my_month;
      if(year_select.value == this_year){
        for(let i = this_month; this_month <= 12 ; this_month++){
          mon_str += `<option value="${this_month}">${this_month}月</option>`;
        }
      } else {
          for(let i = 1; i <= 12 ; i++){
           mon_str += `<option value="${i}">${i}月</option>`;
        }
      }
      month_select.innerHTML = mon_str;
      change_btn.click();
    })
    // change事件: 變更月曆
    month_select.addEventListener('change', function(){
      change_btn.click();
    })

    /* RWD版本 變更月曆 */
    $('#year_select_rwd').on('change', function(){
      var mon_str = "";
      var that = $(this);
      var this_year = my_year;
      var this_month = my_month;
      if(that.val() == this_year){
        for(let i = this_month; this_month <= 12 ; this_month++){
          mon_str += `<option value="${this_month}">${this_month}月</option>`;
        }
      } else {
          for(let i = 1; i <= 12 ; i++){
           mon_str += `<option value="${i}">${i}月</option>`;
        }
      }
      $('#month_select_rwd').html(mon_str);
      changeCalenderRWD();
    })

    $('#month_select_rwd').on('change', function(){
      change_btn.click();
      changeCalenderRWD();
    })

    
    // click事件: select_div的選擇年月btn
    change_btn.addEventListener('click', function(){
      let year_input = year_select.value;
      let month_input = month_select.value;
      
      if($('#select_start').hasClass('selected')){
        clear_btn.click();
        setCalender(month_input, year_input);
      } else {
        if(year_input == my_year && month_input == my_month){
          prevNotsee();
          setCalender(month_input, year_input);
        } else {
          prevSee();
          setCalender(month_input, year_input);
        }
      }
    })
    
    // click事件: 上一個月prev按鈕
    prev.addEventListener('click', function(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);
      
      // 保留選擇的start date check圖案
      if($('#select_start').hasClass('selected')){
        var start_arr = splitDate($('#select_start').text());
        if(cyear_num == start_arr[0] && cmonth_num -1 == start_arr[1]){ // 上一個月是start date
          setCalender_start(start_arr[0], start_arr[1], start_arr[2]);
          prevNotsee();

        } else if (cyear_num -1 == start_arr[0] && cmonth_num == 1 && start_arr[1] == 12){  //上一個月是start date(跨年)
          setCalender_start(start_arr[0], start_arr[1], start_arr[2]);
          prevNotsee();

        } else if(cyear_num == start_arr[0] && cmonth_num == start_arr[1]){
          return;
        } else {
          prevAction();
        }
      } else {
              // 無法看到當月之前的月曆 (prev btn消失)
          if(cyear_num == my_year && cmonth_num -1 == my_month){  //上一個月是當月
            prevNotsee();
            prevAction();
      
          } else if(cyear_num -1 == my_year && cyear_num == 1 && my_month == 12){ //上一個月是當月 (跨年)
            prevNotsee();
            prevAction();
          
          } else if (cyear_num == my_year && cmonth_num == my_month){ //本月
            return;
          } else {
            prevAction();
          }
      }
    })
    
    // click事件: 下一個月next按鈕
    next.addEventListener('click', function(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);
      nextAction();
      
      if(cyear_num == my_year && cmonth_num == my_month){
        prevSee();
      } 
       // 保留選擇的start date check圖案
       if($('#select_start').hasClass('selected')){
        var start_arr = splitDate($('#select_start').text());
        if(cyear_num == start_arr[0] && cmonth_num == start_arr[1]){
          prevSee();
        }
      }
     })

    
    // click事件: 選取日期
    $('.day-list').on('click', 'li', function(){
      var that = $(this);
      var that_day = that.attr('data-day');
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);

      $('#alert_normal').hide();
      $('#alert_rwd').hide();

      //當前頁面已有checked
      if(that.prevAll().hasClass('checked')){
        if(that.hasClass('unable') || that.hasClass('occupied')){
          return;
        } else {
          that.addClass('checked');
          $('.calender').addClass('finish');        
          select_end.innerText = setDate(cyear_text, cmonth_text, that_day);
          $('#select_end').addClass('selected');
          clear_btn.style.display = "block";
          $('#clear_btn_main').show();
          prevNotsee();
          nextNotsee();
        }
      } else if(that.hasClass('checked')){  //再次點到start (取消選擇)
    	  
    	  $('#select_start').removeClass('selected');
          select_start.innerText = "--";
    	  that.removeClass('checked');
    	  
    	  $.each($('li'), function(index, item){
    		 if(item.hasAttribute('data-day')){
    			 if($('#year_cal').text() == my_year && $('#month_cal').text() == my_month){
    				 if($(item).text() >= my_day){
        				 $(item).removeClass('unable');
        			 }
    			 }
    			 else {
    				 $(item).removeClass('unable');
    			 }
    		  }
    	  })
    	  
    	  if($('#year_cal').text() == my_year && $('#month_cal').text() == my_month){
    		  prevNotsee();
    		  nextSee();
    	  } else {
    		  prevSee();
    		  nextSee();
    	  }
      
    	 
        //當前頁面沒checked
      } else {    
          if(that.hasClass('unable')|| that.hasClass('occupied')){
            return;
          } else {
            that.addClass('checked');
            //判斷是否已選start day (跨月份選擇)
            if($('#select_start').hasClass('selected')){
              select_end.innerText = setDate(cyear_text, cmonth_text, that_day);
              prevNotsee();
              nextNotsee();
              $('.calender').addClass('finish');
              $('#select_end').addClass('selected');
              clear_btn.style.display = "block";
              $('#clear_btn_main').show();
            } else { //沒選start day
              that.prevAll().addClass('unable');
              select_start.innerText = setDate(cyear_text, cmonth_text, that_day);
              $('#select_start').addClass('selected');
              
              checkAvailablePeriod();
              
              
            }
        }
        prevNotsee();
      }
    })

    // click事件: 清除btn
    clear_btn.addEventListener('click', function(){
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);

        // 清除已選日期資訊
      select_start.classList.remove('selected');
      select_end.classList.remove('selected');
      select_start.innerText = `--`;
      select_end.innerText = `--`;
   
        //重置calender
      $('.calender').removeClass('finish');
      setCalender(cmonth_text, cyear_text);
      if(cmonth_text == my_month && cyear_text == my_year){
    	  prevNotsee();
      } else{
    	  prevSee();
      }
      nextSee();

       // 清除試算表
      $('.cal_row').addClass('-none');

       // 隱藏clear_btn
      $('#clear_btn_main').hide();
      clear_btn.style.display = "none";

    })

    $('#clear_btn_main').on('click', function(){
      clear_btn.click();
    })

  // click事件: 帶入試算表計算金額
  day_btn.addEventListener('click', function(){
    
    var start_day = document.getElementById('select_start').innerText;
    var end_day = document.getElementById('select_end').innerText;
    $('#alert_normal').hide();

    if(start_day != '--' && end_day != '--'){
      // 帶入日期
      $('.cal_row').removeClass('-none');
      $('#rnt_start').text(start_day);
      $('#rnt_end').text(end_day);
      $('#rntstart_input').val(start_day);
      $('#rntend_input').val(end_day);

      // 計算天數
      var count_day = dateDiff(start_day, end_day);
      $('#rnt_day').text(count_day);
      $('#daytotal_input').val(count_day);

      // 計算費用
      var daycost = $('#rnt_daycost').text();
      $('#rnt_total').text((daycost*count_day).toLocaleString());
      $('#total_input').val(daycost*count_day);

      // 自動到跳到試算表
      window.scrollTo(0, 1380);
    } else{
      $('#alert_normal').html(`<h6>請選擇租借日期</h6>`).fadeIn();
    }

    $('#apply_btn').on('click', function(){
      window.location.href="siteApplyBasic.html";
    })

  });

  day_btn_rwd.addEventListener('click', function(){
    
    var start_day = document.getElementById('select_start').innerText;
    var end_day = document.getElementById('select_end').innerText;
    $('#alert_rwd').hide();

    if(start_day != '--' && end_day != '--'){
      // 帶入日期
      $('.cal_row').removeClass('-none');
      $('#rnt_start').text(start_day);
      $('#rnt_end').text(end_day);
      $('#rntstart_input').val(start_day);
      $('#rntend_input').val(end_day);

      // 計算天數
      var count_day = dateDiff(start_day, end_day);
      $('#rnt_day').text(count_day);
      $('#daytotal_input').val(count_day);

      // 計算費用
      var daycost = $('#rnt_daycost').text();
      $('#rnt_total').text((daycost*count_day).toLocaleString());
      $('#total_input').val(daycost*count_day);


      // 自動到跳到試算表
      window.scrollTo(0, 1250);
    } else{
      $('#alert_rwd').html(`<h6>請選擇租借日期</h6>`).fadeIn();
    }
   


  });





 //======================================== 初始觸發 =========================================//

  // 預設為當日/當前月曆/option
  setCalender(my_month, my_year);
  prevNotsee();
  changeOption(my_month, my_year); 
  $('#rnt_total').text(parseInt($('#rnt_daycost').text()).toLocaleString());
  $('.cal_row').addClass('-none');
});
    