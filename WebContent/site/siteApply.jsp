<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>租借申請 - Reader's Garden</title>
<link rel='stylesheet' href='${pageContext.request.contextPath}/site/css/siteApply.css'>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.css" />
</head>

<body>
	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>

	<div class="latest-news">
		<div class="container">
			<img src="${pageContext.request.contextPath}/site/icon/dot.svg" alt="" class="dot">
			<h1>Rental Service</h1>
			<h3>租 借 申 請</h3>

		</div>
	</div>
	<div class="progress">
		<ol class="progress_ol">
			<li class="progress_li"><span class="p1">1</span> <span
				class="progress_text">您的選擇</span></li>
			<li class="line"></li>
			<li class="progress_li"><span class="progress_circle" id="p2">2</span>
				<span class="progress_text">您的活動</span></li>
			<li class="line"></li>
			<li><span class="progress_circle" id="p3">3</span> <span
				class="progress_text">最後一步</span></li>
		</ol>
	</div>
	<!-- latest-news end -->
		
	<div class="act_row">
		<div class="act_left">

			<div class="choose_div act_container left_side">
				<h3>您的租借資訊</h3>
				<h4 class="errorMsgs">${errorMsgs["left"]}</h4>
				<jsp:useBean id="siteSvc" scope="page" class="com.site.model.SiteService"/>	
				<label for="site">選擇場地：</label> <select name="site" id="site_select">
					<c:forEach var="siteVO" items="${siteSvc.all}">
						<option value="${siteVO.siteid}">${siteVO.name}</option>
					</c:forEach>
				</select><br> <label for="">選擇年月：</label> <select name="year"
					id="year_select">
					<option value="2021">2021年</option>
					<option value="2022">2022年</option>
					<option value="2023">2023年</option>
				</select> <select name="month" id="month_select">
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>
				<button id="search_btn">選擇租借時間</button>
			</div>

			<div class="site_div act_container left_side">
				<h3>您的租借資訊</h3>
				<div class="time_choose">
					<div class="time_left">
						<h4>開始時間</h4>
						<span id="show_start">${rntStartFormat}</span><br> <span
							id="show_wstart">${rntStartWeek}</span><br> <span id="show_tstart">20:00
							~ 22:00</span>
					</div>
					<div class="time_right">
						<h4>結束時間</h4>
						<span id="show_end">${rntEndFormat}</span><br> <span
							id="show_wend">${rntEndWeek}</span><br> <span id="show_tend">19:00
							前</span>
					</div>
				</div>
				<h4>總共租借：</h4>
				<span id="show_day">${dayTotal} 天</span><br>
				<button id="change_time_btn">租借日期不正確嗎？</button>
				<button id="change_time_btn_none" style="display:none;">here</button>
				<hr>
								
				<h4>已選擇</h4>
				<span id="show_site">${sitename}</span><br> 
				<img id="show_img" src="" alt="Theater"><br>
				<button id="change_btn">更改選擇</button>

				<hr>
				<h4>租費計算</h4>
				<div class="rnt">
					<span>$
						<span id="rnt_daycost">${daycost}</span>
					</span> 
					<span>x 
						<span class="rnt_day">${dayTotal} </span>
						天
					</span> 
					<span id="money">= $
						<span id="rnt_total"></span>
					</span>
				</div>
			</div>

			<div class="notice_div act_container left_side" id="notice_normal">
				<h3>注意事項</h3>
				<hr>
				<ul class="notice_zone">
					<li>1. 單日報價包含項目: 場地租金、水電費用、場地清潔費、本網站宣傳廣告服務，以及售票平台服務費。</li>
					<li>2. 場地租借以「天」為計算單位，使用時間為當日20:00至隔日19:00；超時以每小時$10,000(含稅)計算。</li>
					<li>3. 場地租借無附加任何活動用停車位及折抵優惠，但可依活動提供平日包車格服務。</li>
					<li>4. 各場館除基本設備外，器材如需租借請填租借表單，費用另計。</li>
				</ul>
			</div>
		</div>

		<div class="act_right">
			<div class="act_div act_container">
				<h3>填寫活動資訊</h3>
				
				<form method="post" action="<%=request.getContextPath()%>/vendoract/VendorActApply" enctype="multipart/form-data">
				<input type="hidden" name="action" value="info_For_Confirm">
				<input id="rntstart_input" type="hidden" name="rntStart" value="${vactVO.rtlstart}">
				<input id="rntend_input" type="hidden" name="rntEnd" value="${vactVO.rtlend}">
				<input id="siteid_input" type="hidden" name="siteid" value="${vactVO.siteid}">
				<input id="total_input" type="hidden" name="total" value="${vactVO.amount}">	
				<input id="sitename_input" type="hidden" name="sitename" value="${sitename}">	
				<input id="daycost_input" type="hidden" name="daycost" value="${daycost}">
				<input id="rntstart_format_input" type="hidden" name="rntStartFormat" value="${rntStartFormat}">
				<input id="rntend_format_input" type="hidden" name="rntEndFormat" value="${rntEndFormat}">
				<input id="rntstart_w_input" type="hidden" name="rntStartW" value="${rntStartWeek}">
				<input id="rntend_w_input" type="hidden" name="rntEndW" value="${rntEndWeek}">
				<input id="dayTotal_input" type="hidden" name="dayTotal" value="${dayTotal}">			
							
				<div class="act_el">
					<h4 class="errorMsgs">${errorMsgs["actname"]}</h4>
					<label for="name">活動名稱</label> 
					<input type="text" name="name" value="${vactVO == null? "":vactVO.name}" class="input_el" placeholder="請輸入活動名稱">
				</div>
				
				<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />
				<div class="act_el">
					<label for="type">活動類型</label> 
					<select class="type_choose" name="actid">
						<c:forEach var="act" items="${actSvc.all}">
						<option value="${act.actid}" ${vactVO.actid == act.actid? "selected":""}>${act.acttype}</option>
						</c:forEach>
					</select>
				</div>
				<div class="act_el">
					<h4 class="errorMsgs datestart">${errorMsgs["actstart"]}</h4>
					<h4 class="errorMsgs dateend">${errorMsgs["actend"]}</h4><br>
					<label for="">活動日期</label> 
