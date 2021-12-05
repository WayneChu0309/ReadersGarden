<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員中心 - Reader's Garden</title>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/member/css/membercenter.css'>
<style>
* {
	margin: 0;
	padding: 0;
	list-style: none;
	text-decoration: none;
	box-sizing: border-box;
	/*outline: 1px solid slategrey;*/
}

body {
	background-image: url("./image/bg.png");
	background-size: cover;
	background-position: center center;
}

div.all {
	width: 60%;
	height: 660px;
	padding: 10px;
	box-shadow: 5px 5px 5px rgba(0, 0, 0, .3);
	border-radius: 10px;
	z-index: 40;
	overflow: auto;
	margin: 100px 20px 0 350px;

	/* display: none; */
}

.main {
	background-color: #e6f4eb;
	height: 100%;
	flex: 0 1 auto;
	border-radius: 5%;
	border-left: 2px solid #D2D2D2;
	display: flex; /*center*/
}

div.info {
	    width: 50%;
    height: 100
px
;
    margin: 0
px
 auto;
    font-size: 15px;
    border: none;
    margin-top: 10
px
;
    position: absolute;
}

.title {
	font-size: 30px;
	color: #184c75;
	margin-left: 75px;
}

.input {
	width: 330px;
	height: 38px;
	margin: 5px 0 5px 0;
	padding: 10px;
	border: 1px, black;
	border-radius: 25px;
}

.submit {
	width: 20%;
	height: 20%;
	margin: 150px 0 5px 0;
	padding: 10px;
	border: none;
}

div.group {
	width: 300px;
	margin-left: 70px;
}

div.colum {
	    margin-top: 35px;
    margin-left: 120px;
    padding: 10px;
}

input#able.button {
	width: 100px;
	margin-top: 10px;
	background-color: #b9b9ff;
}

font {
	font-size: 10px;
	display: inline;
	color: red;
}

.list {
	border-radius: 25px;
	margin-right: 10px;
	padding: 3 px;
	float: right;
	background-color: #b7d6c9;
	display: -webkit-inline-box;
	padding-right: 20 px;
}

h1 {
	color: #6d7dcb;
	text-align: center;
	font-size: 20px;
}

div.btn_div{

margin-left:380px}

div#password_zone{
margin-top: 90px;
margin-left: 130px;
}

div#edit2{
margin-left:393px;
}


element.style {
    color: #7474b5;
}

input[type=submit]{background-color: #a9e8a8;
    border-radius: 12px;
    width: 100px;}

</style>

</head>
<body>
	<%@ include file="/parts/header.text"%>
	<%@ include file="/member/membercenter.text"%>



<div class="all">


		<div id="" class="main">



	
			<div class="info" id="info">


				<h1>會員資料</h1>
				<div class="list">
					<ul class="list">
						<li><a href="#" class="info_edit edit_active" id="info_edit">資料修改</a><span>/</span>
						</li>
						

						<li><a href="#" class="info_edit" id="password_edit">密碼修改</a>
						</li>
					</ul>
				</div>


			</div>
			<jsp:useBean id="memVO" scope="page"
				class="com.member.model.MemberVO" />
