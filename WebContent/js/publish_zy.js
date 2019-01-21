$(document).ready(function(){

	var append_parent = $("#publish_zy");
	var num = 1;

	/*获取到作业类型*/
	$("#add").click(function(){
		var type = $("select").find("option:selected").val();
		if (type == "single_option") {
			add_single_option(append_parent);
		}else if (type == "multi_option") {
			add_multi_option(append_parent);
		}else if (type == "judge") {
			add_judge(append_parent);
		}else if (type == "short_answer") {
			add_short_answer(append_parent);
		}else{
			alert("error!");
		}
	});

	/*获取到作业信息、内容*/
	$("publish").click(function(){
		var data_post = new Array();

		/*获取作业内容*/
		for(var i = 0;i < $(".container").length;i++){
			for(var j = 0;j < $(".container").length;j++){
				data_post.push($(".container").eq(i).find("input").eq(j).val());
			}
			if($(".container").eq(i).attr("id") == "single_option"){
				data_post.push("single_option");
				data_post.push(";");
			}else if($(".container").eq(i).attr("id") == "multi_option"){
				data_post.push("multi_option");
				data_post.push(";");
			}else if($(".container").eq(i).attr("id") == "judge"){
				data_post.push("judge");
				data_post.push(";");
			}else if($(".container").eq(i).attr("id") == "short_answer"){
				data_post.push("short_answer");
				data_post.push(";");
			}else{
				alert("error!")
			}
		}

		/*获取老师、课程信息*/
		var teacher = $(".teacher span").html();
		var classname = $(".classname span").html();
		var hw_name = $("#hw_name").val();
		console.log(hw_name + data_post);

		/*发送数据*/
		$.post('lalal',{hw_name:hw_name,data_post:data_post,classname:classname,teacher:teacher},function(json){
			console.log("作业发布成功");
			alert("作业发布成功");
			window.location.href="https://www.baidu.com/";
		});
		/*发送数据结束*/

	});
	/*获取到作业信息、内容结束*/


	/*单项选择题与多项选择题能不能合并？？？？？*/
	
	/*生成单项选择题HTML*/
	function add_multi_option(element){
		var html_content = "<div id ='xz' class='xz container'><span class='num'>题号:</span><input type='text'><br/>A:<input type='text'><br/>B:<input type='text'><br/>C:<input type='text'><br/>D:<input type='text'></div>";
		element.append(html_content);
	}

	/*生成多项选择题HTML*/
	function add_multi_option(element){
		var html_content = "";
		element.append(html_content);
	}

	/*生成判断题HTML*/
	function add_multi_option(element){
		var html_content = "";
		element.append(html_content);
	}

	/*生成简答题HTML*/
	function add_multi_option(element){
		var html_content = "";
		element.append(html_content);
	}
});