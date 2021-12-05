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
  
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
</body>
</html>