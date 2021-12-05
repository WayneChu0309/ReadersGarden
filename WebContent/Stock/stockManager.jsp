<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stockType.model.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.stock.model.*" %>

<%
	List<String> addMessage = (List<String>)session.getAttribute("addMessage");
	List<StockVO> addStockList = (List<StockVO>)session.getAttribute("addStockList");
	List<Integer> addStockValue = (List<Integer>)session.getAttribute("addStockValue");
	Gson gson = new Gson();
	
	int addItemsNumber = 0;
	
	String addItems = "";
	
	if (addStockList != null) {
		addItemsNumber = addStockList.size();
		addItems = gson.toJson(addStockList); 
	}
	
	
// actDate 給js抓時間用
	String actDate = java.time.LocalDate.now().toString();
// type 給 js下拉選單用
	StockTypeService typeSvc = new StockTypeService();
	List<StockTypeVO> vo = typeSvc.getAll();
	String typeJson = gson.toJson(vo);
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/Stock/css/stockManager.css'>
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

	<button id="add_items">新增內容</button>
	<button id="submit">送出</button>
	<a href="${pageContext.request.contextPath}/stock/StockManager.do?status=clear" id="clear">清空</a>
    <div class="insert_items">
        <form method="post" action="${pageContext.request.contextPath}/stock/StockManager.do" enctype="multipart/form-data">
        <c:forEach var="stockVO" items="${addStockList}" varStatus="index">
            <div class="item add">
                <input type="text" id="stockname" name="stockname${index.index+1}" placeholder="***請輸入書名/電影名稱***" value=${stockVO.stockName == null ? "" : stockVO.stockName}>
                <select id="stocktype" name="stocktype${index.index+1}">
                    <option value="0">***類別***</option>
                </select>
                <select id="stockkind" disabled="true">
                    <option value="">種類</option>
                </select>
                <input type="text" id="author" name="stockauthor${index.index+1}" placeholder="作者/導演" value=${stockVO.author == null ? "" : stockVO.author}>
                <input type="text" id="press" name="stockpress${index.index+1}" placeholder="出版社/發行公司" value=${stockVO.press == null ? "" : stockVO.press}>
                <input type="text" id="issuedate" name="stockissuedate${index.index+1}" placeholder="出版日/發行日" value=${stockVO.issuedDate == null ? "" : stockVO.issuedDate}>
                <p>內容介紹:</p>
                <textarea id="stockcontent" name="stockcontent${index.index+1}"  cols="30" rows="10" value=${stockVO.stockContent == null ? "" : stockVO.stockContent}>
                </textarea>
                <input type="file" id="stockimg" name="stockimg${index.index+1}" >
                <p>預覽圖</p>
                <img src="${pageContext.request.contextPath}/stock/StockImg?action=addImg&ID=${stockVO.stockImg == null ? -1 : index.index}" alt="">
                <input type="range" name="value${index.index+1}" min="1" max="10" value="${addStockValue[index.index]}">
                <h4>數量:${addStockValue[index.index]}</h4>
<!--                 <button id="delete">刪除</button> -->
                <div id="result" class="error">
                    <h1>${addMessage[index.index]}</h1>
                    <button class='result ${addMessage[index.index] == "新增成功" ? "success" : ""}'>確定</button>
                </div>
            </div>
        </c:forEach>
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
	  	var stockType = <%=typeJson%>;
	  	var nowDate = "<%=actDate%>";
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
	  	
	  	var count = <%=addItemsNumber%>;
	  	var countNumber = <% if (addItems.length() == 0) {%>
	  							"<%=addItems%>";
						  	<%} else {%>
						  		<%=addItems%>;
						  	<%}%>
    
  	</script>
  	<script src='${pageContext.request.contextPath}/Stock/js/stockManager.js'></script>
</body>
</html>