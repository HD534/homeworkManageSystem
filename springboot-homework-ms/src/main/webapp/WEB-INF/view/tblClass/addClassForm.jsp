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

.classInfo .myForm, .layui-form-select {
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
						<div class="layui-row layui-bg-gray classInfo" id="mainPanle">

							<div class="layui-form-item" style="margin-top: 10px">
								<label class="layui-form-label">班级名称</label>
								<div class="layui-input-block">
									<input type="text" name="userName" autocomplete="off" required
										lay-verify="required" placeholder="请输入姓名"
										class="layui-input myForm">
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

							<div class="layui-form-item" style="padding: 5px">
								<div class="layui-input-block demoTable">
									<button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
									<button type="button" class="layui-btn layui-btn-primary" data-type="cancle" lay-filter="cancle">取消</button>
								</div>
							</div>

							<input type="text" id="indexId" class="layui-input layui-hide"/>
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
				var paramMap = data.field
				//console.log(JSON.stringify(paramMap))

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
							parent.layer.close(indexId);
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
				

				var active = {

						cancle : function() {
							
							var indexId = $("#indexId").val();

							parent.layer.close(indexId);
						}
				};
				
				$('.demoTable .layui-btn').on('click', function() {
					debugger;
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
			}

		});
	</script>

</body>

</html>