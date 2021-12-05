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
    <link rel="stylesheet" href="css/vendorOne.css">
</head>
<body>
    <h1>廠商資料查詢</h1>
    <div class="act_div container">
        <h3>所有廠商</h3>
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
            
<%--             <jsp:useBean id="venVO" scope="request" class="com.vendor.model.VendorVO" /> --%>
            <tr>
                <td>${venVO.vendorid}</td>
                <td>${venVO.company}</td>
                <td>${venVO.status}</td>
                <td>${venVO.taxid}</td>
                <td>${venVO.password}</td>
                <td>${venVO.name}</td>
                <td>${venVO.email}</td>
                <td>${venVO.tel}</td>
                <td>${venVO.mobile}</td>
                <td>${venVO.addr}</td>
                <td>
					<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
                        <input type="submit" value="修改">
                        <input type="hidden" name="action" value="getOne_For_Update">
                        <input type="hidden" name="vendorid" value="${venVO.vendorid}">
                    </form>
                </td>
                <td>
                    <form action="">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="vendorid" value="${venVO.vendorid}">
                    </form>
                </td>
            </tr>
        </table>  
    </div>
</body>
</html>