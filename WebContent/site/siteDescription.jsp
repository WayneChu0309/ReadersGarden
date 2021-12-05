<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.site.model.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="com.siteact.model.*"%>

<%
	SiteService siteSvc = new SiteService();
	List<SiteVO> list = siteSvc.getAll();
	session.setAttribute("list", list);

	ActivityService actSvc = new ActivityService();
	List<ActivityVO> category = actSvc.getAll();
	session.setAttribute("actSvc", actSvc);
	session.setAttribute("category", category);

	SiteActService saSvc = new SiteActService();
	List<SiteActVO> salist = saSvc.getAll();
	session.setAttribute("salist", salist);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>場地介紹 - Reader's Garden</title>
<link rel='stylesheet' href='${pageContext.request.contextPath}/site/css/siteDes.css'>

</head>
<body>
	<%@ include file="/parts/header.text"%>
	<%@ include file="/parts/slide.text"%>

	<div class="latest-news">
		<div class="container">
			<img src="${pageContext.request.contextPath}/site/icon/dot.svg" alt="" class="dot">
			<h1>Rental Service</h1>
			<h3>場 地 介 紹</h3>
		</div>
	</div>


	<div class="s_row">
		<!-- side start-->
		<div class="side">
			<h3 class="s_tag">分類標籤</h3>
			<h4>使用建議</h4>
			<ul>
				<li><a href="SiteServlet?action=getAll">全部</a></li>
				<c:forEach var="category" items="${category}">
					<li data-cate="${category.actid}"><a
						href="${pageContext.request.contextPath}/site/SiteServlet?action=getOne_Category&actid=${category.actid}">${category.acttype}</a></li>
				</c:forEach>

			</ul>
			<button type="button" name="button" class="side_btn" id="choose_btn">進階篩選+</button>

			<div class="choose_div -none">
				<div class="choose_title">
					<h3>進階篩選</h3>
					<h4 id="side_back">收起</h4>
				</div>

				<h4>場地面積</h4>
				<span class="range_el">> <span class="range_value"
					id="area_v">20</span>坪
				</span>
				<div class="range-wrap">
					<input class="range_input" id="area_range" type="range" min="20"
						max="100" value="20" step="10">
				</div>

				<h4>單日報價</h4>
				<span class="range_el">< $<span class="range_value"
					id="daycost_v">15000</span>/日
				</span>
				<div class="range-wrap">
					<input class="range_input" id="daycost_range" type="range"
						min="15000" max="150000" value="6000" step="5000">
				</div>

				<h4>容納人數</h4>
				<span class="range_el">> <span class="range_value"
					id="capacity_v">20</span>人
				</span>

				<div class="range-wrap">
					<input class="range_input" id="capacity_range" type="range"
						min="20" max="100" value="20" step="10">
				</div>

				<button type="submit" name="button" class="side_btn" id="select_btn">篩選</button>
			</div>
		</div>
		<!-- side end-->

		<!-- content start-->
		<div class="content">

			<c:forEach var="site" items="${list}">
				<div class="s_element">
					<div class="li_img">
						<img src="<%=request.getContextPath()%>/site/SiteImg?ID=${site.siteid}" alt="">
					</div>
					<h5>${site.name}</h5>
					<ul class="site_des">
						<li>場地面積: ${site.area}坪</li>
						<li>容納人數: ${site.capacity}人</li>
						<li>使用建議: 
							<c:forEach var="sa" items="${salist}">
								<c:if test="${sa.siteid == site.siteid}">
									${actSvc.findOneActivity(sa.actid).acttype}
								</c:if>
							</c:forEach>
						</li>

						<li>單日報價: $ <fmt:formatNumber type="currency" pattern="#,###">${site.daycost}</fmt:formatNumber>/天</li>
					</ul>
					<form method="post" action="<%=request.getContextPath()%>/site/SiteServlet">
						<input type="hidden" name="siteid" value="${site.siteid}">
						<input type="hidden" name="action" value="getOne_For_Query"> 
						<button type="submit" name="button" class="query_btn">查詢時間</button>
					</form>
				</div>
			</c:forEach>

		</div>
	</div>
	<!-- content end-->

	<%@ include file="/parts/footer.text"%>
	<script>
		var siteFilter_url = "<%=request.getContextPath()%>/site/SiteFilter";
		var siteShowCate_url = "<%=request.getContextPath()%>/site/SiteShowCate";
		var siteServlet_url = "<%=request.getContextPath()%>/site/SiteServlet";
	
	</script>
	<script src='./jquery/jquery-3.6.0.min.js'></script>
	<script src='${pageContext.request.contextPath}/site/js/siteDes.js'></script>



</body>
</html>
