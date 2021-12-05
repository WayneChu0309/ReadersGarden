<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>
<%@ page import="redis.clients.jedis.Jedis"%>

<% 
	VendorVO vendorVO = null;
	Cookie[] cookies = request.getCookies();
	if(cookies!= null && cookies.length > 0){
		for(Cookie c : cookies){
			System.out.println(c.getName());
			if(c.getName().equals("autoLogin")){
				
				Jedis jedis = new Jedis("localhost", 6379);
				System.out.println("cookie value" + c.getValue());
				System.out.println("redis" + jedis.get(c.getValue()));
				String jtoken = jedis.get(c.getValue());
				if(jtoken != null){
					Integer vendorid = new Integer(jtoken);
					VendorService venSvc = new VendorService();
					vendorVO = venSvc.findOneVendor(vendorid);
				}
				jedis.close();
			}
		}	
		if(vendorVO != null){
			try {
				session.setAttribute("vendorVO", vendorVO);
				String location = (String) session.getAttribute("location");

				if(location != null) {
					response.sendRedirect(location);
					return;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			response.sendRedirect(request.getContextPath()+"/home/home.jsp");
		}
	}

%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>廠商專區</title>
</head>
<style>
body{
	margin:0;
	color: #c8f7c8;
	background:#c8c8c8;
	font:600 16px/18px 'Open Sans',sans-serif;
}
*,:after,:before{box-sizing:border-box}
.clearfix:after,.clearfix:before{content:'';display:table}
.clearfix:after{clear:both;display:block}
a{color:inherit;text-decoration:none}

.login-wrap{
	width:100%;
	margin:auto;
	max-width:525px;
	min-height:1300px;
	position:relative;
	background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
	background-size: cover;
	background-position: center center;
}
.login-html{
	width:100%;
	height:100%;
	position:absolute;
	padding:40px 70px 50px 70px;
	/*background:rgba(40,57,101,.9);rgb(88 133 124 / 90%)*/
	background:rgb(88 133 124 / 90%)
	
}
.login-html .sign-in-htm,
.login-html .sign-up-htm{
	top:0;
	left:0;
	right:0;
	bottom:0;
	position:absolute;
	transform:rotateY(180deg);
	-webkit-backface-visibility:hidden;
	transition:all .4s linear;
}
.login-html .sign-in,
.login-html .sign-up,
.login-form .group .check{
	display:none;
}
.login-html .tab,
.login-form .group .label,
.login-form .group .button{
	text-transform:uppercase;
}
.login-html .tab{
	font-size:22px;
	margin-right:15px;
	padding-bottom:5px;
	margin:0 15px 10px 0;
	display:inline-block;
	border-bottom:2px solid transparent;
}
.login-html .sign-in:checked + .tab,
.login-html .sign-up:checked + .tab{
	color:#fff;
	border-color:#1161ee;
}
.login-form{
	min-height:345px;
	position:relative;
	perspective:1000px;
	transform-style:preserve-3d;
}
.login-form .group{
	margin-bottom:15px;
}
.login-form .group .label,
.login-form .group .input,
.login-form .group .button{
	width:100%;
	color:#fff;
	display:block;
}
.login-form .group .input,
.login-form .group .button{
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:rgba(255,255,255,.1);
}
.login-form .group input[data-type="password"]{
	text-security:circle;
	-webkit-text-security:circle;
}
.login-form .group .label{
	color: lightgray;
	font-size:12px;
}
.login-form .group .button{
	background:#1161ee;
}
.login-form .group label .icon{
	width:15px;
	height:15px;
	border-radius:2px;
	position:relative;
	display:inline-block;
	background:rgba(255,255,255,.1);
}
.login-form .group label .icon:before,
.login-form .group label .icon:after{
	content:'';
	width:10px;
	height:2px;
	background:#fff;
	position:absolute;
	transition:all .2s ease-in-out 0s;
}
.login-form .group label .icon:before{
	left:3px;
	width:5px;
	bottom:6px;
	transform:scale(0) rotate(0);
}
.login-form .group label .icon:after{
	top:6px;
	right:0;
	transform:scale(0) rotate(0);
}
.login-form .group .check:checked + label{
	color:#fff;
}
.login-form .group .check:checked + label .icon{
	background:#1161ee;
}
.login-form .group .check:checked + label .icon:before{
	transform:scale(1) rotate(45deg);
}
.login-form .group .check:checked + label .icon:after{
	transform:scale(1) rotate(-45deg);
}
.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
	transform:rotate(0);
}
.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
	transform:rotate(0);
}

.hr{
	height:2px;
	margin:60px 0 50px 0;
	background:rgba(255,255,255,.2);
}
.foot-lnk{
	text-align:center;
}

.errorMsgs{
	font-size: 12px;
	font-weight: 500;
	color: pink;
	margin-left: 50px;
	letter-spacing: 1px;
}

#title_vendor{
	color: white;
	text-align: center;
	font-weight: 800;
	font-size: 34px;
	letter-spacing: 3px;
	margin-bottom: 3.5rem;
}

#twzipcode{
	margin: 5px 0 5px 0;
}

.city,
.town{
	background-color: rgba(210,210,210,0.5);
	border-radius: 3px;
	margin-right: 3px;
}

