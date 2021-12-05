<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 	
// 	request.setAttribute("memberId", 1);
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
  <link rel='stylesheet' href='${pageContext.request.contextPath}/Stock/css/stock.css'>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/Stock/css/stock_media.css'>
</head>
<body>
<%@ include file="/parts/header.text" %>
<%@ include file="/parts/slide.text" %>

  <div class="latest-news"  id="target-page">
    <div class="container">
      <img src="${pageContext.request.contextPath}/Stock/icon/dot.svg" alt="" class="dot">
      <h1>Circulation Desk</h1>
      <h3>花園館藏</h3>
    </div>
  </div>


  <%-- search start --%>
  <section class="stock">
    <div class="container">
      <div class="row">
        <div class="search">
          <input type="checkbox" id="side" name="side-check">
          <div class="search-bar">
          	<Form METHOD="GET" ACTION="${pageContext.request.contextPath}/stock/StockServletHibernate.do" class="search-form">
          		<input type="text" name="action" value="search" style="display: none;"> 
            	<input type="search" name="search" placeholder="Want to...">
            	<button type="button"><img src="${pageContext.request.contextPath}/Stock/icon/search.svg" alt=""></button>
            </Form>
            <label for="side" class="side-check"></label>
          </div>
          <input type="radio" id="book" name="stock-kind">
	      <input type="radio" id="movie" name="stock-kind">
          <div class="kind">
            <label for="book" class="book-check">
              書 籍
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </label>
            <label for="movie" class="movie-check">
              電 影
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </label>
          </div>
          <div class="types">
            <ul class="book-types">
            <c:forEach var="bookTypeList" items="${bookType}">
            	<li data-id="${bookTypeList.typeId}"><A href="${pageContext.request.contextPath}/stock/StockServletHibernate.do?action=first&typeId=${bookTypeList.typeId}&page=1">${bookTypeList.typeName}</A></li>
            </c:forEach>
            </ul>
            <ul class="movie-types">
            <%-- 呼叫getMovie method --%>
            <c:forEach var="movieTypeList" items="${movieType}">
           	<%-- ${pageContext.request.contextPath}/Stock/ --%>
            	<li data-id="${movieTypeList.typeId}"><A href="${pageContext.request.contextPath}/stock/StockServletHibernate.do?action=first&typeId=${movieTypeList.typeId}&page=1">${movieTypeList.typeName}</A></li>
	        </c:forEach>
            </ul>
          </div>
        </div>
        <div class="item">
          <div class="container">
		<c:forEach var="stockVO" items="${stockList}"> 
            <div class="items">
              <div class="front">
                <a href=""><img src="${pageContext.request.contextPath}/stock/StockImgHibernate?action=showImg&ID=${stockVO.stockId}" alt=""></a>
                <h3 >【${stockVO.stockName}】</h3>
                <c:choose>
                	<c:when test="${stockVO.stockScore == 0}">
		                <h4>會員評價分數:尚未有評分紀錄</h4> 
                	</c:when>
                	<c:otherwise>
                		<h4>會員評價分數:${stockVO.stockScore}分</h4> 
                	</c:otherwise>
                </c:choose>
                <%-- 判斷是否為 空字串 或 null --%>
                <c:if test="${not empty stockVO.author}">
                	<h4>作者:${stockVO.author}</h4>                
                </c:if>
                <c:if test="${not empty stockVO.issuedDate}">
                	<h4>發行日:${stockVO.issuedDate}</h4>                
                </c:if>
                <h4 data-last="${listSvc.availStock(stockVO.stockId)}">剩餘數量:${listSvc.availStock(stockVO.stockId)}</h4>
				<%--檢查剩餘數量, 0 時 檢查有無預訂訂單, 無訂單時預訂按鈕 disabled --%> 
                <button type="button" data-stock="${stockVO.stockId}" class="btn order" 
                ${member == null or member.status eq '管理員' ? 'disabled' : listSvc.availStock(stockVO.stockId) == 0 ? 
                borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? "disabled" : '' : ''}>預訂</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn love" 
                	${member == null or member.status eq '管理員' ? 'disabled' : markSvc.findByMember(member.number, stockVO.stockId) == true ? 'disabled' : ''}>
                	${markSvc.findByMember(member.number, stockVO.stockId) == true ? '已收藏' : '收藏'}</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn detail">詳細資料</button>
              </div>
              <div class="backface-love">
                <h3 ></h3>
                <p>※注意事項<br/>※可預訂取件日期為七天內<br/>※可提前取件<br/>※借閱期間為預訂日起算一個月</p>
                <c:choose>
	                <c:when test="${not empty member and member.status eq '正常'}">
		                <select name="" id="order-date" ${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '' : 'disabled'}>
		                  <option value="0">${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '請選擇預約借閱日期' : "已預訂:"+=borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId).preBoDate}</option>               
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                </select>
		                <input type="text" value="歸還日期:${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '' : borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId).preReDate}" disabled>
					</c:when>    
					<c:otherwise>
						<select name="" id="order-date">
		                  <option value="0">請選擇預約借閱日期</option>               
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                </select>
		                <input type="text" value="歸還日期:" disabled>
					</c:otherwise>            
                </c:choose>
                <button type="button" data-stockId="${stockVO.stockId}" class="btn confirm" disabled>Confirm</button>
                <button type="button" class="btn close">Close</button>
              </div>
            </div>
         </c:forEach>
          </div>
        </div>
      </div>
      <div class="page-sel">
        <ul class="page-box">
        <c:forEach var="totalpage" items="${totalpage}" varStatus="status"> 
        	<c:choose>
        		<c:when test="${nowPage == status.count}">
        			<li class="now-page"><A href="${pageContext.request.contextPath}/stock/StockServletHibernate.do?action=first&typeId=${typeId}&page=${status.count}">${status.count}</A></li>
        		</c:when>
        		<c:otherwise>
        			<li  class=""><A href="${pageContext.request.contextPath}/stock/StockServletHibernate.do?action=first&typeId=${typeId}&page=${status.count}">${status.count}</A></li>
        		</c:otherwise>
        	</c:choose>
        </c:forEach>
        </ul>
      </div>
    </div>
  </section>
 <%-- search end --%>

