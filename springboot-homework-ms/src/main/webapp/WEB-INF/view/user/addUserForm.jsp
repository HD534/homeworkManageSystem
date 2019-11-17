<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

.courseInfo .myForm, .layui-form-select {
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
					<form class="layui-form " action="addUser" id="courseInfoForm"
						style="margin-top: 20px; padding: 5px">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">添加新人员</blockquote>

							<div class="courseInfo" id="item1">
								<div class="layui-form-item">
									<label class="layui-form-label">账户类型</label>
									<div class="layui-input-block">
										<select name="userType" id="userTypeSelect"
											lay-filter="userTypeSelect" lay-verify="required"
											class="myForm">
											<option value=""></option>
											<option value="0">管理员</option>
											<option value="1">教师</option>
											<option value="2">学生</option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">姓名</label>
									<div class="layui-input-block">
										<input type="text" name="userName" autocomplete="off"  required 
											lay-verify="required" placeholder="请输入姓名"   
											class="layui-input myForm">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">学号</label>
									<div class="layui-input-block">
										<input type="text" name="userCode" autocomplete="off" required
											placeholder="请输入学号" lay-verify="required||number"
											autocomplete="off"  class="layui-input myForm">
											
											<!-- autocomplete="new-password" -->
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">学院</label>
									<div class="layui-input-block">
										<select name="instituteId" id="instituteSelect"
											lay-filter="instituteSelect" lay-verify="required"
											class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								<div class="layui-form-item" id="tblclassDiv">
									<label class="layui-form-label">班级</label>
									<div class="layui-input-block">
										<select name="classId" id="classSelect"
											lay-filter="classSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">密码</label>
									<div class="layui-input-block">
										<input type=text name="password"   placeholder="请输入密码"
											lay-verify="required" id="password" autocomplete="off" 
											class="layui-input myForm" onfocus="this.type='password'">
											<!-- 
											 -->
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">确认密码</label>
									<div class="layui-input-block">
										<input type="text" name="password1" placeholder="请确认密码" 
											id="password1" lay-verify="required" autocomplete="off" 
											class="layui-input myForm"  onfocus="this.type='password'">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">性别</label>
									<div class="layui-input-block">
										<input type="radio" name="sex" value="男" title="男" checked>
										<input type="radio" name="sex" value="女" title="女">

									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">年龄</label>
										<div class="layui-input-block">
											<input type="text" name="age" placeholder="请输入年龄"
												lay-verify="number" autocomplete="off"
												class="layui-input myForm">
										</div>
									</div>

								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">邮箱</label>
									<div class="layui-input-block">
										<input type="text" name="email" placeholder="请输入邮箱"
											lay-verify="email" autocomplete="off" class="layui-input myForm">
									</div>
								</div>



								<div class="layui-form-item" style="padding: 5px">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>

								<input type="text" id="indexId" class="layui-input layui-hide">
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		//Demo
		layui.use([ 'form', 'layer' ], function() {
			var form = layui.form, $ = layui.jquery, layer = layui.layer;
			getInstituteList();
			//form.render();
			//监听提交
			form.on('submit(formDemo)', function(data) {
				//console.log(JSON.stringify(data.field))
				debugger
				//var user = data.field
				if ($("#tblclassDiv").hasClass("layui-hide")) {
					data.field.classId = null
				}
				var paramMap = data.field

				//console.log(JSON.stringify(paramMap))
				//console.log("indexId = " + indexId);
				

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
							parent.layer.close($('#indexId').val());
							console.log(data)
							parent.layer.msg("添加成功")
						} else if (data.code == 2) {
							layer.msg("已存在")
						} else {
							layer.msg("添加失败")
						}

						debugger

					}

				});
				return false;
			});

			form.on('select(userTypeSelect)', function(data) {
				//				layer.msg("账户类型：    "+data)
				debugger;
				if (data.value == "2") {
					$("#tblclassDiv").removeClass("layui-hide");
				} else {
					$("#tblclassDiv").addClass("layui-hide");
				}
				form.render();

			});

			function getInstituteList() {
			
				$.ajax({
					url : "getUserInstitute",
					type : 'GET',
					contentType : "application/json; charset=utf-8",
					dataType : 'json',
					success : function(responseData) {
						debugger;
						console.log(responseData);
						var html = '';
						var data = responseData.data;
						for (var i = 0; i < data.length; i++) {
							html += '<option value='+data[i].id+'>'
									+ data[i].name + '</option>';
						}
						$("#instituteSelect").append(html);
						/* 	$form.find('select[name=instituteName]').append(html);
							console.log($form.find('select[name=instituteName]')) */
						form.render('select');
					}

				})
			}

			form.on('select(instituteSelect)', function(data) {
				listClassByInstitute(data.value);
			});

			function listClassByInstitute(instituteId) {

				var paramMap = {
					"instituteId" : instituteId
				}

				$.ajax({
					url : "listClassByInstitute",
					type : 'POST',
					data : JSON.stringify(paramMap),
					contentType : "application/json; charset=utf-8",
					dataType : 'json',
					success : function(responseData) {
						debugger;
						console.log(responseData);
						var html = '';
						var data = responseData.data;
						for (var i = 0; i < data.length; i++) {
							html += '<option value='+data[i].id+'>'
									+ data[i].name + '</option>';
						}
						$("#classSelect").empty();
						$("#classSelect").append(html);
						form.render('select');
					}

				})
			}
			
			//确认密码的验证密码
			$('#password1').bind({
				blur : function() {
					if (this.value != $('#password').val()) {
						$("#password1").val("");
					 	layer.tips('输入密码不同，请重新确认', '#password1', {
							tips: [1, '#FF5722']
						})  
					}
				}
			});

		});
	</script>

</body>

</html>