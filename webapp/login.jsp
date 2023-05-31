<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
response.setHeader("Content-type", "text/html;charset=UTF-8");
response.setCharacterEncoding("UTF-8");
request.getHeader("text/html;charset=utf-8");
request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">

<title>正在登录请稍后。。。</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" charset="UTF-8">

let user=urldata("user");
let flag=urldata("flag");
//url参数转化成数组
function urldata(data){
var url = decodeURI(window.location.search);
var Objs = url.split("?");
if (Objs.length > 1) {
    //取数组第二位从&进行开始切割，数组index默认从0开始，因为split把字符串转换成数组，所以要取第二位，因为第二位是我们要的值
    var arr = Objs[1].split("&");
    console.log(arr);
    var newarr;//建立一个新数组，用来保存我们要的值
    for (var i = 0; i < arr.length; i++) {
        //以等号分割后的值丢到arr这个数组
        newarr = arr[i].split("=");
        console.log(newarr);
        if (newarr != null && newarr[0] == data) {
            //取第二位的值，split和上面一样的原理
            return newarr[1];
        }
    }
    
  }
}
	var i = 5;
	window.onload = time;
	function time() {
		var s = document.getElementById("s");
		s.innerText = i;
		i--;
		//定时的操作：
		//window.setTimeout('',1000) window.setInterval(); 
		window.setTimeout('time()', 1000);
	}
</script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	display: flex;
	background: linear-gradient(to right, rgb(247, 209, 215),
		rgb(191, 227, 241));
	/*从左到右的渐变*/
}

a{

   text-decoration:none;

}

.div {
	margin: auto;
	position: relative;
	width: 600px;
	height: 200px;
	text-align: center;
	margin-top: 20%;
	margin-left: 30%;
	border-radius: 20px;
	background: rgb(546, 409, 415);
	border: 1px solid rgba(255, 255, 255, .9);
}

.a1 {

	color:red;
	
}

.div a>font:hover {
	cursor: pointer;
	/* border-bottom: 2px solid red; */
	text-decoration: line-through;
}
</style>
<body>
<% 

String user = request.getParameter("user");
String flag = request.getParameter("flag");
//使用url之前要进行编码，缺少会出现？？
String classname = URLEncoder.encode(user);
//String classname = URLDecoder.decode(request.getParameter("user"));//接收解码，配套使用

if(flag.equals("true")){
	
  response.setHeader("refresh","5;URL=manage.html?user="+classname); 
  
}else{
	
  response.setHeader("refresh","5;URL=index.html?user="+classname); 
}
%>
	<div class="div">
		
		<font size="6" color="#A2A778">登陆成功！</font> 
		<font size="6" color="red" id="s">5</font> <font size="6" color="#A2A778">秒后自动跳转到首页</font>
		<br> <br> <br> <a href="index.html?user=<%=user%>"><font size="5"; class="a1">若未跳转？点击此处</font></a>
	
	</div>
</body>

</html>
