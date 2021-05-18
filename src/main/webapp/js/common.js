var baseUrl = "http://192.168.1.250:8080/app-opinion-mobile-war/";
//var outurl="http://localhost:8080/app-opinion-web/user/index7.html";
var indexurl="/app-opinion-mobile-web/login/login.html";
//var outurl="http://localhost:8080/app-opinion-web/user/index7.html";
var outurl="/app-opinion-mobile-web/login/login.html";
//var outurl="http://27.54.248.197:8080/app-opinion-web/user/index7.html";
var reporturl = "http://27.54.248.197:8080/common-timetask/"
var upurl="/app-opinion-mobile-web/index/main.html";
function divHeight(){
	if($("#list").children().length>5){
		$(".divbj").css("background-size","100% 100%");
	}else{
		$(".divbj").css("background-size","100%");
	}
}
function FormatDate (strTime) {
	var date = new Date(strTime);
	var data_month = date.getMonth()+1;
	if(date.getMonth()<10){
		data_month = "0"+data_month;
	}
	var datas = date.getDate();
	if(date.getDate()<10){
		datas = "0"+datas;
	}
	var dateTime = date.getFullYear()+"-"+data_month+"-"+datas;
	var today = new Date();
	var month = today.getMonth()+1;
	if(today.getMonth()<10){
		month="0"+month;
	}
	var todayTime = today.getFullYear()+"-"+month+"-"+today.getDate();
	if(dateTime==todayTime){
		var second = date.getSeconds();
		if(second<10){
			second = "0"+second;
		}
		var hour = date.getHours();
		if(hour<10){
			hour="0"+hour;
		}
		var minute = date.getMinutes();
		if(minute<10){
			minute = "0"+minute;
		}
    	return hour+":"+minute+":"+second;
	}else{
		return dateTime;
	}
    
}