<FORM name="form1" METHOD="post"
		ACTION="<%=request.getContextPath()%>/update/UpdateServlet">
			<div class="colum" id="colum">
			<!--  <form method="post" action="${pageContext.request.contextPath}/update/UpdateServlet"> -->	

					<div class="group">
						<label class="label" style="display: inline;">姓名</label><br>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["name"]}</font>

						</c:if>
						<input id="group" type="text" class="input" name="mname"
							value="${memberVO != null? memberVO.name : member.name}"
							autocomplete="off">
					</div>
					<div class="group">
						<label class="label">信箱</label><br>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["email"]}</font>

						</c:if>
						<input id="group" type="text" class="input" name="memail"
							value="${memberVO != null? memberVO.email : member.email}"
							autocomplete="off">
					</div>
					<div class="group">
						<label class="label">手機</label><br>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["phoneNumber"]}</font>

						</c:if>
						<input id="group" type="text" class="input" name="mphoneNumber"
							value="${memberVO != null? memberVO.phoneNumber : member.phoneNumber}"
							autocomplete="off">
					</div>
					<div class="group">
						<label class="label">地址</label> <br>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["address"]}</font>

						</c:if>
						<input id="group" type="text" class="input" name="maddress"
							value="${memberVO != null? memberVO.address : member.address}"
							autocomplete="off">
					</div>
					<div class="group" id="ID">
						<label class="label">身份證字號</label> <br> <input id="group"
							type="text" class="input" name="mID" value="${member.ID}"
							readonly="readonly">
					</div>
						<div class="group" id="password">


						<label class="label">密碼</label><br>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["password"]}</font>

						</c:if>
						<input type="password" name="mpassword" class="input"
							placeholder="輸入密碼以確認修改" value=""><input type="hidden" name="number"
							value="${member.number}">


					</div>
					
					
					
					<div class="btn_div">

				 <%-- 	<a href="${pageContext.request.contextPath}/update/UpdateServlet?action=getOne_For_Update"
							id="reset_action">重新填寫</a> --%>
							
							<input type="hidden" name="action"
							value="update"> <input type="submit" value="確認修改" id="info_submit">
							
					</div>
					</div>
					</FORM>	
					
					
					


					
					
					
					
				

				



					
			












			

			<div id="password_zone" style="display: none;">
	<FORM name="form2" METHOD="post"
		ACTION="<%=request.getContextPath()%>/update/UpdateServlet">
					<div class="group">
						<label class="label">輸入舊密碼</label>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["passwordOld"]}</font>
						</c:if>

						<input id="group" id="password1" type="password" class="input"
							data-type="password" name="mpassword" value="">
					</div>

					<div class="group">
						<label class="label">輸入新密碼</label>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["passwordNew"]}</font>
						</c:if>

						<input id="group" id="password1" type="password" class="input"
							data-type="password" name="mpasswordNew" value=""
							placeholder="請使用英文及數字,6到16個字">
					</div>

					<div class="group">
						<label for="pass" class="label">確認密碼</label>
						<c:if test="${not empty errorMsgs}">
							<font class="error_msgs" style="display: inline;">${errorMsgs["passwordConfirm"]}</font>
						</c:if>

						<input id="password2" type="password" class="input"
							data-type="password" name="mpasswordConfirm" value=""
							placeholder="再輸入一次密碼"> <input type="hidden" name="number"
							value="${member.number}">
					</div>
					<div id=edit2>
					<input type="hidden" name="action" value="update_Password">
					<input type="submit" value="確認修改" id="update_Password">
					</div>
					</FORM>
			</div>
			
			</div>
	</div>

		

	<script>
	var update2 = `${update2}`;
	var update = `${update}`;
	var locate = `${memberLocate}`;
		$("div.content_row div.content_left div").eq(0).find("a").addClass(
				"now_choice");
		
		$(function() {

			if (locate == "password") {
				$('#info_edit, #password_edit').toggleClass('edit_active');
				$('#colum').hide();
				$('#password_zone').show();
			}

			$('#info_edit').on('click', function() {
				if ($(this).hasClass('edit_active')) {
					return;
				} else {
					$(this).toggleClass('edit_active');
					$('#password_edit').toggleClass('edit_active');
					$('#colum').show();
					$('#password_zone').hide();
				}

			})

			$('#password_edit').on('click', function() {
				if ($(this).hasClass('edit_active')) {
					return;
				} else {
					$(this).toggleClass('edit_active');
					$('#info_edit').toggleClass('edit_active');
					$('#password_zone').show();
					$('#colum').hide();
				}
			})

			$('#confirm_edit').on('click', function() {
				$('#info_btn').click();
			})
			
			if (update== "success") {
					alert("更新成功");
				
			}
			
			if (update2== "success2") {
				alert("更新成功");
			
		}
		})
		
	
	</script>





</body>
</html>