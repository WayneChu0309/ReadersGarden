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
      <h3>่ฑๅ้คจ่</h3>
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
              ๆธ ็ฑ
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </label>
            <label for="movie" class="movie-check">
              ้ป ๅฝฑ
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
            <%-- ๅผๅซgetMovie method --%>
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
                <h3 >ใ${stockVO.stockName}ใ</h3>
                <c:choose>
                	<c:when test="${stockVO.stockScore == 0}">
		                <h4>ๆๅก่ฉๅนๅๆธ:ๅฐๆชๆ่ฉๅ็ด้</h4> 
                	</c:when>
                	<c:otherwise>
                		<h4>ๆๅก่ฉๅนๅๆธ:${stockVO.stockScore}ๅ</h4> 
                	</c:otherwise>
                </c:choose>
                <%-- ๅคๆทๆฏๅฆ็บ ็ฉบๅญไธฒ ๆ null --%>
                <c:if test="${not empty stockVO.author}">
                	<h4>ไฝ่:${stockVO.author}</h4>                
                </c:if>
                <c:if test="${not empty stockVO.issuedDate}">
                	<h4>็ผ่กๆฅ:${stockVO.issuedDate}</h4>                
                </c:if>
                <h4 data-last="${listSvc.availStock(stockVO.stockId)}">ๅฉ้คๆธ้:${listSvc.availStock(stockVO.stockId)}</h4>
				<%--ๆชขๆฅๅฉ้คๆธ้, 0 ๆ ๆชขๆฅๆ็ก้?่จ่จๅฎ, ็ก่จๅฎๆ้?่จๆ้ disabled --%> 
                <button type="button" data-stock="${stockVO.stockId}" class="btn order" 
                ${member == null or member.status eq '็ฎก็ๅก' ? 'disabled' : listSvc.availStock(stockVO.stockId) == 0 ? 
                borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? "disabled" : '' : ''}>้?่จ</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn love" 
                	${member == null or member.status eq '็ฎก็ๅก' ? 'disabled' : markSvc.findByMember(member.number, stockVO.stockId) == true ? 'disabled' : ''}>
                	${markSvc.findByMember(member.number, stockVO.stockId) == true ? 'ๅทฒๆถ่' : 'ๆถ่'}</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn detail">่ฉณ็ดฐ่ณๆ</button>
              </div>
              <div class="backface-love">
                <h3 ></h3>
                <p>โปๆณจๆไบ้?<br/>โปๅฏ้?่จๅไปถๆฅๆ็บไธๅคฉๅง<br/>โปๅฏๆๅๅไปถ<br/>โปๅ้ฑๆ้็บ้?่จๆฅ่ตท็ฎไธๅๆ</p>
                <c:choose>
	                <c:when test="${not empty member and member.status eq 'ๆญฃๅธธ'}">
		                <select name="" id="order-date" ${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '' : 'disabled'}>
		                  <option value="0">${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '่ซ้ธๆ้?็ดๅ้ฑๆฅๆ' : "ๅทฒ้?่จ:"+=borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId).preBoDate}</option>               
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                </select>
		                <input type="text" value="ๆญธ้ๆฅๆ:${borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId) == null ? '' : borrowSvc.findByNumberPreOrder(member.number, stockVO.stockId).preReDate}" disabled>
					</c:when>    
					<c:otherwise>
						<select name="" id="order-date">
		                  <option value="0">่ซ้ธๆ้?็ดๅ้ฑๆฅๆ</option>               
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                </select>
		                <input type="text" value="ๆญธ้ๆฅๆ:" disabled>
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
    <button class="btn book content">่ฎ่่ฉๅน</button>
  </div>
  <div class="love-filter">
    <h3 class="book"></h3>
    <p>ๅ?ๅฅๆธ็ฑคๅ?</p>
    <button type="submit" class="btn book confirm">Confirm</button>
    <button type="button" class="btn book cancel">Cancel</button>
  </div>
  <div class="hint">
    <p>่ซ่ผธๅฅๆๅฐ้้ตๅญ</p>
    <button>ๆ็ฅ้ไบ</button>
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