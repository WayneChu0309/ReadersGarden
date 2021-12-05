<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ArticleService artSvc = new ArticleService();
    List<Article> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="artSvc" scope="page" class="com.article.model.ArticleService" /> --%>

<html>
<head>
<meta charset="UTF-8">
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有文章資料 - listAllArt.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_article.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>文章編號</th>
		<th>文章類別編號</th>
		<th>發布者ID</th>
		<th>文章名稱</th>
		<th>文章內容</th>
		<th>文章發布日期</th>
		<th>修改</th>
		<th>刪除</th>
		<th>案讚</th>
		<th>檢舉</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="art" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${art.AID}</td>
			<td>${art.ACID}</td>
			<td>${art.ACCTID}</td>
			<td>${art.ANAME}</td>
			<td>${art.ADESCRIPT}</td>
			<td>${art.APD}</td> 
	<%-- 		<td><c:forEach var="deptVO" items="${deptSvc.all}">
                    <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】
                    </c:if>
                </c:forEach>
			</td>
	--%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="AID"  value="${art.AID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="AID"  value="${art.AID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>