<!-- 					<input type="text" name="test" value="test"> -->
					<input name="act_start_date" value="${vactVO == null? "":vactVO.actstart}" id="act_start_date" type="text" placeholder="活動開始日" autocomplete="off"> 
					<span>至</span> 
					<input name="act_end_date" value="${vactVO == null? "":vactVO.actend}" id="act_end_date"  type="text" placeholder="活動結束日" autocomplete="off">
					<br> 
					
				</div>
				<div class="act_el">
					<h4 class="errorMsgs datestart">${errorMsgs["tkstart"]}</h4>
					<h4 class="errorMsgs dateend">${errorMsgs["tkend"]}</h4><br>
					<label for="">售票日期</label> 
					<input name="tk_start_date" value="${vactVO == null? "":vactVO.tkstart}" id="tk_start_date" type="text" placeholder="售票開始日" autocomplete="off"> 
					 
					<span>至</span> 
					<input name="tk_end_date" value="${vactVO == null? "":vactVO.tkend}" id="tk_end_date"  type="text" placeholder="售票結束日" autocomplete="off">
				</div>
				<div class="act_el">
					<h4 class="errorMsgs">${errorMsgs["price"]}</h4>
					<label for="tkprice">售票單價</label> 
					<input type="text" name="tkprice" value="${vactVO == null? "":vactVO.price}" class="input_el" placeholder="請輸入票價">
				</div>
				<div class="act_el">
					<h4 class="errorMsgs">${errorMsgs["actcnt"]}</h4>
					<label for="activity_content">活動內容</label>
					<textarea name="activity_content" rows="10" cols="37" placeholder="請填寫活動內容">${vactVO == null? "":vactVO.actcnt}</textarea>
				</div>
				<div class="act_el">
					<h4 class="errorMsgs">${errorMsgs["img"]}</h4>
					<label for="pic">宣傳圖片</label> 
					<input type="file" name="pic" value="" class="input_el" placeholder="請輸入票價">
					<div class="preview">
						<img src='${pageContext.request.contextPath}/vendoract/VendorActImg?action=previewImg' id='preview_img' ${vactVO.img == null? "style='display:none;'" :""}>
						<h6 class="preview_text" ${vactVO.img != null? "style='display:none;'" :""}>預覽圖</h6>
					</div>
				</div>		
				<input type="submit" value="提交" id="submit_btn" style= "display:none;">
			</form>
			</div>

			<div class="notice_div act_container" id="notice_rwd">
				<h3>注意事項</h3>
				<hr>
				<ul class="notice_zone">
					<li>1. 單日報價包含項目: 場地租金、水電費用、場地清潔費、本網站宣傳廣告服務，以及售票平台服務費。</li>
					<li>2. 場地租借以「天」為計算單位，使用時間為當日20:00至隔日19:00；超時以每小時$10,000(含稅)計算。</li>
					<li>3. 場地租借無附加任何活動用停車位及折抵優惠，但可依活動提供平日包車格服務。</li>
					<li>4. 各場館除基本設備外，器材如需租借請填租借表單，費用另計。</li>
				</ul>
			</div>

			<button type="submit" id="next_btn">下一步 : 最終資料確認 ></button>		
		</div>
	</div>
	
	<div class="pop_bg"></div>
	<div class="cal_pop">
		<h6>
			請<span>點擊</span>選擇租借開始與結束日
		</h6>

		<img src="${pageContext.request.contextPath}/site/icon/close.svg" alt="" id="close_pop">

		<!-- calender -->
		<div class="cal_title">
			<h5 id="year_cal">2021</h5>
			<h5>年</h5>
			<h5 id="month_cal">11</h5>
			<h5>月</h5>
		</div>

		<div class="calender_container">
			<span class="prev" id="prev">
				<h5><</h5>
			</span>
			<div class="calender">
				<ul class="w-list">
					<li>週日</li>
					<li>週一</li>
					<li>週二</li>
					<li>週三</li>
					<li>週四</li>
					<li>週五</li>
					<li>週六</li>
				</ul>
				<ul class="day-list" id="day-list">
					<li></li>
					<li>1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
					<li>6</li>
					<li>7</li>
					<li>8</li>
					<li>9</li>
					<li>10</li>
					<li>11</li>
					<li>12</li>
					<li>13</li>
					<li>14</li>
					<li>15</li>
					<li>16</li>
					<li>17</li>
					<li>18</li>
					<li>19</li>
					<li>20</li>
					<li>21</li>
					<li>22</li>
					<li>23</li>
					<li>24</li>
					<li>25</li>
					<li>26</li>
					<li>27</li>
					<li>28</li>
					<li>29</li>
					<li>30</li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
			<span class="next" id="next">
				<h5>></h5>
			</span>
		</div>

		<div class="calender_btm">
			<div class="div_checked"></div>
			<h5 class="text_checked">已預定</h5>
		</div>
		<ul class="select_date">
			<li>租借開始日: <span id="select_start">--</span></li>
			<li>租借結束日: <span id="select_end">--</span></li>
		</ul>
		<button id="confirm_btn">確認</button>
	</div>

	<%@ include file="/parts/footer.text"%>
	<script>
	 var showDes_url = "<%=request.getContextPath()%>/site/SiteShowDes";
	 var showOccupied_url = "<%=request.getContextPath()%>/vendoract/SiteOccupied";
	 var rntStart = "<%=request.getParameter("rntStart")%>";
	 var rntEnd = "<%=request.getParameter("rntEnd")%>";
	 var img_url = "${pageContext.request.contextPath}/site/SiteImg?ID=";

	</script>
	
	<script src='${pageContext.request.contextPath}/site/jquery/jquery-3.6.0.min.js'></script>
	<script src='${pageContext.request.contextPath}/site/js/siteApply.js'></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/datetimepicker/jquery.datetimepicker.full.js"></script>
	
</body>
</html>