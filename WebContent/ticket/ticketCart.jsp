<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendoract.model.*"%>

<%VendorActVO vactVO = (VendorActVO) request.getAttribute("vactVO");%>

<%--
   <% @SuppressWarnings("unchecked")
   Vector<VendorActVO> buylist = (Vector<VendorActVO>) session.getAttribute("shoppingcart");%>
<%if (buylist != null && (buylist.size() > 0)) {%>
 --%>

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
    font-size: 50px;
    text-align: center;
    margin-top: 10px;
}
.border{
	display: inline-block;
	background-color: white;
	opacity: 0.8;
	height: 550px;
	width: 900px;
	margin-left: 300px;
	margin-top: 30px;
    margin-bottom: 60px;
    margin-right:30px;
    padding-right: 10px;
    padding-left: 10px;
    border-radius: 15px;
    padding-bottom: 10px;
}
.orderPrice{
    padding-bottom: 10px;
    margin-left: 35px;
}
.orderNumber{
    margin-bottom: 10px;
    margin-left: 35px;
}
.card-title{
	font-size: 25px;
	margin-top: 30px;
	text-align: center;
}
.card-text{
	font-size: 15px;
	margin-top: 10px;
	margin-bottom: 10px;
}
.btn{
  display: inline-block;
  margin-left: 100px;
  margin-top: 0px;
  height: 30px;
  width: 120px;
}
.img_box{
	display: flex;
	height: 250px;
	width: 50%;
	margin-right: 20px;
	margin-left: 30px;
	margin-top: 25px;
	margin-bottom: 0;
}
.img_box img{
	max-width: 100%;
	max-height: 100%;
	margin: 0 auto;
	
}
.word{
	width: 50%;
	display: flex;
}
.order1{
	text-align: center;
	margin-top: 20px;
}
.top{
	align-items: center;
	display: flex;
}
.bottom{
	margin-top: 30px;
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
 
  <div class="title">購物車</div>

 <jsp:useBean id="vactSvc" scope="page" class="com.vendoract.model.VendorActService"/>
 <c:forEach var="orderBean" items="${orderList}">
	  <div class="card mb-3" style="max-width: 600px;">
	    <div class="row g-0">
	      <div class="border">
	      	<h5 class="card-title">Title: ${vactSvc.findOneVendorAct(orderBean.vactId).name}</h5>
	        	<div class = "top">
			  		<div class="img_box">
			  			<img src="${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=${orderBean.vactId}">
			  		</div>
			  		<div class = "word">
				         <p class="card-text">Detail: ${vactSvc.findOneVendorAct(orderBean.vactId).actcnt}</p>
			         </div>
		         </div>   
		    	 <div class = "bottom">      
		 		  	  <hr>
		   
			          <div class = "order1">Total Price: ${orderBean.total}</div>
			          <div class = "order1">Number of Ticket: ${orderBean.ordercount}</div>
			          
			          
		        	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ticket/VendorTicketServlet" style="display: inline-block; margin-left: 440px; margin-top: 30px;">
				     <input type="text" name="orderId" value="${orderBean.orderId}" style="display:none">
				     <input type="submit" value="刪除">
				     <input type="hidden" name="vactid"  value="${vactVO.vactid}">
				     <input type="hidden" name="action" value="delete"></FORM>
	       		 </div>
	        </div>
	      </div>
	    </div>
</c:forEach>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ticket/VendorTicketServlet" style="margin-left: 600px; display: inline-block">
	 <input type="submit" value="前往付款">
	 <input type="hidden" name="vactid"  value="${vactVO.vactid}">
	 <input type="hidden" name="action" value="buy">
</FORM>	

<div class="btn"> <button type="submit" class="btn btn-primary"><a href='<%=request.getContextPath()%>/vendor_ticketHome.jsp'>返回購物首頁</a></button> </div>


 
 
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
</body>
</html>