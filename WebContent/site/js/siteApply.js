$(function(){

  // 月份
  var leapDayInMonth = [31,29,31,30,31,30,31,31,30,31,30,31];
  var dayInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];
  var week_arr = ['( 星期日 )', '( 星期一 )', '( 星期二 )', '( 星期三 )', '( 星期四 )', '( 星期五 )', '( 星期六 )'];
  
  // 宣告變數
  var my_date = new Date();
  var my_year = my_date.getFullYear();
  var my_month = my_date.getMonth()+1;
  var my_day = my_date.getDate();
  var remain_start;
  var remain_end;

  // 抓取月曆元素
  var cal_element = document.getElementsByClassName("day-list")[0];
  var prev = document.getElementById("prev");
  var next = document.getElementById("next");
  var cmonth = document.getElementById("month_cal");
  var cyear = document.getElementById("year_cal");
  var month_select = document.getElementById("month_select");
  var year_select = document.getElementById('year_select');
  var search_btn = document.getElementById('search_btn');
  var select_start = document.getElementById('select_start');
  var select_end = document.getElementById('select_end');
  var clear_btn = document.getElementById('clear_btn');
  var confirm_btn = document.getElementById('confirm_btn');
    
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
    	      dayStr += `<li class="unable cal_el">${totalDay_prev++}</li>`;
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
    	      dayStr += `<li class="unable cal_el">${count++}</li>`;
    	    }

    	    cal_element.innerHTML = dayStr;
    	    
    	    //填入標題年月
    	    cmonth.innerHTML = month;
    	    cyear.innerHTML = year;
    	    	
    	    // 抓取更新的月曆元素
    	      let cyear_num_next = parseInt(cyear.innerText);
    	      let cmonth_num_next = parseInt(cmonth.innerText);
    	      let start_arr = splitDate($('#select_start').text());
    	      let end_arr = splitDate($('#select_end').text());

    	      // 保留選擇的check圖案
    	      if($('#select_start').hasClass('selected')){      
    	        if(cyear_num_next == start_arr[0] && cmonth_num_next == start_arr[1]){
    	          remainSelected(parseInt(start_arr[2]), null);
    	        }
    	      }

    	      if($('#select_end').hasClass('selected')){
    	        if(cyear_num_next == end_arr[0] && cmonth_num_next == end_arr[1]){
    	          remainSelected(null, parseInt(end_arr[2]));
    	        }
    	      } 
    	      
       	      // prev & next 顯示/隱藏
    	      if($('#year_cal').text() == my_year && $('#month_cal').text() == my_month){
    	    	  prevNotsee();
    	    	  nextSee();
    	      } else {
    	    	  prevSee();
    	    	  nextSee();
    	      }

    	      	//change_time_btn用
    	      if(parseInt(start_arr[0]) == $('#year_cal').text() && parseInt(start_arr[1]) == $('#month_cal').text()){
        	      if($('#select_start').hasClass('selected')){
        	    	  let start_day = parseInt(start_arr[2]);
      				let start_li = $(`li[data-day="${start_day}"]`);
      				$.each(start_li.prevAll(), function(index, item){
      					if($(item).hasClass('occupied')){
      						prevNotsee();
      					}
      				})
        	      }
    	      }

				
    	      
    	      // 顯示可選取區間(遇到已預定日期, 後面會加unable)
    	      checkAvailablePeriod();
    	      
    	      // 顯示選取區間(灰底)
    	      showSelectedPeriod($('#select_start'), $('#select_end'));
    	      
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

      // change事件: 當年份改變, 月份跟著改變 
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
      })
    }

    // change事件: 選擇場地site_select
    $('#site_select').on('change', function(){
    	var siteid = $(this).val();
      $('#show_img').attr('src', `${img_url}`+`${siteid}`);
      $('#siteid_input').val(siteid);
      
      $.ajax({
    	  url: showDes_url,
    	  type: "POST",
    	  data: {"siteid": siteid},
    	  dataType: "json",
    	  success: function(res){
    		  $('#show_site').text(res.name);
    		  $('#sitename_input').val(res.name);
    		  $('#rnt_daycost').text(res.daycost);   
    		  $('#daycost_input').val(res.daycost);
    	  }
      })
    })

    //function: 切換上一個月
    function prevAction(){
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);
      if($('#prev').hasClass('prev_notsee')){
    	  return;
      }
      
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
      if($('#next').hasClass('next_notsee')){
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

    // function: prev按鈕消失/出現
    function prevSeeable(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);

      if(cyear_num == my_year && cmonth_num == my_month){
        $('.prev').addClass('prev_notsee');
      } else {
        $('.prev').removeClass('prev_notsee');
      }
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

    // function: 轉為MM/DD/YYYY日期格式
    function dateTransfer(Date1){
      var day_arr = splitDate(Date1);
      var dformat = new Date(day_arr[1]+'/'+day_arr[2]+'/'+day_arr[0]);
      return dformat;
    };
   
    // function: 日期格式(補零)
    function formatTime(time){
      return time < 10 ? `0${time}` : time;
    }

    // function: 轉為YYYY-MM-DD日期格式 (網頁上放的日期)
    function setDate(year, month, day){
      return `${year}-${formatTime(month)}-${formatTime(day)}`;
    }

    //function: 迴圈移除unable class
    // function removeUnable(that_day, arr){
    //   arr.each(function(index, element){
    //     if(parseInt($(element).attr('data-day')) != null && parseInt($(element).attr('data-day')) < that_day){
        //  $(element).removeClass('unable');
    //     }
    //   })
    // }
    //function: 迴圈加上unable class
    // function addUnable(that_day, arr){
    //   arr.each(function(index, element){
    //     if(parseInt($(element).attr('data-day')) != null && parseInt($(element).attr('data-day')) < that_day){
    //      $(element).addClass('unable');
    //     }
    //   })
    // }

    //function: 保留selected日期
    function remainSelected(startday, endday){
  
      if(endday){ //如果有end day -> 代表兩個都選了
        $(`li[data-day=${startday}]`).addClass('checked');
        $(`li[data-day=${endday}]`).addClass('checked');
      } else if(startday){ //如果只有start day
        $(`li[data-day=${startday}]`).addClass('checked');
      }
    }
    
    //function: 顯示selected日期的區間 (參數為$('#select_start'), $('#select_end'))
    function showSelectedPeriod(start, end){

      let all_arr = $('li');
      let start_y, start_m, start_d, start_arr;
      let end_y, end_m, end_d, end_arr;
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);

      if(start){ //有填start就取值
        start_y = parseInt((splitDate(start.text()))[0]);
        start_m = parseInt((splitDate(start.text()))[1]);
        start_d = parseInt((splitDate(start.text()))[2]);
        start_arr = $(`li[data-day=${start_d}]`).nextAll();
      }

      if(end){ //有填end就取值
        end_y = parseInt((splitDate(end.text()))[0]);
        end_m = parseInt((splitDate(end.text()))[1]);
        end_d = parseInt((splitDate(end.text()))[2]);
        end_arr = $(`li[data-day=${end_d}]`).prevAll();
      }
      
      if(start != null && end != null){
        all_arr.each(function(index, element){
          $(element).removeClass('select_period');
        })
      
          if(cyear_num == start_y && start_y == end_y && cmonth_num == start_m && start_m == end_m){ //若start end 在同年月
            end_arr.each(function(index, element){
              if($(element).attr('data-day') > start_d){
              $(element).addClass('select_period');
              }
            })

          } else if (start_y != end_y || start_m != end_m){ 
            if(!$('#select_end').hasClass('selected')){ // 只選了start date
              return;
            } else if (start_y == cyear_num && start_m == cmonth_num) { //都有選 且 start 在該年月
                all_arr.each(function(index, element){
                  if($(element).attr('data-day') > start_d){
                    $(element).addClass('select_period');
                  }
                })

            } else if (end_y == cyear_num && end_m == cmonth_num){ //都有選 且 end 在該年月
              all_arr.each(function(index, element){
                if($(element).attr('data-day') < end_d){
                  $(element).addClass('select_period');
                }
              })
            } else if (cyear_num == start_y && cmonth_num < start_m){
              return;
            } else if (cyear_num == end_y && cmonth_num > end_m){
              return;
            } else if (cyear_num < start_y || cyear_num > end_y){
              return;

            } else if ($('#select_start').hasClass('selected') && $('#select_end').hasClass('selected')){
                all_arr.each(function(index, element){
                  if($(element).hasClass('unable')){
                    return;
                  } else {
                    $(element).addClass('select_period');
                  }
            })
          }
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
    	let occupied_prev = false;
    	nextSee();
    	
    	console.log(start_arr);
    	console.log(month_cal);
    	console.log(year_cal);
    	
    	//start day在本年月
    	if($('#select_start').hasClass('selected')){
        	if(start_year == year_cal && start_month == month_cal){
            	let start_li = $(`li[data-day=${start_day}]`);
                $.each(start_li.prevAll(), function(index, item){
              	  if($(item).hasClass('occupied')){
              		  occupied_period = true;
              	  } else {
              		  if(occupied_period){
                  		  $(item).addClass('unable');
                  	  }     
              	  }
                })       	
            	occupied_period = false;
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
        		$.each(start_li.nextAll(), function(index, item){
	                if($(item).hasClass('occupied')){
	                	occupied_period = true;
	              		  
	              	} else {
	              		if(occupied_period && item.hasAttribute('data-day')){
	              			$(item).addClass('unable');
	                  	}     
	              	 }
        		})
        	//月份 < start day
        	} else if (start_year == year_cal && start_month > month_cal) {
        		console.log("< start day1");
        		console.log($('.cal_el:last-child'));
                $.each($('.cal_el:last-child').prevAll(), function(index, item){
                	  if($(item).hasClass('occupied')){
                		  occupied_prev = true;
                	  } else {
                		  if(occupied_prev){
                    		  $(item).addClass('unable');
                    	  }     
                	  }
                  })
                  
        	} else if (year_cal < start_year){
        		console.log("< start day2");

                $.each($('.calender:last-child').prevAll(), function(index, item){
              	  if($(item).hasClass('occupied')){
              		occupied_prev = true;
              	  } else {
              		  if(occupied_prev){
                  		  $(item).addClass('unable');
                  	  }     
              	  }
                })
        	}
        	
        	
        	
	        if(occupied_period){
	        	nextNotsee();
	        }
	        
	        if(occupied_prev){
	        	prevNotsee();
	        }
    	}

    	
    	
    }
    
    
        
 //======================================== 事件綁定 =========================================//
 
    // click事件: 上一個月prev按鈕
    prev.addEventListener('click', function(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);

      // 無法看到當月之前的月曆 (prev btn消失)
      if (cyear_num == my_year && cmonth_num == my_month){ //本月
        return;
      } else {
        prevAction();
      }
//      prevSeeable();

//      // 抓更新的月曆元素
//      let cyear_num_prev = parseInt(cyear.innerText);
//      let cmonth_num_prev = parseInt(cmonth.innerText);
//      let start_arr = splitDate($('#select_start').text()); 
//      let end_arr = splitDate($('#select_end').text());
//
//      // 保留選擇的check圖案
//      if($('#select_start').hasClass('selected')){
//        if(cyear_num_prev == start_arr[0] && cmonth_num_prev == start_arr[1]){
//          remainSelected(parseInt(start_arr[2]), null);
//        }
//      }
//
//      if($('#select_end').hasClass('selected')){
//         if(cyear_num_prev == end_arr[0] && cmonth_num_prev == end_arr[1]){
//          remainSelected(null, parseInt(end_arr[2]));
//        }
//      }
//
//      showSelectedPeriod($('#select_start'), $('#select_end'))
      
    })
    
    // click事件: 下一個月next按鈕
    next.addEventListener('click', function(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);

      nextAction();
//      prevSeeable();
      
//      // 抓取更新的月曆元素
//      let cyear_num_next = parseInt(cyear.innerText);
//      let cmonth_num_next = parseInt(cmonth.innerText);
//      let start_arr = splitDate($('#select_start').text());
//      let end_arr = splitDate($('#select_end').text());
//
//      // 保留選擇的check圖案
//      if($('#select_start').hasClass('selected')){      
//        if(cyear_num_next == start_arr[0] && cmonth_num_next == start_arr[1]){
//          remainSelected(parseInt(start_arr[2]), null);
//        }
//      }
//
//      if($('#select_end').hasClass('selected')){
//        if(cyear_num_next == end_arr[0] && cmonth_num_next == end_arr[1]){
//          remainSelected(null, parseInt(end_arr[2]));
//        }
//      } 
//      showSelectedPeriod($('#select_start'), $('#select_end'));
     })

    // click事件: select_div的選擇年月btn
    search_btn.addEventListener('click', function(){
      let year_input = year_select.value;
      let month_input = month_select.value;
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);
     
      setCalender(month_input, year_input);
//      prevSeeable();
      $('.cal_pop').show();
      $('.pop_bg').show();
    })
    
    // click事件: 選取日期
    $('.day-list').on('click', 'li', function(){
      var that = $(this);
      var that_day = parseInt(that.attr('data-day'));
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);
      var that_date = new Date(cmonth_text + "/" + that_day + "/" +cyear_text);
      var start_date = dateTransfer($('#select_start').text());
      var end_date = dateTransfer($('#select_end').text());
      var start_y = parseInt((splitDate($('#select_start').text()))[0]);        
      var start_m = parseInt((splitDate($('#select_start').text()))[1]);
      var start_d = parseInt((splitDate($('#select_start').text()))[2]);
      var end_y = parseInt((splitDate($('#select_end').text()))[0]);        
      var end_m = parseInt((splitDate($('#select_end').text()))[1]);
      var end_d = parseInt((splitDate($('#select_end').text()))[2]);      
      
      if (that.hasClass('unable') || that.hasClass('occupied')){
        return;

      } else if ($('#select_start').hasClass('selected') && $('#select_end').hasClass('selected')){ 
        if (that_date - start_date == 0){ //日期都有選 且 選到start date
            console.log("日期都有選 且 選到start date");

          $('#select_start').text(setDate(end_y, end_m, end_d));
          $('#select_end').text('--').removeClass('selected');
          showSelectedPeriod($('#select_start'), $('#select_end'));
          $('#confirm_btn').hide();       
          
          
        } else if (that_date - end_date == 0){ //日期都有選 且 選到end date
          $('#select_end').text("--").removeClass('selected');
          showSelectedPeriod($('#select_start'), $('#select_end'));
          $('#confirm_btn').hide();
          console.log("日期都有選 且 選到end date");

        } else if (that_date > start_date && that_date < end_date){  //日期都有選 且 選在兩者之間的日期
          $(`li[data-day=${end_d}]`).removeClass('checked');
          $('#select_end').text(setDate(cyear_text, cmonth_text, that_day));
          that.removeClass('select_period');
          showSelectedPeriod($('#select_start'), $('#select_end'));
          console.log("日期都有選 且 選在兩者之間的日期");

        } else if (that_date > end_date){  //日期都有選 且 選在end date之後
          $('#select_end').text(setDate(cyear_text, cmonth_text, that_day));
          showSelectedPeriod($('#select_start'), $('#select_end'));
          if(start_date - end_date != 0){
            $(`li[data-day=${end_d}]`).removeClass('checked');
          }
          console.log("日期都有選 且 選在end date之後");

        } else if (that_date < start_date){  //日期都有選 且 選在start date之前
          $('#select_start').text(setDate(cyear_text, cmonth_text, that_day));
          showSelectedPeriod($('#select_start'), $('#select_end'));
          if(start_date - end_date != 0){
            $(`li[data-day=${start_d}]`).removeClass('checked');
          }
          console.log("日期都有選 且 選在start date之前");
        }

    } else if ($('#select_start').hasClass('selected')){
      var start_date = dateTransfer($('#select_start').text());
      if (that_date < start_date){ // 只有選start date 並 選到start date之前
        $('#select_end').text(setDate(start_y, start_m, start_d)).addClass('selected');
        $('#select_start').text(setDate(cyear_text, cmonth_text, that_day));
        showSelectedPeriod($('#select_start'), $('#select_end'));
        $('#confirm_btn').show().css('display', 'block');
        console.log("只有選start date 並 選到start date之前");     

        } else if (that_date - start_date == 0){ //只有選start date 並再次點擊取消
        	console.log("只有選start date 並再次點擊取消");
            $('#select_start').text('--').removeClass('selected');
            nextSee();
            if(!(my_year == start_y && my_month ==start_m)){
            	prevSee();
            }
            checkAvailablePeriod();
            $.each($('li'), function(index, item){
          	  if(item.hasAttribute('data-day')){
          		  if(cmonth_text == my_month && cyear_text == my_year){
              		  if($(item).text() >= my_day){
              			  $(item).removeClass('unable');
              	  	}
          		  } else {
          			  $(item).removeClass('unable');
          		  }

          	  }
            })
            
        } else { //選end date
          select_end.innerText = setDate(cyear_text, cmonth_text, that_day);
          $('#select_end').addClass('selected');
          $('#confirm_btn').show().css('display', 'block');;
          showSelectedPeriod($('#select_start'), $('#select_end'));
          console.log("選end date");
        }
    } else {
        select_start.innerText = setDate(cyear_text, cmonth_text, that_day);
        $('#select_start').addClass('selected');
        let prevAll = $(`li[data-day="${that_day}"]`).prevAll();
        let nextAll = $(`li[data-day="${that_day}"]`).nextAll();
        let occupied = false;
        $.each(prevAll, function(index, item){
        	if($(item).hasClass('occupied')){
        		occupied = true;
        	} else {
        		if(occupied){
            		$(item).addClass('unable');
        		}
        	}
        })
        
        if(occupied){
        	prevNotsee();
        	$('.prev').addClass('prev_notsee');
        }
        
        occupied = false;
        $.each(nextAll, function(index, item){
        	if($(item).hasClass('occupied')){
        		occupied = true;
        	} else if(!$(item).hasClass('occupied')){
        		if(occupied){
            		$(item).addClass('unable');

        		}
        	}
        })      
        if(occupied){
        	nextNotsee();
        }
        
        
        console.log("第一次點擊 選start date")
    }

      that.toggleClass('checked');

      // 讓clear_btn_main顯示/消失
      if($('#select_start').hasClass('selected')){
        $('#clear_btn_main').show();
      } else {
        $('#clear_btn_main').hide();
      }

    })

    // click事件: 清除btn
    $('#clear_btn_main').on('click', function(){
      var cmonth_text = parseInt(cmonth.innerText);
      var cyear_text = parseInt(cyear.innerText);

        // 清除已選日期資訊
      select_start.classList.remove('selected');
      select_end.classList.remove('selected');
      select_start.innerText = `--`;
      select_end.innerText = `--`;
   
        //重置calender
      $('.calender_container').removeClass('finish');
      setCalender(my_month, my_year);
      prevNotsee();

       // 清除試算表
      $('.cal_row').fadeOut();

       // 隱藏clear_btn
      $('#clear_btn_main').hide();

    })

  // click事件: confirm_btn 帶入日期計算金額 
  confirm_btn.addEventListener('click', function(){
    
    var start_day = document.getElementById('select_start').innerText;
    var end_day = document.getElementById('select_end').innerText;
    var start_y = parseInt((splitDate($('#select_start').text()))[0]);        
    var start_m = parseInt((splitDate($('#select_start').text()))[1]);
    var start_d = parseInt((splitDate($('#select_start').text()))[2]);
    var end_y = parseInt((splitDate($('#select_end').text()))[0]);        
    var end_m = parseInt((splitDate($('#select_end').text()))[1]);
    var end_d = parseInt((splitDate($('#select_end').text()))[2]);

    if(start_day != '--' && end_day != '--'){

      // 彈窗月曆/遮罩消失
      $('.pop_bg').hide();
      $('.cal_pop').hide();
      $('.choose_div').hide();

      // 帶入日期
      $('#show_start').text(`${start_y} 年 ${start_m} 月 ${start_d} 日`);
      $('#rntstart_format_input').val(`${start_y} 年 ${start_m} 月 ${start_d} 日`);
      $('#show_end').text(`${end_y} 年 ${end_m} 月 ${end_d} 日`);
      $('#rntend_format_input').val(`${end_y} 年 ${end_m} 月 ${end_d} 日`);

      $('#show_wstart').text(week_arr[dateTransfer(start_day).getDay()]);
      $('#rntstart_w_input').val(week_arr[dateTransfer(start_day).getDay()]);
      $('#show_wend').text(week_arr[dateTransfer(end_day).getDay()]);
      $('#rntend_w_input').val(week_arr[dateTransfer(end_day).getDay()]);

      $('#rntstart_input').val($('#select_start').text());
      $('#rntend_input').val($('#select_end').text());

      // 計算天數
      var count_day = dateDiff(start_day, end_day);
      $('#show_day').text(`${count_day} 天`);
      $('#dayTotal_input').val(`${count_day}`);
      $('.rnt_day').text(`${count_day}`);

      // 計算費用
      var daycost = $('#rnt_daycost').text();
      $('#rnt_total').text((daycost*count_day).toLocaleString());
      $('#total_input').val(daycost*count_day);

      //site_div & cost_div 顯示 
      $('.site_div').show();
      $('.cost_div').show();

      $('.p1').addClass('check_icon').text('✔');
    }
  });

  //click事件: 變更時間 change_time_btn
  $('#change_time_btn').on('click', function(){
	$('.pop_bg').show();
	$('.cal_pop').show();
	
	//從siteQuery.jsp進,已選擇場地&時間 (第一次修改)
	if($('#rntstart_input').val() != ""){
		$('#confirm_btn').show().css('display', 'block');
		let query_start = $('#rntstart_input').val();
		let query_end = $('#rntend_input').val();
		
		$('#select_start').text(query_start).addClass('selected');
		$('#select_end').text(query_end).addClass('selected');		
		rntStart = "";
		rntEnd = "";
	}
	
		let start_arr = splitDate($('#select_start').text()); 
		let end_arr = splitDate($('#select_end').text());
		let select_start_m = parseInt((splitDate($('#select_start').text()))[1]);
		let select_start_y = parseInt((splitDate($('#select_start').text()))[0]);
		remain_start = $('#select_start').text();
		remain_end = $('#select_end').text();
		
		//切回start date 月份
		setCalender(select_start_m, select_start_y);
	
  })

  // click事件: change_btn更改選擇 (時間&場地)
  $('#change_btn').on('click', function(){
    $('.site_div').hide();
    $('.choose_div').show();
    $('#select_start').removeClass('selected').text("");
    $('#select_end').removeClass('selected').text("");
    $('.p1').text('1').removeClass('check_icon');
  })
  
  //click事件: 下一步next_btn
  $('#next_btn').on('click', function(){
	  $('#submit_btn').click();
  })

  // click事件: 彈窗關閉按鈕
  $('#close_pop').on('click', function(){
    $('.pop_bg').hide();
    $('.cal_pop').hide();
    if(remain_start != null){
      $('#select_start').text(remain_start);
      $('#select_end').text(remain_end);
    } else{
      $('#select_start').text('--').removeClass('selected');
      $('#select_end').text('--').removeClass('selected');
    }
  })
  
  // change事件: 預覽圖
  $("input[type='file']").on("change", function(e){
	  if(this.files.length > 0){
	      preview_img_action(this.files[0]);
	    }else{
	    	$('#preview').html('<span class="text">預覽圖</span>');
	    }  
  })
  
  //預覽圖片 function
  var preview_img_action = function(file){
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.addEventListener('load', function(){
      preview_file = `<img src='${reader.result}' id='preview_img'>`;
      // console.log(preview_file);
      $('.preview').html(preview_file);
    })
  }
  
 //============================== datetimepicker ================================//
  
  $.datetimepicker.setLocale('zh'); // kr ko ja en
  $(function(){
	 $('#act_start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
		minDate:$('#rntstart_input').val()?$('#rntstart_input').val():false,
	    maxDate:$('#act_end_date').val()?$('#act_end_date').val():$('#rntend_input').val()
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#act_end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
		   minDate:$('#act_start_date').val()?$('#act_start_date').val():$('#rntstart_input').val(),
		   maxDate:$('#rntend_input').val()?$('#rntend_input').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#tk_start_date').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
			minDate: my_date,
		    maxDate:$('#tk_end_date').val()?$('#tk_end_date').val():false
		   })
		  },
		  timepicker:false
		 });
		 
		 $('#tk_end_date').datetimepicker({
		  format:'Y-m-d',
		  onShow:function(){
		   this.setOptions({
			   maxDate:$('#act_end_date').val()?$('#act_end_date').val():false
		   })
		  },
		  timepicker:false
		 });
	 
  });
  
  

 //======================================== 初始觸發 =========================================//

  // 預設為當日/當前月曆/option
  $("html,body").animate({"scrollTop": "450px"}, 500);
//  setCalender(my_month, my_year);
  prevNotsee();
  changeOption(my_month, my_year); 
  $('#rnt_total').text(parseInt($('#rnt_daycost').text()*$('.rnt_day').text()).toLocaleString());
  
  
  //一般訪問
  if(($('#siteid_input').val()) == ""){
	  $('.site_div').hide();
	  $('.choose_div').show();
	  $('#siteid_input').val("1");
	  $('#site_select').change();
	  
  //從siteQuery.jsp進來
  } else { 
	  $('.site_div').show();
	  $('.choose_div').hide();
	  
	  var siteid = $('#siteid_input').val();
	  $('#show_img').attr('src', `${img_url}`+`${siteid}`);

	  $('.p1').addClass('check_icon').text('✔');
      $('#total_input').val(parseInt($('#rnt_daycost').text()*$('.rnt_day').text()));
      $('#daycost_input').val($('#rnt_daycost').text());
  }
  
});
    