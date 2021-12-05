<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>廠商資料查詢</title>
    <link rel="stylesheet" href="css/vendorAll.css">
</head>
<body>
    <h1>廠商資料查詢</h1>
    <div class="act_div container">
        <h3>所有廠商</h3>
        
        <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
        
        <table class="vendor_list">
            <tr>
                <td>廠商編號</td>
                <td>廠商名稱</td>
                <td>狀態</td>
                <td>統一編號</td>
                <td>密碼</td>
                <td>負責人</td>
                <td>Email</td>
                <td>電話</td>
                <td>手機</td>
                <td>地址</td>

                <td>修改</td>
                <td>刪除</td>
            </tr>
            <jsp:useBean id="venSvc" scope="page" class="com.vendor.model.VendorService" />
            <c:forEach var="vendor" items="${venSvc.getAll()}">
            <tr>
                <td>${vendor.vendorid}</td>
                <td>${vendor.company}</td>
                <td>${vendor.status}</td>
                <td>${vendor.taxid}</td>
                <td>${vendor.password}</td>
                <td>${vendor.name}</td>
                <td>${vendor.email}</td>
                <td>${vendor.tel}</td>
                <td>${vendor.mobile}</td>
                <td>${vendor.addr}</td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
                        <input type="submit" value="修改">
                        <input type="hidden" name="vendorid" value="${vendor.vendorid}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="vendorid" value="${vendor.vendorid}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
           	</c:forEach>
        </table>  
    </div>
</body>
</html>