
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.article.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>


<%Article art = (Article) request.getAttribute("art");%>

<html>
<head>
<title>�峹��� - listOneArt.jsp</title>

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
		 <h3>�峹��� - listOneArt.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_article.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�峹�s��</th>
		<th>�峹���O�s��</th>
		<th>�o����ID</th>
		<th>�峹�W��</th>
		<th>�峹���e</th>
		<th>�峹�o�����</th>
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


