$(function(){
   
    // 提交按鈕 驗證
    $("button#submit").on("click",function(){
    	$("div.insert_items form").submit();
    	$("section.loadfilter").css("display", "flex");
    	return;
    	var errcontent = [];
    	$("div.insert_items form div.add").each(function(){
    		var err ='';
    		if ($(this).find("input#stockname").val().trim().length == 0) {
    			err += "書名/電影名稱<br>不得為空<br>";
    		} 
    		if ($(this).find("select#stocktype").val() == 0) {
    			err += "類別需選取<br>";
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

    $("div.insert_items form div.add").each(function(){
    	var options = ''
		for (var i = 0; i < stockType.length; i++) {
			options +=  `<option data-kind="${stockType[i]["kind"]}" value="${stockType[i]["typeId"]}">${stockType[i]["typeName"]}</option>`;
		}
    	$(this).find("select#stocktype").append(options);
    })

// 類別選項 stockType 從jsp 頁面取得
    function stockTypeOption(){
    	var newitems = $("div.insert_items form div.add").last()
    	var options = ''
    		for (var i = 0; i < stockType.length; i++) {
    			options +=  `<option data-kind="${stockType[i]["kind"]}" value="${stockType[i]["typeId"]}">${stockType[i]["typeName"]}</option>`;
    		}
    	$(newitems).find("select#stocktype").append(options);
    }
    
    $("div.insert_items form").on("change", "select#stocktype", function(){
    	var kind = $(this).find("option:selected").attr("data-kind");
    	$(this).next().find("option").text(kind)
    })
    
    
    
    
// 預覽圖
    $("div.insert_items form").on("change", "input[type='file']", function(e){
    	var image = $(this).siblings("img");
    	$(image).attr("src", URL.createObjectURL(e.target.files[0]));
    })
    
    
// 新增時增加元素
    $("button#add_items").on("click", function(){
    	var items = `<div class="item${count+1} add">
    		<input type="text" id="stockname" name="stockname${count+1}" placeholder="***請輸入書名***">
    		<select name="stocktype${count+1}" id="stocktype">
    		<option value="0">***類別***</option>
    		</select>
    		<select id="stockkind" disabled="true">
    		<option value="">種類</option>
    		</select>
    		<input type="text" id="author" name="stockauthor${count+1}" placeholder="作者/導演">
    		<input type="text" id="press" name="stockpress${count+1}" placeholder="出版社/發行公司">
    		<input type="text" id="issuedate" autocomplete="off" name="stockissuedate${count+1}" placeholder="出版日/發行日">
    		<p>內容介紹:</p>
    		<textarea name="stockcontent${count+1}" id="" cols="30" rows="10">
    		</textarea>
    		<input type="file" id="stockimg" name="stockimg${count+1}">
    		<p>預覽圖</p>
    		<img src="" alt="">
    		<input type="range" name="value${count+1}" min="1" max="10" value="1">
            <h4>數量:1</h4>
            <div id="result">
                <h1></h1>
                <button class="result">確定</button>
            </div>
    		</div>`;
    	
    	$("div.insert_items form").append(items);
    	// 新增完刷新類別選單
    	stockTypeOption();
    	$(`div.insert_items form div.item${count+1}`).css("animation", "showAddItems .5s");
    	count++;
    })
    
    
    // 數量 range bar 拖動時 標籤數字一起動
    $("div.insert_items form").on("focus", "input[type='range']", function(){
            $(this).bind('input propertychange', function(){
            $(this).next().text(`數量:${$(this).val()}`)
            })
     })
     
     
     // 刪除鈕, 刪除該筆資料, 加上 return false 否則會觸發表單 submit
     $("div.insert_items form").on("click", "button#delete", function(){
            $(this).closest("div").remove();
            return false;
     })
     
     
     // 結果的按鈕, 若有 success 這個 class 確認後移除整筆資料
     // 若無, 保留該筆資料
     $("div.insert_items form").on("click", "button.result", function(){
    	 if ($(this).filter(".success").length != 0) {
    		 $(this).closest("div.add").remove();
    		 return false;
    	 } else {
    		 $(this).closest("div").removeClass("error");
    		 return false;
    	 }
     })
     
     if (count == 0) {
    	$("button#add_items").click();
    }
    
    $("div.insert_items form div select#stocktype").each(function(index){
    	var typeId = $(this).attr("data-type");
    	var kind = $(this).find("option").eq(typeId).attr("data-kind");
    	$(this).find("option").eq(typeId).attr("selected", true);
    	$(this).next().find("option").text(kind);
    })
    
    $("section.loadfilter").css("display", "none");
})     
        
        

