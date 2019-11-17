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
					<form class="layui-form " action="assignClassCourse" id="courseInfoForm"
						style="margin-top: 20px; padding: 5px">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">为班级添加课程</blockquote>

							<div class="courseInfo" id="item1">
								<div class="layui-form-item">
									<label class="layui-form-label">课程名称</label>
									<div class="layui-input-block">
										<input type="text" name="courseName" required
											lay-verify="required" placeholder="请输入课程名称"
											autocomplete="off" class="layui-input myForm">
										<input type="text" name="courseId" class="layui-input myForm layui-hide">
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
								<div class="layui-form-item">
									<label class="layui-form-label">班级</label>
									<div class="layui-input-block">
										<select name="classId" id="classSelect"
											lay-filter="classSelect" lay-verify="required" class="myForm">
										</select>
									</div>
								</div>
							</div>

							<div class="layui-form-item demoTable" style="padding: 5px">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
									<button type="button" class="layui-btn layui-btn-primary" data-type="cancle" lay-filter="cancle">取消</button>
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
				var paramMap = data.field
				//console.log(JSON.stringify(paramMap))
				var indexId = $("#indexId").val();
				console.log("indexId = " + indexId);
				
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
						} else {
							layer.msg("添加失败,已存在")
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
						//console.log(responseData);
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
				debugger;
			}); 
			
			function listClassByInstitute(instituteId){
				
				var paramMap = {"instituteId":instituteId}
				
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

		});
	</script>

</body>

</html>