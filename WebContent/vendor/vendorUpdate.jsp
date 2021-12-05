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
<link rel="stylesheet" href="css/vendorAdd.css">
</head>

<body>
	<h1>修改資料</h1>
	<div class="input_div container">
		<h3>請輸入以下資訊:</h3>
		
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤：</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<jsp:useBean id="venVO" scope="request" class="com.vendor.model.VendorVO"/>
		
		<div class="_el">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
			<table>
				<tr>
					<td><label for="vcompany">廠商名稱:</label> </td>
					<td><input type="text" name="vcompany" value="${venVO.company}" class="input_el" placeholder=""></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td><label>狀態:</label></td> -->
<!-- 					<td> -->
<!-- 						<select name="status"> -->
<!-- 							<option value="正常">正常</option> -->
<!-- 							<option value="停權">停權</option> -->
<!-- 							<option value="永久停業">永久停業</option> -->
<!-- 						</select> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td><label for="vtaxid">統一編號:</label> </td>
					<td><input type="text" name="vtaxid" value="${venVO.taxid}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vpassword">密碼:</label></td>
					<td><input type="text" name="vpassword" value="${venVO.password}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vname">負責人:</label></td>
					<td><input type="text" name="vname" value="${venVO.name}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vemail">Email:</label></td>
					<td><input type="text" name="vemail" value="${venVO.email}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vtel">電話:</label> </td>
					<td><input type="text" name="vtel" value="${venVO.tel}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vmobile">手機:</label> </td>
					<td><input type="text" name="vmobile" value="${venVO.mobile}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td><label for="vaddr">地址:</label> </td>
					<td><input type="text" name="vaddr" value="${venVO.addr}" class="input_el" placeholder=""></td>
				</tr>
				<tr>
					<td></td>
					<td>				
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="vendorid" value="${venVO.vendorid}">
						<input type="submit" value="修改">
					</td>
				</tr>
			
			</table>
			</form>
		</div>
		
		

	</div>
</body>
</html>