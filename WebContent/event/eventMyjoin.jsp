<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



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
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/event/css/common.css'>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/event/css/index.css'>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/event/css/mylist.css'>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/event/css/media.css'>
</head>

<body>

	<%@ include file="/parts/header.text"%>
	<%@ include file="/member/membercenter.text" %>


	<hr />

	<section class="mylist">

		<div class="content_row1">
			<div class="content_right">
				<div class="container_el">
					<div class="info_zone">
						<h5>參加揪團紀錄</h5>
						<table>
							<tr>

								<th>活動名稱</th>
								<th>活動編號</th>
								<th>召集人</th>
								<th>人數限制</th>
								<th>活動時間</th>
								<th>活動內容</th>
<!-- 								<th>活動狀態</th> -->

							</tr>

							<jsp:useBean id="EventSvc" scope="page"
								class="com.event.model.EventService" />
							<c:forEach var="event" items="${myjoin}">

								<tr>
									<td>${event.eventname}</td>
									<td>${event.eventcateid}</td>
									<td>${event.memberid}</td>
									<td>${event.capacity}</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${event.eventstart}" />至<br> <fmt:formatDate
											pattern="yyyy-MM-dd HH:mm" value="${event.eventend}" /></td>
									<td>${event.eventdescription}</td>
<%-- 									<td>${event.eventstatus}</td> --%>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<!--vendor_info end tag-->
		</div>

	</section>

	<script>
		$("div.content_row div.content_left div").eq(4).find("a").addClass("now_choice");
	</script>

	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='./js/index.js'></script>
</body>

</html>