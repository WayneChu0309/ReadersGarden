
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>



<html>
<head>
 <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample.css'>  <%-- 自己的css --%>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/sample/css/sample_media.css'> <%-- 自己的css --%>


<style>
main.table{
    vertical-align: top;
    font-size: 1rem;
    /* border: 1px solid rgb(255, 255, 255); */
  }


.list-title{
	/* background-color: rgb(199, 243, 199); */
	color: black;
	text-align: center;
}

.list-tl{
	text-align: right;
}

div.sidebar{
    margin-top: 30px;
    margin-left: 20px;
	width:150px;
	float:left;
	height:auto;
	text-align:center;
	font-size:20px;
	color: black;
	/* background-color: white; */
	}

    td{
        border: 1px solid black;
        padding: 10px 10px 10px 10px;
        text-align: center;
    } 
    
    .list-no{
        width: 100px;
    }
    
    .list-tl{
        text-align: center;
    }
h1 {
    font-size:40px;
    padding: 20px; 
    height: 70px; 
    text-align: center;
}

h2 {
  margin-top: 20px;
  font-size:25px;
  height: 40px; 
  text-align: center;
}

main.table{
    display: flex;
    vertical-align: top;
    font-size: 1rem;
    max-width: 100%;
    }
  
  .list-bgcolor{
    width: 80%;
    background-color: white;
  }
  
  .sidebar{
    width: 20%;
  }
  
 
  .navigation {
    overflow: hidden;
    background-color: #e9e9e9;
  }
  .navigation a {
    float: left;
    display: block;
    color: black;
    text-align: center;
    padding: 14px 80px;
    text-decoration: none;
    font-size: 17px;
  }
  .navigation a:hover {
    background-color: #ddd;
    color: black;
  }
    .navigation a.active {
    background-color: #2196F3;
    color: white;
    }
    .navigation input[type=text] {
    float: right;
    padding: 6px;
    border: none;
    margin-top: 8px;
    margin-right: 16px;
    font-size: 17px;
    }
  
  
  
  div.sidebar{
    background-color: #ddd;
    width: 200px;
    display: inline-block;
    vertical-align: top;
    font-size: 1rem;
    margin-right: 10px;
    border: 1px solid #999;
  }
  
  head.head{
    background-color: #ddd;
    width: 720px;
    margin: 0 auto 10px auto;
    border: 1px solid #999;
  }
  
  div.body_cont{
    width: 720px;
    margin: 0 auto;
    font-size: 0;
  }
  
  
  nav.navigation{
    text-align: right;
    margin: 10px;
    padding: 20px;
  }

  #btn{
    margin-top: 20px;
    width: 100px;
    height: 50px;
    font-size: 18px;
  }

.title1{
      background-color: rgb(214, 228, 214);
      opacity: 0.7;
      height: 80px;
      color: black;
      text-align: center;
      font-size: 50px ;

}
</style>

</head>


<body bgcolor='white'>

<%@ include file="/parts/header.text" %>
<%@ include file="/parts/slide.text" %>

  <div class="latest-news"  id="target-page">
    <div class="container">
      <img src="./icon/dot.svg" alt="" class="dot">
      <h1>Circulation Desk</h1>
      <h3>花園秘密討論區</h3>
    </div>
  </div>
  
  

<div class="title1">花園討論區首頁</div>

<p>This is the Home page for articles</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/article/articleListAll.jsp'>List</a> all Articles.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
        <b>輸入文章ID (如10):</b>
        <input type="text" name="AID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="artSvc" scope="page" class="com.article.model.ArticleService"/>
    <% System.out.println(artSvc); %>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
       <b>選擇文章類別編號:</b>
       <select size="1" name="AID">
         <c:forEach var="art" items="${artSvc.all}" > 
          <option value="${art.AID}">${art.ACID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/ArticleServlet" >
       <b>選擇文章名稱:</b>
       <select size="1" name="AID">
         <c:forEach var="art" items="${artSvc.all}" > 
          <option value="${art.AID}">${art.ANAME}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增文章</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/article/articleAdd.jsp'>Add</a> a new Article.</li>
</ul>
<%@ include file="/parts/footer.text" %>

<script src='${pageContext.request.contextPath}/sample/js/sample.js'></script>  <%-- 自己的js --%>
</body>
</html>