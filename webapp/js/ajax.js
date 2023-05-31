//不停点击函数
var processID = self.setInterval("ajax()", 500);
var req2;
var req3;
//在页面刷新和关闭的时候触发事件，表示正要去服务器读取新的页面时调用，此时还没开始读取。
//检测资源加载
/*window.onload = function() 
{	
   ajax();// 加上这个可以拦截页面关闭，return 只要有返回就可以
}*/


//ajax();
function ajax() {
	//  const username = document.getElementById('username').value;
	//  const password = document.getElementById('password').value;

	//创建对象
	const xhr = new XMLHttpRequest();

	//true是XHR的异步开启参数，关闭是false
	xhr.open('post', 'http://localhost:8080/VueJava/Time', true);
	//设置响应头，如果参数值中需要‘&’，则必须对其进行编码。
	xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded ');
	//发送，‘get’数据包含在‘url’里，所以send（null），‘post’则用send（data）来发送。
	//xhr.send('username=' + username + '&password=' + password);
	xhr.send(null);

	//打印状态码
	//console.log(xhr.readyState);

	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4) {

			if (xhr.status >= 200 && xhr.status < 300) {

				//打印状态码
				// console.log(xhr.readyState);
				//xhr的两种接收方法,文本数据（字符串）
				var req = xhr.responseText
				//xhr的两种接收方法,XML文件
				//var req = req.responseXML;
				console.log(req);
				//alert(req);
				//弹出返回结果  

				//result.innerHTML = xhr.response;

				var h = t_h(req);
				var m = t_m(req);
				var s = t_s(req);

				document.getElementById("h").innerHTML = h;
				document.getElementById("m").innerHTML = m;
				document.getElementById("s").innerHTML = s;

				console.log(tt(req));

				//长轮询
				// ajax();
			}

		}

	}


}

ajax1();
function ajax1() {
	//创建对象
	const xhr = new XMLHttpRequest();

	//短轮询
	setInterval(function () {

		//true是XHR的异步开启参数，关闭是false
		xhr.open('post', 'http://localhost:8080/VueJava/began', true);
		//设置响应头，如果参数值中需要‘&’，则必须对其进行编码。
		xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded ');
		//发送，‘get’数据包含在‘url’里，所以send（null），‘post’则用send（data）来发送。
		//xhr.send('username=' + username + '&password=' + password);
		xhr.send(null);
		Polling();

		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4) {
				if (xhr.status >= 200 && xhr.status < 300) {

					//打印状态码
					console.log(xhr.readyState);
					//xhr的两种接收方法,文本数据（字符串）
					var req = xhr.responseText;
					//xhr的两种接收方法,XML文件
					//var req = req.responseXML;

					//将json字符串转换成js对象
					var req1 = eval("(" + req + ")");
					//将js对象转换成json格式字符串
					var a = JSON.stringify(req1);
					console.log(req1);
					//console.log(a);

					return req2 = req1;

					//ajax1();
				}

			}
		}
	}, 2000)

}


function tt(tm) {
	let data = parseInt(tm) + (8 * 60 * 60);

	let m = parseInt(((data % (24 * 60 * 60)) / 60) % 60);
	let h = parseInt(data % (24 * 60 * 60) / 3600);
	let s = parseInt((data % (24 * 60 * 60)) - (h * 60 * 60) - (m * 60));

	return h + ":" + m + ":" + s;
}


var t_h = function (date) {
	let data = parseInt(date) + (8 * 60 * 60);
	//data = (hours < 10 ? ('0' + hours) : hours);

	let h = parseInt((data % (24 * 60 * 60)) / 3600);
	return h;

}
var t_m = function (date) {
	let data = parseInt(date) + (8 * 60 * 60);

	let m = parseInt(((data % (24 * 60 * 60)) / 60) % 60);
	return m;

}
var t_s = function (date) {
	let data = parseInt(date) + (8 * 60 * 60);

	let m = parseInt(((data % (24 * 60 * 60)) / 60) % 60);
	let h = parseInt((data % (24 * 60 * 60)) / 3600);
	let s = parseInt((data % (24 * 60 * 60)) - (h * 60 * 60) - (m * 60));
	return s;

}

Vue.config.productionTip = false//阻止Vue的开发提示
const vm = new Vue({
	el: "#t1",
	data: function () {
		return {
			home: 0, //换页起始下标
			end: 4, //换页结束下标
			itemq: null,
			x: 0,
			user: null,
		}
	},
	mounted() {
		window.Polling = this.Polling;    // 方法赋值给window

	},
	methods: {

		Polling() {

			this.itemq = req2,
				this.user = user,
				console.log("对象加载");
		},
		//上一页方法
		updata(home, end) {
			if (0 >= home) {
				alert("已经到头了！！")
			} else {
				this.home = home - 4;
				this.end = end - 4;

			}
		},
		//下一页方法
		dndata(home, end, index) {
			if (end >= index) {
				alert("已经到头了！！")
			} else {
				this.home = home + 4;
				this.end = end + 4;

			}

		},
	},
	/*watch: {
		itemq: {
			deep: true,
			handler(newValue) {

				//this.x <= 3 ? this.x : this.x = 0;
				//this.x++;
 
				/*  this.flag=false
				 if(true){

					  this.$nextTick(()=>{

						this.flag=true
					  }) 
					  },*/
	/*	}
	}
}*/

})
setInterval(() => {
	fetch('http://localhost:8080/VueJava/Quote_image', {
		method: 'Get',
		headers: {
			'Content-Type': 'application/json'
		},
	})
		.then(response => response.json())
		.then(data => {
			console.log(data),
				req3 = data,
				Polling1();
		}
		)
		.catch(error => console.error(error))
}, 1000);

const vm1 = new Vue({
	el: "#t2",
	data: function () {
		return {
			itemq: null,
			user: null,
		}
	},
	mounted() {
		window.Polling1 = this.Polling;    // 方法赋值给window
	},
	methods: {

		Polling() {

			this.itemq = req3,
				this.user = user,
				console.log("对象赋值");
		}

	}

})

function url_ht() {

	if (user != '' && user != null) {
		window.location.href = "User-open.html?user=" + user;
	} else {
		alert("您还没有登录");
	}

}