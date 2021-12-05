<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page import="com.article.model.*"%> --%>

<%-- <% --%>
//   Article art = (Article) request.getAttribute("art");
<%-- %> --%>

<!-- <html> -->

<!-- <head> -->
<!--   <meta charset="UTF-8"> -->
<!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--   <title>Reader's Garden</title> -->
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  自己的css --%>
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> 自己的css --%>


<!-- <meta charset="UTF-8"> -->
<!-- <title>新增文章 - addArt.jsp</title> -->

<!-- <style> -->

/* table#backToHome { */
/* 	background-color: white; */
/*     opacity: 0.8; */
/*     border: 0.5px solid black; */
/*     text-align: center; */
/*     float: right; */
/*     margin-right: 300px; */
/*     margin-top: 350px; */
/* } */

/* nav.navigation{ */
/*   text-align: right; */
/*   margin: 10px; */
/*   padding: 20px; */
/* } */
/* .title{ */
/*     font-size: 70px; */
/*     text-align: center; */
/*     margin-top: 20px; */
/* } */

/* .mb-3{ */
/*   text-align: center; */
/* } */

/* .form-label1{ */
/*   font-size: 30px; */
/*   margin-right: 10px; */
/* } */

/* .form-label2{ */
/*   font-size: 30px; */
/*   margin-right: 10px; */
/* } */


/* .button2{ */
/*   width: 100px; */
/*   height: 50px; */
/*   float: right; */
/*   margin-bottom: 100px; */
/*   margin-right: 400px; */
/* } */


/* .send{ */
/* 	margin-left: 400px; */
/* } */

<!-- </style> -->
<!-- </head> -->
<!-- <body bgcolor='white'> -->

<%-- <%@ include file="/parts/header.text" %> --%>
<%-- <%@ include file="/parts/slide.text" %> --%>

<!--   <div class="latest-news"  id="target-page"> -->
<!--     <div class="container"> -->

<!--       <h1>Create A Forum</h1> -->
<!--       <h3>新增文章</h3> -->
<!--     </div> -->
<!--   </div> -->
  
  
<!--   <table id="backToHome"> -->
<!-- 	<tr><td> -->
<%-- 		 <h4><a href="<%=request.getContextPath()%>/newArticle/newArticleHome.jsp">返回討論區首頁</a></h4> --%>
<!-- 	</td></tr> -->
<!-- </table> -->



<%-- <%-- 錯誤表列 --%> --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<!-- <FORM METHOD="post" ACTION="NewArticleServlet" name="form1"> -->

<!-- <div class="title">新增文章</div> -->

<!-- <div class="mb-3"> -->
<!--   <label for="Input1" class="form-label1">文章名稱</label> -->
  
<!--   		<td><input type="TEXT" name="ANAME" size="45"  -->
<%-- 			 value="<%= (art==null)? "this is a sample name" : art.getANAME()%>" /></td> --%>
  
<!-- </div> -->
<!-- <div class="mb-3"> -->
<!--   <label for="Input2" class="form-label2">文章類別</label> -->
<!--   		<td><input type="TEXT" name="ACID" size="45" -->
<%-- 			 value="<%= (art==null)? "1" : art.getACCTID()%>" /></td> --%>
<!-- </div> -->

<!-- <div class="mb-3"> -->
<!--   <label for="Input2" class="form-label2">發布者ID:</label> -->
<!-- 		<td><input type="TEXT" name="ACCTID" size="45" -->
<%-- 			 value="<%= (art==null)? "2" : art.getACCTID()%>" /></td> --%>
<!-- </div> -->


<!-- <div class="mb-3"> -->
<!--   <label for="Input2" class="form-label2">文章內容</label> -->
<!-- 		<td><input type="TEXT" name="ADESCRIPT" size="45" -->
<%-- 			 value="<%= (art==null)? "TTT" : art.getADESCRIPT()%>" /></td> --%>
<!-- </div> -->


<!-- <br> -->
<!-- <div class="send"> -->
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="送出新增"></FORM> -->
<!-- </div> -->
<!-- </body> -->



<!-- <!-- =========================================以下為 datetimepicker 之相關設定========================================== --> -->

<%-- <%  --%>
//   java.sql.Date APD = null;
//   try {
// 	  APD = art.getAPD();
//    } catch (Exception e) {
// 	   APD = new java.sql.Date(System.currentTimeMillis());
//    }
<%-- %> --%>
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
// 	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       //timepicker:true,
// 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=APD%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
//         // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//         //      1.以下為某一天之前的日期無法選擇
//         //      var somedate1 = new Date('2017-06-15');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() <  somedate1.getYear() || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});

        
//         //      2.以下為某一天之後的日期無法選擇
//         //      var somedate2 = new Date('2017-06-15');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() >  somedate2.getYear() || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});


//         //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
//         //      var somedate1 = new Date('2017-06-15');
//         //      var somedate2 = new Date('2017-06-25');
//         //      $('#f_date1').datetimepicker({
//         //          beforeShowDay: function(date) {
//         //        	  if (  date.getYear() <  somedate1.getYear() || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
//         //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
//         //		             ||
//         //		            date.getYear() >  somedate2.getYear() || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//         //              ) {
//         //                   return [false, ""]
//         //              }
//         //              return [true, ""];
//         //      }});
        
<!-- </script> -->

<%-- <%@ include file="/parts/footer.text" %> --%>

<%-- <script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  自己的js --%>
<!-- </body> -->
<!-- </html> -->


 
