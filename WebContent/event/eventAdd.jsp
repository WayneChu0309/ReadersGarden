<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<section class="create">
		<div class="container">
			<div class="row">
				<hr />
				<div class="form">
					<div class="content">
						<h1>�ڭn�o�_���ʡG��T��g</h1>

						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~�G</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<jsp:useBean id="event" scope="request"
							class="com.event.model.Event" />
						<%-- 		<div>${venVO.company == null}</div> --%>

						<form method="post"
							action="<%=request.getContextPath()%>/event/EventServlet?action=insert">
							<div class="form-control">
								<label for="">���ʦW��</label> <input type="text" name=eventname
									value="${event==null ?" " : event.eventname}" />
							</div>

							<div class="form-control">
								<label for="">��������</label> <select name="eventcateid"
									id="dateSelect">
									<option value="1">�}�i</option>
									<option value="2">Ū�ѷ|</option>
									<option value="3">��L����</option>
								</select>
							</div>

							<div class="form-control">
								<label for="">�H�ƻݨD</label> <input type="number" name=capacity
									min="2" value="${capacity==null ?" " : event.capacity}"/>
							</div>

							<div class="form-control">
								<label for="">���ʮɶ�</label> <input type="datetime-local"
									name="eventstart" /><span>��</span> <input type="datetime-local"
									name="eventend" />
							</div>

							<div class="form-control">
								<label for="">���ʳ��W�ɶ�</label> <input type="datetime-local"
									name="eventappstart" /><span>��</span> <input
									type="datetime-local" name="eventappend" />
							</div>

							<div class="form-control">
								<label for="">���ʤ��e</label>
								<textarea name="eventdescription" id="" cols="30" rows="10"
									value="${eventdescription==null ?" " :event.eventdescription}"></textarea>
							</div>
							<div class="btns">
								<a href="eventInfo.jsp" class="btn-filled btn_disabled">��^</a>
								<button type="submit" class="btn-filled">�ڭn�o�_����</button>
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