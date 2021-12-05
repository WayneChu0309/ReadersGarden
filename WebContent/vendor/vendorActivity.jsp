<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>廠商會員中心 - Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/vendor/css/vendorAct.css'>
  
</head>
<body>
	<%@ include file="/parts/header.text"%>

<!-- content start -->
  <div class="content_row">
    <div class="content_left">
      <div><a href="${pageContext.request.contextPath}/vendor/VendorServlet?action=getOne_For_Update" class="content_label">廠商資料</a></div>
      <div><a href="${pageContext.request.contextPath}/vendoract/VendorActServlet?action=vendor_Order" class="content_label">我的訂單</a></div>
      <div><a href="#" class="content_label now_choice">我的活動</a></div>
    </div>
    
    <div class="content_right">
      <div class="container_el">
        <div class="info_zone">
          <h5>檢視我的活動</h5>
          <select class="status_select">
          	<option value="所有活動">所有活動</option>
          	<option value="申請中">申請中</option>
          	<option value="待上架">待上架</option>
          	<option value="售票中">售票中</option>
          	<option value="已結束">已結束</option>
         	<option value="已取消">已取消</option>
          </select>
          
          <select class="act_select">
          	<option>${vact == null? "查詢中":vact.name}</option>
          </select>
          
          <button id="choose_btn">選擇</button>
		          
          <div class="my_act">
          	<c:choose>
	          	<c:when test="${not empty vact}">
		          	<div class="top_show">
		          		<div id="show_img">
		          			<img src="${pageContext.request.contextPath}/vendoract/VendorActImg?action=showImg&vactid=${vact.vactid}">
			          	</div>
			          	
			          	<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService"/>
			          	<div id="show_detail">
			          		<h4 id="show_name">${vact.name}</h4>
			          		<h6>訂單編號: </h6><span id="show_vactid">${vact.vactid}</span><br>
			          		<h6>活動類型: </h6><span id="show_cate">${actSvc.findOneActivity(vact.actid).acttype}</span><br>
			          		<h6>活動日期: </h6><span id="show_actStart">${vact.actstart}</span> ~<span id="show_actEnd">${vact.actend}</span><br>
			          		<h6>售票日期: </h6><span id="show_tkStart">${vact.tkstart}</span> ~<span id="show_tkEnd">${vact.tkend}</span><br>
			          		<h6>票價: </h6><span id="show_price"> ${vact.price}元</span><br>  
<!-- 			          		<a id="more_function" href="#">前往售票頁面</a>      	 -->
			          	</div> 
		          	</div>
		          	<hr>
		          	<div class="btm_show">
		          		<h6>活動介紹</h6>
		          		<span id="show_content">${vact.actcnt}</span>
		          	</div>
		          </c:when>
		          <c:otherwise>
		          	<div class="top_show">
		          		<h3>尚無活動紀錄</h3>
		          	</div>
		          </c:otherwise>
	          </c:choose>
          </div>
        </div>
      </div>
    </div> <!--content_right end tag-->
    
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

  <script>
	var orderAllocate_url = "<%=request.getContextPath()%>/vendoract/VendorOrderAllocate";
	var orderOne_url = "<%=request.getContextPath()%>/vendoract/VendorOrderOne";
	var img_url = "<%=request.getContextPath()%>/vendoract/VendorActImg?action=showImg&vactid=";
	var vendorid = `${vendorVO.vendorid}`;
	console.log(vendorid);
	
  </script>
  <script src='<%=request.getContextPath()%>/vendor/jquery/jquery-3.6.0.min.js'></script>
  <script src='<%=request.getContextPath()%>/vendor/js/vendorAct.js'></script>


</body>
</html>
