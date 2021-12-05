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
  margin-top: 70px; 
 }

.text{
	margin-left: 250px;
	margin-top: 60px;
 	font-size: 20px;
}

</style>

</head>
<body>

<div class="container-box">
    <div class="container">
	    <div class="title" color = "white">掃描完畢! 兌換完成</div>
		<div class="text">活動名稱: ${actname}</div>    
		<div class="text">開始時間: ${actstart}</div>
		<div class="text">結束時間: ${actend}</div>
		<div class="text">${message}</div>
   </div>
</div>


</body>
</html>