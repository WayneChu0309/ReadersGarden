<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
 
<%
    EventService eventSvc = new EventService();
    List<Event> list = eventSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="EventSvc" scope="page" class="com.event.model.EventService" />

<html>
<head>
<title>所有活動資料 - listAllEmp.jsp</title>

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
		 <h3>所有活動資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/event/event_select.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>活動編號</th>
		<th>活動類別編號</th>
		<th>召集人</th>
		<th>人數限制</th>
		<th>活動名稱</th>
		<th>活動內容</th>
		<th>活動開始時間</th>
		<th>活動結束時間</th>
		<th>活動報名開始時間</th>
		<th>活動報名結束時間</th>
		<th>活動狀態</th>
	</tr>

	<c:forEach var="event" items="${list}">
		
		<tr>
			<td>${event.eventid}</td>
			<td>${event.eventcateid}</td>
			<td>${event.memberid}</td>
			<td>${event.capacity}</td>
			<td>${event.eventname}</td>
			<td>${event.eventdescription}</td> 
			<td>${event.eventstart}</td>
			<td>${event.eventend}</td>
			<td>${event.eventappstart}</td>
			<td>${event.eventappend}</td>
			<td>${event.eventstatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empno"  value="${event.eventid}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="empno"  value="${event.eventid}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>