#member_zone{
	float: right;
}

</style>
<body>

<div class="login-wrap">
 
	<div class="login-html">
		<h2 id="title_vendor">廠商專區</h2>
		<a id="member_zone" href="<%=request.getContextPath()%>/login/login.jsp">一般會員</a>
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登入</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">註冊</label>
		
		<div class="login-form" >
	
			<div class="sign-in-htm" >
			<form class="login" ACTION="<%=request.getContextPath()%>/vendor/VendorLogin" method="post" id="form1">
				<div class="group">

					<label for="user" class="label">信箱</label>					
					<c:if test="${errorLogin != null}">
						<span class="errorMsgs">${errorLogin.email}</span>
					</c:if>
					<input id="email_input" type="text" name="email" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label">密碼</label>
					<c:if test="${errorLogin != null}">
						<span class="errorMsgs">${errorLogin.password}</span>
					</c:if>
					<input id="password_input" type="password" name="password" class="input" >
				</div>
	
				<div class="group">
					<input name="autoLogin" value="true" id="check" type="checkbox" class="check" checked>
					<label for="check"><span class="icon"></span> 保持登入</label>
				</div>
				<div class="group">
					<input type="submit" class="button" value="登入"  id="login" >
				</div>
					
				<div class="hr"></div>
				<div class="foot-lnk">
					<a href="<%=request.getContextPath()%>/login/vendorForgetPassword.jsp">忘記密碼?</a>
				</div>
			</form>
			</div>
			
			<div class="sign-up-htm">
			<form method="post" action="<%=request.getContextPath()%>/vendor/VendorServlet">
				<div class="group">
					<label for="user" class="label">廠商名稱</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["company"]}</font><br>
					</c:if>
					<input type="text" class="input" name="vcompany" value="${venVO == null? " " : venVO.company}">
				</div>
				<div class="group">
					<label for="vtaxid" class="label">統一編號</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["taxid"]}</font><br>
					</c:if>
					<input type="text" class="input" name="vtaxid" value="${venVO == null? " " : venVO.taxid}" >
				</div>
				<div class="group">
					<label for="vpassword" class="label">密碼</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["password"]}</font><br>
					</c:if>
					<input type="password" class="input" name="vpassword">
				</div>
				<div class="group">
				     <label for="vpassword" class="label">確認密碼</label>
				     <c:if test="${not empty errorMsgs}">
				      <font class="errorMsgs">${errorMsgs["passwordConfirm"]}</font><br>
				     </c:if>
				     <input type="password" class="input" name="vpasswordConfirm">
				</div>
				
				<div class="group">
					<label for="vname" class="label">聯絡人</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["name"]}</font><br>
					</c:if>
					<input type="text" name="vname" class="input" value="${venVO == null? " " : venVO.name}">
				</div>
				<div class="group">
					<label for="vemail" class="label">電子郵件</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["email"]}</font><br>
					</c:if>
					<input type="text" class="input" name="vemail" value="${venVO == null? " " : venVO.email}">
				</div>
				<div class="group">
					<label for="vtel" class="label">聯絡電話</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["tel"]}</font><br>
					</c:if>
					<input type="text" class="input" name="vtel" value="${venVO == null? " " : venVO.tel}">
				</div>
				<div class="group">
					<label for="pass" class="label">手機 (非必填)</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["mobile"]}</font><br>
					</c:if>
					<input type="text" class="input" name="vmobile" value="${venVO == null? " " :	venVO.mobile}">
				</div>
				<div class="group">
					<label for="pass" class="label">聯絡地址</label>
					<c:if test="${not empty errorMsgs}">
						<font class="errorMsgs">${errorMsgs["addr"]}</font><br>
					</c:if>
					<div id="twzipcode"></div>
					
					<input type="text" class="input" name="vaddr" value="${venVO == null? " " : addr}">
				</div>
				<div class="group">
					<input type="hidden" name="action" value="insert">
					<input type="submit" class="button" value="Sign Up">
				</div>
			</form>
				<div class="hr"></div>
				<div class="foot-lnk">
					<label for="tab-1">Already Member?</a>
				</div>
			</div>
		</div>
		
	</div>
	


 <script src="<%=request.getContextPath() %>/jquery/jquery-3.6.0.min.js"></script>
 
   <script>
   	var home = "<%=request.getContextPath() %>/home/home.jsp";
   
   </script>
   
 	<script	src="${pageContext.request.contextPath}/vendor/jquery/jquery.twzipcode.min.js"></script>
	
	<script>
	
	
	var city = `${city}`
	var town = `${town}`
	
		$("#twzipcode").twzipcode({
		countySel: city, // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
		districtSel: town, // 地區預設值
		zipcodeIntoDistrict: true, // 郵遞區號自動顯示在地區
		css: ["city form-control", "town form-control"], // 自訂 "城市"、"地區" class 名稱
		countyName: "city", // 自訂城市 select 標籤的 name 值
		districtName: "town" // 自訂地區 select 標籤的 name 值
		});
	
		if(`${register}` == "register"){
			$("div.login-html input").attr("checked", false)
			$("div.login-html input#tab-2").attr("checked", true)
			console.log('yes');
		}
	</script>
 
 
 
 
</body>
</html>