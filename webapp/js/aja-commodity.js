let user = urldata("user");
let req;
	fetch('http://localhost:8080/VueJava/Req_commodity?user='+user, {
		method: 'Post',
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

		Vue.config.productionTip = false//阻止Vue的开发提示
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
				console.log(itemq);
		}

	},

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