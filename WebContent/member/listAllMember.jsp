<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
   
%>
 <%-- ><jsp:useBean id="member" scope="page" class="com.member.model.MemberService" /> --%>

<html>
<head>
<title>所有會員資料 </title>

<style>
body{background-color:#e6f4eb}
  table#table-1 {
	background-color: #94e4a5;
    border: 2px solid black;
    text-align: center;
     width: 100%;
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
	width: 1200px;
	background-color: #eaf2f6;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #47cb2e;
 
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
		 <h3>所有會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/member/memberBackend.jsp">回首頁</a></h4>
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
		<th>會員編號</th>
		<th>姓名</th>
		
		<th>信箱</th>
		<th>電話</th>
		<th>地址</th>
		<th>身分證字號</th>
		<th>會員狀態</th>
		<th>密碼</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.number}</td>
			<td>${memberVO.name}</td>
			
			<td>${memberVO.email}</td>
			<td>${memberVO.phoneNumber}</td>
			<td>${memberVO.address}</td>
			<td>${memberVO.ID}</td>
			<td>${memberVO.status}</td> 
			<td>${memberVO.password}</td>  
		
		
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

</body>
</html>