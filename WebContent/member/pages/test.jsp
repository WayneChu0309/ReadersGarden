<html>
<head>
<title>new document </title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<script type="text/javascript">
function writeInfo() {
if (msg = confirm("是否確錄入使用者資訊")) {
var names = "_blank";
var name = prompt("請輸入您的姓名", "");
var pwd = prompt("請輸入您的密碼", "");
if (name.length < 5 || name.length > 16) {
alert("姓名不符合要求,請重新輸入");
} else if (pwd.length < 5 || pwd.length > 16) {
alert("密碼長度不符合要求");
} else {
alert("資訊錄入成功!");
window.location.href = "http://www.52aixuexi.com";
}
} else {
return false;
}
}
</script>
</head>
<body>
<input type="button" value="點此錄入使用者資訊"onclick="writeInfo()" />
</body>
</html>