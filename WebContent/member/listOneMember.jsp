<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>


<html>
<head>
<title>會員資料 - listOneEmp.jsp</title>

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
		 <h3>會員資料 </h3>
		 <h4><a href="<%=request.getContextPath()%>/member/memnerBackend.jsp">回首頁</a></h4>
	</td></tr>
</table>

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
</table>

</body>
</html>