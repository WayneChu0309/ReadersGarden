<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>廠商專區</title>
</head>
<style>
body {
	margin: 0;
	color: #c8f7c8;
	background: #c8c8c8;
	font: 600 16px/18px 'Open Sans', sans-serif;
}

*, :after, :before {
	box-sizing: border-box
}

.clearfix:after, .clearfix:before {
	content: '';
	display: table
}

.clearfix:after {
	clear: both;
	display: block
}

a {
	color: inherit;
	text-decoration: none
}

.login-wrap {
	width: 100%;
	margin: auto;
	max-width: 525px;
	min-height: 1200px;
	position: relative;
	background:
		url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg)
		no-repeat center;
	box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
	background-size: cover;
	background-position: center center;
}

.login-html {
	width: 100%;
	height: 100%;
	position: absolute;
	padding: 40px 70px 50px 70px;
	background: rgb(88 133 124/ 90%)
}

.login-html .sign-in-htm, .login-html .sign-up-htm {
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	position: absolute;
}

.login-html .sign-in, .login-html .sign-up, .login-form .group .check {
	display: none;
}

.login-html .tab {
	font-size: 22px;
	margin-right: 15px;
	padding-bottom: 5px;
	margin: 0 15px 10px 0;
	display: inline-block;
	border-bottom: 2px solid transparent;
}

.login-html .sign-in:checked+.tab, .login-html .sign-up:checked+.tab {
	color: #fff;
	border-color: #1161ee;
}

.login-form {
	min-height: 345px;
	position: relative;
	perspective: 1000px;
	transform-style: preserve-3d;
}

.login-form .group {
	margin-bottom: 15px;
}

.login-form .group .label, .login-form .group .input, .login-form .group .button
	{
	width: 100%;
	color: #fff;
	display: block;
}

.login-form .group .input, .login-form .group .button {
	border: none;
	padding: 15px 20px;
	border-radius: 25px;
	background: rgba(255, 255, 255, .1);
}

.login-form .group .label {
	color: lightgray;
	font-size: 12px;
}

.login-form .group .button {
	background: #1161ee;
}

.login-form .group label .icon {
	width: 15px;
	height: 15px;
	border-radius: 2px;
	position: relative;
	display: inline-block;
	background: rgba(255, 255, 255, .1);
}

.login-form .group .check:checked+label .icon:before {
	transform: scale(1) rotate(45deg);
}

.login-form .group .check:checked+label .icon:after {
	transform: scale(1) rotate(-45deg);
}

.login-html .sign-in:checked+.tab+.sign-up+.tab+.login-form .sign-in-htm
	{
	transform: rotate(0);
}

.login-html .sign-up:checked+.tab+.login-form .sign-up-htm {
	transform: rotate(0);
}

.hr {
	height: 2px;
	margin: 60px 0 50px 0;
	background: rgba(255, 255, 255, .2);
}

.foot-lnk {
	text-align: center;
}

.errorMsgs {
	font-size: 12px;
	font-weight: 500;
	color: pink;
	margin-left: 50px;
	letter-spacing: 1px;
}

#title_vendor {
	color: white;
	text-align: center;
	font-weight: 800;
	font-size: 34px;
	letter-spacing: 3px;
	margin-bottom: 3.5rem;
}

#twzipcode {
	margin: 5px 0 5px 0;
}

.city, .town {
	background-color: rgba(210, 210, 210, 0.5);
	border-radius: 3px;
	margin-right: 3px;
}

#member_zone {
	float: right;
}
</style>
<body>

	<div class="login-wrap">

		<div class="login-html">
			<h2 id="title_vendor">一般會員</h2>
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">重設密碼</label>

			<div class="login-form">

				<div class="sign-in-htm">
					<form class="login"
						ACTION="<%=request.getContextPath()%>/login/memberForgetPassword"
						method="post" id="form1">
						<div class="group">
							<label for="user" class="label">新密碼</label>
							<c:if test="${errorMsgs != null}">
								<span class="errorMsgs">${errorMsgs.password}</span>
							</c:if>
							<input id="email_input" type="password" name="password" class="input">
						</div>

						<div class="group">
							<label for="user" class="label">確認密碼</label>
							<c:if test="${errorMsgs != null}">
								<span class="errorMsgs">${errorMsgs.passwordConfirm}</span>
							</c:if>
							<input id="email_input" type="password" name="passwordConfirm" class="input">
						</div>

						<div class="group">
							<input type="hidden" name="number" value="${number}">
							<input type="hidden" name="action" value="reset">
							<input type="submit" class="button" value="送出" id="login">
						</div>

						<div class="hr"></div>
						<div class="foot-lnk">
							<a href="<%=request.getContextPath()%>/login/login.jsp">返回登入/註冊頁面</a>
						</div>
					</form>
				</div>

			</div>
		</div>

	</div>


	<script src="<%=request.getContextPath()%>/jquery/jquery-3.6.0.min.js"></script>
	<script>
   	var home = "<%=request.getContextPath()%>
		/home/home.jsp";
	</script>
	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery.twzipcode.min.js"></script>

</body>
</html>