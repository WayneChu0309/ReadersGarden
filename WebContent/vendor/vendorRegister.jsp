<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>廠商註冊 - Reader's Garden</title>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/vendor/css/vendorRegister.css'>

</head>
<body>
	<%@ include file="/parts/header.text"%>

	<jsp:useBean id="venVO" scope="request" class="com.vendor.model.VendorVO" />

	<!-- content start -->
	<div class="vendor_info">
		<div class="container_el" id="info_edit_zone">
			<h3>註冊成為廠商</h3>
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<div class="info_zone">
					<h5>請輸入以下資料：</h5>
					<h6>(*必填欄位)</h6><br>
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["company"]}</font><br>
					</c:if>
					<label for="vcompany">廠商名稱</label><font>*</font>
					<input type="text" name="vcompany" value="${venVO == null? " " : venVO.company}" class="input_el" placeholder="請輸入公司名稱" autocomplete="off">
					<br> 
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["taxid"]}</font><br>
					</c:if>
					<label for="vtaxid">統一編號</label><font>*</font>
					<input type="text" name="vtaxid" value="${venVO == null? " " : venVO.taxid}" class="input_el" placeholder="請輸入統一編號" autocomplete="off">
					<br> 
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["password"]}</font><br>
					</c:if>
					<label for="vpassword">輸入密碼</label><font>*</font>
					<input type="password" name="vpassword" value="" class="input_el" placeholder="請輸入密碼">
					<br> 
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["name"]}</font><br>
					</c:if>
					<label for="vname">聯絡人員</label><font>*</font>
					<input type="text" name="vname" value="${venVO == null? " " : venVO.name}" class="input_el" placeholder="請輸入聯絡人姓名">
					<br>
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["email"]}</font><br>
					</c:if>
					<label for="vemail">電子郵件</label><font>*</font>
					<input type="text" name="vemail" value="${venVO == null? " " : venVO.email}" class="input_el" placeholder="請輸入聯絡信箱">
					<br> 
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["tel"]}</font><br>
					</c:if>
					<label for="vtel">聯絡電話</label><font>*</font>
					<input type="text" name="vtel" value="${venVO == null? " " : venVO.tel}" class="input_el" placeholder="請輸入聯絡電話">
					<br>
					
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["mobile"]}</font><br>
					</c:if>
					<label for="vmobile">聯絡手機</label><font style="color:transparent;">*</font>
					<input type="text" name="vmobile" value="${venVO == null? " " :	venVO.mobile}" class="input_el" placeholder="請輸入聯絡人手機">
					<br>

					<label for="vaddr">聯絡地址</label><font>*</font>
					<div id="twzipcode"></div>
					<br> 
					
					<input type="text" name="vaddr" value="${venVO == null? " " : addr}" id="detail_addr_input" placeholder="請輸入完整地址">
					<c:if test="${not empty errorMsgs}">
						<font class="error_msgs">${errorMsgs["addr"]}</font><br>
					</c:if>
					 
					<input type="hidden" name="action" value="insert">
				</div>
				
				<div class="btn_div">
					<input type="reset" value="清空"> 
					<input id="info_btn" type="submit" value="送出">
				</div>
			</form>
		</div>

	</div>
	<!--vendor_info end tag-->
	</div>
	</div>
	<script src='${pageContext.request.contextPath}/vendor/jquery/jquery-3.6.0.min.js'></script>
	<script	src='${pageContext.request.contextPath}/vendor/js/vendorRegister.js'></script>
	<script	src="${pageContext.request.contextPath}/vendor/jquery/jquery.twzipcode.min.js"></script>
	
	<script>
	var city = `${city}`
	var town = `${town}`
	
		$("#twzipcode").twzipcode({
		countySel: city, // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
		districtSel: town, // 地區預設值
		zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
		css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱
		countyName: "city", // 自訂城市 select 標籤的 name 值
		districtName: "town" // 自訂地區 select 標籤的 name 值
		});
	</script>

</body>
</html>
