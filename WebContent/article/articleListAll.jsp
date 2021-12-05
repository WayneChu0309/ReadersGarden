<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="java.util.*"%> --%>
<%-- <%@ page import="com.article.model.*"%> --%>


<%-- <% --%>
//     ArticleService artSvc = new ArticleService();
//     List<Article> list = artSvc.getAll();
//     pageContext.setAttribute("list",list);
<%-- %> --%>

<%-- <% --%>
//   Article art = (Article) request.getAttribute("art");
<%-- %> --%>

<!-- <!DOCTYPE html> -->
<!-- <html> -->

<!-- <style> -->

/* .table1{ */
/*     margin-left: 100px; */
/*     margin-bottom: 50px; */
/*     background-color: rgb(255, 255, 255); */
/*     width: calc(100% - 200px - 10px); */
/*     vertical-align: top; */
/*     font-size: 1rem; */
/* } */
    
/*     .title1{ */
/*       background-color: rgb(214, 228, 214); */
/*       opacity: 0.7; */
/*       height: 80px; */
/*       color: black; */
/*       text-align: center; */
/*       font-size: 50px ; */
/* } */
/*     .title{ */
/*       height: 80px; */
/*       color: black; */
/*       text-align: center; */
/*       font-size: 50px ; */
/* } */
    
/*     .list-tl{ */
/*       text-align: center; */
/* } */
    
    
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
<!--       <h3>祕密花園</h3> -->
<!--     </div> -->
<!--   </div> -->
  
  
<%--   <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
  
  
<!-- <div class="title1">搜尋結果</div> -->
  
<%-- <%--   --%>
<%-- <div class="title"> --%>
<%-- <nav class="navigation"> --%>
<%--   <div class="container-fluid"> --%>
<%--     <form class="d-flex"> --%>
<%--       <input type="TEXT" name="ACID" size="45" value="<%= (art==null)? "1" : art.getACCTID()%>" /> --%>

<%-- 	<div class="send"> --%>
<%-- 	<input type="hidden" name="action" value="${art.ACID}"> --%>
<%-- 	<input type="submit" value="search"> --%>
<%-- 	</div> --%>
<%--     </form> --%>

<%--   </div> --%>
<%-- </nav> --%>
<%-- </div> --%> --%>

<!-- <!-- </header> --> -->

<!-- <!-- table body --> -->

<!--   <table class="table1"> -->

<!--  	<tr> -->
<!-- 		<th>文章編號</th> -->
<!-- 		<th>文章類別編號</th> -->
<!-- 		<th>發布者ID</th> -->
<!-- 		<th>文章名稱</th> -->
<!-- 		<th>文章內容</th> -->
<!-- 		<th>文章發布日期</th> -->
<!-- 		<th>修改</th> -->
<!-- 		<th>刪除</th> -->
<!-- 		<th>案讚</th> -->
<!-- 		<th>檢舉</th> -->
<!-- 	</tr>      -->
      
<%-- <%@ include file="pages/page1.file" %>  --%>
<%-- 	<c:forEach var="art" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
		
<%-- 		<jsp:useBean id="acSvc" scope="page" class="com.articleclass.model.Article_ClassService"/> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${art.AID}</td> --%>
<%-- 			<td>${acSvc.getOneArticle(art.ACID).classname}</td> --%>
<%-- 			<td>${art.ACCTID}</td> --%>
<%-- 			<td>${art.ANAME}</td> --%>
<%-- 			<td>${art.ADESCRIPT}</td> --%>
<%-- 			<td>${art.APD}</td>  --%>
     
<!--  			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="AID"  value="${art.AID}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="AID"  value="${art.AID}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 	</c:forEach>     --%>
     
     
     
      
<!--   </table> -->
  
<%-- <%@ include file="pages/page2.file" %> --%>


<!--   <table id="backToHome"> -->
<!-- 	<tr><td> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/article_homepage.jsp">返回討論區首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->

<%-- <%@ include file="/parts/footer.text" %> --%>

<%-- <script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  自己的js --%>
<!-- </body> -->
<!-- </html> -->