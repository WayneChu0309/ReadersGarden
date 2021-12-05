<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>login</title>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/login/css/login.css'>
</head>

<body>

	<div class="login-wrap">

		<div class="login-html">
		<h2 id="title_vendor">一般會員</h2>
		<a id="member_zone" href="<%=request.getContextPath()%>/login/vendorLogin.jsp">廠商專區</a>
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">登入</label> <input id="tab-2" type="radio"
				name="tab" class="sign-up"><label for="tab-2" class="tab">註冊</label>

			<div class="login-form">

				<div class="sign-in-htm">
					<form class="login" ACTION="#" method="post" id="form1">
						<div class="group">
							<label for="user" class="label">信箱</label> <input
								id="email_input" type="text" name="email" class="input">
						</div>
						<div class="group">
							<label for="pass" class="label">密碼</label> <input
								id="password_input" type="password" name="password"
								class="input">
						</div>

						<!-- 	<div class="group">
					<input id="check" type="checkbox" class="check" checked>
					<label for="check"><span class="icon"></span> 保持登入</label>
				</div>  -->
						<div class="group">
							<input type="button" class="button" value="登入" id="login">
						</div>

						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="<%=request.getContextPath()%>/login/memberForgetPassword.jsp">忘記密碼?</a>
						</div>
					</form>

				</div>

				<jsp:useBean id="memVO" scope="page"
					class="com.member.model.MemberVO" />
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/register/RegisterServlet">

					<div class="sign-up-htm" id="singup">

						<div class="group">


							<label for="user" class="label" style="display: inline;">姓名</label>
							<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["name"]}</font>

							</c:if>
							<input id="mname" type="text" class="input" name="mname"
								value="<%=(memberVO == null) ? "" : memberVO.getName()%>"
								placeholder="請使用中文, 20個字以內">
						</div>
						<div class="group">

							<label for="pass" class="label" style="display: inline;">信箱</label>
							<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["email"]}</font>
								<br>
							</c:if>
							<input id="memail" type="email" class="input" name="memail"
								value="<%=(memberVO == null) ? "" : memberVO.getEmail()%>">
						</div>
						<div class="group">

							<label for="pass" class="label" style="display: inline;">手機</label>
							<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["phoneNumber"]}</font>
								<br>
							</c:if>
							<input id="mphoneNumber" type="text" class="input"
								name="mphoneNumber"
								value="<%=(memberVO == null) ? "" : memberVO.getPhoneNumber()%>">
						</div>
						<div class="group">

							<label for="pass" class="label" style="display: inline;">地址</label>
							<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["address"]}</font>
								<br>
							</c:if>
							<input id="maddress" type="text" class="input" name="maddress"
								value="<%=(memberVO == null) ? "" : memberVO.getAddress()%>">
						</div>
						<div class="group">

							<label for="pass" class="label" style="display: inline;">身份證字號</label>
							<c:if test="${not empty errorMsgs}">
								<font class="id" style="display: inline;">${errorMsgs["ID"]}</font>
								<br>
							</c:if>
							<input id="mID" type="text" class="input" name="mID"
								value="<%=(memberVO == null) ? "" : memberVO.getID()%>">
						</div>
						<div class="group">

							<label for="pass" class="label" style="display: inline;">密碼</label>
							<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["password"]}</font>
								<br>
							</c:if>
							<input id="mpassword" id="password1" type="password"
								class="input" data-type="password" name="mpassword"
								value="<%=(memberVO == null) ? "" : memberVO.getPassword()%>"
								placeholder="請使用英文及數字,6到16個字">
						</div>
						<div class="group">
							<label for="pass" class="label">確認密碼</label> <input
								id="password2" type="password" class="input"
								data-type="password" name="mpassword2"
								value="<%=(memberVO == null) ? "" : memberVO.getPassword()%>"
								placeholder="再輸入一次密碼">
						</div>


						<div class="group" name="submit">
							<input id="register1" type="hidden" name="action"
								value="register"> <input id="register2" type="submit"
								class="button" name="submit" value="確認註冊">

						</div>
						<div class="hr"></div>

					</div>
				</FORM>
			</div>

		</div>
</div>


		<script src="<%=request.getContextPath()%>/jquery/jquery-3.6.0.min.js"></script>
		<script src="<%=request.getContextPath()%>/login/js/login.js"></script>

		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
		<!-- 若需相容 IE11，要加載 Promise Polyfill-->
		<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
		<script
			src="${pageContext.request.contextPath}/jquery/jquery.twzipcode.min.js"></script>
		<script>

   			var home = "<%=request.getContextPath()%>/home/home.jsp";

		
			var register = `${register}`;
			if (register == "fail") {
				alert("註冊失敗");
				$('#signup').show();}
				
			
		
		</script>
</body>
</html>