<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="com.article.model.*"%> --%>
<!-- <!DOCTYPE html> -->
<%-- <%Article art = (Article) request.getAttribute("art");%> --%>

<!-- <html> -->

<!-- <style> -->

/* .table1{ */
/*     margin-left: 100px; */
/*     margin-bottom: 50px; */
/*         background-color: rgb(255, 255, 255); */
/*         width: calc(100% - 200px - 10px); */
/*         vertical-align: top; */
/*         font-size: 1rem; */
/*       } */
    
/*     .title{ */
/*         background-color: rgb(214, 228, 214); */
/*       height: 80px; */
/*         color: black; */
/*         text-align: center; */
/*       font-size: 50px ; */
/*     } */
    
    
/*     .list-tl{ */
/*         text-align: center; */
/*     } */
    
    
/*     td{ */
/*        border: 1px solid black; */
/*        padding: 10px 10px 10px 10px; */
/*       text-align: center; */
/*     }  */
    
/*     nav.navigation{ */
/*       margin-top: 25px; */
/*       float: right; */
/*       width: auto; */
/*       height: 200px; */
/*     } */
/*     .button{ */
/*     text-align: right; */
/*     margin-left: 1000px; */
/*       width: 200px; */
/*       height: 200px; */
/*     }   */
      
/* table#backToHome { */
/* 	background-color: white; */
/*     opacity: 0.8; */
/*     border: 0.5px solid black; */
/*     text-align: center; */
/*     float: right; */
/*     margin-right: 200px; */
/* } */
/* .article-title{ */
/*   font-size: 60px; */
/*   text-align: center; */
/* } */

/* p{ */
/*   display: inline-block; */
/* 	text-align: left; */
/*   margin-right:300px; */
/*   margin-top: 20px; */
/*   margin-left: 350px; */
/*   height: auto; */
/*   width: 600px; */
/*   font-size: 20px; */
/* } */

/* .publisher{ */
/*   margin-top: 20px; */
/* } */

/* .form-control{ */
/*   width: 600px; */
/*   height: 100px; */
/* } */

/* .form{ */
/*   display: inline-block; */
/*   margin-top: 20px; */
/*   margin-left: 350px; */
/* } */

/* .button-form{ */
/*   /* float: right; */ */
/*   margin-left: 1000px; */
/*   margin-top: 30px; */
/*   margin-bottom: 100px; */
/*   width: 150px; */
/*   height: 60px; */
/* } */

/* table#datepublish{ */
/* 	display: inline-block; */
/* 	text-align: center; */
/* 	margin-right: 30px; */
/* 	margin-left: 250px; */

/* } */
/* table#datepublish2{ */
/* 	display: inline-block; */
/* 	text-align: center; */
/* 	margin-right: 30px; */
/* 	margin-left: 300px; */

/* } */
/* .title1{ */
/* 	text-align: center; */
/* 	font-size: 30px; */
/* } */

/* .button-form{ */
/*   /* float: right; */ */
/*   margin-left: 1000px; */
/*   margin-top: 30px; */
/*   margin-bottom: 100px; */
/*   width: 150px; */
/*   height: 60px; */
/* } */
<!-- </style> -->

<!-- <head> -->
<!--   <meta charset="UTF-8"> -->
<!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--   <title>Reader's Garden</title> -->
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  自己的css --%>
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> 自己的css --%>
<!-- </head> -->
<!-- <body> -->
	
<%-- <%@ include file="/parts/header.text" %> --%>
<%-- <%@ include file="/parts/slide.text" %> --%>

<!--   <div class="latest-news"  id="target-page"> -->
<!--     <div class="container"> -->
<!--       <img src="./icon/dot.svg" alt="" class="dot"> -->
<!--       <h1>Circulation Desk</h1> -->
<!--       <h3>花園館藏</h3> -->
<!--     </div> -->
<!--   </div> -->
  
<%-- <h1 class="article-title"><%=art.getANAME()%></h1> --%>
<!--  	<tr> -->
<%-- 		<p>文章內容: <%=art.getADESCRIPT()%></p>  --%>
<!-- 	</tr>    -->

<!-- <table id="datepublish"> -->
<!--  	<tr> -->
<!-- 		<th>文章發布者ID:</th> -->
<%-- 		<td><%=art.getACCTID()%></td>  --%>
<!-- 	</tr> -->

<!-- </table>  -->
<!-- <table id="datepublish2"> -->

<!--  	<tr> -->
<!-- 		<th>文章發布日期:</th> -->
<%-- 		<td><%=art.getAPD()%></td>  --%>
<!-- 		</tr> -->
<!-- </table> -->

<%-- <%--  --%>
<%-- <table class="table1"> --%>
<%-- 	<tr> --%>
<%-- 		<th>文章編號</th> --%>
<%-- 		<th>文章類別編號</th> --%>
<%-- 		<th>發布者ID</th> --%>
<%-- 		<th>文章名稱</th> --%>
<%-- 		<th>文章內容</th> --%>
<%-- 		<th>文章發布日期</th> --%>
<%-- 	</tr> --%>
<%-- 	<tr> --%>
<%-- 		<td><%=art.getAID()%></td> --%>
<%-- 		<td><%=art.getACID()%></td> --%>
<%-- 		<td><%=art.getACCTID()%></td> --%>
<%-- 		<td><%=art.getANAME()%></td> --%>
<%-- 		<td><%=art.getADESCRIPT()%></td> --%>
<%-- 		<td><%=art.getAPD()%></td> --%>
<%-- 	</tr> --%>
<%-- </table> --%>
<%--   --%>   --%>
  

<%--   <button class="button-form"><a href="<%=request.getContextPath()%>/article_home.jsp">返回討論區首頁</a></button> --%>
  
  
  
  
  
<%-- <%@ include file="/parts/footer.text" %> --%>

<%-- <script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  自己的js --%>
<!-- </body> -->
<!-- </html> -->