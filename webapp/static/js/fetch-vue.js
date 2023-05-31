var name = urldata("name");
var user = urldata("user");
var reqq;

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

setInterval(function () {
    fetch('http://localhost:8080/VueJava/Req_commodity?Cname=' + name + '&user=undefined', {
        method: 'Get',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            reqq = data,
                Polling();
            remarks(reqq);
        }
        )
        .catch(error => console.error(error))
}, 2000);

function OData() {
    let name = urldata("name");
    if (user != 'undefined' && user != null && user != '') {

        fetch('http://localhost:8080/VueJava/Req_commodity?Cname=' + name + '&user=' + user, {
            method: 'Get',
            headers: {
                'Content-Type': 'application/json'
            },

        })
            .then(response => response.text())
            .then(data => {
                console.log(data);
                alert(data);
            }
            )
            .catch(error => console.error(error))
    } else {
        alert("登录后才能购买商品！！")
    }
}

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
            fetch('http://localhost:8080/VueJava/UserQuote?rname=' + remarks[0].Cname + '&rurl=' + remarks[0].url + '&flag=CC', {
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
                    + reqq[0].Cname + '&rurl=' + reqq[0].url + '&flag=LC' + '&remarks=' + remarks)
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