<%@ include file="/parts/footer.text" %>

  <%-- light box start --%>
  <div class="main-filter"></div>  
  <div class="detail-filter">
    <p></p>
    <div>
    </div>
    <button class="btn book skip">Skip</button>
    <button class="btn book content">讀者評價</button>
  </div>
  <div class="love-filter">
    <h3 class="book"></h3>
    <p>加入書籤嗎?</p>
    <button type="submit" class="btn book confirm">Confirm</button>
    <button type="button" class="btn book cancel">Cancel</button>
  </div>
  <div class="hint">
    <p>請輸入搜尋關鍵字</p>
    <button>我知道了</button>
  </div>
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
  <%-- light box end --%>
  <script>
  	
  
  	var starimg = "${pageContext.request.contextPath}/Stock/icon/fullstar.svg";
  	var typeId = ${typeId};
  	var orderData = {};
  	var markData = {};
  	markData["memberId"] = '${member.number}';
  	orderData["memberId"] = '${member.number}';
  	var markURL = "${pageContext.request.contextPath}/marks/MarksAjax";
  	var orderURL = "${pageContext.request.contextPath}/borrow/BorrowAjax.order";
  	var contentURL = "${pageContext.request.contextPath}/borrow/BorrowContentAjax.do";
	var stockInf = ${stockInf};
	
  	var nowYear = <%=actYear%>
  	var nowMonth = <%=actMonth%>
  	var nowDay = <%=actDay%>
  </script>
  <script src='${pageContext.request.contextPath}/Stock/js/stockHibernate2.js'></script>
</body>
</html>