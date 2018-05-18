<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
</head>
<body>
	<div>
		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">学生姓名</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="userName" id="userName"
						autocomplete="off">
				</div>

				<label class="layui-form-label">学号</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="userCode" id="userCode"
						autocomplete="off">
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学院</label>
				<div class="layui-input-inline">
					<select name="instituteSelect" id="instituteSelect"
						id="instituteSelect" lay-filter="instituteSelect">
						<option value=""></option>
					</select>
				</div>


				<label class="layui-form-label">班级</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="className" id="className"
						autocomplete="off">
				</div>

			</div>
			<div class="buttonClick">
				<div class="layui-input-inline demoTable"
					style="padding: 10px; margin-left: 20px; margin-right: 20px">
					<button type="button" class="layui-btn layui-icon"
						data-type="addNewUser" style="margin-left: 20px; margin-right: 20px" >&#xe61f; 新增人员</button>
					<button type="button" class="layui-btn layui-icon"
						data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
				</div>
			</div>
		</form>
		

		<table class="layui-hide " id="studentInfoTable" lay-filter="studentInfoTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			</script>
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
	layui.use([ 'table', 'form', 'layer' ], function() {
							var table = layui.table;
							var form = layui.form;
							var layer = layui.layer;
							var $ = layui.jquery;
							
							var tableIns = table.render({
								id : 'studentInfoTable',
								elem : '#studentInfoTable',
								url : 'listStudnets',
								cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
								//,height:  'full-20'
								,
								page : true,
								cols : [ [
								{field : 'userCode',width : '10%',title : '学号'}, 
								{field : 'userName',width : '20%',title : '姓名'}, 
								{field : 'age',width : '10%',title : '年龄'}, 
								{field : 'email',width : '10%',title : '邮箱'}, 
								{field : 'instituteName',title : '学院',width : '20%'} //minWidth : 100 //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
								, {
									field : 'className',
									title : '班级',
									width : '10%'
								}, {
									fixed : 'right',
									width : 165,
									align : 'center',
									toolbar : '#barDemo'
								}  ] ]
							});
							
							getInstituteList();
							form.render();

							//监听工具条
							table.on('tool(studentInfoTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
								var data = obj.data //获得当前行数据
								, layEvent = obj.event; //获得 lay-event 对应的值
								console.log(data)
								if (layEvent === 'download') {
									debugger
									window.open("util/download?fileId="+ data.fileId)
									layer.msg('查看操作');
								} else if (layEvent === 'del') {
									layer.confirm('真的删除行么', function(index) {
										obj.del(); //删除对应行（tr）的DOM结构
										layer.close(index);
										//向服务端发送删除指令
									});
								} else if (layEvent === 'edit') {
									layer.msg('编辑操作');
								}
							});

							var $ = layui.$, active = {
								
								reload: function(){
									tableReload();
								}
								
							};

							$('.demoTable .layui-btn').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
							
							function tableReload(courseId) {
								var userName_val = $('#userName').val();
								userName_val = userName_val == null ? null
										: userName_val == '' ? null : userName_val;
								
								var userCode_val = $('#userCode').val();
								userCode_val = userCode_val == null ? null
										: userCode_val == '' ? null
												: userCode_val;
								
								var instituteId_val = $('#instituteSelect').val();
								instituteId_val = instituteId_val == null ? null
										: instituteId_val == '' ? null : instituteId_val;
								
								var className_val = $('#className').val();
								className_val = className_val == null ? null
										: className_val == '' ? null : className_val;

								debugger;
								console.log(courseId)
								//执行重载
								table.reload('studentInfoTable', {
									page : {
										curr : 1
									//重新从第 1 页开始
									},
									method : 'POST',
									where : {
										userName : userName_val,
										userCode : userCode_val,
										instituteId : instituteId_val,
										className : className_val
									}
								});
							}
							
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
										form.render('select');
									}

								})
							}

						});
	</script>

</body>
</html>