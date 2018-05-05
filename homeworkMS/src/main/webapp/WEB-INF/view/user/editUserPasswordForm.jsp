<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<script src="layui/layui.js"></script>
<script src="layui/layui.all.js"></script>
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

.userInfo .myForm, .layui-form-select {
	width: 80%
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Form div</title>
</head>
<body class="layui-container">
	<div class="layui-row ">
		<div class="layui-col-md12 ">
			<div class="layui-row ">
				<div class="layui-col-md10 layui-col-md-offset1">
					<form class="layui-form " action="editUserPassword"
						id="userInfoForm" style="margin-top: 20px; padding: 5px">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">修改密码</blockquote>

							<div class="userInfo" id="item1">
								<div class="layui-form-item">
									<label class="layui-form-label">旧密码</label>
									<div class="layui-input-block">
										<input type="text" name="userOldPassword" autocomplete="off"
											required id="userTypeName" lay-verify="required"
											placeholder="请输入旧密码" class="layui-input myForm"
											onfocus="this.type='password'">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">新密码</label>
									<div class="layui-input-block">
										<input type="text" name="userNewPassword" autocomplete="off"
											required id="userNewPassword" lay-verify="required"
											placeholder="请输入新密码" class="layui-input myForm"
											onfocus="this.type='password'">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">确认密码</label>
									<div class="layui-input-block">
										<input type="text" name="userNewPassword1" autocomplete="off"
											required id="userNewPassword1" placeholder="请再次输入新密码"
											lay-verify="required" autocomplete="off"
											class="layui-input myForm" onfocus="this.type='password'">

										<!-- autocomplete="new-password" -->
									</div>
								</div>


								<div class="layui-form-item" style="padding: 5px">
									<div class="layui-input-block">
										<button lay-submit class="layui-btn" lay-filter="formDemo">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>

								<input type="text" id="indexId" class="layui-input layui-hide">

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		layui.use([ 'form', 'layer' ], function() {

			var form = layui.form, $ = layui.jquery, layer = layui.layer;
			//getInstituteList();
			form.render("");
			//监听提交

			form.on('submit(formDemo)', function(data) {
				//console.log(JSON.stringify(data.field))
				debugger
				//var user = data.field
				var paramMap = data.field

				//console.log(JSON.stringify(paramMap))
				//console.log("indexId = " + indexId);
				layer.open({
					title : '修改密码提示',
					content : '修改成功后会退出登陆。确认修改密码？',
					btn : [ '确认修改', '取消' ],
					yes : function(index, layero) {
						$.ajax({
							data : JSON.stringify(paramMap),
							url : data.form.action,
							contentType : "application/json",
							type : 'POST',
							dataType : "json",
							success : function(data) {
								if (data.code == 0) {
									//layer.close(indexId)
									//layer.closeAll();
									layer.close(index)
									layer.msg("修改成功，请重新登陆！");
									setTimeout(function() {
										parent.window.location = href = "mianLogin";
									}, 1000);

								} else if (data.code == 2) {
									layer.msg("旧密码错误，请重新填写")
								} else {
									layer.msg("修改失败，请联系管理员")
								}

							}

						});

					},
					btn2 : function(index, layero) {
						layer.close(index)

					},
					cancel : function() {
						//右上角关闭回调
						//return false 开启该代码可禁止点击该按钮关闭
					}
				});

				return false;
			});
			//确认密码的验证密码
			$('#userNewPassword1').bind({
				blur : function() {
					if (this.value != $('#userNewPassword').val()) {
						$("#userNewPassword1").val("");
						layer.tips('输入密码不同，请重新确认', '#userNewPassword1', {
							tips : [ 1, '#FF5722' ]
						})
					}
				}
			});

		});
	</script>

</body>

</html>