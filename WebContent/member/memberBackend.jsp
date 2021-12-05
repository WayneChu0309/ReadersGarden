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
	height: 550px;
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
	width: 250px;
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
    padding: 90px;
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
	border-radius:15px;
	font-weight:bold;
	margin-right: 10px;
	padding: 3 px;
	float: right;
	background-color: #b7d6c9;
	display: -webkit-inline-box;
	padding-right: 20 px;
}

ul{display:block;	
    line-height:40px;
    }
 li{color:#3652c9;}

h1 {
    padding-top: 10
px
;
	color: #53913f;
	text-align: center;
	font-size: 30px;
	    margin-top: 30
px
;
}

input{color:black;

background-color:white;}


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



</style>
</head>
<body>
	<%@ include file="/parts/header.text"%>
	<%@ include file="/member/membercenter.text"%>
<div class="all">
	




		<div id="" class="main">




			<div class="info" id="info">


				<h1>會員資料查詢</h1>
				<div class="list">
					<ul class="list">
						<li><a href='<%=request.getContextPath()%>/member/listAllMember.jsp' class="listmember" id="listmember">列出所有會員</a>
						</li>
						

						
					</ul>
				</div>


			</div>
			<jsp:useBean id="memVO" scope="page"
				class="com.member.model.MemberVO" />

			<div class="colum" id="colum">
			<!--  <form method="post" action="${pageContext.request.contextPath}/update/UpdateServlet"> -->	
<c:if test="${not empty errorMsgs}">
	
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>

  
  
  <li>
    <FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/MemberServlet">
        <b>輸入會員編號 :</b>
        <input type="text" name="number">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </form>
  </li>

  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
   
  <li>
     <FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/MemberServlet">
       <b>選擇會員編號:</b>
       <select size="1" name="number">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.number}">${memberVO.number}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
  </form>
  </li>
  
  <li>
      <FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/member/MemberServlet">
       <b>選擇會員姓名:</b>
       <select size="1" name="number">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.number}">${memberVO.name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="button" value="送出">
    </form>
  </li>
</ul>
</div>
					
					


					<input type="hidden" name="number" value="${member.number}">
					
	</div>				
				
					
					
</div>
				



					
			












			
	

		





</body>
</html>