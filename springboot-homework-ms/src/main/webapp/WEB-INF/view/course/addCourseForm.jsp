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
					<form class="layui-form " action="addCourse" id="courseInfoForm">
						<div class=" layui-bg-gray" id="mainPanle">

							<div class="courseInfo" id="item1" >
								<div class="layui-form-item" style="padding-top: 10px">
									<label class="layui-form-label">课程名称</label>
									<div class="layui-input-block">
										<input type="text" name="courseName" required
											lay-verify="required" placeholder="请输入课程名称"
											autocomplete="off" class="layui-input myForm">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">课程简介</label>
									<div class="layui-input-block">
										<input type="text" name="courseDesc" placeholder="请输入课程简介"
											autocomplete="off" class="layui-input myForm">
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
									<label class="layui-form-label">学期</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">

										</select>
									</div>
								</div>
								<c:if test="${userType eq 0}">
									<div class="layui-form-item">
										<label class="layui-form-label">任课教师</label>
										<div class="layui-input-block">
											<select name="teacherId" id="teacherSelect"
												lay-filter="teacherSelect" lay-verify="required" class="myForm">

											</select>
										</div>
									</div>
								</c:if>
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
			getTermList();
			getInstituteList();
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
			
			function getTermList() {
				$.ajax({
					url : "getTermList",
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
						$("#termSelect").append(html);
					/* 	$form.find('select[name=instituteName]').append(html);
						console.log($form.find('select[name=instituteName]')) */
						form.render('select');
					}

				})
			}

			form.on('select(instituteSelect)', function(data) {
				
				var teacherSelect = $("#teacherSelect");
				if(teacherSelect.length>0){
					listTeacher(data.value)
				}
				
				
			}); 
			
			function listTeacher(instituteId){
				
				var paramMap = {"instituteId":instituteId}
				
				$.ajax({
					url : "listTeacherByInstituteId",
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
						$("#teacherSelect").empty();
						$("#teacherSelect").append(html);
						form.render('select');
					}

				}) 
			}

		});
	</script>

</body>

</html>