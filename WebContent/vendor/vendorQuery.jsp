<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>廠商資料查詢</title>
<link rel="stylesheet" href="css/vendorQuery.css">
</head>

<body>
	<h1>廠商資料查詢</h1>
	<div class="input_div container">
		<h3>輸入查詢</h3>
		
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤：</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		
		
		<div class="_el">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<label for="company">廠商編號:</label> 
				<input type="text" name="vendorid" value="" class="input_el" placeholder="請輸入廠商編號"> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="查詢">
			</form>
		</div>

		<div class="_el">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<label for="company">廠商名稱:</label> 
				<input type="text" name="vendorname" value="" class="input_el" placeholder="請輸入廠商名稱"> 
				<input type="hidden" name="action" value="find_By_Keyword"> 
				<input type="submit" value="查詢">
			</form>
		</div>
	</div>

	<div class="select_div container">
		<h3>選項查詢</h3>
		<div class="_el">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<label for="vendorid">廠商編號:</label> 
				<jsp:useBean id="venSvc" scope="page" class="com.vendor.model.VendorService" />
				<select class="" name="vendorid">
					<c:forEach var="vendor" items="${venSvc.getAll()}">
					<option value="${vendor.vendorid}">${vendor.vendorid}</option>
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="查詢">
			</form>
		</div>

		<div class="_el">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<label for="vendorid">廠商名稱:</label> 
				<select class="" name="vendorid">
					<c:forEach var="vendor" items="${venSvc.getAll()}">
					<option value="${vendor.vendorid}">${vendor.company}</option>
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display"> 
				<input type="submit" value="查詢">
			</form>
		</div>
	</div>

	<div class="arrange_div container">
		<h3>廠商管理</h3>
		<ul>
			<li><a href="<%=request.getContextPath()%>/vendor/vendorAdd.jsp">新增廠商</a></li>
			<li><a href="<%=request.getContextPath()%>/vendor/vendorAll.jsp">顯示全部</a></li>
		</ul>
	</div>

</body>
</html>