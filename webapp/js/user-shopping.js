let user = urldata("user");
var req;
var reqq;
//查询用户的拍品
fetch('http://localhost:8080/VueJava/user_shopping?user=' + user + '&flag=', {
	method: 'Get',
	headers: {
		'Content-Type': 'application/json'
	},
})
	.then(response => response.json())
	.then(data => {
		console.log(data),
			req = data,
			Polling();
	}
	)
	.catch(error => console.error(error))

const vm = new Vue({
	el: "#t1",
	data: function () {
		return {
			itemq: null,

		}
	},
	mounted() {
		window.Polling = this.Polling;    // 方法赋值给window
	},
	methods: {

		Polling() {
			this.itemq = req,
				console.log("对象赋值");
		},
		delt_G(fl) {		
			let f2 = fl;
			console.log(f2);
			fetch('http://localhost:8080/VueJava/user_shopping?flag=' + f2 + '&user=' + user)
				.then(response => response.json())
			alert('您删除该了商品！！');
			location.reload();
		}

	}

})
//查询用户的商品
fetch('http://localhost:8080/VueJava/user_shopping?user=' + user + '&flag=', {
	method: 'Post',
	headers: {
		'Content-Type': 'application/json'
	},
})
	.then(response => response.json())
	.then(data => {
		console.log(data),
			reqq = data,
			Pollingg();
	}
	)
	.catch(error => console.error(error))

const vmm = new Vue({
	el: "#t2",
	data: function () {
		return {
			itemq: null,

		}
	},
	mounted() {
		window.Pollingg = this.Polling;    // 方法赋值给window
	},
	methods: {

		Polling() {
			this.itemq = reqq,
				console.log("对象赋值");
		},
		delt_P(f1) {		
			let f2 = f1;
			console.log(f2);

			fetch('http://localhost:8080/VueJava/user_shopping?user=' + user + '&flag=' + f2, {
				method: 'Post',
				headers: {
					'Content-Type': 'application/json'
				},
				// body: JSON.stringify({
				// 	user: user,
				// 	flag: f2
				// })
			})
				.then(response => response.json())
				.catch(error => console.error(error))
				alert('我们将会扣除您的定金，请注意查收！！');
			location.reload();
		}
	}

})
//url参数转化成数组
function urldata(data) {
	var url = decodeURI(window.location.search);
	var Objs = url.split("?");
	if (Objs.length > 1) {
		//取数组第二位从&进行开始切割，数组index默认从0开始，因为split把字符串转换成数组，所以要取第二位，因为第二位是我们要的值
		var arr = Objs[1].split("&");

		var newarr;//建立一个新数组，用来保存我们要的值
		for (var i = 0; i < arr.length; i++) {
			//以等号分割后的值丢到arr这个数组
			newarr = arr[i].split("=");

			if (newarr != null && newarr[0] == data) {
				//取第二位的值，split和上面一样的原理
				return newarr[1];
			}
		}
	}
}

