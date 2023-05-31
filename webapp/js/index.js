/** banner **/
var banner = document.getElementById("banner");
var circleList = document.getElementById("circle-list");
var lis = banner.children[0].children;
var cirLis = circleList.children[0].children;
var bannerLeft = document.getElementById("banner-left");
var bannerRight = document.getElementById("banner-right");
//设置圆点总的宽度
var cirLisWidth = (cirLis.length) * 11 + 14;
circleList.style.width = cirLisWidth + "px";
//设置圆点的个数
var index = 0;
//轮播图自动轮播
var run;
autoRun();
function autoRun() {
	run = setInterval(function () {
		lis[index].removeAttribute("class");
		cirLis[index].removeAttribute("class");
		index++;
		if (index == lis.length) {
			index = 0;
		}
		lis[index].setAttribute("class", "active");
		cirLis[index].setAttribute("class", "active-circle");
	}, 2000)
}
//鼠标移入banner
banner.onmouseenter = function () {
	clearInterval(run);
}
banner.onmouseleave = function () {
	autoRun();
}
//鼠标移入圆点
for (var i = 0; i < cirLis.length; i++) {
	cirLis[i].onmouseover = (function (i) {
		return function () {
			lis[index].removeAttribute("class");
			cirLis[index].removeAttribute("class");
			index = i;
			lis[i].setAttribute("class", "active");
			cirLis[i].setAttribute("class", "active-circle");
		}
	})(i)
}
//向左点击
bannerLeft.onclick = function () {
	lis[index].removeAttribute("class");
	cirLis[index].removeAttribute("class");
	index--;
	if (index < 0) {
		index = lis.length - 1;
	}
	lis[index].setAttribute("class", "active");
	cirLis[index].setAttribute("class", "active-circle");
}
//向右点击
bannerRight.onclick = function () {
	lis[index].removeAttribute("class");
	cirLis[index].removeAttribute("class");
	index++;
	if (index == lis.length) {
		index = 0;
	}
	lis[index].setAttribute("class", "active");
	cirLis[index].setAttribute("class", "active-circle");
}
/** 图书 **/
var tushu = document.getElementById("tushu");
var tushucircleList = document.getElementById("tushuCircle-list");
var tushulis = tushu.children[0].children;
var tushucirLis = tushucircleList.children[0].children;
var tushuLeft = document.getElementById("tushu1Left");
var tushuRight = document.getElementById("tushu1Right");
//设置圆点总的宽度
var tushucirLisWidth = (tushucirLis.length) * 11 + 14;
tushucircleList.style.width = tushucirLisWidth + "px";
//设置圆点的个数
var index1 = 0;
//自动轮播
var run1;
autoRun1();
function autoRun1() {
	run1 = setInterval(function () {
		tushulis[index1].removeAttribute("class");
		tushucirLis[index1].removeAttribute("class");
		index1++;
		if (index1 == tushulis.length) {
			index1 = 0;
		}
		tushulis[index1].setAttribute("class", "active");
		tushucirLis[index1].setAttribute("class", "active-circle");
	}, 2000)
}
//鼠标移入图书
tushu.onmouseenter = function () {
	clearInterval(run1);
}
tushu.onmouseleave = function () {
	autoRun1();
}
//鼠标移入圆点
for (var i = 0; i < tushucirLis.length; i++) {
	tushucirLis[i].onmouseover = (function (i) {
		return function () {
			tushulis[index1].removeAttribute("class");
			tushucirLis[index1].removeAttribute("class");
			index1 = i;
			tushulis[i].setAttribute("class", "active");
			tushucirLis[i].setAttribute("class", "active-circle");
		}
	})(i)
}
//向左点击
tushuLeft.onclick = function () {
	tushulis[index1].removeAttribute("class");
	tushucirLis[index1].removeAttribute("class");
	index1--;
	if (index1 < 0) {
		index1 = tushulis.length - 1;
	}
	tushulis[index1].setAttribute("class", "active");
	tushucirLis[index1].setAttribute("class", "active-circle");
}
//向右点击
tushuRight.onclick = function () {
	tushulis[index1].removeAttribute("class");
	tushucirLis[index1].removeAttribute("class");
	index1++;
	if (index1 == tushulis.length) {
		index1 = 0;
	}
	tushulis[index1].setAttribute("class", "active");
	tushucirLis[index1].setAttribute("class", "active-circle");
}
/** 倒计时 **/
function countTime() {
	//获取当前时间  
	var date = new Date();
	var now = date.getTime();
	//设置截止时间  
	var str = "2018/12/20 00:00:00";
	var endDate = new Date(str);
	var end = endDate.getTime();

	//时间差  
	var leftTime = end - now;
	//定义变量 h,m,s保存倒计时的时间  
	var h, m, s;
	if (leftTime >= 0) {
		h = Math.floor(leftTime / 1000 / 60 / 60 % 24);
		m = Math.floor(leftTime / 1000 / 60 % 60);
		s = Math.floor(leftTime / 1000 % 60);
	}
	(h < 10) ? h = "0" + h : h;
	(m < 10) ? m = "0" + m : m;
	(s < 10) ? s = "0" + s : s;
	//将倒计时赋值到div中  
	//document.getElementById("_d").innerHTML = d+"天";  
	document.getElementById("_h").innerHTML = h;
	document.getElementById("_m").innerHTML = m;
	document.getElementById("_s").innerHTML = s;
	//递归每秒调用countTime方法，显示动态时间效果  
	setTimeout(countTime, 1000);
}
countTime();
/** 闪购 **/
var shangou = document.getElementById("shangou");//闪购div
var shangouCon = document.querySelector(".flashover_item_con_div");	//获取要移动的div
var goodlist = document.getElementsByClassName("flashover_goodlist");//单个商品div
var gLen = goodlist.length;//商品个数
var showW = parseInt((234 + 14) * 4);	//div显示宽度
var divW = parseInt((234 + 14) * gLen); //div总宽度
var runL, runR;
function flashover(val) {
	switch (val) {
		case "R":
			clearInterval(runL);
			autoR();
			setTimeout("clearInterval(runR)", 200);
			break;
		case "L":
			clearInterval(runR);
			autoL();
			setTimeout("clearInterval(runL)", 200);
			break;
	}
}
function autoL() {
	var boxLeft = shangouCon.style.left;
	var boxLeftNum = parseInt(boxLeft.substr(0, boxLeft.length - 2));	//获取距离左侧距离
	var a = boxLeftNum;
	runL = setInterval(function () {
		if (a > -992 && a <= 0) {
			a = a - 248;
			shangouCon.style.left = a + "px"; //每点击一次，向右移动50px
			console.log(a);
		}
	}, 50);
}
function autoR() {
	var boxLeft = shangouCon.style.left;
	var boxLeftNum = parseInt(boxLeft.substr(0, boxLeft.length - 2));	//获取距离左侧距离
	var a = boxLeftNum;
	runR = setInterval(function () {
		if (a >= -992 && a < 0) {
			a = a + 248;
			shangouCon.style.left = a + "px"; //每点击一次，向右移动50px
			console.log(a);
		}
	}, 50);
}
/** tab **/
var houseElectricalTitle = document.getElementById("houseElectricalTitle").getElementsByTagName("li");
var houseElectricalDiv = document.getElementById("houseElectricalDiv").getElementsByClassName("wiring_right_con");
var tabLen = houseElectricalTitle.length;
for (var i = 0; i < tabLen; i++) {
	houseElectricalTitle[i].index = i;
	houseElectricalTitle[i].onmouseover = function () {
		var num = parseInt(this.index);
		for (var j = 0; j < tabLen; j++) {
			houseElectricalTitle[j].className = "";
			houseElectricalDiv[j].className = "wiring_right_con hide";
		}
		houseElectricalTitle[num].className = "active";
		houseElectricalDiv[num].className = "wiring_right_con show";
	}
}