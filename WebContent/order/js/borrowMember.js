var month = ["", "一月", "二月" ,"三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];

function dateformat(date){
	var datelist = date.split(" ");
	return "評分日期:"+`${datelist[2]}-${month.indexOf(datelist[0])}-${datelist[1].slice(0, -1)}`;
}


$(function(){

	
	$("div.stockorder div.container").on("change", "input[type='checkbox']", function(){
        if ($(this).prop("checked") == true) {
          $("div.stockorder div.container ul").removeClass("choose");
          $(this).closest("ul").addClass("choose")
          $("div.stockorder div.container ul:not(.choose) input[type='checkbox']").prop("checked", false);
          $("div.stockorder div.container ul:not(.choose)").css("max-height", "50px");
          $("div.stockorder div.container ul:not(.choose)").css("width", "320px");
          $("div.stockorder div.container ul:not(.choose)").css("border-color", "rgb(196, 196, 196)");
          $(this).closest("ul").css("max-height", "500px");
          $(this).closest("ul").css("width", "100%");
          $(this).closest("ul").css("border-color", "#ffd700");
        } else {
          $(this).closest("ul").css("border-color", "rgb(196, 196, 196)");
          $(this).closest("ul").css("max-height", "50px");
          $(this).closest("ul").css("width", "320px");
          $(this).closest("ul").removeClass("choose");
        }
	})



	//綁定評論文字區塊 on change 未輸入時清空
	$("ul.orderdetail li.content textarea").on("change", function(){
		if ($(this).val().trim() == 0) {
			$(this).val("");
		}
	})


	// 綁定評論文字 keyup 事件
	$("ul.orderdetail li.content textarea").on("focus", function(){
		var star = $(this).closest("ul").find("input[type='radio']:checked")
		
		if (star.length != 0) {
			$(this).keyup(function(e){
				if ($(this).val().trim().length != 0) {
					$(this).closest("ul").find("li.submit button").attr("disabled", false)
				} else {
					$(this).closest("ul").find("li.submit button").attr("disabled", true)
				}
			})			
		}
	})


	$("ul.orderdetail li.score input").on("change", function(){
		var content = $(this).closest("ul").find("li.content textarea").val().trim();
		if (content.length != 0) {
			$(this).closest("ul").find("li.submit button").attr("disabled", false)
		}
	})


	$("ul.orderdetail li.submit button").on("click", function(){
		var scoreData = {};
		var scoreinput = $(this).closest("ul").find("input[type='radio']");
		var textarea = $(this).closest("ul").find("li.content textarea");
		var scorebutton = $(this);
		var scoreDate = $(this).closest("ul").find("li.scoreDate");
		var starinput = $(this).closest("ul").find("input[type='radio']:checked");
		var starval = $(this).closest("ul").find("input[type='radio']:checked").attr("id");
		var label = $(this).closest("ul").find(`label.${starval}`);
		var scoretitle = $(this).closest("ul").find("li.scoretitle");
		
		
		scoreData["borrowId"] = $(this).closest("ul").find("li.borrowId").attr("data-borrowId");
		scoreData["score"] = $(this).closest("ul").find("input[type='radio']:checked").val();
		scoreData["scoreContent"] = $(textarea).val();
		
		$.ajax({
			type: "POST",
	        url: scoreURL,
	        data: scoreData,
	        dataType: "json",
	        success: function (res) {
				if (res != 0 && res.length !=0) {
					$(scoretitle).text("***已評分***");
					$(starinput).prop("checked", false);
					label.click();
					$(scoreinput).attr("disabled", true);
					$(textarea).attr("disabled", true);
					$(scorebutton).attr("disabled", true);
					$(scoreDate).text(dateformat(res["scoreDate"]));
				}
	        }
		})
	})

	$("ul.orderdetail li.score.exist input[type='radio']").attr("disabled", true);
})

