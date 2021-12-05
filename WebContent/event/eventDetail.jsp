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
<title>�o�_����</title>
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
			<h3>�� ��</h3>
		</div>
	</div>


	<section class="details">
		<div class="container">
			<div class="row">
				<hr />
				<div class="activity">
					<h1>���ʸ�T</h1>
					<div class="content">
						<table>
							<tr>
								<td>���ʦW��</td>
								<td>${event.eventname}</td>
							</tr>
							<tr>
								<td>��������</td>
								<td>�}�i</td>
							</tr>
							<tr>
							<jsp:useBean id="partiSvc" scope="page"
							class="com.partiinf.model.PartiinfService" />
								<td>�H�ƻݨD</td>
								<td>${partiSvc.findByJoin(event.eventid)}/${event.capacity}</td>
							</tr>
							<tr>
								<td>���ʮɶ�</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${event.eventstart}" /> �� <fmt:formatDate
										pattern="yyyy-MM-dd HH:mm" value="${event.eventend}" /></td>
							</tr>
							<tr>
								<td>���ʳ��W�ɶ�</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${event.eventappstart}" /> �� <fmt:formatDate
										pattern="yyyy-MM-dd HH:mm" value="${event.eventappend}" /></td>
							</tr>
							<tr>
								<td>���ʤ��e</td>
								<td>${event.eventdescription}</td>
							</tr>
						</table>
						<div class="btns">
							<a href="<%=request.getContextPath()%>/event/eventInfo.jsp" class="btn-filled">��^</a>

							<form name="form" method="post"
								action="<%=request.getContextPath()%>/partiinf/partiServlet">
								<!-- 								<a href="eventJoin.jsp" class="btn-filled">�ڭn�ѥ[</a>  -->
								<input type="hidden" name="eventid" value="${event.eventid}">
								<input type="hidden" name="action" value="getOne_join">
								<button value="����" class="btn-filled" style="display: none;"></button>
								<button type="submit" class="btn-filled">�ڭn�ѥ[</button>				
								
							</form>
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