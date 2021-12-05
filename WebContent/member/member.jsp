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
<title>Reader's Garden</title>
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

form {
	width: 75%;
	height: 500px;
	padding: 10px;
	box-shadow: 5px 5px 5px rgba(0, 0, 0, .3);
	border-radius: 10px;
	z-index: 40;
	overflow: auto;
	margin: 100px auto 0;
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
	margin: 0px auto;
	font-size: 20px;
	border: none;
	margin-top: 10px;
}

.title {
	font-size: 30px;
	color: #184c75;
	margin-left: 75px;
}

.input {
	width: 250px;
	height: 38px;
	margin: 5px 0 5px 0;
	padding: 10px;
	border: 1px,black;
	border-radius: 25px;
}

.submit {
	width: 20%;
	height: 20%;
	margin: 350px 0 5px 0;
	padding: 10px;
	border: none;
}

div.group {
	width: 300px;
	margin-left: 70px;
}

div.colum {
	padding: 5px;
}

input#able.button{
width:100px;
margin-top:10px;

background-color:#b9b9ff;
}

font{
	font-size: 10px;
	
    display:inline;
	color:red;
	
}
</style>
</head>
<body>

	<%@ include file="/parts/header.text"%>


	<!--login會員登入-->
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/update/UpdateServlet">




		<div id="" class="main">





			<div class="info " id="info">



				<h1 class="title">會員資料</h1>

				<c:forEach var="memberVO" items="${memberSvc.all}">
					<option value="${memberVO.number}">${memberVO.number}
				</c:forEach>




				<jsp:useBean id="memVO" scope="page"
					class="com.member.model.MemberVO" />
				<div class="colum ">
					<div class="group">
						<label class="label"style="display: inline;">姓名</label></br>
						<c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["name"]}</font>

							</c:if> <input id="group" type="text"
							class="input" name="mname" value="${memberVO.name}" autocomplete="off">
					</div>
					<div class="group">
						<label class="label">信箱</label></br><c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["email"]}</font>

							</c:if> <input id="group" type="text"
							class="input" name="memail" value="${memberVO.email}" autocomplete="off">
					</div>
					<div class="group">
						<label class="label">手機</label></br><c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["phoneNumber"]}</font>

							</c:if> <input id="group" type="text"
							class="input" name="mphoneNumber" value="${memberVO.phoneNumber}" autocomplete="off">
					</div>
					<div class="group">
						<label class="label">地址</label> </br><c:if test="${not empty errorMsgs}">
								<font class="error_msgs" style="display: inline;">${errorMsgs["address"]}</font>

							</c:if>
						<input id="group" type="text" class="input" name="maddress"
							value="${memberVO.address}" autocomplete="off">
					</div>
					<div class="group" id="ID">
						<label class="label">身份證字號</label> </br>
						<input id="group" type="text" class="input" name="mID"
							value="${memberVO.ID}" readonly="readonly">
							
							
				
					</div>



					<input type="hidden" name="number" value="${memberVO.number}">

				</div>
			</div>
			
			

			<div class="submit">
			
			<a href="#" class="info_edit edit_active" id="info_edit">資料修改</a>

			<a href="#" class="info_edit" id="password_edit">密碼修改</a>

				<div class="btn_div">
					<a
						href="${pageContext.request.contextPath}/update/UpdateServlet?action=getOne_For_Update&number=1"
						id="reset_action">重新填寫</a> <input type="hidden" name="action"
						value="update"> <input type="hidden" name="number"
						value=""> <input type="submit" value="送出修改">
				</div>

			</div>

			<div id="password_zone" style="display: none;">
			<h1 class="title">會員資料</h1>
				<div class="group">
					<label class="label">輸入舊密碼</label> <input id="group" id="password1"
						type="password" class="input" data-type="password"
						name="mpassword" value="${memberVO.password}">
				</div>

				<div class="group">
					<label class="label">輸入新密碼</label> <input id="group" id="password1"
						type="password" class="input" data-type="password"
						name="mpassword" value="${memberVO.password}"
						placeholder="請使用英文及數字,6到16個字">
				</div>

				<div class="group">
					<label for="pass" class="label">確認密碼</label> <input id="password2"
						type="password" class="input" data-type="password"
						name="mpassword2"
						value="<%=(memberVO == null) ? "" : memberVO.getPassword()%>"
						placeholder="再輸入一次密碼">
				</div>

			</div>

		</div>
	</form>


	<%@ include file="/parts/footer.text"%>

	<script>
	var locate = `${memberLocate}`;
	$(function(){
		
	  	if(locate == "password"){
	  		$('#info_edit, #password_edit').toggleClass('edit_active');
	  		$('#info').hide();
	  		$('#password_zone').show();
	  	}
	  	
	  $('#info_edit').on('click', function(){
	    if($(this).hasClass('edit_active')){
	      return;
	    } else{
	      $(this).toggleClass('edit_active');
	      $('#password_edit').toggleClass('edit_active');
	      $('#info').show();
	      $('#password_zone').hide();
	    }

	  })

	  $('#password_edit').on('click', function(){
	    if($(this).hasClass('edit_active')){
	      return;
	    } else{
	      $(this).toggleClass('edit_active');
	      $('#info_edit').toggleClass('edit_active');
	      $('#password_zone').show();
	      $('#info').hide();
	    }
	  })
	  
	  $('#confirm_edit').on('click', function(){
		  $('#info_btn').click();
	  })
	  
	})
	  
</script>


</body>
</html>