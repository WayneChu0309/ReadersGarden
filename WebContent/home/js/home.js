$(".row-items").each(function () {
    var that = this;
    $(that).children("a").on("mouseenter", function(){
        $(this).css("opacity", "1");
        $(that).children("img").css("opacity", ".5")
    });
    $(that).children("a").on("mouseleave", function(){
        $(this).css("opacity", "0");
        $(that).children("img").css("opacity", "1")
    });
})

var month = ["", "一月", "二月" ,"三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];

function dateformat(date){
	var datelist = date.split(" ");
	return `${datelist[2]}-${month.indexOf(datelist[0])}-${datelist[1].slice(0, -1)}`;
}

$(function() {
	initTextarea();
    var curMonthDays = new Date(now_year, now_month, 0).getDate();
    var day = new Date(now_year, now_month-1, 1).getDay();
    var days = '';
    for(var i = 0; i <= day; i++){
        if(i < day) {
            days += `<li></li>`;
        }else{
            for (var k = 1; k < (curMonthDays+1); k++) {
                if (k == nowDay) {
                    days += `<li class="today">${k}</li>`;
                } else {
                    days += `<li>${k}</li>`;
                }
            }
            $("ul.days").append(days)
            var h4 = ''
            h4 += month_list[now_month] + "<br />'" + nowDay;
            $("h4.today").html(h4)
        }
    }
})

function initTextarea(){
	var bulletincontent = $("textarea#bulletin").val().trim()
    $("textarea#bulletin").val(bulletincontent)
    var bulletin = document.getElementById("bulletin");
	bulletin.style.height = 'auto';
	bulletin.style.height = bulletin.scrollHeight+20+'px';
}


