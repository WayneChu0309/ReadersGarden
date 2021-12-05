<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendoract.model.*"%>
<%VendorActVO vactVO = (VendorActVO) request.getAttribute("vactVO");%>

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
    font-size: 100px;
    text-align: center;
    margin-top: 10px;

}

.form-label{
    margin-top: 50px;
    font-size: 20px;
    margin-left: 10px;
}

.form-control{
    margin-top: 20px;
    font-size: 20px;
}
.form-select{
    margin-top: 20px;
    font-size: 20px;
}

.btn{
    margin-top: 15px;
}

.card{
	display: inline-block;
	background-color: white;
	opacity: 0.8;
	height: 300px;
	width: 450px;
    margin-left: 200px;
    margin-top: 40px;
	padding-top: 30px;
	margin-bottom: 60px;
	border-radius: 15px;
}

.form{
	display: inline-block;
	background-color: white;
	opacity: 0.8;
	height: 300px;
	width: 500px;
    margin-top: 60px;
    margin-left: 500px;
    margin-right: 100px;
    margin-bottom: 100px;
    font-size: 40px;
    padding-left: 50px;
 	border-radius: 15px;
}

.col-12{
	text-align: center;
}

.image_box{
	display: flex;
	height: 150px;
	width: 300px;
	background-color: black;
	margin-left: 80px;
	margin-bottom: 10px;
}

.image_box img{
	max-width: 100%;
	max-height: 100%;
	margin: 0 auto;
}

.card-title{
	margin-left: 30px;
	text-align: center;
	margin-top: 1px;
}

.card-text{
	margin-left: 15px;
	text-align: center;
}

.outer{
	background-color: rgb(255, 255, 255, 0.5);
}

.inner{
	background-color: rgb(255, 255, 255, 0.6);
}

.payment{
	font-size: 40px;
	text-align: center;
}
input.card2{
  width: 42px;
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
  
 
  <div class="title">購票資訊</div>

<div class = "outer">
<jsp:useBean id="vactSvc" scope="page" class="com.vendoract.model.VendorActService"/>


<c:forEach var="orderBean" items="${orderList}">
<div class="card mb-3" style="max-width: 600px;">
    <div class="row g-0">
      <div class="image_box">
  		 <img src="${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=${orderBean.vactId}">     
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h5 class="card-title">Card Title: ${vactSvc.findOneVendorAct(orderBean.vactId).name}</h5>
          <p class="card-text">Price For This Card: ${orderBean.total}</p>
          <p class="card-text">Number of Ticket(s): ${orderBean.ordercount}</p>
        </div>
      </div>
    </div>
  </div>
</c:forEach>

</div>
  <!-- form -->
  
<div class = "inner">
	<div class = "payment">填寫付款資訊</div>
	  <form class="form" METHOD="post" ACTION="<%=request.getContextPath()%>/ticket/VendorTicketServlet">
	    <div class="col-md-12">
	      <div class = "form-label">Total Amount: ${totalCount}</div>
	      <label for="inputpay" class="form-label">Payment Method: </label>
	      <select id="inputpay" class="form-select">
	        <option selected>Choose...</option>
	        <option>信用卡</option>
	      </select>
	    </div>
	
			  <input type="text" style="display:none">
			  <label>信用卡卡號：</label>
			  <input type="text" class="card2" maxlength="4">
			  <input type="text" class="card2" maxlength="4">
			  <input type="text" class="card2" maxlength="4">
			  <input type="text" class="card2" maxlength="4">



		 
			 <input id="pay" type="submit" value="付款結帳" disabled>
			 <input type="hidden" name="vactid"  value="${vactVO.vactid}">
			 <input type="hidden" name="action" value="checkout" >
		</FORM>
</div>
 
 
 
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
<script>
	
	$("input.card2").on("keydown", function(e){
	    //console.log(e.which);
	    if((e.which >= 48 && e.which <= 57) || e.which == 8){
	      
	      //console.log(e.target.value.length);
	      if(e.target.value.length == 0 && e.which == 8){
	        $(this).prev().focus();
	      }
	    }else{
	      e.preventDefault();
	    }
	});

	$("input.card2").on("keyup", function(e){
	  
	  // \D 代表非數字字元，g 代表全域尋找
	  //let str = e.target.value;
	  //console.log(e.target.value);
	  let str = (e.target.value).replace(/\D/g, "");
	  
	  $(this).val(str);
	  
	  //console.log(str.length);
	  if(str.length == 4){
	    $(this).next().focus();
	  }
	});
	
	$("input.card2").on("change", function(){
		var check = 0;
		$("input.card2").each(function(){
			if($(this).val().length != 4) {
				check = 0;
			} else {
				check = 1;
			}
		})
		if (check == 1) {
			$("form.form input#pay").attr("disabled", false);
		}
	})
	
</script>
</body>
</html>