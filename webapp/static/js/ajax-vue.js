//不停点击函数
var processID = self.setInterval("ajax()", 1000);
var reqq;
//在页面刷新和关闭的时候触发事件，表示正要去服务器读取新的页面时调用，此时还没开始读取。
//检测资源加载
/*window.onload = function() 
{	
   ajax();// 加上这个可以拦截页面关闭，return 只要有返回就可以
}*/
let name = urldata("name");
let user = urldata("user");

function Price() {
    const Price = document.getElementById('Price').value;


    if (Price > (vm.commodity.LPrice)) {

        //创建对象
        const xhr = new XMLHttpRequest();

        //true是XHR的异步开启参数，关闭是false
        xhr.open('get', 'http://localhost:8080/VueJava/quote?Price=' + Price + '&user=' + user + '&Lname=' + name, true);
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
                    alert(req);
                    //弹出返回结果  
                    //var req1 = eval("(" + req + ")");

                    //result.innerHTML = xhr.response;

                }

            }

        }

    } else {

        alert("出价小于竞拍，出价失败！！");
    }

}

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

//ajax();
function ajax() {
    //  const username = document.getElementById('username').value;
    //  const password = document.getElementById('password').value;

    //创建对象
    const xhr = new XMLHttpRequest();

    //true是XHR的异步开启参数，关闭是false
    xhr.open('get', 'http://localhost:8080/VueJava/began?Lname=' + name, true);
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
                //alert(req);
                //弹出返回结果  
                var req1 = eval("(" + req + ")");
                Polling();
                remarks(req1);
                //result.innerHTML = xhr.response;
                //获取时间戳
                document.getElementById("h").innerHTML = Pdata(req1.date).h1;
                document.getElementById("m").innerHTML = Pdata(req1.date).m1;
                document.getElementById("s").innerHTML = Pdata(req1.date).s1;

                let add = Pdata(req1.date).h2 + ":" + Pdata(req1.date).m2 + ":" + Pdata(req1.date).s2;
                document.getElementById("data").innerHTML = add;

                if (Pdata(req1.date).cuo <= 0) {
                    alert("拍卖结束!!");
                    window.location.href = "index.html?user=" + user;

                }

                return reqq = req1;

                //长轮询
                // ajax();
            }

        }

    }

}

//截至、倒计时方法
function Pdata(data) {

    let date = new Date().valueOf();
    //毫秒转换成秒
    date = parseInt(date / 1000);
    // 取天余数，得今天过了多少秒,注意时差
    let cuoo = parseInt((date + 8 * 60 * 60) % (24 * 60 * 60));
    let data1 = parseInt(data % (24 * 60 * 60));

    let cuo = parseInt(data - cuoo);
    // 小时换算
    let h1 = parseInt(cuo / 3600);
    let h2 = parseInt(data1 / 3600);
    // 分钟换算
    let m1 = parseInt((cuo / 60) % 60);
    let m2 = parseInt((data1 / 60) % 60);

    let s1 = cuo - (h1 * 60 * 60) - (m1 * 60);
    let s2 = data1 - (h2 * 60 * 60) - (m2 * 60);
    return {
        h1, m1, s1,
        h2, m2, s2, cuo
    }
}


Vue.config.productionTip = false//阻止Vue的开发提示
const vm = new Vue({
    el: "#t1",
    data: function () {
        return {
            commodity: null,
            remarks: null,
        }
    },
    mounted() {
        window.Polling = this.Polling;    // 方法赋值给window
        window.remarks = this.remarksS;
    },
    methods: {

        Polling() {
            this.commodity = reqq,
                console.log(this.commodity);
            console.log("对象赋值");
        },
        remarksS(remarks) {
            fetch('http://localhost:8080/VueJava/UserQuote?rname=' + remarks.Lname + '&rurl=' + remarks.url + '&flag=LL', {
                method: 'Get',
                headers: {
                    'Content-Type': 'application/json'
                },
            })

                .then(Response => Response.json())
                .then(data => {
                    this.remarks = data,
                        console.log(data)
                });
        },

        updata() {
            const remarks = document.getElementById('remarks').value;
            if (remarks != '' && remarks != null && remarks != 'undefined') {
                fetch('http://localhost:8080/VueJava/UserQuote?rname='
                    + reqq.Lname + '&rurl=' + reqq.url + '&flag=LC' + '&remarks=' + remarks)
                    .then(Response => Response.text())
                    .then(data => {
                        alert(data);
                    })
            } else {

                alert("请输入评论！！");
            }
        }

    }

})