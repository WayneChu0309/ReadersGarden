<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!--   <meta charset="UTF-8"> -->
<!--   <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<!--   <title>Reader's Garden</title> -->
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  自己的css --%>
<%--   <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> 自己的css --%>



<!-- <style> -->

  
/* main.table{ */
/*   display: flex; */
/*   /* background-color: rgb(255, 255, 255); */ */
/*   vertical-align: top; */
/*   font-size: 1rem; */
/*   border: 1px solid rgb(255, 255, 255); */
/*   max-width: 100%; */
/*   } */

/* .list-bgcolor{ */
/*   width: 80%; */
/*   background-color: white; */
/* } */

/* .sidebar{ */
/*   width: 20%; */
/* } */


/* .navigation { */
/*   overflow: hidden; */
/*   background-color: #e9e9e9; */
/* } */
/* .navigation a { */
/*   float: left; */
/*   display: block; */
/*   color: black; */
/*   text-align: center; */
/*   padding: 14px 80px; */
/*   text-decoration: none; */
/*   font-size: 17px; */
/* } */
/* .navigation a:hover { */
/*   background-color: #ddd; */
/*   color: black; */
/* } */
/*   .navigation a.active { */
/*   background-color: #2196F3; */
/*   color: white; */
/*   } */
/*   .navigation input[type=text] { */
/*   float: right; */
/*   padding: 6px; */
/*   border: none; */
/*   margin-top: 8px; */
/*   margin-right: 16px; */
/*   font-size: 17px; */
/*   } */
/* /* img{ */
/*   float: right; */
/*   height: 40px; */
/*   width: 40px; */

/* } */ */


/* div.sidebar{ */
/*   background-color: #ddd; */
/*   width: 200px; */
/*   display: inline-block; */
/*   vertical-align: top; */
/*   font-size: 1rem; */
/*   margin-right: 10px; */
/*   border: 1px solid #999; */
/* } */

/* head.head{ */
/*   background-color: #ddd; */
/*   width: 720px; */
/*   margin: 0 auto 10px auto; */
/*   border: 1px solid #999; */
/* } */

/* div.body_cont{ */
/*   width: 720px; */
/*   margin: 0 auto; */
/*   font-size: 0; */
/* } */


/* nav.navigation{ */
/*   text-align: right; */
/*   margin: 10px; */
/*   padding: 20px; */
/* } */

<!-- </style> -->

<!-- </head> -->
<!-- <body> -->
	
<%-- <%@ include file="/parts/header.text" %> --%>
<%-- <%@ include file="/parts/slide.text" %> --%>

<!--   <div class="latest-news"  id="target-page"> -->
<!--     <div class="container"> -->
<!--       <img src="./icon/dot.svg" alt="" class="dot"> -->
<!--       <h1>Circulation Desk</h1> -->
<!--       <h3>花園館藏</h3> -->
<!--     </div> -->
<!--   </div> -->
  
  
  
<!-- <main class = "table"> -->
<!-- <!-- sidebar --> -->
<!-- <div class="sidebar"> -->
<!--   <h2 width = "160"> -->
<!--     書籍類別 -->
<!--   </h2> -->
  
<!-- <ul> -->
<!--   <li> -->
<!--     <a href="url">scary</a>	 -->
<!--   </li> -->
<!--   <li> -->
<!--     <a href="url">funny</a>	 -->
<!--   </li> -->
<!--   <li> -->
<!--     <a href="url">hilarious</a>	 -->
<!--   </li> -->
<!-- </ul> -->
<!-- <h2 width = "160"> -->
<!--   電影類別 -->
<!-- </h2> -->
    
<!--   <ul> -->
<!--     <li> -->
<!--       <a href="url">horror</a>	 -->
<!--     </li> -->
<!--     <li> -->
<!--       <a href="url">fantastic</a>	 -->
<!--     </li> -->
<!--     <li> -->
<!--       <a href="url">hell</a>		 -->
<!--     </li> -->
<!--   </ul> -->

<!--   <button id ="btn" style="background-color: rgba(196, 233, 217, 0.884);" type="button"> -->
<!--     發布文章 -->
<!--   </button> -->
<!--   <!-- <input type="button" value="我是按鈕" style="width:120px;height:40px;border:2px blue none;background-color:pink;"> --> -->
<!-- </div> -->

<!-- <!-- 表格標題 --> -->
<!--   <table width="100%" cellpadding="4" cellspacing="0" class="list-bgcolor"> -->
<!--     <tr class="list-title"> -->
<!--       <td class="list-no" width="5%">文章主題</td> -->
<!--       <td width="57%" nowrap align="center"> -->
<!--       <div class="list-tl" align="right">文章名稱</div> -->
<!--       </td> -->
<!--       <td width="8%" nowrap align="center">發布者</td> -->
<!--       <td width="8%" nowrap align="center">發布日期</td> -->
<!--     </tr> -->
  
<!--     表格內容 -->
<!--     <tr> -->
<!--       <td>01</td> -->
<!--       <td class="list-tl"><a href ="url">Design</a></td> -->
<!--       <td>A</td> -->
<!--       <td>2021.5.10</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>02</td> -->
<!--       <td class="list-tl"><a href ="url">Fruit</a></td> -->
<!--       <td>B</td> -->
<!--       <td>2021.5.18</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>03</td> -->
<!--       <td class="list-tl"><a href ="url">Application</a></td> -->
<!--       <td>C</td> -->
<!--       <td>2021.5.23</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>04</td> -->
<!--       <td class="list-tl"><a href ="url">Development</a></td> -->
<!--       <td>D</td> -->
<!--       <td>2021.6.7</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>05</td> -->
<!--       <td class="list-tl"><a href ="url">Business</a></td> -->
<!--       <td>E</td> -->
<!--       <td>2021.6.21</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>06</td> -->
<!--       <td class="list-tl"><a href ="url">Funny</a></td> -->
<!--       <td>F</td> -->
<!--       <td>2021.6.26</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>07</td> -->
<!--       <td class="list-tl"><a href ="url">Interface</a></td> -->
<!--       <td>G</td> -->
<!--       <td>2021.6.30</td> -->
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>08</td> -->
<!--       <td class="list-tl"><a href ="url">Readers Digest</a></td> -->
<!--       <td>H</td> -->
<!--       <td>2021.7.10</td> -->
<!--     </tr> -->
<!-- </table> -->
<!-- </main> -->
  
  
  
  
  
  
  
<%-- <%@ include file="/parts/footer.text" %> --%>

<%-- <script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  自己的js --%>
<!-- </body> -->
<!-- </html> -->