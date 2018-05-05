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

.homeworkInfo .myForm, .layui-form-select {
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
					<form class="layui-form " action="uploadHomework"
						id="uploadHomeworkInfoForm" style="margin-top: 20px; padding: 5px"
						enctype="multipart/form-data">

						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">学生上传作业</blockquote>

							<div class="homeworkInfo" id="item1">
								<!-- <div class="layui-form-item">
									<label class="layui-form-label">学期</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div> -->

								<!-- <div class="layui-form-item">
									<label class="layui-form-label">学院</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div>
								
								<div class="layui-form-item">
									<label class="layui-form-label">教师</label>
									<div class="layui-input-block">
										<select name="termValue" id="termSelect"
											lay-filter="termSelect" lay-verify="required" class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div> -->

								<!-- <div class="layui-form-item">
									<label class="layui-form-label">课程名称</label>
									<div class="layui-input-block">
										<select name="courseId" id="courseSelect"
											lay-filter="courseSelect" lay-verify="required"
											class="myForm">
											<option value=""></option>
										</select>
									</div>
								</div> -->
								<div class="layui-form-item" style="margin-top: 10px">
									<label class="layui-form-label">作业名称</label>
									<div class="layui-input-block">
										<input type="text" name="homeworkName" autocomplete="off"
											required lay-verify="required" placeholder="请填写作业名称"
											class="layui-input myForm" />
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">作业描述</label>
									<div class="layui-input-block">
										<textarea name="homeworkDesc" placeholder="请填写作业描述" style="min-height:50px" class="layui-textarea myForm"></textarea>
											<!-- autocomplete="new-password" -->
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 15px">
									<label class="layui-form-label">作业文件</label>
									<div class="layui-input-block">
										<button type="button" class="layui-btn layui-btn-normal"
											id="studentHomeworkFileInput" class="myForm">
											<i class="layui-icon">&#xe67c;</i>选择文件
										</button>
									</div>
								</div>

								<div class="layui-form-item"
									style="padding: 5px; margin-top: 25px">
									<div class="layui-input-block">
										<button class="layui-btn" id="submit" lay-submit=""
											lay-filter="formDemo">立即提交</button>
										<button type="button" class="layui-btn layui-btn-primary"
											data-type="cancle" lay-filter="cancle">取消</button>
									</div>
								</div>
								<input type="text" id="homeworkId"
									class="layui-input layui-hide" /> <input type="text"
									id="indexId" class="layui-input layui-hide" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		//Demo
		layui
				.use(
						[ 'form', 'layer', 'upload', 'laydate' ],
						function() {
							var form = layui.form, $ = layui.jquery, upload = layui.upload, layer = layui.layer;
							//getTermList();
							//监听提交

							//文件示例
							upload.render({
								elem : '#studentHomeworkFileInput',
								auto : false,
								accept : 'file' //普通文件
								//,multiple: true
								,
								bindAction : '#submit',
								done : function(res) {
									console.log(res)
								}
							});

							form.render();

							form.on('submit(formDemo)', function(data) {
								//console.log(JSON.stringify(data.field))
								debugger

								var paramMap = data.field
								if (paramMap.file.length == 0) {
									layer.msg("请选择上传文件!");
									return false;
								}

								var formData = new FormData(
										$("#uploadHomeworkInfoForm")[0])
								var indexId = $("#indexId").val();
								var homeworkId = $("#homeworkId").val();
								formData.append("homeworkId", homeworkId);
								//console.log(JSON.stringify(paramMap))
								//console.log("indexId = " + indexId);
								var loadingIndex;
								layer.open({
									type : 3,
									shade : 0.3,
									success : function(layero, index) {
										loadingIndex = index;
									}
								});

									$.ajax({
										data : formData,
										url : data.form.action,
										type : 'POST',
										dataType: 'json',
										cache: false,
										async: false,
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
												parent.layer.msg("上传成功")
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