<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stockType.model.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.stock.model.*" %>

<%	
	Gson gson = new Gson();
	int updateTypeId = 0;
	Object typeId = session.getAttribute("updateTypeId");
	if (typeId != null) {
		updateTypeId = (int) typeId;
	}
	
	Object updateNumber = session.getAttribute("updateStockValue");
	
	Integer upNumber = 0;
	
	if (updateNumber != null) {
		upNumber = (int) updateNumber;
	}
	
	
	
	
	// 取得該類型的清單
	List<StockVO> updateStockList = (List<StockVO>) session.getAttribute("updateStockList");
	
	String updateItems = "";
	if (updateStockList != null) {
		updateItems = gson.toJson(updateStockList); 
	}
	
	
// actDate 給js抓時間用
	String actDate = java.time.LocalDate.now().toString();

%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/Stock/css/stockManagerUpdate.css'>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
</head>
<style>
	.xdsoft_datetimepicker .xdsoft_datepicker {
		width: 300px; /* width:  300px; */
	}
	
	.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
		height: 151px; /* height:  151px; */
	}
</style>
<body>
<%@ include file="/parts/header.text" %>
<%	
	

%>
            <jsp:useBean id="StockTypeSvc" scope="page" class="com.stockType.model.StockTypeService"/>

	<section id="updatearea">
	<form method="GET" action="${pageContext.request.contextPath}/stock/StockManagerUpdate.do" id="selectType">
        <select name="type" id="type">
            <option value="0">選擇類型</option>
			<c:forEach var="typeList" items="${StockTypeSvc.all}">
			<option value="${typeList.typeId}">${typeList.typeName}</option>
			</c:forEach>
        </select>
	</form>
        <select name="" id="namesearch">
            <option value="-1">名稱搜尋</option>
            <c:forEach var="stock" items="${updateStockList}" varStatus="index">
            <option value="${index.index}">${stock.stockName}</option>
            </c:forEach>
            
        </select>
        
        <select name="" id="idsearch">
            <option value="-1">編號搜尋</option>
            <c:forEach var="stock" items="${updateStockList}" begin="0" varStatus="index">
            <option value="${index.index}">${stock.stockId}</option>
            </c:forEach>
        </select>

        <button id="submit">確認修改</button>
    </section>
    
    <div class="insert_items">
        <form method="POST" action="${pageContext.request.contextPath}/stock/StockManagerUpdate.do" enctype="multipart/form-data">
        <jsp:useBean id="updateStock" scope="session" class="com.stock.model.StockVO"/>
        <c:if test="${not empty updateStock.stockId}">
            <div class="item update">
            	<input type="text" id="stockId" name="stockId" value="${updateStock.stockId}">
                <input type="text" id="stockname" name="stockname" placeholder="***請輸入書名/電影名稱***" value="${updateStock.stockName}">
                <input type="text" id="author" name="stockauthor" placeholder="作者/導演" value="${updateStock.author}">
                <input type="text" id="press" name="stockpress" placeholder="出版社/發行公司" value="${updateStock.press}">
                <input type="text" id="issuedate" name="stockissuedate" placeholder="出版日/發行日" value="${updateStock.issuedDate}">
                <p>內容介紹:</p>
                <textarea id="stockcontent" name="stockcontent"  cols="30" rows="10" value="">
                ${updateStock.stockContent}
                </textarea>
                <input type="file" id="stockimg" name="stockimg" >
                <p>預覽圖</p>
                <img src="${pageContext.request.contextPath}/stock/StockImg?action=updateImg&ID=1" alt="">
                <input type="range" name="value" min="0" max="10" value='${updateNumber}'>
                <h4>新增數量:0</h4>
                <div id="result" class="error">
                    <h1>${updateMessage}</h1>
                    <button class='result ${updateMessage == "修改成功" ? "success" : "error"}'>確定</button>
                </div>
            </div>
        </c:if>
        </form>
    </div>
    <section class="loadfilter">
	    <div id="loading">
	      <span style="--i:1;"></span>  
	      <span style="--i:2;"></span>  
	      <span style="--i:3;"></span>  
	      <span style="--i:4;"></span>  
	      <span style="--i:5;"></span>  
	      <span style="--i:6;"></span>  
	      <span style="--i:7;"></span>  
	      <span style="--i:8;"></span>  
	      <span style="--i:9;"></span>  
	      <span style="--i:10;"></span>  
	      <span style="--i:11;"></span>  
	      <span style="--i:12;"></span>  
	      <span style="--i:13;"></span>  
	      <span style="--i:14;"></span>  
	      <span style="--i:15;"></span>  
	      <span style="--i:16;"></span>  
	      <span style="--i:17;"></span>  
	      <span style="--i:18;"></span>  
	      <span style="--i:19;"></span>  
	      <span style="--i:20;"></span>  
	    </div>
  	</section>



	<script src='${pageContext.request.contextPath}/jquery/jquery-3.6.0.min.js'></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.full.js"></script>
  	<script>
       <%-- 給js產生下拉選單用 --%>
       
        var ajaxURL = "${pageContext.request.contextPath}/stock/StockManagerAjax";
       
        var updateTypeId = <%=updateTypeId%>;
        
        var imgURL = "${pageContext.request.contextPath}/stock/StockImg?action=showImg&ID=";

        var nowDate = "<%=actDate%>";
	  	
	  	var updateItems = <% if (updateItems.length() == 0) {%>
	  							"<%=updateItems%>";
						  	<%} else {%>
						  		<%=updateItems%>;
						  	<%}%>
						  	
		var updateNumber = <%=upNumber%>;
						  	
						  	
	  	
	  	
	  	$.datetimepicker.setLocale('zh');
	  	$("div.insert_items form").on("focus", "input#issuedate", function(){
	  		var date = $(this).val();
	  		if ($(this).attr("id") == "issuedate") {
		  		$(this).datetimepicker({
		 	       format: 'Y-m-d',
		 	       timepicker: false,
		 	       value: nowDate,
		 	       maxDate: "+1970-01-01"
		         });
		  		$(this).attr("id", "focus")
		  		if (date.length != 0){
		  			$(this).val(date);
		  		}
		  		$(this).focus();	  			
	  		}
	  		
	  	})
	  	
	  	$("div.insert_items form").on("change","input#focus", function(){
	  		if ($(this).val() > nowDate) {
	  			$(this).val(nowDate);
	  		}
	  	})


    
  	</script>
  	<script src='${pageContext.request.contextPath}/Stock/js/stockManagerUpdate.js'></script>
</body>
</html>