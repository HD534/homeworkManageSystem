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
					<form class="layui-form " action="addHomework" id="homeworkInfoForm"
						style=" padding: 5px" enctype="multipart/form-data">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">作业批改</blockquote>

							<div class="courseInfo " id="item1">
								<div class="layui-form-item layui-hide">
									<label class="layui-form-label">学期</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								
								 <div class="layui-form-item layui-hide">
									<label class="layui-form-label">学院</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								
							<!-- 	<div class="layui-form-item">
									<label class="layui-form-label">教师</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div> -->
								
								<div class="layui-form-item layui-hide">
									<label class="layui-form-label">课程名称</label>
									<div class="layui-input-block">
										<select name="courseId" id="courseSelect"
											lay-filter="courseSelect" lay-verify="required"
											class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">作业名称</label>
									<div class="layui-input-block">
										<input type="text" name="homeworkName" autocomplete="off"  required 
											lay-verify="required" placeholder="请填写作业名称"   
											class="layui-input myForm">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">作业描述</label>
									<div class="layui-input-block">
										<textarea name="homeworkDesc" style="min-height:50px"
										placeholder="请填写作业描述"  class="layui-textarea myForm"></textarea>
											<!-- autocomplete="new-password" -->
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">作业文件</label>
									<div class="layui-input-block">
										<button type="button" class="layui-btn layui-btn-normal" 
										id="homeworkFileInput"  class="myForm"><i class="layui-icon">&#xe67c;</i>选择文件</button>
									</div>
								</div>
								<div class="layui-form-item" >
									<label class="layui-form-label">截至日期</label>
									<div class="layui-input-block">
										<input type="text" class="layui-input myForm" id="homeworkDueDate" name="homeworkDueDate"
										 placeholder="yyyy-MM-dd" >
									</div>
								</div>
								<div class="layui-form-item demoTable" style="padding: 5px">
									<div class="layui-input-block">
										<button class="layui-btn" id="submit" lay-submit="" lay-filter="formDemo">立即提交</button>
										<button type="button" class="layui-btn layui-btn-primary" data-type="cancle" lay-filter="cancle">取消</button>
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
		//Demo
		layui.use([ 'form', 'layer' ,'upload','laydate'], function() {
			var form = layui.form, $ = layui.jquery, upload = layui.upload,layer = layui.layer,laydate = layui.laydate;
			getTermList();
			//监听提交
			//常规用法
			  laydate.render({
			    elem: '#homeworkDueDate'
			  });
			
			//文件示例
			upload.render({
			    elem: '#homeworkFileInput'
			    ,auto: false
			    ,accept: 'file' //普通文件
			    //,multiple: true
			    ,bindAction: '#submit'
			    ,done: function(res){
			      console.log(res)
			    }
			 });
			form.render();
			form.on('submit(formDemo)', function(data) {
				//console.log(JSON.stringify(data.field))
				debugger
				
				var paramMap = data.field
				
				var formData = new FormData($("#homeworkInfoForm")[0])
				var indexId = $("#indexId").val();
				//console.log(JSON.stringify(paramMap))
				//console.log("indexId = " + indexId);
				var loadingIndex;
				layer.open({
				  type:3,shade: 0.3,content:'加载中',
					success : function(layero,
							index) {
						loadingIndex = index;
					}
				});  
				
				$.ajax({
					data : formData,
					url : data.form.action,
					type : 'POST',
					dataType: 'json',
					cache: false,
					async: true,
					contentType: false,
					processData: false,
					success : function(data) {
						if (data.code == 0) {
							//layer.close(indexId)
							//layer.closeAll();
							layer.close(loadingIndex);
							debugger;
							parent.layer.close(indexId);
							console.log(data)
							parent.layer.msg("添加成功")
						} else if (data.code == 2) {
							layer.close(loadingIndex);
							layer.msg("已存在")
						} else {
							layer.close(loadingIndex);
							layer.msg("添加失败")
						}

					}

				}); 
				return false;
			});
			
			
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
			
			
			
			form.on('select(termSelect)', function(data) {
				debugger;
				listCourseByTypeAndTerm(data.value);
			}); 
			
			function listCourseByTypeAndTerm(termValue){
				
				var paramMap = {
						"termValue" : termValue
				}
				debugger;
				 $.ajax({
					url : "listCourseByTypeAndTerm",
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
							debugger;
							html += '<option value='+data[i].courseId+'>'
									+ data[i].courseName + '</option>';
						}
						$("#courseSelect").append(html);
					
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