<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.markRecord.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MarkRecordService markRecordSvc = new MarkRecordService();
    List<MarkRecordVO> list = markRecordSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>
 <%-- ><jsp:useBean id="member" scope="page" class="com.member.model.MarkRecordService" /> --%>

<html>
<head>
<title>所有書籤資料 - listAllMarkRecord.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有書籤資料 - listAllMarkRecord.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/markRecord/selectMarkRecord.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>SERIALNUMBER</th>
		<th>NUMBER</th>
		<th>STOCKID</th>
		<th>VACID</th>
		
	</tr>
	<%@ include file="pages/page1.file" %> 
	 <jsp:useBean id="MarkRecordSvc" scope="page" class="com.markRecord.model.MarkRecordService" />
	<c:forEach var="MarkRecordVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${MarkRecordVO.serialNumber}</td>
			<td>${MarkRecordVO.number}</td>
			<td>${MarkRecordVO.stockID}</td>
			<td>${MarkRecordVO.vacID}</td>
		 
		 <%--	<td><c:forEach var="memberVO" items="${member.all}">
                     <c:if test="${empVO.deptno==deptVO.deptno}">
	                    ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】
                    </c:if> 
                </c:forEach> 
			</td> --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/markRecord/MarkRecordServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="serialNumber"  value="${MarkRecordVO.serialNumber}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/markRecord/MarkRecordServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="serialNumber"  value="${MarkRecordVO.serialNumber}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>