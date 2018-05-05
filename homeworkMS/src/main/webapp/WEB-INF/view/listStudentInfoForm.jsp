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
		<fieldset class="layui-elem-field layui-field-title">
			<legend>学生信息详情</legend>
				<div class="layui-form-item">
					<label class="layui-form-label">学号：</label>
					<div class="layui-input-block">
						<input class="layui-input " name="id" id="demoReload"
						autocomplete="off" width="30%">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block demoTable">
						<button class="layui-btn" data-type="reload">查询</button>
					</div>
				</div>
		</fieldset>
		

		<table class="layui-hide " id="studentInfoTable" lay-filter="studentInfoTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="download">下载</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			</script>
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
		layui.use(
						'table',
						function() {
							var table = layui.table;

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
								      var demoReload = $('#demoReload');
								      debugger;
								      console.log(demoReload.val());
								      
								      //执行重载
								      table.reload('studentInfoTable', {
								        page: {
								          curr: 1 //重新从第 1 页开始
								        }
								        ,where: {
								            userCode: demoReload.val()
								        }
									});
								}
								
							};

							$('.demoTable .layui-btn').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});

						});
	</script>

</body>
</html>