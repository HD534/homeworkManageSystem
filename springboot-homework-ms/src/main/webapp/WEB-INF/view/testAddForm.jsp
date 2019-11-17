<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Form div</title>
</head>
<body class="layui-container">
	<div class="layui-row ">
		<div class="layui-col-md12 ">
			<div class="layui-row ">
				<div class="layui-col-md12 ">

					<div class="layui-row layui-bg-gray" id="mainPanle">

						<blockquote class="layui-elem-quote">作业管理系统</blockquote>
						<form class="layui-form " action=""
							style="margin-top: 20px; padding: 5px">

							<div class="AddItem" id="item1">
								<div class="layui-form-item">
									<label class="layui-form-label">题目1</label>
									<div class="layui-input-block">
										<textarea name="itemDesc1" placeholder="请输入内容"
											class="layui-textarea" style="width: 90%"></textarea>
									</div>
								</div>

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" type="button">
											<i class="layui-icon">&#xe608;</i> 添加图片说明
										</button>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">A</label>
									<div class="layui-input-block">
										<textarea name="A1" placeholder="请输入内容" class="layui-textarea"
											style="width: 90%"></textarea>
									</div>
								</div>

								<div class="layui-form-item">
									<label class="layui-form-label">B</label>
									<div class="layui-input-block">
										<textarea name="B1" placeholder="请输入内容" class="layui-textarea"
											style="width: 90%"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">C</label>
									<div class="layui-input-block">
										<textarea name="C1" placeholder="请输入内容" class="layui-textarea"
											style="width: 90%"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">D</label>
									<div class="layui-input-block">
										<textarea name="D1" placeholder="请输入内容" class="layui-textarea"
											style="width: 90%"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block">
										<div class="layui-btn-container">
											<input type="radio" lay-filter="chooseSingleMul" name="choose2" value="1" title="单选" checked> 
											<input type="radio" lay-filter="chooseSingleMul" name="choose2" value="2" title="多选">
										</div>
									</div>
								</div>
								<div class="layui-form-item " id="singleItem1">
									<label class="layui-form-label">正确选项</label>
									<div class="layui-input-block" >
										<input type="radio" name="choose1" value="A" title="A" >
										<input type="radio" name="choose1" value="B" title="B">
										<input type="radio" name="choose1" value="C" title="C">
										<input type="radio" name="choose1" value="D" title="D">
									</div>
								</div>
								<div class="layui-form-item layui-hide" id="mulItem1">
									<label class="layui-form-label">正确选项</label>
									<div class="layui-input-block" >
										<input type="checkbox" name="A" title="A" > <input
											type="checkbox" name="B" title="B"> <input
											type="checkbox" name="C" title="C"> <input
											type="checkbox" name="D" title="D">
									</div>
								</div>
							</div>

							<div class="layui-form-item" id="addBtnDiv">
								<div class="layui-input-block">
									<div class="layui-btn-container">
										<button class="layui-btn" lay-filter="addItem" id="add-btn"
											type="button">
											<i class="layui-icon">&#xe608;</i> 添加下一条记录
										</button>
									</div>
								</div>

							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">密码框</label>
								<div class="layui-input-inline">
									<input type="password" name="password" lay-verify="required"
										placeholder="请输入密码" autocomplete="off" class="layui-input">
								</div>
								<div class="layui-form-mid layui-word-aux">辅助文字</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">选择框</label>
								<div class="layui-input-block">
									<select name="city" lay-verify="required">
										<option value=""></option>
										<option value="0">北京</option>
										<option value="1">上海</option>
										<option value="2">广州</option>
										<option value="3">深圳</option>
										<option value="4">杭州</option>
									</select>
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">复选框</label>
								<div class="layui-input-block">
									<input type="checkbox" name="like[write]" title="写作"> <input
										type="checkbox" name="like[read]" title="阅读" checked>
									<input type="checkbox" name="like[dai]" title="发呆">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">开关</label>
								<div class="layui-input-block">
									<input type="checkbox" name="switch" lay-skin="switch">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">单选框</label>
								<div class="layui-input-block">
									<input type="radio" name="sex" value="男" title="男"> <input
										type="radio" name="sex" value="女" title="女" checked>
								</div>
							</div>

							<div class="layui-form-item layui-form-text">
								<label class="layui-form-label">文本域</label>
								<div class="layui-input-block">
									<textarea name="desc" placeholder="请输入内容"
										class="layui-textarea"></textarea>
								</div>
							</div>

							<div class="layui-form-item">
								<!-- 	<div class="layui-input-block">
									<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
									<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								</div> -->
							</div>
						</form>
						
					</div>

				</div>
			</div>
		</div>
	</div>

	<script>
		//Demo
		layui.use([ 'form', 'layer' ], function() {
			var form = layui.form, layer = layui.layer
			//监听提交
			form.on('submit(formDemo)', function(data) {
				layer.msg(JSON.stringify(data.field));
				return false;
			});

		});
	</script>

