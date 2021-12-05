<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/vendor/css/vendorBackend.css'>
</head>
<body>

<%@ include file="/parts/header.text" %>
<%@ include file="/parts/manager.text" %>

<section id="manager_area">
	<section id="manager">
	    <div class="action">
	      <form method="POST" action="${pageContext.request.contextPath}/vendor/VendorServlet">
	      	<input type="text" id="action" name="action" value="getOne_For_Display" style="display:none">
	        <input type="text" name="vendorid" value="" placeholder="請輸入廠商編號..." autocomplete="off">
	       	<c:if test="${errorMsgs != null}">
	        	<span class="errorMsgs">${errorMsgs[0]}</span>
	        </c:if>
	        <button class="btn memberselect">查詢</button>
	      </form>
	      <form method="POST" action="${pageContext.request.contextPath}/vendor/VendorServlet">
	      	<input type="text" id="action" name="action" value="find_By_Keyword" style="display:none">
	        <input type="text" name="vendorname" value="" placeholder="請輸入廠商名稱關鍵字..." autocomplete="off">
	      	<c:if test="${errorMsgsKeyWord != null}">
	        	<span class="errorMsgsKeyWord">${errorMsgsKeyWord[0]}</span>
	        </c:if>
	        <button class="btn memberselect">查詢</button>
	      </form>
	      	<button class="btn allselect">顯示全部</button>
	      
	      
	    </div>
	    <div class="choice">
	      <div class="orderitems">
	        <ul class="title">
	          <li class="vendorid">廠商編號</li>
	          <li class="company">廠商名稱</li>
	          <li class="taxid">統一編號</li>
	          <li class="name">負責人</li>
	          <li class="email">Email</li>
	          <li class="tel">電話</li>
	          <li class="mobile">手機</li>
	          <li class="addr">地址</li>
	          <li class="status" colspan="2">帳戶狀態</li>
	        </ul>
	        
	        <h1>
	      	  <c:if test="${venVO.vendorid == null && venlist == null}">
	      		  查無結果
	      	  </c:if>
	        </h1>
	        
	       <c:choose>
	       	<c:when test="${venVO.vendorid != null}">
	       		  <ul class="orderitem">
			          <li class="vendorid">${venVO.vendorid}</li>
			          <li class="company">${venVO.company}</li>
			          <li class="taxid">${venVO.taxid}</li>
			          <li class="name">${venVO.name}</li>
			          <li class="email">${venVO.email}</li>
			          <li class="tel">${venVO.tel}</li>
			          <li class="mobile">${venVO.mobile}</li>
			          <li class="addr">${venVO.addr}</li>
			         
					<c:choose>
				          <c:when test="${venVO.status == '正常'}">
				          	<li class="update unable okay"><button data-vendorid="${venVO.vendorid}">正常</button></li>
				          	<li class="update" ><button data-vendorid="${venVO.vendorid}">停權</button></li>
				          </c:when>
				          <c:otherwise>
				          	<li class="update"><button data-vendorid="${venVO.vendorid}">正常</button></li>
				          	<li class="update unable stop" ><button data-vendorid="${venVO.vendorid}">停權</button></li>
				          </c:otherwise>
			          </c:choose>

			        </ul>     
	       	</c:when>

	       	<c:otherwise>
	       		<c:forEach var="vendor" items="${venlist}">
			        <ul class="orderitem">
			          <li class="vendorid">${vendor.vendorid}</li>
			          <li class="company">${vendor.company}</li>
			          <li class="taxid">${vendor.taxid}</li>
			          <li class="name">${vendor.name}</li>
			          <li class="email">${vendor.email}</li>
			          <li class="tel">${vendor.tel}</li>
			          <li class="mobile">${vendor.mobile}</li>
			          <li class="addr">${vendor.addr}</li>
			          
			          <c:choose>
				          <c:when test="${vendor.status == '正常'}">
				          	<li class="update unable okay"><button data-vendorid="${vendor.vendorid}">正常</button></li>
				          	<li class="update" ><button data-vendorid="${vendor.vendorid}">停權</button></li>
				          </c:when>
				          <c:otherwise>
				          	<li class="update"><button data-vendorid="${vendor.vendorid}">正常</button></li>
				          	<li class="update unable stop" ><button data-vendorid="${vendor.vendorid}">停權</button></li>
				          </c:otherwise>
			          </c:choose>
			          
			        </ul>     
	     		 </c:forEach>
	       	</c:otherwise>
	       </c:choose>
	      </div>
	    </div>
  </section>
  </section>

<%@ include file="/parts/footer.text" %>

	<script>
		$("a#vendor").addClass("select");
		var vendorStatus_url = `${pageContext.request.contextPath}/vendor/vendorStatus`;
		var vendorAll_url = `${pageContext.request.contextPath}/vendor/VendorServlet?action=getAll`;
	</script>
	<script src='${pageContext.request.contextPath}/vendor/js/vendorBackend.js'></script>
</body>
</html>