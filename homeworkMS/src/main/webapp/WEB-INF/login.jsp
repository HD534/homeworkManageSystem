<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="./jquery/jquery-3.3.1.js">
	
</script>
<script type="text/javascript" src="./jquery/jquery.form.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/TestSpringMvc/selectPolicy">aaa</a>
	<button onclick="checkUserName()">checkUserName</button>
	<button onclick="register()">register</button>
	<button onclick="login()">login</button>
	<form id="form1" action="/TestSpringMvc/login" method="post">

		<table>
			<tr>
				<td>userName</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	$(function() {
		/** 验证文件是否导入成功  */
		$("#form1").ajaxForm(function(data) {
			debugger;
			console.log(data)
			if(data==0){
				windows.location.href="index"
			}
			
		});
	});

	function checkUserName() {

		debugger;
		$.ajax({
			url : "/TestSpringMvc/checkUserName",
			type : 'POST',
			data : {
				username : 'username1'
			},
			//contentType : "application/json",/* 后台接收需要是一个model 不能是单个属性且须加上@RequestBody注解*/
			success : function(responseStr) {
				debugger
			}
		})
	}
	function register() {
		debugger;
		$.ajax({
			url : "/TestSpringMvc/register",
			type : 'POST',
			data : {
				username : "user111",
				password : "1111",
				age :"12"
			},
			success : function(responseStr) {
				debugger
			}

		})
	}
	function login() {
		debugger;
		$.ajax({
			url : "/TestSpringMvc/login",
			type : 'POST',
			data : {
				username : "user1",
				password : "pass1"
			},
			success : function(responseStr) {

				var policys = responseStr.policy;
				debugger
			}

		})

	}

</script>
</html>