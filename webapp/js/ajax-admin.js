// 创建空对象
var formData = {};

function ajax() {
	//  const username = document.getElementById('username').value;
	//  const password = document.getElementById('password').value;

	//创建对象
	const xhr = new XMLHttpRequest();

	//true是XHR的异步开启参数，关闭是false
	xhr.open('post', 'http://localhost:8080/VueJava/UserQuote', true);
	//设置响应头，如果参数值中需要‘&’，则必须对其进行编码。
	xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded ');
	//发送，‘get’数据包含在‘url’里，所以send（null），‘post’则用send（data）来发送。
	//xhr.send('username=' + username + '&password=' + password);
	//把js对象转换成json字符
	var json = JSON.stringify(formData);
	xhr.send(json);
	//打印状态码
	//console.log(xhr.readyState);
	xhr.onreadystatechange = function () {
			if (xhr.readyState == 4) {
				if (xhr.status >= 200 && xhr.status < 300) {

					//打印状态码
					console.log(xhr.readyState);
					//xhr的两种接收方法,文本数据（字符串）
					var req = xhr.responseText;
					//xhr的两种接收方法,XML文件
					//var req = req.responseXML;
                     alert(req);
					//将json字符串转换成js对象
					//var req1 = eval("(" + req + ")");
					//将js对象转换成json格式字符串
					//var a = JSON.stringify(req1);
					
				  //console.log(a);

				}

			}
		}
}

//时间判断
function Pdata() {

	let hInput = parseInt(document.getElementById("h1").value);
	let mInput = parseInt(document.getElementById("m2").value);
	let sInput = parseInt(document.getElementById("s3").value);
	let date = new Date().valueOf();

	console.log(hInput + ":" + mInput + ":" + sInput);

	if (hInput >= 0 && hInput < 24 && 0 <= mInput && mInput < 60 && 0 <= sInput && sInput < 60) {


		let sj = hInput * 60 * 60 + mInput * 60 + sInput;

		//毫秒转换成秒
		date = parseInt(date / 1000);
		// 取天余数，得今天过了多少秒,注意时差
		let cuoo = parseInt((date + 8 * 60 * 60) % (24 * 60 * 60));
		console.log(sj);
		console.log(cuoo);
		if (sj < cuoo) {

			alert("已经设定好“明天”" + hInput + ":" + mInput + ":" + sInput + "截至");
            sj= sj+24*60*60;
			formData["date"] = sj;

		} else {

			alert("已经设定好“今天”" + hInput + ":" + mInput + ":" + sInput + "截至");

			formData["date"] = sj;
		}

		return true;

	} else {

		alert("请输入24小时之内的正确时间！！");
		return false;
	}

}

//表单转换对象
function submitForm() {
	// 获取表单元素
	let typeInput = document.getElementById("type").value;
	let nameInput = document.getElementById("Lname").value;
	let wraparoundInput = document.getElementById("wraparound").value;
	let priceInput = document.getElementById("price").value;
	let urlInput = document.getElementById("url").files[0];
	let detailInput = document.getElementById("detail").value;
	flag = Pdata();
	if (flag) {
		if (nameInput != "" && wraparoundInput != "" && priceInput != "" && urlInput != undefined) {
			// 设置对象属性
			formData["type"] = typeInput;
			formData["Lname"] = nameInput;
			formData["wraparound"] = wraparoundInput;
			formData["price"] = priceInput;
			formData["url"] = urlInput.name;
			formData["detail"] = detailInput;
			formData["LPrice"] = priceInput;
			//启用阿贾克斯
			ajax();
			
			const fedata = new FormData();
			fedata.append("file", urlInput);
			fetch("http://localhost:8080/VueJava/Quote_image",{
				method: "post",
				body: fedata
			});
			console.log(urlInput);
			console.log(fedata);
		} else {

			alert("拍品添加失败！！拍品名称、拍品简介、起拍价、展示图片为必填项！！");

		}
	}
	// 输出结果
	console.log(formData);
}