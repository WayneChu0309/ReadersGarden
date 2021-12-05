<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>資料確認 - Reader's Garden</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/site/css/siteApplyConfirm.css'>

</head>
<body onload="connect();">
	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>

	<div class="latest-news">
		<div class="container">
			<img src="${pageContext.request.contextPath}/site/icon/dot.svg" alt="" class="dot">
			<h1>Rental Service</h1>
			<h3>租 借 申 請</h3>

		</div>
	</div>
	<!-- latest-news end -->

	<!-- progress start -->
	<div class="progress">
		<ol class="progress_ol">
			<li class="progress_li"><span
				class="progress_circle progress_check">✔</span> <span
				class="progress_text">您的選擇</span></li>
			<li class="line"></li>
			<li class="progress_li"><span
				class="progress_circle progress_check" id="p2">✔</span> <span
				class="progress_text">您的活動</span></li>
			<li class="line"></li>
			<li><span class="progress_circle" id="p3">3</span> <span
				class="progress_text">最後一步</span></li>
		</ol>
	</div>
	<!-- progress end -->

	<div class="confirm_container">
		<h3>
			親愛的用戶您好：<br>請於提交申請前，再次確認以下資料是否正確，謝謝您的協助！
		</h3>

		<div class="title_div">
			<h4>租借選擇</h4>
		</div>

		<table class="rnt_table">
			<tr>
				<td class="col_name">場地名稱</td>
				<td>${sitename}</td>
			</tr>
			<tr>
				<td class="col_name">租借時間</td>
				<td>${vactVO.rtlstart} ~ ${vactVO.rtlend}</td>
			</tr>
			<tr>
				<td class="col_name">租借天數</td>
				<td>${dayTotal}天</td>
			</tr>
			<tr>
				<td class="col_name">單日費用</td>
				<td>${daycost}元</td>
			</tr>
			<tr>
				<td class="col_name">總金額</td>
				<td>${vactVO.amount}元</td>
			</tr>
		</table>

		<div class="title_div">
			<h4>活動資訊</h4>
		</div>

		<table class="act_table">
			<tr>
				<td class="col_name">活動圖片</td>
				<td><img src='${pageContext.request.contextPath}/vendoract/VendorActImg?action=previewImg' id='preview_img'></td>
			</tr>
			<tr>
				<td class="col_name">活動名稱</td>
				<td>${vactVO.name}</td>
			</tr>
			<tr>
				<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService"/>
				<td class="col_name">活動類型</td>
				<td>${actSvc.findOneActivity(vactVO.actid).acttype}</td>
			</tr>
			<tr>
				<td class="col_name">活動時間</td>
				<td>${vactVO.actstart} ~ ${vactVO.actend}</td>
			</tr>
			<tr>
				<td class="col_name">售票時間</td>
				<td>${vactVO.tkstart} ~ ${vactVO.tkend}</td>
			</tr>
			<tr>
				<td class="col_name">售票單價</td>
				<td>${vactVO.price}元</td>
			</tr>
			<tr>
				<td class="col_name">活動內容</td>
				<td>${vactVO.actcnt}</td>
			</tr>
		</table>

		<div class="title_div">
			<h4>租借單位</h4>
		</div>

		<table class="basic_table">
			<tr>
				<td class="col_name">廠商編號</td>
				<td id="vendorid">${vendorVO.vendorid}</td>
			</tr>
			<tr>
				<td class="col_name">公司名稱</td>
				<td>${vendorVO.company}</td>
			</tr>
			<tr>
				<td class="col_name">統一編號</td>
				<td>${vendorVO.taxid}</td>
			</tr>
			<tr>
				<td class="col_name">負責人</td>
				<td>${vendorVO.name}</td>
			</tr>
			<tr>
				<td class="col_name">信箱</td>
				<td>${vendorVO.email}</td>
			</tr>
			<tr>
				<td class="col_name">連絡電話</td>
				<td>${vendorVO.tel}</td>
			</tr>
			<tr>
				<td class="col_name">地址</td>
				<td>${vendorVO.addr}</td>
			</tr>
		</table>
	</div>
	<div class="btn_div">
		<button id="edit_btn">修改資料</button>
		<button id="apply_btn">提交申請</button>

	</div>


	<div class="pop_bg"></div>
	<div class="alert_pop" id="success">
		<h4>提交申請成功</h4>
		<h5>
			訂單處理中, 您的訂單編號為<span id="new_vactid">1001</span>
		</h5>
		<h5>
			結果通知信將於<span>24小時內</span>寄發至您的信箱，<br>請多加留意，謝謝。
		</h5>
		<h6 id="redirect_click">點擊返回首頁，或10秒後自動跳轉</h6>
	</div>
	<div class="alert_pop" id="error">
		<h4>提交申請失敗</h4>
		<h5 id="eror_message">所選時間已被預訂，請重新選擇</h5>
		<h6 id="return_btn">返回前頁</h6>
	</div>

	<%@ include file="/parts/footer.text"%>
	<script>
		var redirect_url = `${pageContext.request.contextPath}/home/home.jsp`;
		var back_url = `${pageContext.request.contextPath}/site/siteApply.jsp`;
		var apply_url = `${pageContext.request.contextPath}/vendoract/NewVendorAct`;
		
		var MyPoint = "/vendoract/VendoractWebSocket";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		
		var webSocket;
		
		function connect() {
	  		webSocket = new WebSocket(endPointURL);
	  		webSocket.addEventListener("open", function(){
 	  			console.log("connect")
 	  		})
		}
	</script>
	
	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='${pageContext.request.contextPath}/site/js/siteApplyConfirm.js'></script>
	
</body>
</html>