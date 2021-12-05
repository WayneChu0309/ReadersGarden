<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.event.model.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>發起活動</title>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/common.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/index.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/join.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/media.css'>
</head>

<body>

	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>


	<div class="latest-news">
		<div class="container">
			<img src="./icon/dot.svg" alt="" class="dot">
			<h1>Grouping</h1>
			<h3>揪 團</h3>
		</div>
	</div>


	<section class="join">
		<div class="container">
			<div class="row">
				<hr />
				<div class="activity">
					<h1>
					參加失敗！您已參加過這個活動。					
					</h1>
					<div class="content">
<!-- 						<table> -->
<!-- 							<tr> -->
<!-- 								<td>活動名稱</td> -->
<%-- 								<td>${event.eventname}</td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>人數</td> -->
<%-- 								<td>1/${event.capacity}</td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>活動時間</td> -->
<%-- 								<td><font color="#C80815"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" --%>
<%-- 										value="${event.eventstart}" /> 至 <fmt:formatDate --%>
<%-- 										pattern="yyyy-MM-dd HH:mm" value="${event.eventend}" /></font></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td>活動內容</td> -->
<%-- 								<td>${event.eventdescription}</td> --%>
<!-- 							</tr> -->
<!-- 						</table> -->
						<div class="btns">
							<a href="<%=request.getContextPath()%>/event/eventInfo.jsp" class="btn-filled">返回</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>



	<%@ include file="/parts/footer.text"%>

	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='./js/index.js'></script>
</body>

</html>