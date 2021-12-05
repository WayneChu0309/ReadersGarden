<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>會員中心 - Reader's Garden</title>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/marks/css/marks.css'>
</head>
<body>
	<%@ include file="/parts/header.text" %>
	<%@ include file="/member/membercenter.text" %>

	<section id="loveitem">
	    <div class="loveitems">
	    <c:forEach var="mark" items="${markList}">
	      <ul>
	        <li><a href="${pageContext.request.contextPath}/stock/StockServletHibernate.do?action=quick&ID=${mark.stockId}">
	        	${stockSvc.findByStockId(mark.stockId).stockName}</a><button data-markId="${mark.marksId}">X</button></li>
	      </ul>
	    </c:forEach>
	    </div>
  	</section>
  
  
  <script>
  	$("div.content_row div.content_left div").eq(2).find("a").addClass("now_choice");
  
  	var markData = {};
		markData["memberId"] = 1;
  	var markURL = "${pageContext.request.contextPath}/marks/MarksAjax";
  	$("section#loveitem ul li button").on("click", function(){
  		var markItem = $(this).closest("ul")
  		markData["marksId"] = $(this).attr("data-markId");
  		markData["action"] = "cancel";
  		$.ajax({
            type: "POST",
            url: markURL,
            data: markData,
            dataType: "json",
            success: function (res) {
            	if (res == 1) {
            		markItem.remove();
            	}
            },
    	
	    	complete: function(xhr){     
				$("section.loadfilter").css("display", "none");
	        }
        })
  	})
  	
  
  </script>
</body>
</html>