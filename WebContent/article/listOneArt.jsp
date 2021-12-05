
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.article.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>


<%Article art = (Article) request.getAttribute("art");%>

<html>
<head>
<title>文章資料 - listOneArt.jsp</title>

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
		 <h3>文章資料 - listOneArt.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_article.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>文章類別編號</th>
		<th>發布者ID</th>
		<th>文章名稱</th>
		<th>文章內容</th>
		<th>文章發布日期</th>
	</tr>
	<tr>
		<td><%=art.getAID()%></td>
		<td><%=art.getACID()%></td>
		<td><%=art.getACCTID()%></td>
		<td><%=art.getANAME()%></td>
		<td><%=art.getADESCRIPT()%></td>
		<td><%=art.getAPD()%></td>
	</tr>
</table>

</body>
</html>


