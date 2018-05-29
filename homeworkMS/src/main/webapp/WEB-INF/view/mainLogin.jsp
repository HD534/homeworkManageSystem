<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<css> </css>
<script src="layui/layui.js"></script>
<script src="jquery/jquery-3.3.1.js"></script>

<style type="text/css">
.login-bg {
	background: #FFFFFFF 0 0 no-repeat;
	/*background: url(image/bg.png) no-repeat center;*/
	width: 100%;
	height: auto;
	background-size: 100%;
	/*overflow: hidden;*/
}
</style>
<title>main Login</title>
</head>
<body class="login-bg layui-container">
	<div class="layui-row ">
		<div class="layui-col-md12 ">
			<div class="layui-row ">
				<div class="layui-col-md4 layui-col-md-offset4"
					style="margin-top: 50px">
					<div class="login_logo" style="text-align: center;margin-bottom:10px">
						<img src="image/GDUT_logo_school1.png" >
					</div>
					<form class="layui-form" action="login" method="POST">
						<div class="layui-row layui-bg-gray">
							<blockquote class="layui-elem-quote">作业管理系统</blockquote>

							<div class="layui-form-item"
								style="padding: 5px; padding-top: 20px">
								<label class="layui-form-label">学号/工号：</label>
								<div class="layui-input-block">
									<input type="text" name="userCode" required
										lay-verify="required" placeholder="请输入学号/工号"
										autocomplete="off" class="layui-input" style="width: 80%">
								</div>
							</div>

							<div class="layui-form-item" style="padding: 5px;">
								<label class="layui-form-label">密码：</label>
								<div class="layui-input-block">
									<input type="password" name="password" required
										lay-verify="required" placeholder="请输入密码" autocomplete="off"
										class="layui-input" style="width: 80%" />

								</div>
							</div>

							<div class="layui-form-item"  style="padding: 5px;">
								<label class="layui-form-label">账户类型：</label>
								<div class="layui-input-block">
									<input type="radio" name="userType" value="2" title="学生"
										checked> 
										<input type="radio" name="userType" value="1" title="教师">
										<input type="radio" name="userType" value="0" title="管理员">
								</div>
							</div>

							<div class="layui-form-item" style="padding: 10px;" >
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit lay-filter="formDemo"
										style="margin-right: 20px">登陆</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									<!-- <a href="registerForm" class="layui-btn layui-btn-normal">注册</a> -->
								</div>
							</div>
							<blockquote class="layui-elem-quote " align="center">
								<i class="layui-icon" style="font-size: 15px; color: #1E9FFF;">
									&#xe60b;&nbsp; 没有账号以及忘记密码等账户管理问题请联系管理教师 </i>
							</blockquote>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		//防止嵌套
		window.onload = function(){
			
			if(window.top!=window) window.top.location="mainLogin";
		}
		
		layui.use('form', function() {
			var form = layui.form;

			//监听提交
			form.on('submit(formDemo)', function(data) {
				debugger
				console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
				console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
				console.log(data.form.action) //被执行提交的form对象，一般在存在form标签时才会返回
				console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				var user = data.field
				
				$.ajax({
					data : JSON.stringify(user),
					url :  data.form.action,
					contentType : "application/json; charset=utf-8",
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						debugger
						if (data == 0) {
							layer.msg("登陆成功");
							window.location.href = "DashBoard"
						} else if (data == -1) {
							layer.msg("密码不正确，请重新输入");
							return false;
						} else {
							layer.msg("账户不存在，请确认账户类型或重新输入账户");
							return false;
						}
					}
				});
				return false;
			});
		});
	</script>

</body>
</html>