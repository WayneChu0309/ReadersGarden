<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.event.model.*"%>

<%
  Event event = (Event) request.getAttribute("event"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

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
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/details.css'>
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

	<section class="create">
		<div class="container">
			<div class="row">
				<hr />
				<div class="form">
					<div class="content">
						<h1>修改資訊填寫</h1>

						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤：</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<jsp:useBean id="eventSvc" scope="request"
							class="com.event.model.Event" />

						<form method="post"
							action="<%=request.getContextPath()%>/event/EventServlet?action=update">
							<div class="form-control">
							<input type="text" name="eventid" value="${event.eventid}" style="display:none">
								<label for="">活動名稱</label> <input type="text" name=eventname
									value="${event.eventname}" />
							</div>

							<div class="form-control">
								<label for="">活動類型</label> <select name="eventcateid"
									id="dateSelect">
									<option value="1">逛展</option>
									<option value="2">讀書會</option>
									<option value="3">其他活動</option>
								</select>
							</div>

							<div class="form-control">
								<label for="">人數需求</label> <input type="number" name=capacity
									min="2" value="${event.capacity}"/>
							</div>

							<div class="form-control">
								<label for="">活動時間</label> <input type="datetime-local"
									name="eventstart" value="${eventstart}"/><span>至</span> <input type="datetime-local"
									name="eventend" value="${eventend}" />
							</div>

							<div class="form-control">
								<label for="">活動報名時間</label> <input type="datetime-local"
									name="eventappstart" value="${eventappstart}"/><span>至</span> <input
									type="datetime-local" name="eventappend" value="${eventappend}"/>
							</div>

							<div class="form-control">
								<label for="">活動內容</label>
								<input name="eventdescription" id="" cols="30" rows="10"
									value="${event.eventdescription}"/>
							</div>
							<div class="btns">
								<a href="${pageContext.request.contextPath}/eventlist/EventlistServlet?action=mylist" class="btn-filled btn_disabled">返回</a>
								<button type="submit" class="btn-filled">修改</button>
							</div>
						</form>
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