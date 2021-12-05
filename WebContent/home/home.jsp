<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
String nowTime = df.format(new java.util.Date());
int actYear = java.time.LocalDate.now().getYear();
int actMonth = java.time.LocalDate.now().getMonthValue();
int actDay = java.time.LocalDate.now().getDayOfMonth();
int firstDayOfMonth = java.time.LocalDate.of(actYear, actMonth, 1).getDayOfWeek().getValue();

%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/home/css/home.css'>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/home/css/home_media.css'>
</head>
<body onload="connect();">

<%@ include file="/parts/header.text" %>
<%@ include file="/parts/slide.text" %>
  

  <div class="latest-news">
    <div class="container">
      <img src="${pageContext.request.contextPath}/home/icon/dot.svg" alt="" class="dot">
      <h1>Latest News</h1>
      <h3>最新消息</h3>
    </div>
  </div>
  <%-- search start --%>
  <section class="search">
    <div class="container">
      <div class="row">
        <div class="search">
          <div class="search-bar">
            <input type="search" placeholder="How to...">
            <button type="button"><img src="${pageContext.request.contextPath}/home/icon/search.svg" alt=""></button>
          </div>
        </div>
        <div class="title">
          <ul>
            <li>all</li>
            <li>event</li>
            <li>book</li>
            <li>group</li>
            <li>news</li>
          </ul>
        </div>
      </div>
    </div>
  </section>
 <%-- search end --%>



  <section class="info">
    <div class="container">
      <div class="row">
        <div class="cal">
          <div class="cal-items">
            <h4 class="today">SEP<br>'3</h4>
            <ul class="day">
              <li>S</li>
              <li>M</li>
              <li>T</li>
              <li>W</li>
              <li>T</li>
              <li>F</li>
              <li>S</li>
            </ul>
            <ul class="days">
            </ul>
          </div>
          <div class="seat">
            <div class="text-time">
              <h3>CAPACITY:</h3>
              <p>Latest updated:<%=nowTime%></p>
            </div>
            <h2>${capacity}</h2>
          </div>
        </div>
        <div class="news">
          <div class="new-event">
            <h2 class="title">event:${newAct.get(0).name}</h2>
            <h3 class="name">售票時間:${newAct.get(0).tkstart}至${newAct.get(0).tkend}</h3>
            <a href="${pageContext.request.contextPath}/ticket/VendorTicketServlet?action=view&vactid=${newAct.get(0).vactid}">${newAct.get(0).actcnt}</a>
          </div>
          <div class="new-group">
            <h2 class="title">group:${newEvent.get(0).eventname}</h2>
            <h3 class="name">報名時間:
	            <fmt:formatDate pattern="yyyy-MM-dd" value="${newEvent.get(0).eventappstart}"/>至
	            <fmt:formatDate pattern="yyyy-MM-dd" value="${newEvent.get(0).eventappend}"/>
            </h3>
            <a href="<%=request.getContextPath()%>/event/EventServlet?action=getOne_event&eventid=${newEvent.get(0).eventid}">${newEvent.get(0).eventdescription}</a>
          </div>
          <div class="new-bulletin">
            <h2 class="title">news ${newBulletin.buDate}</h2>
            <h3 class="name">公告
            <c:if test="${member.status eq '管理員'}">
            	<button id="changebulletin">修改公告</button>
            </c:if>
            </h3>
            <textarea id="bulletin" disabled>
	            ${newBulletin.buContent}
            </textarea>
          </div>
        </div>
      </div>
    </div>
  </section>

<%@ include file="/parts/footer.text" %>
  <script src='${pageContext.request.contextPath}/home/js/home.js'></script>
  <script>
  	var month_list = ['','JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC'];
  	
  	var bulletinURL = "${pageContext.request.contextPath}/bulletin/BulletinAjax";
  	
  	$("button#changebulletin").on("click", function(){
  		if ($(this).filter(".add").length == 0) {
  			$(this).toggleClass("add");
  			$("textarea#bulletin").attr("disabled", false);
  			// 編輯框加class 改樣式
  			$("textarea#bulletin").toggleClass("add");
  		} else {
  			$(this).toggleClass("add");
  			$("textarea#bulletin").toggleClass("add");
  			$("textarea#bulletin").attr("disabled", true);
  			var bulletin = $("textarea#bulletin").val().trim();
  			changeBulletin(bulletin)
  		}
  	})
  	
  	$("textarea#bulletin").on("change", function(){
  		$(this).val($(this).val.trim());
  	})
  	
  	
  	
  	
  	// 還沒加會員編號驗證管理員 / fixed
  	function changeBulletin(bulletin){
  		$.ajax({
			type: "POST",
	        url: bulletinURL,
	        data: {"buContent":bulletin},
	        dataType: "json",
	        success: function (res) {
				$("textarea#bulletin").val(res["buContent"])
				initTextarea();
				var date = "news " + dateformat(res["buDate"]);
				$("div.new-bulletin h2.title").html(date);
	        }
		})
  	}
  	
  	
  	
  	
  	
  	
  	var now_year = <%=actYear%>
	var now_month = <%=actMonth%>
	var nowDay = <%=actDay%>

  	var capacity = "${capacity}";
  	  
  	var MyPoint = "/CapacityWebSocket/Capacity";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
  	
  	function connect() {
  		webSocket = new WebSocket(endPointURL);
  		
  		webSocket.addEventListener("open", function(){
  			webSocket.send(1);
  		})
  		
  		webSocket.addEventListener("message", function(e){
  			var info = JSON.parse(e.data)
  			$(".text-time p").text("Latest updated:" + Object.values(info)[0]);
  			$(".seat h2").text(Object.keys(info)[0]);
  		})
  	}
  
  </script>
</body>
</html> 