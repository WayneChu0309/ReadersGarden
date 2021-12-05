<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>會員中心 - Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/borrow/css/borrowMember.css'>
</head>
<body>

	<%@ include file="/parts/header.text" %>
	<%@ include file="/member/membercenter.text" %>


<div class="stockorder">
    <div class="container">
<c:if test="${empty memberAllboList}">
	尚無借閱紀錄
</c:if>    
    
<c:forEach var="borrow" items="${memberAllboList}" varStatus="index">
      <ul class="orderdetail">
        <input type="checkbox" id="check${index.index}">
        <label for="check${index.index}"><span></span></label>
        <li class="borrowId" data-borrowId="${borrow.borrowId}"></li>
        <li class="stockName">名稱:
        <c:choose>
       		<c:when test="${borrow.stockBean.stockName.length() > 8}">
       			${borrow.stockBean.stockName.substring(0, 8)}...
       		</c:when>
       		<c:otherwise>
       			${borrow.stockBean.stockName}
       		</c:otherwise>
       	</c:choose>
        <span><p>${borrow.stockBean.stockName}</p><img src="${pageContext.request.contextPath}/stock/StockImgHibernate?action=showImg&ID=${borrow.stockBean.stockId}" alt=""></span></li>
        
        <c:if test="${borrow.boStates == 4 || borrow.boStates == 5}">
        		
        	<c:choose>
        		<c:when test="${not empty borrow.score}">
        		<li class="scoretitle">***已評分***</li>
        			<li class="score exist">
				        <input type="radio" name="score-${index.index}" id="1-${index.index}" value="1" ${borrow.score == 1 ? 'checked':''}>
			            <input type="radio" name="score-${index.index}" id="2-${index.index}" value="2" ${borrow.score == 2 ? 'checked':''}>
			            <input type="radio" name="score-${index.index}" id="3-${index.index}" value="3" ${borrow.score == 3 ? 'checked':''}>
			            <input type="radio" name="score-${index.index}" id="4-${index.index}" value="4" ${borrow.score == 4 ? 'checked':''}>
			            <input type="radio" name="score-${index.index}" id="5-${index.index}" value="5" ${borrow.score == 5 ? 'checked':''}>
			            <div class="control">
			              <label for="1-${index.index}"></label>
			              <label for="2-${index.index}"></label>
			              <label for="3-${index.index}"></label>
			              <label for="4-${index.index}"></label>
			              <label for="5-${index.index}"></label>
			            </div>
        		</c:when>
        		<c:otherwise>
        		<li class="scoretitle">點擊星星來評分!!!</li>
		        	<li class="score">
			            <input type="radio" name="score-${index.index}" id="1-${index.index}" value="1">
			            <input type="radio" name="score-${index.index}" id="2-${index.index}" value="2">
			            <input type="radio" name="score-${index.index}" id="3-${index.index}" value="3">
			            <input type="radio" name="score-${index.index}" id="4-${index.index}" value="4">
			            <input type="radio" name="score-${index.index}" id="5-${index.index}" value="5">
			            <div class="control">
			              <label for="1-${index.index}" class="1-${index.index}"></label>
			              <label for="2-${index.index}" class="2-${index.index}"></label>
			              <label for="3-${index.index}" class="3-${index.index}"></label>
			              <label for="4-${index.index}" class="4-${index.index}"></label>
			              <label for="5-${index.index}" class="5-${index.index}"></label>
			            </div>
			        </li>
        		</c:otherwise>
        	</c:choose>
	        <c:choose>
	        	<c:when test="${not empty borrow.scoreContent}">
		        	<li class="content"><textarea disabled>${borrow.scoreContent}</textarea></li>
	        	</c:when>
	        	<c:otherwise>
		        	<li class="content"><textarea placeholder="請留下您的評論..."></textarea></li>
	        	</c:otherwise>
	        </c:choose>
        </c:if>
        <li class="preBoDate">預訂時間:${borrow.preBoDate == null ? "---" : borrow.preBoDate}</li>
        <li class="actBoDate">借閱時間:${borrow.actBoDate == null ? "---" : borrow.actBoDate}</li>
        <li class="preReDate">預計歸還:${borrow.preReDate == null ? "---" : borrow.preReDate}</li>
        <li class="actReDate">實際歸還:${borrow.actReDate == null ? "---" : borrow.actReDate}</li>
        <li class="scoreDate">評分日期:${borrow.scoreDate == null ? "---" : borrow.scoreDate}</li>
        <li class="boStates">借閱狀態:
			<c:choose>
				<c:when test="${borrow.boStates == 1 || borrow.boStates == 8}">
					預訂
				</c:when>
				<c:when test="${borrow.boStates == 2}">
					借閱中
				</c:when>
				<c:when test="${borrow.boStates == 3}">
					已逾期,尚未歸還
				</c:when>
				<c:when test="${borrow.boStates == 4}">
					已歸還
				</c:when>
				<c:when test="${borrow.boStates == 5}">
					逾期歸還
				</c:when>
				<c:when test="${borrow.boStates == 6 || borrow.boStates == 7}">
					預約未取,已取消
				</c:when>
			</c:choose>
		</li>
		
		<c:if test="${borrow.boStates == 4 || borrow.boStates == 5}">
	        <li class="submit"><button disabled>submit</button></li>
		</c:if>
      </ul>
</c:forEach>
    </div>
  </div>



<script src='${pageContext.request.contextPath}/borrow/js/borrowMember.js'></script>

<script>
	$("div.content_row div.content_left div").eq(1).find("a").addClass("now_choice");
	$("div.container ul label").on("click", function(){
		if ($(this).siblings("input[type='checkbox']").prop("checked") == false) {
			var index = $(this).closest("ul").prevAll().length
			$("div.stockorder div.container").scrollTop(index * 55);
		}
	})
	
	var scoreURL = "${pageContext.request.contextPath}/borrow/BorrowScoreAjax.do";

</script>
</body>
</html>