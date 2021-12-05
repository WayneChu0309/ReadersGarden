$(function(){

  // 月份
  var leapDayInMonth = [31,29,31,30,31,30,31,31,30,31,30,31];
  var dayInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];
  
  // 當前日期
  var my_date = new Date();
  // console.log(my_date);
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

    // startDay前加入節點
    for(let i = 0; i < startDay; i++){
      dayStr += `<li class="unable">${totalDay_prev++}</li>`;
    }

    // 迴圈放入日期
    for(let i = 1; i <= totalDay; i++){
      dayStr += `<li data-day="${i}">${i}</li>`;
    }

    // endDay後也加入節點
    for(let i = endDay; i < 6; i++){
      dayStr += `<li class="unable">${count++}</li>`;
    }

    cal_element.innerHTML = dayStr;
    
    //填入標題年月
    cmonth.innerHTML = month;
    cyear.innerHTML = year;

    }

    // function: 根據當前日期改變select option
    function changeOption(month, year){
      var this_year = my_year;
      var this_month = my_month;
      var mon_str = "";
      var year_str = "";
      //年份
      for(let i = 0; i < 3 ; i++){
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
      var cal_days = (Math.abs(dformat2 - dformat1) / 1000 / 60 / 60 / 24) + 1;
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
        
 //======================================== 事件綁定 =========================================//

    // click事件: site_btn (移動到step2)
    $('#site_btn').on('click', function(){
      window.scrollTo(0, 985);
    })
    
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
      prevSeeable();

      // 抓更新的月曆元素
      let cyear_num_prev = parseInt(cyear.innerText);
      let cmonth_num_prev = parseInt(cmonth.innerText);
      let start_arr = splitDate($('#select_start').text()); 
      let end_arr = splitDate($('#select_end').text());

      // 保留選擇的check圖案
      if($('#select_start').hasClass('selected')){
        if(cyear_num_prev == start_arr[0] && cmonth_num_prev == start_arr[1]){
          remainSelected(parseInt(start_arr[2]), null);
        }
      }

      if($('#select_end').hasClass('selected')){
         if(cyear_num_prev == end_arr[0] && cmonth_num_prev == end_arr[1]){
          remainSelected(null, parseInt(end_arr[2]));
        }
      }

      showSelectedPeriod($('#select_start'), $('#select_end'))
      
    })
    
    // click事件: 下一個月next按鈕
    next.addEventListener('click', function(){
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);

      nextAction();
      prevSeeable();
      
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
      showSelectedPeriod($('#select_start'), $('#select_end'));
     })

    // click事件: select_div的選擇年月btn
    change_btn.addEventListener('click', function(){
      let year_input = year_select.value;
      let month_input = month_select.value;
      let cyear_num = parseInt(cyear.innerText);
      let cmonth_num = parseInt(cmonth.innerText);
     
      if($('#select_start').hasClass('selected')){
        clear_btn_main.click();
        setCalender(month_input, year_input);
      } else {
          setCalender(month_input, year_input);
      }

      prevSeeable();

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

      $('.alert_div').hide();
      
      
      if (that.hasClass('unable')){
        return;

      } else if ($('#select_start').hasClass('selected') && $('#select_end').hasClass('selected')){ 

        if (that_date - start_date == 0){ //日期都有選 且 選到start date
          $('#select_start').text(setDate(end_y, end_m, end_d));
          showSelectedPeriod($('#select_start'), $('#select_end'));
          console.log("日期都有選 且 選到start date");

          if (start_date - end_date == 0){ //start & end 同一天 再次點擊取消
            $('#select_start').text("--");
            $('#select_end').text("--");
            $('#select_start').removeClass('selected');
            $('#select_end').removeClass('selected');
        
            console.log("start & end 同一天 再次點擊取消");
  
          }
          
        } else if (that_date - end_date == 0){ //日期都有選 且 選到end date
          
          $('#select_end').text(setDate(start_y, start_m, start_d));
          showSelectedPeriod($('#select_start'), $('#select_end'));
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
        if(that.hasClass('checked')){ //只有選start date 並 再次點擊取消
          $('#select_start').text("--").removeClass('selected');
          console.log("只有選start date 並 再次點擊取消");    

        } else if (that_date < start_date){ // 只有選start date 並 選到start date之前 (原start變end)
          $('#select_end').text(setDate(start_y, start_m, start_d)).addClass('selected');
          $('#select_start').text(setDate(cyear_text, cmonth_text, that_day));
          showSelectedPeriod($('#select_start'), $('#select_end'));
          console.log("只有選start date 並 選到start date之前 (原start變end)");     

        } 
        // else { //選end date
        //     select_end.innerText = setDate(cyear_text, cmonth_text, that_day);
        //     $('#select_end').addClass('selected');
        //     showSelectedPeriod($('#select_start'), $('#select_end'));

        //     console.log("選end date");
        // }
      } else {
        select_start.innerText = setDate(cyear_text, cmonth_text, that_day);
        select_end.innerText = setDate(cyear_text, cmonth_text, that_day);
        $('#select_start').addClass('selected');
        $('#select_end').addClass('selected');
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

  // click事件: 帶入試算表計算金額
  day_btn.addEventListener('click', function(){
    
    var start_day = document.getElementById('select_start').innerText;
    var end_day = document.getElementById('select_end').innerText;
    $('.alert_div').hide();

    if(start_day != '--' && end_day != '--'){

      $('.cal_row').hide();

      // 帶入日期
      $('.cal_row').fadeIn(1800);
      $('#rnt_start').text(start_day);
      $('#rnt_end').text(end_day);

      // 計算天數
      var count_day = dateDiff(start_day, end_day);
      $('#rnt_day').text(count_day);

      // 計算費用
      var daycost = $('#rnt_daycost').text();
      $('#rnt_total').text((daycost*count_day).toLocaleString());

      // 自動到跳到試算表
      window.scrollTo(0, 1420);

    } else{
      $('.alert_div').html(`<h6>請選擇租借日期</h6>`);
      $('.alert_div').fadeIn();
    }

  });

 //======================================== 初始觸發 =========================================//

  // 預設為當日/當前月曆/option
  setCalender(my_month, my_year);
  prevNotsee();
  changeOption(my_month, my_year); 
  $('#rnt_start').text(setDate(my_year, my_month, my_day));
  $('#rnt_end').text(setDate(my_year, my_month, my_day));
  $('#rnt_total').text(parseInt($('#rnt_daycost').text()).toLocaleString());
});
    