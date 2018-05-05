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
					<form class="layui-form " action="" id="userInfoForm"
						style="margin-top: 20px; padding: 5px">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">个人信息</blockquote>

							<div class="courseInfo" id="item1">
								<c:if test="${userType eq 2||userType eq 1}">
								<div class="layui-form-item">
									<label class="layui-form-label">学院</label>
									<div class="layui-input-block">
										<input type="text" name="instituteName" required
											lay-verify="required" 
											autocomplete="off" class="layui-input myForm " disabled>
									</div>
								</div>
								</c:if>
								<c:if test="${userType eq 2}">
								<div class="layui-form-item">
									<label class="layui-form-label">班级</label>
									<div class="layui-input-block">
										<input type="text" name="className" required
											lay-verify="required" 
											autocomplete="off" class="layui-input myForm" disabled>
									</div>
								</div>
								</c:if>
								<div class="layui-form-item">
									<label class="layui-form-label">学号<c:if test="${userType eq 0||userType eq 1}">/工号</c:if></label>
									<div class="layui-input-block">
										<input type="text" name="userCode" required
											lay-verify="required"
											autocomplete="off" class="layui-input myForm" disabled>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">姓名</label>
									<div class="layui-input-block">
										<input type="text" name="userName" required
											lay-verify="required" 
											autocomplete="off" class="layui-input myForm" disabled>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">性别</label>
								
									<div class="layui-input-block " >
										<input type="radio" name="sex" value="男" title="男"  > 
										<input type="radio" name="sex" value="女" title="女"  >
									</div>
									
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">年龄</label>
									<div class="layui-input-block">
										<input type="text" name="age" required
											lay-verify="required" placeholder="请填写年龄"
											autocomplete="off" class="layui-input myForm">
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">Email</label>
									<div class="layui-input-block">
										<input type="text" name="courseName" required
											lay-verify="required||email" placeholder="请填写Email"
											autocomplete="off" class="layui-input myForm">
									</div>
								</div>
							</div>

							<div class="layui-form-item" style="padding: 5px">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit="" lay-filter="formDemo">修改</button>
									<!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
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
			var form = layui.form, $ = layui.jquery, 
			layer = layui.layer;
			getUserInfo();
			//form.render();
			//监听提交
			form.on('submit(formDemo)', function(data) {
				//layer.msg(JSON.stringify(data.field));
				//console.log(JSON.stringify(data.field))
				var paramMap = data.field
				//console.log(JSON.stringify(paramMap))
				var indexId = $("#indexId").val();
				//console.log("indexId = " + indexId);
 
				$.ajax({
					data : JSON.stringify(paramMap),
					url : data.form.action,
					contentType : "application/json",
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.code == 0) {
							//layer.close(indexId)
							//layer.closeAll();
							parent.layer.close(indexId);
							console.log(data)
							parent.layer.msg("添加成功")
						} else if (data.code == 2) {
							layer.msg("已存在课程")
						} else {
							layer.msg("添加失败")
						}

						debugger

					}

				});  
				return false;
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
			
			function getUserInfo() {
				$.ajax({
					url : "getUserInfo",
					type : 'get',
					contentType : "application/json; charset=utf-8",
					dataType : 'json',
					success : function(responseData) {
						debugger;
						console.log(responseData);
						var data = responseData.data;
						
					 	$('#userInfoForm').find('input[name=instituteName]').val(data.instituteName);
					 	$('#userInfoForm').find('input[name=className]').val(data.className);
					 	$('#userInfoForm').find('input[name=userCode]').val(data.userCode);
					 	$('#userInfoForm').find('input[name=userName]').val(data.userName);
					 	$('#userInfoForm').find('input[name=sex][value="'+data.sex+'"]').attr("checked",true); 
					 	$('#userInfoForm').find('input[name=age]').val(data.age);
					 	$('#userInfoForm').find('input[name=email]').val(data.email);
					 	form.render();
						/*console.log($form.find('select[name=instituteName]')) */
					}

				})
			}

			

		});
	</script>

</body>

</html>