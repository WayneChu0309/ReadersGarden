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


.title{
    font-size: 40px;
    text-align: center;
    margin-top: 100px;
    margin-bottom: 100px;
}


.btn{
  text-align: center;
  display: block;
  margin: 0 auto;
}

.btn_div{
  width: 200px;
  height: 200px;
  margin: 0 auto;
}

.backgroundouter{
	background-color: rgb(255, 255, 255, 0.3);
	width: 800px;
	height: 500px;
	padding-top: 30px;
	padding-bottom: 30px;
	margin-left: 370px;
	margin-top: 30px;
	border-radius: 20px;
}

</style>



</head>
<body>
	
<%@ include file="/parts/header.text" %>
<%@ include file="/parts/slide.text" %>

  <div class="latest-news"  id="target-page">
    <div class="container">
      <img src="./icon/dot.svg" alt="" class="dot">
      <h1>Circulation Desk</h1>
      <h3>花園館藏</h3>
    </div>
  </div>
  
  
  
  
  
  <script src='./jquery/jquery-3.6.0.min.js'></script>
  <script src='./js/index.js'></script>
  <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


<div class = "backgroundouter">
  <div class="title">付款成功!<br> 請至會員中心查看訂單紀錄,<br> 領取QR Code以進入會場</div>


  <div class="btn_div">
    <button type="submit" class="btn btn-primary"><a href='<%=request.getContextPath()%>/vendor_ticketHome.jsp'>確認<br> (將返回購物車首頁)</a></button>
  </div>
  
</div>  

  
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
</body>
</html>