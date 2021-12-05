<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  <%-- 自己的css --%>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> <%-- 自己的css --%>

<style>

.border{
	display: inline-block;
	background-color: white;
	opacity: 0.8;
	height: 650px;
	width: 450px;
	margin-top: 50px;
	margin-left: 150px;
    margin-bottom: 100px;
    margin-right:30px;
    padding-top: 20px;
    padding-left: 20px;
    border-radius: 15px;
	}
.border img{
	max-width: 100%;
}
.card-body{
	text-align: center;
	}
h1 {
  font-size: 40px;
  text-align: center;
}

h5{
  font-size: 20px;
  margin-top: 10px;
}

.card{
	height: 250px;
	width: 400px;
	display: flex;
}

.card img{
	max-width: 100%;
	max-height: 100%;
	margin: 0 auto;
}
.outer{
	background-color: rgb(255, 255, 255, 0.3);
	padding: 3rem;
	padding-left: 30px;
	height: auto;
	width: 1400px;
	margin-left: 80px;
	border-radius: 60px;
}
</style>

</head>
<body>
	
<%@ include file="/parts/header.text" %>
<%@ include file="/parts/slide.text" %>

  <div class="latest-news"  id="target-page">
    <div class="container">
      <img src="./icon/dot.svg" alt="" class="dot">
      <h1>Garden's Expo</h1>
      <h3>花園展覽</h3>
    </div>
  </div>
  
  <jsp:useBean id="vactSvc" scope="page" class="com.vendoract.model.VendorActService"/>
  <jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService"/>
<div class = "outer">
<h1>購票區</h1>

<c:forEach var="vactVO" items="${vactSvc.all}">
  <div class="border">
	 <div class="card"><img src="${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=${vactVO.vactid}"></div> 
		<div class="card-body" text-align="center">
		<h5 class="title">${vactVO.name}</h5></br>

		<div class="location"><img src="http://imgs.utiki.com.tw/Data/UTIKI_UDN_RWD//Images/icon-date@2x.png">

		<div class="content">${vactVO.tkstart}~${vactVO.tkend}(銷售日期)</div></br>
		</div>
   
			<div class="location"><img src="http://imgs.utiki.com.tw/Data/UTIKI_UDN_RWD//Images/icon-loc@2x.png">
			<div class="content">活動開始時間: ${vactVO.tkstart}</div>
     		<div class="content">活動類型: ${actSvc.findOneActivity(vactVO.actid).acttype}</div>
			</div>

			<div class="price">票價: ${vactVO.price}</div>
    </br>
			
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ticket/VendorTicketServlet" style="margin-bottom: 0px;">
		 <input type="submit" value="查看更多">
		 <input type="hidden" name="vactid"  value="${vactVO.vactid}">
		 <input type="hidden" name="action" value="view">
	</FORM>			

	</div>
</div>
</c:forEach>
</div>
  <script src='./jquery/jquery-3.6.0.min.js'></script>
  <script src='./js/index.js'></script>
  
  
  
  

  
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
</body>
</html>