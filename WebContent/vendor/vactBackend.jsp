<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/vendor/css/vactBackend.css'>
</head>
<body>

<%@ include file="/parts/header.text" %>
<%@ include file="/parts/manager.text" %>

<jsp:useBean id="siteSvc" scope="page" class="com.site.model.SiteService"/>
<jsp:useBean id="statusnameSvc" scope="page" class="com.statusname.model.StatusNameService"/>
<jsp:useBean id="vendorSvc" scope="page" class="com.vendor.model.VendorService"/>


<section id="manager_area">
	<section id="manager">
	    <div class="action">
	      <form method="POST" action="${pageContext.request.contextPath}/vendoract/VendorActServlet">
	      	<input type="text" id="action" name="action" value="getOne_For_Display" style="display:none">
	        <input type="text" name="vactid" value="" placeholder="請輸入訂單編號..." autocomplete="off">
	        <c:if test="${errorMsgs != null}">
	        	<span class="errorMsgs">${errorMsgs}</span>
	        </c:if>
	        <button class="btn memberselect">查詢訂單</button>
	      </form>
	      	<button class="btn allselect">顯示全部</button>
	      	<button class="btn statusselect">依狀態查詢</button>
	      	<button class="btn siteselect">依場地查詢</button>
	      	
	      	<select class="-none" id="progress_select">
	      	   <option value="-1">請選擇</option>
	      		<c:forEach var="status" items="${statusnameSvc.all}">
	      			<option value="${status.statusid}">${status.status}</option>
			    </c:forEach>
	      	</select>
	      	
	      	
      		<select class="-none" id="site_select">
      			<option value="-1">請選擇</option>
      			<c:forEach var="site" items="${siteSvc.all}">
	      			<option value="${site.siteid}">${site.name}</option>
			    </c:forEach>
      		</select>

	      
	    </div>
	    <div class="choice">
	      <div class="orderitems">
	        <ul class="title">
	          <li class="vactid">訂單編號</li>
	          <li class="date">訂單日期</li>
	          <li class="vendorname">廠商名稱</li>
	          <li class="siteid">場地名稱</li>
	          <li class="rtlstart">租借開始日期</li>
	          <li class="rtlend">租借結束日期</li>
	          <li class="amount">訂單金額</li>
	          <li class="detail">申請內容</li>
	          <li class="progress">訂單狀態</li>
	          <li class="change">狀態變更</li>
	        </ul>
	        
	        <h1 id="result_show">
	      	  <c:if test="${vactlist== null && vact.vactid == null}">
	      		  查無結果
	      	  </c:if>
	        </h1>
	        
	       <c:choose>
	       	<c:when test="${vact.vactid != null}">
	       		  <ul class="orderitem" data-vactid="${vact.vactid}">
			          <li class="vactid">${vact.vactid}</li>
				      <li class="date">${vact.date}</li>
				      <li class="vendorname">${vendorSvc.findOneVendor(vact.vendorid).company}</li>				      
			          <li class="siteid" data-siteid="${vact.siteid}">${siteSvc.findOneSite(vact.siteid).name}</li>
			          <li class="rtlstart">${vact.rtlstart}</li>
			          <li class="rtlend">${vact.rtlend}</li>
			          <li class="amount"><fmt:formatNumber type="currency" pattern="$#,###">${vact.amount}</fmt:formatNumber></li>
			          <li class="detail" id="${vact.vactid}"><h3 class="check_act">查看</h3></li>
			          <li class="progress" data-progress="${vact.progress}">${statusnameSvc.findOneStatusName(vact.progress).status}</li>
			          <li class="change">
			          	<select class="-none" class="progress_change">
	      	   				<option value="-1">請選擇</option>
	      					<c:forEach var="status" items="${statusnameSvc.all}">
	      					<option value="${status.statusid}">${status.status}</option>
			    			</c:forEach>
	      				</select>	
	      				<h3>變更</h3>		          
			         </li>
			       </ul>     
	       	</c:when>

	       	<c:otherwise>
	       		<c:forEach var="vact" items="${vactlist}">
			        <ul class="orderitem" data-vactid="${vact.vactid}">
			          <li class="vactid">${vact.vactid}</li>
				      <li class="date">${vact.date}</li>
				      <li class="vendorname">${vendorSvc.findOneVendor(vact.vendorid).company}</li>				      
			          <li class="siteid" data-siteid="${vact.siteid}">${siteSvc.findOneSite(vact.siteid).name}</li>
			          <li class="rtlstart">${vact.rtlstart}</li>
			          <li class="rtlend">${vact.rtlend}</li>
			          <li class="amount"><fmt:formatNumber type="currency" pattern="$#,###">${vact.amount}</fmt:formatNumber></li>
			          <li class="detail" id="${vact.vactid}"><h3 class="check_act">查看</h3></li>
			          <li class="progress" data-progress="${vact.progress}">${statusnameSvc.findOneStatusName(vact.progress).status}</li>	          
 					  <li class="change">
			          	<select class="-none" class="progress_change">
	      	   				<option value="-1">請選擇</option>
	      					<c:forEach var="status" items="${statusnameSvc.all}">
	      					<option value="${status.statusid}">${status.status}</option>
			    			</c:forEach>
	      				</select>	
	      				<h3>變更</h3>		          
			         </li>  			          	        
			        </ul>     
	     		 </c:forEach>
	       	</c:otherwise>
	       </c:choose>
	      </div>
	    </div>
  </section>
  </section>
  
  <div class="pop_bg"></div>
  <div class="pop_item">
	<button id="close_btn">關閉</button>
  	<div class="pop_content">
  		<h2>活動申請</h2>
	  	<h3>訂單編號: <span id="pop_vactid"></span></h3>
	  	
	  	<h4>一、活動申請人</h4>
	  	<hr>
	  	<h5>廠商編號: </h5><span id="pop_vendorid"></span><br>
	  	<h5>公司名稱: </h5><span id="pop_company"></span><br>
	  	<h5>地址: </h5><span id="pop_addr"></span><br>
	  	<h5>統一編號: </h5><span id="pop_taxid"></span><br>
	  	<h5>負責人: </h5><span id="pop_name"></span><br>
	  	<h5>連絡電話: </h5><span id="pop_tel"></span><br><br>
	  	
	  	<h4>二、活動內容概述</h4>
	  	  	<hr>
	  	
	  	<h5>活動名稱: </h5><span id="pop_actname"></span><br>
	  	<h5>活動類型: </h5><span id="pop_acttype"></span><br>
	  	<h5>活動開始日期: </h5><span id="pop_actstart"></span><br>
	  	<h5>活動結束日期: </h5><span id="pop_actend"></span><br>
	  	<h5>售票開始日期: </h5><span id="pop_tkstart"></span><br>
	  	<h5>售票結束日期: </h5><span id="pop_tkend"></span><br>
	  	<h5>售票價格: </h5><span id="pop_tkprice"></span><br>
	  	<h5 class="cnt">活動內容: </h5><br>
	  	<span id="pop_actcnt"></span><br>
	  	<h5 class="cnt">附圖: </h5><img src="#" id="pop_img">
	  	<br>
	  	<div id="vendoract_check">
	  	
	  		<h4>審核結果: </h4>
	  			<input type="hidden" name="progress" value="0" id="pop_progress"> 
	  			<div id="result_maker">
		  			<input type="radio" name="result" value="pass" id="choice1">
			  		<label for="choice1">通過</label>
			  		<input type="radio" name="result" value="failed" id="choice2">
			  		<label for=choice2>不通過</label><br>
			  		<textarea id="result_message"></textarea><br>
			  		<h4 id="alert_message">請填寫不通過原因</h4>
			  		<button id="result_submit">送出</button>
	  			</div>
	  			<div id="result_zone"></div>
	  	</div>
  	</div>
  	

  
  </div>

<%@ include file="/parts/footer.text" %>

	<script>
		$("a#vendor").addClass("select");
		var vactAll_url = `${pageContext.request.contextPath}/vendoract/VendorActServlet?action=getAll`;
		var vact_check_url = `${pageContext.request.contextPath}/vendoract/VendorActCheck`;
		var vact_img_url = `${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=`;
		var vact_progress_url = `${pageContext.request.contextPath}/vendoract/VendorActProgress`;
		var check_progress_url = `${pageContext.request.contextPath}/vendoract/CheckVendorActProgress`;
		var email_url = `${pageContext.request.contextPath}/vendoract/VendorActResultEmail`;

	</script>
	<script src='${pageContext.request.contextPath}/vendor/js/vactBackend.js'></script>
</body>
</html>