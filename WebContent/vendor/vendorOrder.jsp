<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.vendor.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>廠商會員中心 - Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/vendor/css/vendorOrder.css'>
  
</head>
<body>
	<%@ include file="/parts/header.text"%>
	
	<!-- content start -->
  <div class="content_row">
    <div class="content_left">
      <div><a href="${pageContext.request.contextPath}/vendor/VendorServlet?action=getOne_For_Update" class="content_label">廠商資料</a></div>
      <div><a href="#" class="content_label now_choice">我的訂單</a></div>
      <div><a href="${pageContext.request.contextPath}/vendoract/VendorActServlet?action=vendor_Activity" class="content_label">我的活動</a></div>
    </div>
    <div class="content_right">
      <div class="container_el">
        <div class="info_zone">
          <h5>所有訂單列表</h5>
          	
          	<jsp:useBean id="siteSvc" scope="page" class="com.site.model.SiteService"/>
          	<c:choose>
          		<c:when test="${not empty vactlist}">
            		<c:forEach var="vact" items="${vactlist}">
			            <div>
				            <table>
				              <tr>
				                <td class="order_title">訂單編號</td>
				                <td class="order_title">訂單日期</td>
				                <td class="order_title">場地名稱</td>
				                <td class="order_title">租借日期</td>
				                <td class="order_title">總天數</td>
				                <td class="order_title">訂單金額</td>
			<!-- 	                <td class="order_title">訂單進度</td> -->
			<!-- 	                <td class="order_title"></td> -->
				              </tr>
					              <tr>
					                <td id="vactid">${vact.vactid}</td>
					                <td id="order_date">${vact.date}</td>
					                <td id="siteid">${siteSvc.findOneSite(vact.siteid).name}</td>
					                <td id="rtldate">
					                	<span id="rtlstart">${vact.rtlstart}</span> ~ 
					                	<span id="rtlend">${vact.rtlend}</span>
					                </td>
					                <td class="totalday">計算中</td>
					                <td id="amount">$ <fmt:formatNumber type="currency" pattern="#,###">${vact.amount}</fmt:formatNumber>
					                
					                </td>
			<%-- 		                <td id="progress">${vact.progress}</td> --%>
			<!-- 		                <td><button class="cancel_btn">取消訂單</button></td> -->
					              </tr>
				            </table>
				            	
				            	<c:choose>
				            		<c:when test="${vact.progress != '99' && vact.progress != '100'}">
							            <button class="detail_btn progress_btn">訂單進度</button>
							            <a href="${pageContext.request.contextPath}/vendoract/VendorActServlet?action=getOne_VendorAct&vactid=${vact.vactid}" class="detail_btn">活動資訊</a>
							            
							            <!-- 訂單已完成就不能取消訂單 -->
							            <c:if test="${vact.progress < '3'}">
							           		<button class="detail_btn cancel_btn">取消訂單</button>
							            </c:if>
							            
							         </c:when>
							         <c:otherwise>
							         	<p class="canceled_order">✓ 訂單已取消</p>
							         </c:otherwise>
					            </c:choose>
					            
				            <ol class="progress_ol -none" id="progress${vact.vactid}">
				              <li class="placeorder reach">
				                <b class="dot"></b> 
				                <p>訂單成立</p>
				              </li>
				              <li class="progress_li ${vact.progress >= 1? "reach" : ""}">
				                <b class="dot"></b> 
				                <p>活動審核</p>
				                
				              </li>
				              <li class="progress_li ${vact.progress >= 2? "reach" : ""}">
				                <b class="dot"></b> 
				                <p>等待付款</p>
				                
				              </li>
				              <li class="progress_li ${vact.progress == 3? "reach" : ""}">
				                <b class="dot"></b> 
				                <p>付款完成</p>
				                
				            </ol>
			      	</div>
			      	</c:forEach>
            	</c:when>
            	<c:otherwise>
            		<h3>尚無訂單資料</h3>
            	</c:otherwise>
            </c:choose>
            


        </div>
      </div>
    </div> <!--vendor_info end tag-->
  </div>

  <div class="pop_bg"></div>
  <div class="pop_cancel" id="cancel_alert">
    <h5>確定要取消訂單嗎？</h5>
    <h6>訂單取消後，將不為您保留場地時間。</h6>
    <button id="not_cancel">我不要取消</button>
    <button id="confirm_cancel">確定取消</button>
  </div>

  <div class="pop_cancel" id="success_alert">
    <h5>訂單取消成功</h5>
    <button id="cancel_success">確定</button>
  </div>
  
    <div class="pop_cancel" id="failure_alert">
    <h5>訂單取消失敗，請洽客服人員。</h5>
    <button id="cancel_failure">確定</button>
  </div>

  <script src='${pageContext.request.contextPath}/vendor/jquery/jquery-3.6.0.min.js'></script>
  <script src='${pageContext.request.contextPath}/vendor/js/vendorOrder.js'></script>
  <script>
  	var cancelOrder_url = `${pageContext.request.contextPath}/vendoract/VendorOrderCancel`;
  </script>
</body>
</html>