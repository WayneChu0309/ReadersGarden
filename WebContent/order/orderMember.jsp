<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員中心 - Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/order/css/orderMember.css'>
<style>

.title{
  text-align: center;
  font-size: 40px;
  margin-top: 20px;
}

main.table{
  vertical-align: top;
  font-size: 2rem;
  margin-left: 20px;
}
td{
 border: 1px solid black;
 padding: 10px 10px 10px 10px;
 text-align: center;
 } 

.tablecontent{
  margin-top: 30px;
  margin-left: 80px;
}
</style>

</head>
<body>

	<%@ include file="/parts/header.text" %>
	<%@ include file="/member/membercenter.text" %>


<div class="container-box">
    <div class="container">
    
    <div class="title" color = "white">訂單紀錄</div>

  <table width="80%" cellpadding="4" cellspacing="0" class="tablecontent">
    <tr class="list-title">
      <td class="list-no" width="30%">訂單ID (OrderID)</td>
      <td width="50%" nowrap align="center">活動名稱</td>
      <td class="list-no" width="30%">票張數量</td>
      <td class="list-no" align="center" width="30%">總價</td>

    </tr>
  
    <!-- 表格內容 -->
    <jsp:useBean id="actSvc" class="com.vendoract.model.VendorActService"></jsp:useBean>
<c:forEach var="order" items="${memberAllboList}" varStatus="index">
    <tr>
      <td>${order.orderId}</td>
      <td class="list-tl"><a href="${pageContext.request.contextPath}/order/memberorder?action=get_one&orderId=${order.orderId}&total=${order.total}">
      	${actSvc.findOneVendorAct(order.vactId).name}</a></td>
      <td>${order.ordercount}</td>
      <td>$${order.total}</td>
    </tr>
</c:forEach>
</table>

    </div>
</div>


<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/borrow/js/borrowMember.js'></script>

<script>
	$("div.content_row div.content_left div").eq(1).find("a").addClass("now_choice");
	$("div.container ul label").on("click", function(){
		if ($(this).siblings("input[type='checkbox']").prop("checked") == false) {
			var index = $(this).closest("ul").prevAll().length
			$("div.stockorder div.container").scrollTop(index * 55);
		}
	})
	
	var scoreURL = "${pageContext.request.contextPath}/borrow/BorrowScoreAjax.do";

</script>
</body>
</html>