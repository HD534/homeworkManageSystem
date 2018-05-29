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

 .myForm, .layui-form-select {
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
					<form class="layui-form " action="editClass" id="userInfoForm"
						style="margin-top: 20px; padding: 5px">
						<div class="layui-row layui-bg-gray" id="mainPanle">

							<blockquote class="layui-elem-quote">修改班级信息</blockquote>

							<div class="classInfo" id="item1">
								<div class="layui-form-item">
									<label class="layui-form-label">学院</label>
									<div class="layui-input-block">
										<input type="text" name="instituteName" autocomplete="off"
											required id="instituteName" lay-verify="required" disabled=""
											class="layui-input myForm">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">班级名称</label>
									<div class="layui-input-block">
										<input type="text" name="className" id="className" autocomplete="off" required
											lay-verify="required" placeholder="请输入班级名称"
											class="layui-input myForm">
									</div>
								</div>
							</div>

							<div class="layui-form-item" style="padding: 5px">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div>
							</div>

							<input type="text" id="indexId" class="layui-input layui-hide">
							<input type="text" id="classid" class="layui-input layui-hide">

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
				paramMap.classid = $("#classid").val()

				//console.log(JSON.stringify(paramMap))
				//console.log("indexId = " + indexId);

				/* $.ajax({
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
							parent.layer.msg("修改成功")
						}  else {
							layer.msg("修改失败")
						}

						debugger

					}

				}); */
				layer.msg("修改成功")
				return false;
			});

		});
	</script>

</body>

</html>