var statesName = ["","預訂","借閱中","逾期未還","已歸還","逾期歸還","取消","確認取消","確認預訂"];
var statesType = ["add","order","return","overreturn","","","cancel","cancel","confirm"];
var month = ["", "一月", "二月" ,"三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"];


// 日期格式化, gson轉換變中文, 格式化成數字
function dateformat(date){
	var datelist = date.split(" ");
	return `${datelist[2]}-${month.indexOf(datelist[0])}-${datelist[1].slice(0, -1)}`;
}


// checkbox 檢查, 只要有一個打勾, 送出按鈕即可動作
function checkboxconfirm() {
	var check = true;
	$("ul.orderitem:not(.add) li.move input[type=checkbox]").each(function(){
		if ($(this).prop("checked")) {
			check =  false;
		}
	})
	return check;
}



$(function() {
	
	
	if ($("ul.orderitem li.move input").length == 0) {
		$("div.choice button.checkall").attr("disabled", true);
	}
	
	
	// 判斷states 更新按鈕名稱
	$("ul.orderitem").each(function(){
		var statesObj = $(this).children("li.bostates");
		var bostates = $(this).children("li.bostates").attr("data-bostates");
		$(statesObj).text(statesName[bostates]);
		if ((bostates == 1 || bostates == 6)) {
			$(this).find("button").text("確認");
		} else if (bostates == 1 || bostates == 8){
			$(this).find("button").text("借閱");
		} else if (bostates == 2 || bostates == 3) {
			$(this).find("button").text("歸還");
		} else if (bostates == 7) {
			$(this).find("button").text("已取消");
		} else {
			$(this).find("button").text("已歸還");
		}
	})
	
	// 編號搜尋欄位, 限制數字
	$("section#manager div.action form input").on("focus", function(){
		$(this).keydown(function(e){
			if ((e.which >= 48 && e.which <=57)) {
				$(this).siblings("button.memberselect").attr("disabled", false);
			} else if(e.which == 8) {
				if ($(this).val().trim().length == 1) {
					$(this).siblings("button.memberselect").attr("disabled", true);
				}
			} else if(e.which == 13){
				if ($(this).val().trim().length != 1) {
					
				}
			} else {
				e.preventDefault();
			}
		})
	})
	
	
	
	// 輸入會員編號時 才能做會員訂單查詢
	$("section#manager div.action form input").on("change", function(){
		if ($(this).val().trim().length != 0){
			$(this).siblings("button.memberselect").attr("disabled", false);
		} else {
			$(this).siblings("button.memberselect").attr("disabled", true);
		}
	})
	
	
	// 主要button點擊切換 請求 參數
	$("section#manager div.action button.selectall").on("click", function(){
		$(this).siblings("input#action").val("selectall");
	})
	// 主要button點擊切換 請求 參數
	$("section#manager div.action button.orderselect").on("click", function(){
		$(this).siblings("input#action").val("orderselect");
	})
	// 主要button點擊切換 請求 參數
	$("section#manager div.action button.cancelselect").on("click", function(){
		$(this).siblings("input#action").val("cancelselect");
	})
	
	// 會員button點擊切換 請求 參數
	$("section#manager form.memberform button.illegltotal").on("click", function(){
		$(this).siblings("input#action").val("illegltotal");
	})
	
	// 會員button點擊切換 請求 參數
	$("section#manager form.memberform button.normaltotal").on("click", function(){
		$(this).siblings("input#action").val("normaltotal");
	})
	
	
	// 增加訂單用
	$("div.choice").on("focus", "ul.orderitem input", function(){
  		if ($(this).val() == "輸入錯誤" || $(this).val() == "查無會員" || $(this).val() == "已被預訂") {
  			$(this).val('');
  		}
  		
		$(this).keydown(function(e){
			if ((e.which >= 48 && e.which <=57) || e.which == 13 || e.which == 8) {
			} else {
				e.preventDefault();
			}
		})
	})
  	
  	// 增加訂單用
  	$("div.choice").on("change", "ul.orderitem input[type='text']", function(){
  		var item = $(this).closest("ul.orderitem");
  		var memberinput = $(item).find("li.membername input").val().trim();
  		var listinput = $(item).find("li.listid input").val().trim();
  		var checkbox = $(item).find("li.move input");
  		
  		$(item).find("li.listid").attr("data-listId", listinput);
  		orderData["memberId"] = memberinput;
  		
  		
  		if ((memberinput.length != 0) && (listinput.length != 0)){ 
  			$(item).removeClass("add");
  			$(checkbox).prop("disabled", false);
  		} else {
  			$(item).addClass("add");
  			$(checkbox).prop("disabled", true);
  		}
  	})
	
	
	
	// 全選按鈕
	$("div.choice button.checkall").on("click", function(){
  		$(this).toggleClass("choice");
  		if ($(this).hasClass("choice")) {
	  		$("ul.orderitem:not(.add) li.move input[type=checkbox]").prop("checked", true);
	  		$("ul.orderitem:not(.add) li.update button").attr("disabled", false);
	  		$("div.choice button.allsubmit").attr("disabled", false);
  		} else {
  			$("ul.orderitem:not(.add) li.move input[type=checkbox]").prop("checked", false);
	  		$("ul.orderitem:not(.add) li.update button").attr("disabled", true);
	  		$("div.choice button.allsubmit").attr("disabled", true);
  		}
	
  	})
	

  	
  	// 單選checkbox
  	$("div.choice").on("change", "ul.orderitem:not(.add) input", function(){
		if ($(this).prop("checked")) {
			$(this).closest("li").siblings("li.update").find("button").attr("disabled", false);			
			$("section#manager div.action form button.allsubmit").attr("disabled", false);
		} else {
			$(this).closest("li").siblings("li.update").find("button").attr("disabled", true);
			$("section#manager div.action form button.allsubmit").attr("disabled", checkboxconfirm());
		}
	})
	
	// 送出按鈕
	$("div.choice button.allsubmit").on("click", function(){
		$("section.loadfilter").css("display", "flex");
		
		$("section#manager div.choice ul.orderitem:not(.add) button").each(function(){
			$(this).click();
		})
		
		$("section.loadfilter").css("display", "none");
		
		return false;
	})
	
	
	
	
	// ajax 動作
	$("div.choice").on("click", "ul.orderitem:not(.add) button", function(){
		orderData["borrowId"] = $(this).attr("data-borrowId");
		orderData["listId"] = $(this).closest("ul.orderitem").find("li.listid").attr("data-listId");
		orderData["boStates"] = $(this).closest("ul.orderitem").find("li.bostates").attr("data-bostates");
		orderData["action"] = statesType[orderData["boStates"]]; 
		var that = $(this).closest("ul.orderitem");
	  	$.ajax({
	        type: "POST",
	        url: URL,
	        data: orderData,
	        dataType: "json",
	        success: function (res) {
	        	console.log(res)
	        	if (res == 0) {
	        		$(that).find("li.listid input").val("");
	        		$(that).find("li.listid input").attr("placeholder","*查無此筆*");
	        		return;
	        	} else if (res == 1) {
	        		$(that).find("li.listid input").val("");
	        		$(that).find("li.listid input").attr("placeholder","*已被預訂*");
	        		return;
	        	} else if (res == 2) {
	        		$(that).find("li.membername input").val("");
	        		$(that).find("li.membername input").attr("placeholder", "*查無此會員*")
	        		return;
	        	} else if (res == 3) {
	        		$(that).find("li.membername input").val("");
	        		$(that).find("li.membername input").attr("placeholder", "管理員???")
	        	} else {
	    			if (res["actBoDate"] != undefined) {
	    				$(that).find("li.actBoDate").text(dateformat(res["actBoDate"]))	    				
	    			}
	    			
	    			if (res["actReDate"] != undefined) {
	    				$(that).find("li.actReDate").text(dateformat(res["actReDate"]))
	    			}
	    			
	    			if (res["preReDate"] != undefined) {
	    				$(that).find("li.preReDate").text(dateformat(res["preReDate"]))
	    			}
	    			
	    			var stockname = res["stockBean"]["stockName"];
	    			var formatename = stockname.length > 3 ? stockname.substring(0, 3) + '...' : stockname;
	    			$(that).find("li.stockname").html(`<span>${stockname}</span>${formatename}`);
	    			
	    			$(that).find("li.update button").attr("data-borrowId", res["borrowId"]);
	    			$(that).find("input[type='text']").attr("disabled", true);
	    			
	    			$(that).find("li.bostates").attr("data-bostates", res["boStates"]);
	    			$(that).find("li.bostates").text(`${statesName[res["boStates"]]}`);
	    			
	    			if (res["boStates"] == 2) {
	    				$(that).find("input[type=checkbox]").prop("checked", false);
	    				$(that).find("button").text("歸還");
	    				$(that).find("button").attr("disabled", true);
	    			} else {
	    				$(that).remove();
	    			}
	        	}
	        }
        })
  	})
  	
  	$("section.loadfilter").css("display", "none");
  	
})