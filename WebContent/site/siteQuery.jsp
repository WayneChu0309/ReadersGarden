<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.site.model.*"%>
<%@ page import="com.siteact.model.*"%>
<%@ page import="com.activity.model.*"%>

<% 
	SiteService siteSvc = new SiteService();
	SiteVO siteOne = siteSvc.findOneSite(1);
	request.setAttribute("siteOne", siteOne);
	List<SiteVO> sitelist = siteSvc.getAll();
	request.setAttribute("sitelist", sitelist);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>可租借場地查詢 - Reader's Garden</title>
<link rel='stylesheet' href='${pageContext.request.contextPath}/site/css/siteQuery.css'>

</head>
<body>
	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>

	<jsp:useBean id="siteVO" scope="request" class="com.site.model.SiteVO" />
	<jsp:useBean id="saSvc" scope="page" class="com.siteact.model.SiteActService" />
	<jsp:useBean id="actSvc" scope="page" class="com.activity.model.ActivityService" />

	<div class="latest-news">
		<div class="container">
			<img src="${pageContext.request.contextPath}/site/icon/dot.svg" alt="" class="dot">
			<h1>Rental Service</h1>
			<h3>可租借場地查詢</h3>
		</div>
	</div>

	<!-- Step1 選場地 -->
	<div class="element_site">
		<!-- RWD的step1-->
		<div class="step_div">
			<div class="step1 step_rwd">Step1: 選擇場地</div>
			<select name="site" id="site_select_rwd">
				<c:forEach var="site" items="${sitelist}">
					<option value="${site.siteid}"
						${siteVO.siteid == site.siteid? "selected":""}>${site.name}</option>
				</c:forEach>
			</select>
		</div>
		
		<!-- RWD的step1-->
		<div class="li_img">
			<div class="item">
				<img class="site_img" src="<%=request.getContextPath()%>/site/SiteImg?ID=${siteVO.name == null? "1" : siteVO.siteid}" alt="Theater">
				<!-- RWD的遮罩跟btn -->
				<div class="img_cover">
					<ul>
						<li id="li_title" class="site_name">${siteVO.name == null? siteOne.name : siteVO.name}</li>
						<li>場地面積: <span class="site_area">${siteVO.name == null? siteOne.area : siteVO.area}</span> 坪</li>
						<li>容納人數: 約 <span class="site_capacity">${siteVO.name == null? siteOne.capacity : siteVO.capacity}</span>人</li>
						<li>使用建議: <span class="acttcype">
							<c:forEach var="sa" items="${saSvc.all}">
								<c:if test="${sa.siteid == (siteVO.name == null? siteOne.siteid : siteVO.siteid)}">
									${actSvc.findOneActivity(sa.actid).acttype}
								</c:if>
							</c:forEach>
							</span>
						</li>
						<li>單日報價: $
							<span class="site_daycost" id="price"> 
								<fmt:formatNumber type="currency" pattern="#,###">${siteVO.name == null? siteOne.daycost : siteVO.daycost}</fmt:formatNumber>
							</span>/天
						</li>
					</ul>
				</div>
				<button class="site_btn" id="site_btn_rwd">pick</button>
				<!-- RWD的遮罩跟btn -->
			</div>
		</div>

		<ul class="site_des">
			<div class="step1" id="step1_normal">Step1: 選擇場地</div>
			<select name="site" id="site_select">
				<c:forEach var="site" items="${sitelist}">
					<option value=${site.siteid} ${siteVO.siteid == site.siteid? "selected":""}>${site.name}</option>
				</c:forEach>
			</select>
			<li>場地名稱: <span class="site_name">${siteVO.name == null? siteOne.name : siteVO.name}</span></li>
			<li>場地面積: <span class="site_area">${siteVO.name == null? siteOne.area : siteVO.area}</span>坪</li>
			<li>容納人數: 約 <span class="site_capacity">${siteVO.name == null? siteOne.capacity : siteVO.capacity}</span>人</li>
			<li>使用建議: <span class="acttype">
				<c:forEach var="sa" items="${saSvc.all}">
					<c:if test="${sa.siteid == (siteVO.name == null? siteOne.siteid : siteVO.siteid)}">
						${actSvc.findOneActivity(sa.actid).acttype}
					</c:if>
				</c:forEach></span>
			</li>
			<li>單日報價: $
				<span class="site_daycost" id="price">
					<fmt:formatNumber type="currency" pattern="#,###">${siteVO.name == null? siteOne.daycost : siteVO.daycost}</fmt:formatNumber>
				</span>/天
			</li>
			<button class="site_btn" id="site_btn">下一步 ></button>
		</ul>
	</div>


	<!-- Step2 選日期-->
	<div class="cal_bg">

		<!-- RWD的step2-->
		<div class="step_div" id="step2_rwd">
			<div class="step2 step_rwd">Step2: 選擇租借日期</div>
			<br> <select name="year" id="year_select_rwd">
				<option value="2021">2021年</option>
				<option value="2022">2022年</option>
				<option value="2023">2023年</option>
			</select> <select name="month" id="month_select_rwd">
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
		</div>
		<!-- RWD的step2-->

		<div class="cal_left">
			<div class="step2 step_rwd">Step2: 選擇租借日期</div>
			<br> <label for="">選擇年月：</label> <select name="year"
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
			<button id="change_btn">確定</button>
			<h4>請點擊右方月曆選擇日期區間</h4>
			<ul class="select_date">
				<li>租借開始: <span id="select_start">--</span></li>
				<li>租借結束: <span id="select_end">--</span></li>
			</ul>
			<button id="clear_btn_main">清除重選</button>
			<div class="alert_div" id="alert_normal">請選擇租借日期</div>
			<button id="day_btn">取得費用</button>
		</div>

		<div class="cal_right">
			<!-- calender 上面的字 -->
			<div class="cal_title">
				<h5 id="year_cal">2021</h5>
				<h5>年</h5>
				<h5 id="month_cal">11</h5>
				<h5>月</h5>
			</div>

			<!-- calender 本身 -->
			<div class="calender_container">
				<span class="prev" id="prev">
					<h5><</h5>
				</span>
				<div class="calender">
					<ul class="w-list">
						<li>Sun</li>
						<li>Mon</li>
						<li>Tue</li>
						<li>Wed</li>
						<li>Thu</li>
						<li>Fri</li>
						<li>Sat</li>
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
				<div class="text_checked">已預定</div>
				<div class="alert_div" id="alert_rwd">請選擇租借日期</div>

			</div>
			<button id="clear_btn" style="display: none;">清除重選</button>
			<button class="day_btn" id="day_btn_rwd">pick</button>

		</div>

	</div>


	<!-- 租費試算 -->


	<div class="cal_row">
		<div class="cal_div">
			<!-- <h5>租費試算</h5> -->
			<!-- RWD的step3-->
			<div class="step_div">
				<div class="step_rwd" id="step3_rwd">Step3: 租費試算 ✔</div>
			</div>
			<!-- RWD的step3-->
			<div class="step3">Step3: 租費試算 ✔</div>
			<br>
			<div class="step3_content">
				<h6>場地名稱：</h6>
				<span class="site_name" id="rnt_site">${siteVO.name == null? siteOne.name : siteVO.name}</span><br>
				<h6>租借開始：</h6>
				<span id="rnt_start">--</span><br>
				<h6>租借結束：</h6>
				<span id="rnt_end">--</span><br>
				<h6>單 日 費 用：</h6>
				<span>$<span id="rnt_daycost">${siteVO.name == null? siteOne.daycost : siteVO.daycost}</span></span><br>
				<h6>租 借 天 數：</h6>
				<span>x <span id="rnt_day">1</span></span><br>
				<hr>
				<h6 id="total">合 計：</h6>
				<span id="money">$<span id="rnt_total">--</span></span>
			</div>
		</div>
		<div class="cal_ask">
			<h4>報價內容明細</h4>
			<ul>
				<li>✓ 場 地 租 金</li>
				<li>✓ 水 電 費 用</li>
				<li>✓ 場 地 清 潔 費</li>
				<li>✓ 網 站 宣 傳 廣 告</li>
				<li>✓ 售 票 平 台 服 務 費</li>
			</ul>
			<h5>點擊下方按鈕即可進入申請頁面</h5>
			<h6>若有特殊需求, 歡迎來電或email諮詢, 將有專人為您服務。</h6>
			<form method="post" action="<%=request.getContextPath()%>/site/SiteServlet">
				<input type="hidden" name="action" value="getOne_For_Apply">
				<input id="site_input" type="hidden" name="siteName" value="${siteVO.name == null? siteOne.name : siteVO.name}">
				<input id="rntstart_input" type="hidden" name="rntStart" value="">
				<input id="rntend_input" type="hidden" name="rntEnd" value="">
				<input id="daycost_input" type="hidden" name="daycost" value="${siteVO.name == null? siteOne.daycost : siteVO.daycost}">
				<input id="daytotal_input" type="hidden" name="dayTotal" value="">
				<input id="siteid_input" type="hidden" name="siteid" value="${siteVO.name == null? "1" : siteVO.siteid}">
				<button type="submit" id="apply_btn">我要申請</button>
			</form>
		</div>
	</div>

	<%@ include file="/parts/footer.text"%>
	
	<script>

	// 	給Ajax用
	 var showDes_url = "<%=request.getContextPath()%>/site/SiteShowDes";
	 var showCate_url = "<%=request.getContextPath()%>/site/SiteShowCate"; 
	 var showOccupied_url = "<%=request.getContextPath()%>/vendoract/SiteOccupied";
	
	
	</script>
	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='${pageContext.request.contextPath}/site/js/siteQuery.js'></script>

</body>
</html>