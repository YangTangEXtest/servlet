<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.json.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  
<script type="text/javascript">
	function message() {
		var userName = document.getElementById("userName").value;
		var passWord = document.getElementById("passWord").value;
		$.ajax({
        //直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
        type : "get",      
        //servlet文件名为Calculator，需要提前在web.xml里面注册
        url : "test", 
        dataType : "text",  //数据类型，可以为json，xml等等，自己百度
        data :
        {
             "username" : userName,        //操作数 
             "password":passWord,     //操作符
        },
        success : function(Result)
        {
               //Result为后端post函数传递来的数据，这里写结果操作代码
        },
        error : function(xhr, status, errMsg)
        {
             alert("数据传输失败!");
        }
    })
	}
</script>
</head>
<body>
 
	<form action="test" method="post">
		<table align="center">
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="userName"></td>
				<td><div id="a" style="color: red"></div></td>
			</tr>
			<tr>
				<td>密    码:</td>
				<td><input type="text" id="passWord"></td>
				<td><div id="b" style="color: red"></div></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" value="submit" onclick="message()"></td>
			</tr>
		</table>
	</form>
 
 
</body>
</html>