<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="mysql.UseServer" import="java.util.List"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setCharacterEncoding("UTF-8");
request.getHeader("text/html;charset=utf-8");
UseServer accontDao = new UseServer();
String name = "";
String Paw = "";
if (request.getParameter("name") != null) {
	name = request.getParameter("name");
	Paw = request.getParameter("Paw");
	System.out.println(name);
	System.out.println(Paw);

	if (!name.equals("") && !Paw.equals("")) {
		//查询用户是否存在
		String sql = "SELECT *FROM `user` WHERE username='{0}' && `password`='{1}'";
		sql = sql.replace("{0}", name);
		sql = sql.replace("{1}", Paw);

		//添加用户账户
		String sq2 = "INSERT INTO `user` (username,`password`,adminstrator) VALUES ('{0}','{1}','true')";
		sq2 = sq2.replace("{0}", name);
		sq2 = sq2.replace("{1}", Paw);
		try {
	List list = accontDao.DiySql(sql);
	if (list != null && !list.isEmpty()) {

		out.println("用户存在！！，请更换账户与密码");
	} else {

		int req = accontDao.UpdateSql(sq2);
		if (req != 0 && !(req < 0)) {

			out.println("您的账户添加成功！！");
		} else {

			out.println("账户添加出现错误！！请重试或联系管理员");
		}
	}
		} catch (ClassNotFoundException e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
		}
	}
}
%>
<html>
<head>
<title>后台管理员添加页面</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

input {
	outline: none;
}

html, body {
	height: 100%;
}

body {
	display: flex;
	background: linear-gradient(to right, rgb(247, 209, 215),
		rgb(191, 227, 241));
	/*从左到右的渐变*/
}

.box {
	width: 600px;
	height: 600px;
	display: flex;
	position: relative;
	/* background-color: #fff; */
	margin: auto;
	border-radius: 8px;
	border: 1px solid rgba(255, 255, 255, .6);
	box-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
	flex-direction: column;
}

.input-box {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.btu-box {
	display: flex;
	justify-content: center;
}

.btu-box p {
	height: 30%;
	line-height: 30%;
	font-size: 14px;
	color: rgb(255, 255, 255);
}

.btu-box {
	display: flex;
	justify-content: center;
}

.btu-box p:hover {
	cursor: pointer;
	border-bottom: 1px solid white;
}

button {
	width: 100px;
	height: 30px;
	margin: 0 7px;
	line-height: 30px;
	border: none;
	border-radius: 4px;
	background-color: #b0cfe9;
	color: white;
}

.register-form {
	flex: 1;
	height: 100%;
}

.title-box {
	height: 100px;
	line-height: 100px;
}

.title-box h1 {
	text-align: center;
	color: white;
	letter-spacing: 5px;
	text-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
}

input {
	width: 60%;
	height: 40px;
	margin-bottom: 20px;
	text-indent: 4px;
	border: 1px solid #b0cfe9;
	border-radius: 4px;
}

input:focus {
	color: #b0cfe9;
}

input:focus::placeholder {
	opacity: 0;
}

.img-box {
	margin: 0 auto;
	width: 100px;
	height: 100px;
	margin: 20px auto;
	border-radius: 50%;
	overflow: hidden;
	box-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
}

.img-box img {
	width: 100%;
	transition: 0.6s;
}

.pre-box  h1, p {
	text-align: center;
}
</style>
<body>
	<div class="box">
		<div class="pre-box">
			<h1>Welcome</h1>
			<p>JOIN US!</p>
			<div class="img-box">
				<img src="./png/hhcat.png" alt="">
			</div>
		</div>
		<div class="register-form">
			<div class="title-box">
				<h1>注册</h1>
			</div>
			<div class="input-box">
				<input type="text" placeholder="用户名" id="Name"> <input
					type="password" placeholder="密码" id="Paw"> <input
					type="password" placeholder="确认密码" id="Pawd">
			</div>
			<div class="btu-box">
				<button onclick="btu()">注册</button>
			</div>
		</div>
	</div>
</body>
<script>


//以fetch的Ajax方式请求这个jsp
function btu(){
	const Name = document.getElementById('Name').value;
	const Paw = document.getElementById('Paw').value;
	const Pawd = document.getElementById('Pawd').value;
	if(Paw == Pawd ){
	fetch('http://localhost:8080/VueJava/admin-log.jsp?name=' + Name + '&Paw='+Paw , {
	    method: 'Get',
	    headers: {
	        'Content-Type': 'application/json'
	    }
	})
	    .then(response => response.text())
	    .then(data => {
	    	//分割返回的字符串
	      var Objs = data.split("<");
	       alert(Objs[0]);
	    }
	    )
	    .catch(error => console.error(error))
	}else{
		
		alert('密码不一致')
		
	}
}

</script>
</html>