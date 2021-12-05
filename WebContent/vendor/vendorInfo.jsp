<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>

<% 
	VendorVO venderVO = (VendorVO) session.getAttribute("vendorVO");
	
	String addr = venderVO.getAddr();
	String[] addrs = addr.split(" ");
	
	session.setAttribute("addrs", addrs);

%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>廠商會員中心 - Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/vendor/css/vendorInfo.css'>
  
</head>
<body>
	<%@ include file="/parts/header.text"%>

<!-- content start -->
  <div class="content_row">
    <div class="content_left">
      <div><a href="#" class="content_label now_choice">廠商資料</a></div>
      <div><a href="${pageContext.request.contextPath}/vendoract/VendorActServlet?action=vendor_Order" class="content_label">我的訂單</a></div>
      <div><a href="${pageContext.request.contextPath}/vendoract/VendorActServlet?action=vendor_Activity" class="content_label">我的活動</a></div>
    </div>
    <div class="content_right">

      <div class="vendor_info">
        <div class="edit_zone">
          <a href="#" class="info_edit edit_active" id="info_edit">資料修改</a>
          <a href="#" class="info_edit" id="password_edit">密碼修改</a>
        </div>

        <div class="container_el" id="info_edit_zone">
          <form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
  
            <div class="info_zone">
              <h5>廠商基本資料維護</h5>
              <span class="info_label">公司名稱: </span>
              <span id="vcompany">${not empty venVO ? venVO.company : vendorVO.company}</span>
              <input type="hidden" name="vcompany" value="${not empty venVO ? venVO.company : vendorVO.company}">
              
              <span class="info_label">統一編號:</span> 
              <span id="vtaxid">${vendorVO != null? vendorVO.taxid : venVO.taxid}</span>
              <input type="hidden" name="vtaxid" value="${not empty venVO ? venVO.taxid : vendorVO.taxid}">
              
              <span class="info_label">廠商編號:</span>
              <span id="vendorid">${vendorVO != null? vendorVO.vendorid : venVO.vendorid}</span>
              <input type="hidden" name="vendorid" value="${not empty venVO ? venVO.vendorid : vendorVO.vendorid}">
              
              <br>
  
              <label for="vname">聯絡人員:</label>
              <input type="text" name="vname" value="${venVO != null ? venVO.name : vendorVO.name}" class="input_el" placeholder="" autocomplete="off">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["name"]}</font>
              </c:if>
              <br>
  
              <label for="vemail">電子郵件:</label>
              <input type="text" name="vemail" value="${not empty venVO ? venVO.email : vendorVO.email}" class="input_el" placeholder="" autocomplete="off">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["email"]}</font>
              </c:if>
              <br>
  
              <label for="vtel">聯絡電話:</label>
              <input type="text" name="vtel" value="${not empty venVO ? venVO.tel : vendorVO.tel}" class="input_el" placeholder="" autocomplete="off">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["tel"]}</font>
              </c:if>
              <br>
  
              <label for="vmobile">聯絡手機:</label>
              <input type="text" name="vmobile" value="${not empty venVO ? venVO.mobile : vendorVO.mobile}" class="input_el" placeholder="" autocomplete="off">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["mobile"]}</font>
              </c:if>
              <br> 
              <label for="vaddr">聯絡地址:</label>
              <div id="twzipcode"></div>
              <input type="text" name="vaddr" value="${addrs[2]}" id="detail_addr_input">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["addr"]}</font>
              </c:if>
              <br>

              <label for="vpassword">輸入密碼:</label>
              <input type="password" name="vpassword" value="" class="input_el" placeholder="請輸入密碼">
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["password"]}</font>
              </c:if>
              <br>
          
              <input type="hidden" name="action" value="update"> 
            </div>
            <div class="btn_div">
              <a href="${pageContext.request.contextPath}/vendor/VendorServlet?action=getOne_For_Update" id="reset_action">重新填寫</a>
              <a id="confirm_edit">確認修改</a>
              <input id="info_btn" type="submit" value="確認修改">
            </div>
          </form>
        </div>

        <div class="container_el" id="password_edit_zone" style="display: none;">
          <form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
  
            <div class="info_zone">
              <h5>修改密碼</h5>
              <h6> ( 提示: 密碼由8~10碼之英數字組成 )</h6><br>

              <label for="vpassword">輸入舊密碼:</label>
              <input type="password" name="vpassword" value="" class="input_el" placeholder="">
              <span class="password_alert" id="password_error" style="display:none;">密碼錯誤</span>
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["passwordOld"]}</font>
              </c:if>
              <br>
              
              <label for="passwordNew">輸入新密碼:</label>
              <input type="password" name="passwordNew" value="" class="input_el" placeholder="">
              <span class="password_alert" id="password_format" style="display:none;">密碼需由8~10碼之英數字組成</span>
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["passwordNew"]}</font>
              </c:if>
              <br>

              <label for="passwordConfirm">確認新密碼:</label>
              <input type="password" name="passwordConfirm" value="" class="input_el" id="new_confirm" placeholder="">
              <span class="password_alert" id="password_confirm" style="display:none;">確認密碼需與新密碼一致</span>
              <c:if test="${not empty errorMsgs}">
              	<font class="error_msgs">${errorMsgs["passwordConfirm"]}</font>
              </c:if>
              
              
              <input type="hidden" name="action" value="update_Password"> 
              <input type="hidden" name="vendorid" value="1">
  
            </div>
            <div class="btn_div_password">
              <input id="password_btn" type="submit" value="修改密碼">
            </div>
          </form>
        </div>
      </div> <!--vendor_info end tag-->
    </div>
  </div>
  
  <div class="pop_bg"></div>
  <div class="pop_alert">
  	<h3>提示</h3>
  	<h4>修改成功</h4>
  </div>

  <script src='${pageContext.request.contextPath}/vendor/jquery/jquery-3.6.0.min.js'></script>
  <script src='${pageContext.request.contextPath}/vendor/js/vendorInfo.js'></script>
  <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.twzipcode.min.js"></script>
  <script>
  
  	//所在標籤
  	var locate = `${vendorLocate}`;
  	var update = `${update}`;
  	
  	//zipcode設定
  	var city = `${addrs[0]}`;	
  	var town = `${addrs[1]}`;
  	console.log(locate);
	console.log(city);
  	
    $("#twzipcode").twzipcode({
    countySel: city, // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
    districtSel: town, // 地區預設值
    zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
    css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱
    countyName: "city", // 自訂城市 select 標籤的 name 值
    districtName: "town" // 自訂地區 select 標籤的 name 值
    });
    
    if(update == "success"){
    	$('.pop_bg').show();
    	$('.pop_alert').show();
    }
    
    $('.pop_bg, .pop_alert').on('click', function(){
    	$('.pop_bg').hide();
    	$('.pop_alert').hide();
    })
    
  </script>



</body>
</html>