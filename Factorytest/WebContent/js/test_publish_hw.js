//获取URL地址中的参数
function getRequest(){

	console.log("执行了getRequest()函数，获取URL中的参数返回");

	var url = window.location.search;
	var theRequest = new Object();
	if (url.indexOf("?") == 0) {
		var str = url.substr(1);//str为：clid=1&type=publish
		strs = str.split("&");//strs为：clid=1,type=publish
		for (var i = 0; i < strs.length; i++){
			theRequest[strs[i].split("=")[0]] = strs[i].split("=")[1];
		}
		return theRequest;
	}//end of if
}//end of getRequest()

//获取班级信息
function get_class_info(){

	console.log("执行了get_class_info(),通过postJSON()获得班级信息");

	var req = {clid:clid}
	var jsonStr = $.toJSON(req);
	postJSON("class.java",jsonStr,function showResponse(response) {
		class_info = response.body;
		if (type == "edit") {
			if (hid == undefined) {
				return;
			}
			$("#update_or_submit").text("更新作业");
			get_show_homework();
		} else {
			$("#update_or_submit").text("提交作业")；
			homework = {
				type: "O",
				name: "新建个人在线作业",
				begin_t: getNowFormatDate(),
				end_t: getNowFormatDate(),
				hard_ddl:getNowFormatDate(),
				score_face: 0,
				score_weight: 0,
				finish_number: 0
			};
			homework.homework = new Array();
			questions = homework.homework;
			showDescription(homework);
		}
	})//end of postJSON
}//end of get_class_info()

//通过JSON进行数据传输
function postJSON(url,jsonStr,successFunction,async=true,dataType="json",contentType="application/text"){
	
	console.log("执行了postJSON（），传过去的数据是"+jsonStr);

	$.ajax({
		url:url,
		type:'POST',
		async:async,
		data:jsonStr,
		processData:false,
		dataType:dataType,
		contentType:contentType,
		success:function(response,status,xhr) {
			var response;
			if (dataType != "json") {
				response = $.parseJSON(response);
			}
			if (status != "success") {
				alert("未知错误");
			} else if (response['code'] != 0) {
				alert("错误"+response['code']+":"+response['msg']);
			} else{
				successFunction(response);
			}
		},
		error : function(xhr,error,exception) {
			alert("postJSON失败"+exception.toString());
		}
	});//end of ajax
}//end of postJSON

//获取时间信息
function getNowFormDate() {

	console.log("执行了函数：getNowFormDate()");

	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth()+1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <=9) {
		strDate = "0" + strDate;
	}
	var strHour = date.getHours();
	var strMin = date.getMinutes();
	var strSec = date.getSeconds();
	if (strHour >= 0 && strHour <=9) {
		strHour = "0" + strHour;
	}
	if (strMin >= 0 && strMin <=9) {
		strMin = "0" + strMin;
	}
	if (strSec >= 0 && strSec <= 9) {
		strSec = "0" + strSec;
	}

	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate + " " +strHour + seperator2 + strMin + seperator2 + strSec;
	return currentdate;
}//end function getNowFormDate

//显示作业概览信息
function showDescription(hw){

	console.log("执行了函数：showDescription(hw)");

	var color;
	var typestr;
	var strnow = getNowFormDate();
	if (strnow < hw.begin_t) {
		color = "330099";
	} else if (strnow < hw.hard_ddl) {
		color = "ff0000";
	} else {
		color = "66666";
	}
	switch (hw.type) {
		case "O": 
			typestr = "个人在线作业";
			break;
		case "F":
			typestr = "个人离线作业"；
			break;
		case "G":
			typestr = "小组离线作业"；
			break;
	}
	$("#div_descripthon").empty();
	html = '<h2 class="hw_title" id="hwtitle">'+hw.name+'</h2>';
	html += '     <div class="pb_container">';
	html += '       <h4 id="hw_info" class="hw_title">&nbsp;&nbsp;作业信息：</h4>';
	html += '       <div id="hw_info_detail">';
	html += '         <div class="hwifdt_left">';
	html += '           <p>起始时间：'+hw.begin_t+'</p>';
	html += '           <p>截止时间：'+hw.end_t+'</p>';
	html += '           <p>终止时间：'+hw.hard_ddl+'</p>';
	html += '         </div>';
	html += '         <div class="hwifdt_right">';
	html += '           <p>作业类型：'+typestr+'</p>';
	html += '           <p>作业比重：'+hw.score_face+'</p>';
	html += '           <p>完成人数：'+hw.finish_number+'</b>/'+'class_info.student_num'+'</p>';
	html += '         </div>';
	html += '       </div>';
	html += '       <p id="edit_hwinfo" class="hw_title">编辑&nbsp;&nbsp;</p>';
	html += '     </div>';
	console.log("生成的html:"+html);
	$("#div_descripthon").append(html);
}//end of showDescription

//页面加载执行，获取班级ID，类型，作业类型
$(document).ready(function(){
	console.log("执行了document.ready()");
	var request = getRequest();
	clid = request.clid;
	type = request.type;
	hid = request.hid;
	if (clid == undefined || type == undefined){
		return;
	}
	get_class_info();
})