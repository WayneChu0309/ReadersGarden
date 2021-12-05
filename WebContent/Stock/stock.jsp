<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.stock.model.*"%>



<%-- <jsp:useBean id="stockList" scope="session" type="java.util.List<StockVO>" /> --%>

<% 	
	List<StockVO> stockList = (List<StockVO>)session.getAttribute("stockList");
	request.setAttribute("memberId", 1);
	int memberId = (int) request.getAttribute("memberId");
	
	if (stockList == null) {
		StockService stockSvc = new StockService();
		stockList = stockSvc.getAll(1);
		session.setAttribute("stockList", stockList);
		session.setAttribute("typeId", 1);
	}
	
	
	int actYear = java.time.LocalDate.now().getYear();
	int actMonth = java.time.LocalDate.now().getMonthValue();
	int actDay = java.time.LocalDate.now().getDayOfMonth();
	
	int total = stockList.size();
	int totalPage = total % 30 != 0 ? (total / 30) + 1 : total / 30 ;
	int begin = 0;
	int row = 30;
	int pageNumber = 1;
	//  typeId 給 ajax 用
	int typeId = (int)session.getAttribute("typeId");
	String actPage = request.getParameter("page");
	String keyword = request.getParameter("search");
	if (keyword == null) {
		keyword = "";
	}
	if (actPage != null) {
		pageNumber = Integer.parseInt(actPage);
		begin = (pageNumber - 1) * 30;
		row = 30 * pageNumber;
		if (pageNumber > totalPage) {
			pageNumber = totalPage;
			begin = (totalPage - 1) * 30;
			row = 30 * pageNumber;
		}
	}
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
          	<Form METHOD="POST" ACTION="${pageContext.request.contextPath}/stock/StockServlet.page" class="search-form">
          		<input type="text" style="display: none;">
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
        <%--     <jsp:useBean id="typeList" scope="page" class="com.stockType.model.StockTypeJNDIDAO"/> --%>
            <jsp:useBean id="StockTypeSvc" scope="page" class="com.stockType.model.StockTypeService"/>
            <jsp:useBean id="BorrowSvc" scope="page" class="com.borrow.model.BorrowService"/>
            <%-- 呼叫getBook method --%>
            <c:forEach var="bookTypeList" items="${StockTypeSvc.book}">
            	<li data-id="${bookTypeList.typeId}"><A href="${pageContext.request.contextPath}/stock/StockServlet.page?action=getAll&typeId=${bookTypeList.typeId}">${bookTypeList.typeName}</A></li>
            </c:forEach>
            </ul>
            <ul class="movie-types">
            <%-- 呼叫getMovie method --%>
            <c:forEach var="movieTypeList" items="${StockTypeSvc.movie}">
           	<%-- ${pageContext.request.contextPath}/Stock/ --%>
            	<li data-id="${movieTypeList.typeId}"><A href="${pageContext.request.contextPath}/stock/StockServlet.page?action=getAll&typeId=${movieTypeList.typeId}">${movieTypeList.typeName}</A></li>
	        </c:forEach>
            </ul>
          </div>
        </div>
        <div class="item">
          <div class="container">
        <jsp:useBean id="stockSvc" scope="page" class="com.stock.model.StockService"/>
        <jsp:useBean id="stockListSvc" scope="page" class="com.stockList.model.StockListService"/>
        <jsp:useBean id="borrowSvc" scope="page" class="com.borrow.model.BorrowService"/>
        <%-- <jsp:useBean id="stockLast" scope="page" class="com.stock.model.StockJNDIDAO"/> --%>
		<c:forEach var="stockVO" items="${stockList}" varStatus="status" begin="<%=begin%>" end="<%=row-1%>">
            <div class="items">
              <div class="front">
              <%-- ${pageContext.request.contextPath}/Stock/ --%>
                <a href=""><img src="${pageContext.request.contextPath}/stock/StockImg?action=showImg&ID=${stockVO.stockId}" alt=""></a>
                <h3 >【${stockVO.stockName}】</h3>
                <%-- 判斷是否為 空字串 或 null --%>
                <c:if test="${not empty stockVO.author}">
                	<h4>作者:${stockVO.author}</h4>                
                </c:if>
                <c:if test="${not empty stockVO.issuedDate}">
                	<h4>發行日:${stockVO.issuedDate}</h4>                
                </c:if>
                <h4 data-last="${stockListSvc.getAvail(stockVO.stockId) - borrowSvc.getNoOrder(stockVO.stockId)}">
                剩餘數量:${stockListSvc.getAvail(stockVO.stockId) - borrowSvc.getNoOrder(stockVO.stockId)}</h4>
<%--                 <h4 data-last="${stockSvc.getLast(stockVO.stockId)}">剩餘數量:${stockSvc.getLast(stockVO.stockId)}</h4> --%>
                <button type="button" data-stock="${stockVO.stockId}" class="btn order" ${stockListSvc.getAvail(stockVO.stockId) - borrowSvc.getNoOrder(stockVO.stockId) == 0 ? "disabled" : ''}>預訂</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn love">收藏</button>
                <button type="button" data-stock="${stockVO.stockId}" class="btn detail">詳細資料</button>
              </div>
              <div class="backface-love">
                <h3 ></h3>
                <p>※注意事項<br>※可預訂取件日期為七天內<br>※可提前取件<br>※歸還日為預訂日後三十天</p>
                <c:choose>
	                <c:when test="${not empty memberId}">
		                <select name="" id="order-date" ${borrowSvc.getPreOrder(memberId, stockVO.stockId) == null ? '' : 'disabled'}>
		                  <option value="0">${borrowSvc.getPreOrder(memberId, stockVO.stockId) == null ? '請選擇預約借閱日期' : "已預訂:"+=borrowSvc.getPreOrder(memberId, stockVO.stockId).preBoDate}</option>               
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                  <option value=""></option>
		                </select>
		                <input type="text" value="歸還日期:${borrowSvc.getPreOrder(memberId, stockVO.stockId) == null ? '' : borrowSvc.getPreOrder(memberId, stockVO.stockId).preReDate}" disabled>
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
          <% for (int i = 1; i <= totalPage; i++) {
          	if (pageNumber == i) {%>
          		<li data-page="<%=i%>" class="now-page"><A href="<%=request.getRequestURI()%>?page=<%=i%>"><%=i%></A></li>
          	<%} else {%>
          		<li data-page="<%=i%>"><A href="<%=request.getRequestURI()%>?page=<%=i%>"><%=i%></A></li>
          	<%}%>
          <%}%>
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
    <button class="btn book skip">Skip</button>
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
  	var orderData = {};
  	orderData["memberId"] = "${memberId}";
  	var orderURL = "${pageContext.request.contextPath}/borrow/BorrowAjax";
    var url = "${pageContext.request.contextPath}/stock/StockAjax";
  	var data = {'typeId':<%=typeId%>, "keyword": "<%=keyword%>"};
  	var nowYear = <%=actYear%>
  	var nowMonth = <%=actMonth%>
  	var nowDay = <%=actDay%>
  </script>
  <script src='${pageContext.request.contextPath}/Stock/js/stock.js'></script>
</body>
</html>