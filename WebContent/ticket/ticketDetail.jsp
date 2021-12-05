<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendoract.model.*"%>
<%
	VendorActVO vactVO = (VendorActVO) request.getAttribute("vactVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Reader's Garden</title>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/sample/css/sample.css'>
<%-- 自己的css --%>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/sample/css/sample_media.css'>
<%-- 自己的css --%>

<style>
.btn {
	display: inline-block;
	margin-top: 30px;
	margin-left: 470px;
	height: 20px;
}

.btn2 {
	display: inline-block;
	margin-top: 30px;
	margin-left: 30px;
	height: 20px;
}

.content2 {
	font-size: 30px;
	background-color: white;
	opacity: 0.8;
	min-height: 300px;
	width: 800px;
	margin-left: 300px;
	margin-bottom: 20px;
	text-align: left;
	align-items: center;
	padding: 1.2rem;
	vertical-align: middle;
	border-radius: 15px;
}

.info {
	text-align: right;
	margin-right: 30px;
	margin-top: 30px;
}

.card_title {
	font-size: 40px;
	text-align: center;
}

.outter {
	padding: 1rem;
}

.ticketTitle {
	font-size: 30px;
	text-align: center;
	background-color: rgb(189, 231, 217);
	opacity: 0.8;
}

.inner {
	background-color: rgb(255, 255, 255, 0.3);
	padding: 3rem;
}

.inner2{
	display: flex;
	margin-bottom: 30px;


}
.counter {
	margin-top: 40px;
	margin-left: 600px;
	margin-bottom: 100px;
}

.counter li {
	float: left;
	width: 30px;
	height: 30px;
	text-align: center;
	line-height: 30px;
	border: thin solid;
	background-color: #fff
}

.counter li input {
	font-size: 20px;
	width: 100%;
	height: 100%;
	outline: none;
	margin: 0;
	padding: 0;
	border: 1px solid transparent;
	border-radius: 0;
}

#countnum {
	border-left: hidden;
	border-right: hidden;
	color: #666
}

.subcontent {
	font-size: 10px;
	width: 50%;
	padding: 8px;
	font-size: 16px;
}

.subimage {
	width: 50%;
}

.subimage img {
	max-width: 100%;
}
.numTicket{
	display: inline-block;
}
.bottom{
	margin-bottom: 15px;
}
</style>


</head>
<body>

	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>

	<div class="latest-news" id="target-page">
		<div class="container">
			<img src="./icon/dot.svg" alt="" class="dot">
	      <h1>Garden's Expo</h1>
	      <h3>花園展覽</h3>
		</div>
	</div>

	<jsp:useBean id="vactSvc" scope="page"
		class="com.vendoract.model.VendorActService" />
	<div class="outter">
		<div class="ticketTitle">${vactVO.name}</div>
		<div class="inner">

			<div class="content2">
				<div class="inner2">

					<div class="subimage">
						<img
							src="${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=${vactVO.vactid}">
					</div>

					<div class="subcontent">
						<div class="tx">start Time: ${vactVO.actstart}</div>
						<div class="tx">end Time: ${vactVO.actend}</div>
						<div class="tx">Price: ${vactVO.price}</div>
						<div class="tx">Ticket sell Start Date: ${vactVO.tkstart}</div>
						<div class="tx">Ticket sell end Date: ${vactVO.tkend}</div>

						<div class="numTicket">購買數量:</div>

						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/ticket/VendorTicketServlet"
							style="margin-left: 15px; display: inline-block">
							<select name="count">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>

							</select> <input type="submit" value="add to cart" style="margin-left: 10px"
								${member == null ? "disabled" : ""}	> <input
								type="hidden" name="vactid" value="${vactVO.vactid}"> <input
								type="hidden" name="action" value="add">
						</FORM>



						<div class="btn2">
							<button type="button">
								<a href='<%=request.getContextPath()%>/vendor_ticketHome.jsp'>Back
							</button>
							</a>
						</div>

					</div>

				</div>
				<hr>
				</br>
				<div class="bottom">${vactVO.actcnt}</div>
			</div>

		</div>

	</div>



	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='./js/index.js'></script>




	<%@ include file="/parts/footer.text"%>

	<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>
	<%-- 自己的js --%>
</body>
</html>