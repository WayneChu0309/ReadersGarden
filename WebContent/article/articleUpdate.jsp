<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="com.article.model.*"%> --%>

<%-- <% --%>
// Article art = (Article) request.getAttribute("art"); //EmpServlet.java (Concroller) 存入req的empVO(art)物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
<%-- %> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->


<!-- <style> -->
/* .table1{ */
/*     margin-left: 100px; */
/*     margin-bottom: 50px; */
/*     background-color: rgb(255, 255, 255); */
/*     opacity: 0.8; */
/*     heigh: 300px; */
/*     width: calc(100% - 200px - 10px); */
/*     vertical-align: top; */
/*     font-size: 1rem; */
/* } */
/* .article-title{ */
/*   font-size: 70px; */
/*   text-align: center; */
/* } */

/* p{ */
/*   display: inline-block; */
/*   float: right; */
/*   margin-right:300px; */
/*   margin-top: 50px; */
/*   margin-left: 50px; */
/*   height: auto; */
/*   width: 600px; */
/*   font-size: 20px; */
/* } */

/* .like{ */
/*   display: inline-block; */
/*   margin-left: 500px; */
/* } */

/* .saved{ */
/*   display: inline-block; */
/*   margin-left: 30px; */
/* } */


/* .someone{ */
/*   display: inline-block; */
/*   margin-top: 50px; */
/*   margin-left: 150px; */
/* } */

/* .publisher{ */
/*   margin-top: 20px; */
/* } */

/* .form-control{ */
/*   width: 600px; */
/*   height: 100px; */
/* } */

/* .form{ */
/*   margin-top: 20px; */
/*   margin-left: 500px; */
/* } */

/* .button-form{ */
/*   /* float: right; */ */
/*   margin-left: 1000px; */
/*   margin-top: 30px; */
/*   margin-bottom: 100px; */
/*   width: 150px; */
/*   height: 60px; */
/* } */




<!-- </style> -->


<!-- <head> -->
<!--   <meta charset="UTF-8"> -->
<!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--   <title>Reader's Garden</title> -->
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  自己的css --%>
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> 自己的css --%>
<!-- </head> -->
<!-- <body> -->
<%-- <%-- 錯誤表列 --%> --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <FORM METHOD="post" ACTION="ArticleServlet" name="form1"> -->

	
<%-- <%@ include file="/parts/header.text" %> --%>
<%-- <%@ include file="/parts/slide.text" %> --%>

<!--   <div class="latest-news"  id="target-page"> -->
<!--     <div class="container"> -->
<!--       <img src="./icon/dot.svg" alt="" class="dot"> -->
<!--       <h1>Circulation Desk</h1> -->
<!--       <h3>花園討論修改</h3> -->
<!--     </div> -->
<!--   </div> -->
  
<!-- <table class="table1"> -->
<!-- 	<tr> -->
<!-- 		<td>文章編號:</td> -->
<%-- 		<td><%=art.getAID()%><input type="hidden" name="AID" value="<%=art.getAID()%>"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>文章類別編號:</td> -->
<%-- 		<td><%=art.getACID()%><input type="hidden" name="ACID" value="<%=art.getACID()%>"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>發布者ID:</td> -->
<%-- 		<td><%=art.getACCTID()%><input type="hidden" name="ACCTID" value="<%=art.getACCTID()%>"></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>文章名稱:</td> -->
<%-- 		<td><%=art.getANAME()%><input type="hidden" name="ANAME" value="<%=art.getANAME()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>文章內容:<font color=red><b>*</b></font></td> -->
<%-- 		<td><input type="TEXT" name="ADESCRIPT" size="45"	value="<%=art.getADESCRIPT()%>" /></td> --%>
<!-- 	</tr> -->



<!-- </table> -->

<!--  <br> -->
<!-- <input type="hidden" name="action" value="update"> -->
<%-- <input type="hidden" name="AID" value="<%=art.getAID()%>"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->
<!-- </body> -->



<!-- <!-- =========================================以下為 datetimepicker 之相關設定========================================== --> -->

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
/*   .xdsoft_datetimepicker .xdsoft_datepicker { */
/*            width:  300px;   /* width:  300px; */ */
/*   } */
/*   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { */
/*            height: 151px;   /* height:  151px; */ */
/*   } */
<!-- </style> -->

<!-- <script> -->
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=art.getAPD()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
         
  
  
<%-- <%@ include file="/parts/footer.text" %> --%>

<%-- <script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  自己的js --%>
<!-- </body> -->
<!-- </html> -->