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
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/details.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/event/css/media.css'>
</head>

<body>

	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>


	<div class="latest-news">
		<div class="container">
			<img src="${pageContext.request.contextPath}/site/icon/dot.svg" alt="" class="dot">
			<h1>Grouping</h1>
			<h3>揪 團</h3>
		</div>
	</div>

	<!-- search start -->
	<section class="search">
		<div class="container">
			<div class="row">
				<div class="filter">
					<div class="category">

						<form name="form1" method="post"
							action="<%=request.getContextPath()%>/event/EventServlet">
							<label for="category">Category:</label> <select name="category"
								id="categorySelect" onchange="form1.submit()">
								<option value="">請選擇</option>
								<option value="0">全部</option>
								<option value="1">逛展</option>
								<option value="2">讀書會</option>
								<option value="3">其他活動</option>
							</select> <input type="hidden" name="action" value="cateselect">
						</form>
					</div>

					<div class="date">

						<form name="form2" method="post"
							action="<%=request.getContextPath()%>/event/EventServlet">
							<label for="date">日期:</label> <select name="date" id="dateSelect"
								onchange="form2.submit()">
								<option value="">請選擇</option>
								<option value="1">最新活動</option>
								<option value="2">即將結束</option>								
							</select> <input type="hidden" name="action" value="dateselect">
						</form>
					</div>
				</div>
				<div class="search">
					<div class="search-bar">
						<input type="search" placeholder="How to...">
						<button type="button">
							<img src="./icon/search.svg" alt="">
						</button>
					</div>
				</div>
			</div>
			<hr />
		</div>
	</section>
	<!-- search end -->


	<section class="info">
		<div class="container">
			<div class="row">
				<a href="eventAdd.jsp" role="button" class="btn-create btn-filled">我要發起活動</a>
				<article>
					<div class="activities">


						<jsp:useBean id="eventSvc" scope="page"
							class="com.event.model.EventService" />
						<jsp:useBean id="partiSvc" scope="page"
							class="com.partiinf.model.PartiinfService" />
						<c:forEach var="eventVO"
							items="${eventlist != null ? eventlist : eventSvc.all}">
							<c:if test="${partiSvc.findByJoin(eventVO.eventid) < eventVO.capacity}">

							<form name="form3" method="post"
								action="<%=request.getContextPath()%>/event/EventServlet"
								class="activity" class="form3">
								<!-- 															<a href="" role="button" class="activity" onchange="form3.submit()"> -->
								<figure class="cover">
									<img
										src="${pageContext.request.contextPath}/groupImg/${eventVO.eventcateid}.jpg">
								</figure>

								<div class="content">
									<div class="date">
										<div>
											<fmt:formatDate pattern="dd" value="${eventVO.eventstart}" />
										</div>
										<div>
											<fmt:formatDate pattern="MMM" value="${eventVO.eventstart}" />
										</div>
									</div>
									<div class="details">
										<h4>
											<fmt:formatDate pattern="yyyy-MM-dd E"
												value="${eventVO.eventstart}" />
										</h4>
										<h2>${eventVO.eventname}</h2>
										<h3>人數: ${partiSvc.findByJoin(eventVO.eventid)}/${eventVO.capacity}</h3>
										<jsp:useBean id="memSvc" class="com.member.model.MemberService"></jsp:useBean>
										<h3>發布人: ${memSvc.getOneMember(eventVO.memberid).name}</h3>
										<h4>
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${eventVO.eventstart}" />
											至
											<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${eventVO.eventend}" />
										</h4>
									</div>
								</div>
								<div class="tags">
									<h5>#${eventVO.eventname}</h5>
									<div>
<!-- 										<h5> -->
<!-- 											<i class="far fa-eye"></i>7417 -->
<!-- 										</h5> -->
										<h5>
											<i class="fas fa-user-check"></i>${partiSvc.findByJoin(eventVO.eventid)}
										</h5>
									</div>
								</div>
								<!-- 																</a> -->
								<input type="hidden" name="eventid" value="${eventVO.eventid}">
								<input type="hidden" name="action" value="getOne_event">
								<button value="提交" class="submit_btn" style="display: none;"></button>

							</form>
							</c:if>
						</c:forEach>

					</div>
<!-- 					<button type="button" class="btn-more btn-text">Read More</button> -->
				</article>
			</div>
		</div>
	</section>

	<%@ include file="/parts/footer.text"%>

	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='./js/eventInfo.js'></script>
</body>

</html>