</body>
<script type="text/javascript">
	var item_count = 1;
	layui.use([ 'jquery', 'layer', 'form' ],function() {
		var form = layui.form, 
		$ = layui.$, //重点处
		layer = layui.layer		
		form.on('radio(chooseSingleMul)',function(data){
			debugger
			console.log(data.value)
			if(data.value=='1'){
				$("#singleItem1").removeClass('layui-hide');
				$("#mulItem1").addClass('layui-hide');
			}else{
				$("#mulItem1").removeClass('layui-hide');
				$("#singleItem1").addClass('layui-hide');
			}
		})
		
		$("#add-btn").on("click",
					function() {
						debugger
						item_count = item_count + 1
						var newDiv = document
								.createElement('div');
						var str = '<div class="AddItem" id="item'+item_count+'" lay-filter="item'+item_count+'"> '
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">题目'
								+ item_count
								+ '</label>'
								+ '<div class="layui-input-block">'
								+ '<textarea name="itemDesc'
								+ item_count
								+ '" placeholder="请输入内容"'
								+ 'class="layui-textarea" style="width: 90%"></textarea>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<div class="layui-input-block">'
								+ '<button class="layui-btn" type="button">'
								+ '<i class="layui-icon">&#xe608;</i> 添加图片说明'
								+ '</button>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">A</label>'
								+ '<div class="layui-input-block">'
								+ '<textarea name="desc" placeholder="请输入内容"'
								+ 'class="layui-textarea" style="width: 90%"></textarea>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">B</label>'
								+ '<div class="layui-input-block">'
								+ '<textarea name="desc" placeholder="请输入内容"'
								+ 'class="layui-textarea" style="width: 90%"></textarea>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">C</label>'
								+ '<div class="layui-input-block">'
								+ '<textarea name="desc" placeholder="请输入内容"'
								+ '	class="layui-textarea" style="width: 90%"></textarea>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">D</label>'
								+ '<div class="layui-input-block">'
								+ '<textarea name="desc" placeholder="请输入内容"'
								+ 'class="layui-textarea" style="width: 90%"></textarea>'
								+ '</div>'
								+ '</div>'
								+ '<div class="layui-form-item">'
								+ '<label class="layui-form-label">正确选项</label>'
								+ '<div class="layui-input-block">'
								+ '<input type="checkbox" name="A" title="A"> '
								+ '<input type="checkbox" name="B" title="B">'
								+ '<input type="checkbox" name="C" title="C"> '
								+ '<input type="checkbox" name="D" title="D">'
								+ "</div>" + "</div>" +

								'</div>'

						newDiv.innerHTML = str;

						$("#addBtnDiv").before(newDiv)
						form.render('select'); //刷新select选择框渲染
						form.render('checkbox');
						form.render('radio');
		})
	})

	$(function() {
		/** 验证文件是否导入成功  */
		/* $("#form1").ajaxForm(function(data) {
			debugger;
			console.log(data)
			if (data == 0) {
				windows.location.href = "index"
			}

		}); */
	});

	function checkUserName() {

		debugger;
		$.ajax({
			url : "/TestSpringMvc/checkUserName",
			type : 'POST',
			data : {
				username : 'username1'
			},
			//contentType : "application/json",/* 后台接收需要是一个model 不能是单个属性且须加上@RequestBody注解*/
			success : function(responseStr) {
				debugger
			}
		})
	}
	function register() {
		debugger;
		$.ajax({
			url : "/TestSpringMvc/register",
			type : 'POST',
			data : {
				username : "user111",
				password : "1111",
				age : "12"
			},
			success : function(responseStr) {
				debugger
			}

		})
	}
	function login() {
		debugger;
		$.ajax({
			url : "/TestSpringMvc/login",
			type : 'POST',
			data : {
				username : "user1",
				password : "pass1"
			},
			success : function(responseStr) {

				var policys = responseStr.policy;
				debugger
			}

		})

	}
</script>
</html>