var bookContent = {};
var kind;
var returnDay;
var selDay;
// slide area
$(function() {
	var lastBookId = parseInt($("ul.book-types li").last().attr("data-id"));
	var addClass = data["typeId"] <= lastBookId ? "book" : "movie";
	kind = addClass == "book" ? "書名:" : "電影名稱:";
	$("section.stock div.items h3").addClass(addClass)
	$("section.stock div.items button").addClass(addClass)
	$("div.love-filter h3").addClass(addClass)
	$("div.love-filter button").addClass(addClass)
	$("div.detail-filter button").addClass(addClass)
	$("section.stock .search input").each(function(){
		if ($(this).attr("id") == addClass){
			$(this).attr("checked", true);
		}
	})	
    
    $("section.stock .search .types li").each(function(){
    	if ($(this).attr("data-id") == data["typeId"]) {
    		$(this).addClass("check");
    	}
    })
    
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        success: function (res) {
            bookContent = {};
            if (res.length == 0) {
            	$("div.hint p").text("查無結果");
            	$("div.main-filter").css("display", "block");
                $("div.hint").css("display","block");  	
            }
        	$.each(res, function(index, item){
        		var stockId = item.stockId;
                var stockName = item.stockName;
                var author = item.author != undefined ? item.author : "未提供";
                var press = item.press != undefined ? item.press : "未提供";
                var issuedDate = item.issuedDate != undefined ? item.issuedDate : "未提供";
                var stockcontent = item.stockContent;
                var items = {"stockName":stockName, "author":author, "press":press, 
                "issuedDate":issuedDate,"stockContent":stockcontent};
                bookContent[stockId] = items;
        	})
        },
    	complete: function(){
    		$("section.loadfilter").css("display", "none");
    	}
    
    });
    
//    $("button.btn.love").on("click", function(){
//    	console.log(1)
//    	$.ajax({
//            type: "GET",
//            url: ajaxURL,
//            data: data,
//            dataType: "json",
//            success: function (res) {
//            }
//        });
//    })
	
  $("button.btn.confirm").on("click", function(){
		orderData["stockId"] = $(this).attr("data-stockId");
		orderData["preBoDate"] = $(this).siblings("select").val()
		$.ajax({
	        type: "POST",
	        url: orderURL,
	        data: orderData,
	        dataType: "json",
	        success: function (res) {
	        }
	   });
  })
       
})


// 選擇預約日期後, 歸還日期加一個月
$(".backface-love select").on("change", function(){
    if (this.value == 0) {
        $(this).siblings("input").val("歸還日期:");
        $(this).siblings("button.confirm").attr("disabled", true);
    } else {
        var selYear = this.value.split("-");
        selDay = `${selYear[0]}-${selYear[1]}-${selYear[2]}`;
        var reYear = selYear[1] == "12" ? parseInt(selYear[0])+1 : selYear[0];
        var reMonth = selYear[1] == "12" ? "01" : parseInt(selYear[1])+1;
        returnDay = `${reYear}-${reMonth}-${selYear[2]}`;
//        console.log("預約日" + selDay);
//        console.log("歸還日" + returnDay);
        $(this).siblings("input").val(`歸還日期:${reYear}年${reMonth}月${selYear[2]}日`);
        $(this).siblings("input").attr("data-preReDate", `${reYear}-${reMonth}-${selYear[2]}`)
        $(this).siblings("button.confirm").attr("disabled", false);
    }
})

// 預約日期下拉選單, 當前日期+6天
$(".backface-love select").each(function(){
//    var nowDate = new Date()
//    var nowMonth = nowDate.getMonth() + 1 // 目前月份 , 月份從0開始, 需加1
//    var nowYear = nowDate.getFullYear() // 目前年份
//    var nowDay = nowDate.getDate();    // 目前幾號
    var monthDay = new Date(nowYear,nowMonth,0).getDate(); // 當月幾天
    var overDar = monthDay - nowDay;
    var nextYear = nowMonth == 12 ? nowYear+1 : nowYear;
    var nextMonth = nowMonth == 12 ? 1 : nowMonth + 1;
    if (overDar >= 7) {
        for(var i = 0; i < 8; i++) {
            $(this).children("option").eq(i+1).val(`${nowYear}-${nowMonth}-${nowDay + i}`);
            $(this).children("option").eq(i+1).text(`${nowYear}年${nowMonth}月${nowDay+i}日`);
        };
    } else {
        var j = 1;
        for(var i = 0; i < 8; i++) { 
            if (i <= overDar) { 
                $(this).children("option").eq(i+1).val(`${nowYear}-${nowMonth}-${nowDay + i}`);
                $(this).children("option").eq(i+1).text(`${nowYear}年${nowMonth}月${nowDay+i}日`);
            } else {
                $(this).children("option").eq(i+1).val(`${nextYear}-${nextMonth}-${j}`);
                $(this).children("option").eq(i+1).text(`${nextYear}年${nextMonth}月${j}日`);
                j++;
            }
        };
    }
})


// 翻牌
$("section.stock .row .items").on("click", "button.order", function () {
    // 獲取 img src
    // console.log($(this).closest("div.front").children("a").children("img")[0].src);
    // 設置 img src $("img").attr("src", path); 
    // console.log($(this).attr("data-stock"))
    $(this).closest("div.items").children("div.front").css("transform", "rotateY(-180deg)")
    $(this).closest("div.items").children("div.backface-love").css("transform", "rotateY(0deg)")
})

