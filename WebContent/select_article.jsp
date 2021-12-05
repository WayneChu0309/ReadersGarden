
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<title>Article: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Article: Home</h3></td></tr>
</table>

<p>This is the Home page for articles</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/article/articleListAll.jsp'>List</a> all Articles.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
        <b>輸入文章ID (如10):</b>
        <input type="text" name="AID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="artSvc" scope="page" class="com.article.model.ArticleService"/>
    <% System.out.println(artSvc); %>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
       <b>選擇文章類別編號:</b>
       <select size="1" name="AID">
         <c:forEach var="art" items="${artSvc.all}" > 
          <option value="${art.AID}">${art.AID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
       <b>選擇文章名稱:</b>
       <select size="1" name="AID">
         <c:forEach var="art" items="${artSvc.all}" > 
          <option value="${art.AID}">${art.ANAME}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增文章</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/article/addArt.jsp'>Add</a> a new Article.</li>
</ul>

</body>
</html>