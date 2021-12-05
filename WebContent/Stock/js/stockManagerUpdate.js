$(function(){
	
//	 選擇類別時, 重載入的類別顯示為選擇的類別
	if (updateTypeId != 0) {
		$("select#type").val(updateTypeId)
	}

	// 選擇類型時, 帶出該類清單
	$("select#type").on("change", function(){
		if ($(this).val() != 0) {
			$("section.loadfilter").css("display", "flex");
			$("form#selectType").submit();
		}
	})
	
	if (updateStockInf.length != 0){
		for (var i = 0; i < updateItems.length; i++) {
			if (updateItems[i]["stockId"] == updateStockInf["stockId"] ){
				$("select#idsearch").val(i);
				$("select#namesearch").val(i);
				$("section.loadfilter").css("display", "none");
			} 
		}
	}
	
	
	// 選擇編號或名稱時, 兩個下拉選單選中同個物件
	$("select#namesearch").on("change", function(){
		if ($(this).val() != -1) {
			$("select#idsearch").val($(this).val());
			showUpdateInf($(this).val());
		}
	})
	
	$("select#idsearch").on("change", function(){
		if ($(this).val() != -1){
			$("select#namesearch").val($(this).val());
			showUpdateInf($(this).val());
		}
	})
	
	
	// 拿 該筆清單
	function listajax(stockId){
		 $.ajax({
	           type: "POST",
	           url: ajaxURL,
	           data: {"stockId":stockId},
	           dataType: "json",
	           success: function (res) {
	        	   $("div.insert_items div.listitem button").remove();
	        	   $("div.insert_items div.listitem ul").remove();
	        	   $.each(res, function(index, item){
	        		   var content = "";
	        		   var listId = item["listId"];
	        		   content += `<ul><li>館藏編號:${listId}</li>`;
	        		   var liststates = item["listStates"] == 1 ? "正常" : item["listStates"] == 2 ? "預訂/訂閱" : "報廢";
	        		   content += `<li>館藏狀態:${liststates}</li>`;
	        		   
	        		   if (item["listStates"] == 1) {
	        			   content += `<li>報廢<input type='checkbox'><button id='scrapped' data-listId=${listId} disabled>確認</button></li>`;
	        		   } else if (item["listStates"] == 2) {
	        			   content += `<li>報廢<input type='checkbox' disabled><button id='scrapped' data-listId=${listId} disabled>確認</button></li>`;
	        		   }

	        		   $("div.insert_items div.listitem").append(content);
	        	   })
	           }
	       });
	}
	
	$("div.insert_items").on("change", "input[type='checkbox']", function(){
		if ($(this).prop("checked")) {
			$(this).siblings("button").attr("disabled", false)
		} else {
			$(this).siblings("button").attr("disabled", true)
		}
	})
	
	$("div.insert_items").on("click", "button#scrapped", function(){
		var that = this;
	    	 $.ajax({
	           type: "POST",
	           url: listAjaxURL,
	           data: {"listId":$(this).attr("data-listId")},
	           dataType: "json",
	           success: function (res) {
	        	   if (res == 1) {
	        		   $(that).closest("ul").find("li").eq(1).text("館藏狀態:報廢");
	        		   $(that).closest("ul").find("li").eq(2).remove();
	        	   }
	           }
	       });
	})
	
	
    // 提交按鈕 驗證
    $("button#submit").on("click",function(){
    	$("div.insert_items form").submit();
    	$("section.loadfilter").css("display", "flex");
    	return;
    	var errcontent = [];
    	$("div.insert_items form div.update").each(function(){
    		var err ='';
    		if ($(this).find("input#stockname").val().trim().length == 0) {
    			err += "書名/電影名稱<br>不得為空<br>";
    		} 
    		
    		if ($(this).find("textarea").val().trim().length == 0) {
    			err += "介紹內容需補充<br>";
    		}
    		
    		if ($(this).find("img").attr("src").length == 0) {
    			err += "圖片需選取";
    		}
    		if (err.length != 0) {
    			errcontent.push(err);
    			$(this).find("div h1").html(err);
    			$(this).find("div").addClass("error")
    		}
    	})
    	
    	if(errcontent.length != 0) {
    		return false;
    	} else {    		
    		$("div.insert_items form").submit();    		
    	}
    })
    
    
    // 書名框 
    $("div.insert_items form").on("focus", "input#stockname", function(){
    	var input = $(this).val().trim();
        if (input.length == 0) {
            $(this).val("");
        }
    })
    
    
// 預覽圖
    $("div.insert_items form").on("change", "input[type='file']", function(e){
    	var image = $(this).siblings("img");
    	$(image).attr("src", URL.createObjectURL(e.target.files[0]));
    })
    
    
    // 數量 range bar 拖動時 標籤數字一起動
    $("div.insert_items form").on("focus", "input[type='range']", function(){
            $(this).bind('input propertychange', function(){
            $(this).next().text(`新增數量:${$(this).val()}`)
            })
     })
     
    if (updateNumber != undefined) {
    	$("div.insert_items form input[type='range']").val(updateNumber);
    	$("div.insert_items form h4").text(`新增數量:${updateNumber}`);    	
    }
     
  
     // 結果的按鈕, 若有 success 這個 class 確認後移除整筆資料
     // 若無, 保留該筆資料
     $("div.insert_items form").on("click", "button.result", function(){
    	 if ($(this).filter(".success").length != 0) {
    		 $(this).closest("div.update").remove();
    		 $("button#submit").attr("disabled", false);
    		 clearUpdate();
    		 return false;
    	 } else {
    		 $(this).closest("div").removeClass("error");
    		 $("button#submit").attr("disabled", false);
    		 return false;
    	 }
     })
     
     function clearUpdate(){
    	 $.ajax({
           type: "POST",
           url: ajaxURL,
           data: "",
           dataType: "json",
           success: function (res) {
           }
       });
     }
     
     
     function showUpdateInf(index) {
    	 $("div.insert_items div.listitem").remove();
		var items = `<div class="item update">
			<input type="text" id="stockId" name="stockId" value="${updateItems[index]["stockId"]}">
    		<input type="text" id="stockname" name="stockname" placeholder="***請輸入書名***" value="${updateItems[index]["stockName"]}">
    		<input type="text" id="author" name="stockauthor" placeholder="更新作者/導演" value="${updateItems[index]["author"] == undefined ? "" : updateItems[index]["author"]}">
    		<input type="text" id="press" name="stockpress" placeholder="更新出版社/發行公司" value="${updateItems[index]["press"] == undefined ? "" : updateItems[index]["press"]}">
    		<input type="text" id="issuedate" autocomplete="off" name="stockissuedate" placeholder="更新出版日/發行日" value="${updateItems[index]["issuedDate"] == undefined ? "" : updateItems[index]["issuedDate"]}">
    		<p>內容介紹:</p>
    		<textarea name="stockcontent" id="" cols="30" rows="10">
    			${updateItems[index]["stockContent"]}
    		</textarea>
    		<input type="file" id="stockimg" name="stockimg">
    		<p>預覽圖</p>
    		<img src="${imgURL+updateItems[index]["stockId"]}" alt="">
    		<input type="range" name="value" min="0" max="10" value="0">
            <h4>新增數量:0</h4>
            <div id="result">
                <h1></h1>
                <button class="result">確定</button>
            </div>
    		</div>`;	
		
		$("div.insert_items form").html(items);
		$("div.insert_items").append("<div class='listitem'><button id='getlist'>取得館藏清單</button></div>");

	}
     
     
     $("div.insert_items").on("click", "button#getlist", function(){
    	 var stockId = $("select#idsearch option:selected").text();
    	 listajax(stockId);
     })
    
     if ($("div.insert_items form button.result").filter(".success").length != 0 ){
    	 $("button#submit").attr("disabled", true);
     }
     
     if ($("div.insert_items form button.result").filter(".error").length != 0 ){
    	 $("button#submit").attr("disabled", true);
     }
     
    $("section.loadfilter").css("display", "none");
})     
        
        