$("section.stock .row .items").on("click", "button.confirm", function () {
    $(this).closest("div.items").children("div.front").css("transform", "rotateY(0deg)")
    $(this).closest("div.items").children("div.backface-love").css("transform", "rotateY(180deg)")
})

$("section.stock .row .items").on("click", "button.close", function () {
    $(this).closest("div.items").children("div.front").css("transform", "rotateY(0deg)")
    $(this).closest("div.items").children("div.backface-love").css("transform", "rotateY(180deg)")
})

$("section.stock .row .items .backface-love").each(function(){
    var bookName = $(this).closest(".items").children(".front").children("h3").text();
    $(this).children("h3").text(bookName);
})


// filter
$("div.love-filter .btn").on("click", function () {
    $("div.main-filter").css("display", "none");
    $("div.love-filter").css("display", "none");
})


// items btn change color
$("section.stock .row .items").on("click", "button.love", function(){
    // 取stockName
    var bookName = $(this).closest("div.front").children("h3").text();
    // 取stockId
    var stockId = $(this).attr("data-stock");
    $("div.love-filter h3").text(bookName);
    $("div.love-filter button.confirm").attr("data-stock", stockId);
    $("div.main-filter").css("display", "block");
    $("div.love-filter").css("display", "block");
})


$("div.front").on("click", "button.detail", function(){
    $("div.detail-filter p").html("");
    $("div.main-filter").css("display", "block");
    $("div.detail-filter").css("display", "block");
    $("div.detail-filter button.skip").text("Skip");
    var stockId = $(this).attr("data-stock");
    $("div.detail-filter button.skip").attr("data-stock", stockId)
    showContent(stockId)
})

var interval;

$("div.detail-filter button.skip").on("click", function(){
    clearInterval(interval);
    var stockId = $(this).attr("data-stock")
    var content = newContent(bookContent[stockId]["stockContent"]);
    $("div.detail-filter p").html(`${kind}${bookContent[stockId]["stockName"]}<br>作者:${bookContent[stockId]["author"]}<br>出版社:${bookContent[stockId]["press"]}<br>出版日:${bookContent[stockId]["issuedDate"]}<br>內容簡介:${content}`)
    if ($(this).text() == "Close") {
        $("div.main-filter").css("display", "none");
        $("div.detail-filter").css("display", "none");
        $(this).text("Skip")
    }
    $(this).text("Close");
})

function newContent(content) {
	var brSymbol = ["◆","？","?","。","◎"]
	var returnContent = '';
	for (var i = 0; i < content.length; i++) {
		returnContent += content[i];
		if (brSymbol.indexOf(content[i]) != -1) {
			returnContent += "<br>"
		}
	}
	return returnContent;
}

function showContent(stockId) {
    var brSymbol = ["◆","？","?","。","◎"]
    var nameLength = bookContent[stockId].stockName.length + kind.length;
    var authorLength = bookContent[stockId].author.length + 3 + nameLength;
    var pressLength = bookContent[stockId].press.length + 4 + authorLength;
    var issuedDateLength = bookContent[stockId].issuedDate.length + 4 + pressLength;
    var contentLength = bookContent[stockId].stockContent.length + 5 + issuedDateLength;
    var contentString = `${kind}${bookContent[stockId]["stockName"]}作者:${bookContent[stockId]["author"]}出版社:${bookContent[stockId]["press"]}出版日:${bookContent[stockId]["issuedDate"]}內容簡介:${bookContent[stockId]["stockContent"]}`;
    var i = 0;
    interval = setInterval(
        function(){
            if (i == nameLength || i == authorLength || i == pressLength || i == issuedDateLength || i == contentLength) {
                $("div.detail-filter p").append("<br>");
            }
            
            $("div.detail-filter p").append(contentString[i])
            if (i > issuedDateLength && brSymbol.indexOf(contentString[i]) != -1) {
                $("div.detail-filter p").append("<br>");
            }
            
            i++;
        }, 10
    );
}

//搜尋驗證用
$("section.stock .search form.search-form button").on("click", function(){
    var input = $(this).prev().val().trim()
    if (input.length == 0) {
        $("div.main-filter").css("display", "block");
        $("div.hint p").text("請輸入搜尋關鍵字");
        $("div.hint").css("display","block");
    } else {
    	$("section.loadfilter").css("display", "flex");
        $("section.stock .search form.search-form").submit();
    }
})

$("section.stock .search form.search-form input").on("focus", function(){
    var input = $(this).val().trim();
    if (input.length == 0) {
        $(this).val("");
    }
    // 在focus 綁定keydown事件, enter為13
    $(this).keydown(function(e){
        if (e.which == 13) {
            $("section.stock .search form.search-form button").click();
        }
    });
})

$("div.hint button").on("click", function(){
    $("div.main-filter").css("display", "none");
    $("div.hint").css("display","none");
})

//$("div.main-filter").on("click", function(){
//    $("div.main-filter").css("display", "none");
//    $("div.hint").css("display","none");
//    $("div.love-filter").css("display", "none");
//})

$("section.stock .search .types li a").on("click", function(){
	$("section.loadfilter").css("display", "flex");
})








