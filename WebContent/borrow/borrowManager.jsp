<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%

int actYear = java.time.LocalDate.now().getYear();
int actMonth = java.time.LocalDate.now().getMonthValue();
int actDay = java.time.LocalDate.now().getDayOfMonth();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/borrow/css/borrowManager.css'>
</head>
<body>

<%@ include file="/parts/header.text" %>
<%@ include file="/parts/manager.text" %>

<section id="manager_area">
	<section id="manager">
	    <div class="action">
	      <form method="POST" id="manageraction" action="${pageContext.request.contextPath}/borrow/BorrowServlet.do">
	      	<input type="text" id="action" name="action" value="" style="display:none">
	        <input type="text" id="memberinput" name="memberId" value="" placeholder="請輸入會員編號..." autocomplete="off">
	        <button class="btn memberselect" disabled>會員訂單查詢</button>
	        <button class="btn orderselect">館內預約訂單查詢</button>
	        <button class="btn cancelselect">館內取消訂單查詢</button>
	        <button class="btn selectall">館內逾期訂單查詢</button>
	        <button class="btn addorder">新增借閱訂單</button>
	      </form>
	        <c:if test="${not empty selMember}">
	        
		        <form class="memberform" action="${pageContext.request.contextPath}/borrow/BorrowServlet.do">
			        <p>會員姓名:${selMember.name}</p>
			        <input type="text" name="memberId" value="${selMember.number}" style="display:none">
			        <input type="text" id="action2" name="action" value="" style="display:none">
			        <button class="btn illegltotal" data-number='${memberBorrow["illgelNumber"]}'>取消及逾期訂單共${memberBorrow["illgelNumber"]}筆</button>
			        <button class="btn normaltotal" data-number='${memberBorrow["successNumber"]}'>正常訂單共${memberBorrow["successNumber"]}筆</button>
	      		</form>
	        </c:if>
	      
	    </div>
	    <div class="choice">
	    	<button class="btn checkall">全部選取</button>
	        <button class="btn allsubmit" disabled>送出</button>
	      <div class="orderitems">
	        <ul class="title">
	          <li class="membername">會員姓名</li>
	          <li class="stockname">館藏名稱</li>
	          <li class="listid">館藏編號</li>
	          <li class="preBoDate">預訂日期</li>
	          <li class="actBoDate">借閱日期</li>
	          <li class="preReDate">預計歸還日期</li>
	          <li class="actReDate">實際歸還日期</li>
	          <li class="bostates">借閱狀態</li>
	          <li class="update">變更</li>
	          <li class="move">勾選</li>
	        </ul>
	        
	        <h1>
		        <c:choose>
		      	  <c:when test="${selMember != null && boList.size() == 0 && memberBorrow == null}">
		      		  會員姓名:${selMember.name}---無未完成借閱訂單---
		      	  </c:when>
		      	  <c:when test="${boList.size() == 0 && selMember != null}">
		      		  查無紀錄
		      	  </c:when>
		      	  <c:when test="${memberId == 0}">
		      		  查無會員
		      	  </c:when>
		        </c:choose>
	        </h1>
	     <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>
	      <c:forEach var="borrow" items="${boList}">
	        <ul class="orderitem">
	          <li class="membername">${memSvc.getOneMember(borrow.memberId).name}</li>
	          <li class="stockname">
	            <span><p>${borrow.stockBean.stockName}</p><img src="${pageContext.request.contextPath}/stock/StockImgHibernate?action=showImg&ID=${borrow.stockBean.stockId}" alt=""></span>
	           <c:choose>
	           		<c:when test="${borrow.stockBean.stockName.length() > 3}">
	           			${borrow.stockBean.stockName.substring(0, 3)}...
	           		</c:when>
	           		<c:otherwise>
	           			${borrow.stockBean.stockName}
	           		</c:otherwise>
	           </c:choose>
	          </li>
	          <li class="listid" data-listId="${borrow.listId}">${borrow.listId}</li>
	          <li class="preBoDate">${borrow.preBoDate == null ? "---" : borrow.preBoDate}</li>
	          <li class="actBoDate">${borrow.actBoDate == null ? "---" : borrow.actBoDate}</li>
	          <li class="preReDate">${borrow.preReDate == null ? "---" : borrow.preReDate}</li>
	          <li class="actReDate">${borrow.actReDate == null ? "---" : borrow.actReDate}</li>
	          <li class="bostates" data-bostates="${borrow.boStates}">
	          </li>
	          <li class="update"><button data-borrowId="${borrow.borrowId}" disabled>借閱</button></li>
	          <li class="move">
		          <c:if test="${borrow.boStates <= 3 or borrow.boStates == 8 or borrow.boStates == 6}">
			          <input type="checkbox">
			      </c:if>
		      </li>
	        </ul>     
	      </c:forEach>
	        
	      </div>
	
	    </div>
  </section>
  </section>
  <section class="loadfilter">
    <div id="loading">
      <span style="--i:1;"></span>  
      <span style="--i:2;"></span>  
      <span style="--i:3;"></span>  
      <span style="--i:4;"></span>  
      <span style="--i:5;"></span>  
      <span style="--i:6;"></span>  
      <span style="--i:7;"></span>  
      <span style="--i:8;"></span>  
      <span style="--i:9;"></span>  
      <span style="--i:10;"></span>  
      <span style="--i:11;"></span>  
      <span style="--i:12;"></span>  
      <span style="--i:13;"></span>  
      <span style="--i:14;"></span>  
      <span style="--i:15;"></span>  
      <span style="--i:16;"></span>  
      <span style="--i:17;"></span>  
      <span style="--i:18;"></span>  
      <span style="--i:19;"></span>  
      <span style="--i:20;"></span>  
    </div>
  </section>

<%@ include file="/parts/footer.text" %>
	
  <script>
  	$("a#artist").addClass("select");
  	
  	var URL = "${pageContext.request.contextPath}/borrow/MemberBorrowAjax.do";
  	var orderData = {};
  	orderData['memberId'] = "${memberId}";
  	
  	$("button.addorder").on("click", function(){
  	   var additems = '<ul class="orderitem add">';
        additems +=  '<li class="membername"><input type="text" placeholder="*會員編號" value="${memberId == null ? "" : memberId}" ${memberId == null ? "" : "disabled"} autocomplete="off"></li>';
        additems += '<li class="stockname">---</li>';
        additems += '<li class="listid"><input type="text" placeholder="*必填欄位..." autocomplete="off"></li>';
        additems += '<li class="preBoDate">---</li>';
        additems += '<li class="actBoDate"><%=actYear%>-<%=actMonth%>-<%=actDay%></li>';
        additems += '<li class="preReDate">---</li>';
        additems += '<li class="actReDate">---</li>';
        additems += '<li class="bostates" data-bostates="0">---</li>';
        additems += '<li class="update"><button disabled>借閱</button></li>';
        additems += '<li class="move"><input type="checkbox" disabled></li></ul>';
       $("ul.title").after(additems);
       return false;
  	})
  	
  </script>  
  <script src='${pageContext.request.contextPath}/borrow/js/borrowManager.js'></script>
</body>
</html>