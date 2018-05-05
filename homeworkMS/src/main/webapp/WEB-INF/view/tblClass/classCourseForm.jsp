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
			<legend>列偏移</legend>
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">单行输入框</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="title"
							autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-filter="demo1">查询</button>

					</div>
				</div>
			</form>
		</fieldset>
		<div class="layui-btn-group demoTable">
			<button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
			<button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
			<button class="layui-btn" data-type="isAll">验证是否全选</button>
			<button type="button" class="layui-btn layui-btn-primary"
				data-type="downLoadAll">全部下载</button>
				
			搜索ID：
			<div class="layui-inline">
				<input class="layui-input" name="id" id="demoReload"
					autocomplete="off">
			</div>
			<button class="layui-btn" data-type="reload">搜索</button>
		</div>

		<table class="layui-hide " id="classCourse" lay-filter="classCourse">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="download">下载</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			</script>
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
		layui
				.use(
						'table',
						function() {
							var table = layui.table;

							var tableIns = table.render({
								id : 'classCourse',
								elem : '#classCourse',
								url : 'listClassCourse',
								cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
								//,height:  'full-20'
								,
								page : true,
								cols : [ [
								 {
									field : 'courseName',
									width : '10%',
									title : '课程名称'
								}, {
									field : 'courseDesc',
									width : '20%',
									title : '课程简介'
								}, {
									field : 'instituteName',
									title : '学院',
									width : '15%'
									
								}, {
									field : 'className',
									width : '15%',
									title : '班级'
								//minWidth : 100
								} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
								, {
									field : 'teacherName',
									title : '教师',
									width : '15%'
								}, {
									title : '操作',
									fixed : 'right',
									width : "25%",
									align : 'center',
									toolbar : '#barDemo'
								} ] ]
							});

							//监听工具条
							table.on('tool(classCourse)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
								var data = obj.data //获得当前行数据
								, layEvent = obj.event; //获得 lay-event 对应的值
								console.log(data)
								if (layEvent === 'download') {
									debugger
									window.open("util/download?fileId="
											+ data.fileId)
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
								getCheckData : function() { //获取选中数据
									var checkStatus = table.checkStatus('classCourse'), 
									data = checkStatus.data;
									layer.alert(JSON.stringify(data));
								},
								getCheckLength : function() { //获取选中数目
									var checkStatus = table.checkStatus('classCourse'), 
									data = checkStatus.data;
									layer.msg('选中了：' + data.length + ' 个');
								},
								isAll : function() { //验证是否全选
									var checkStatus = table.checkStatus('classCourse');
									layer.msg(checkStatus.isAll ? '全选' : '未全选')
								},
								downLoadAll : function() { //验证是否全选
									var checkStatus = table.checkStatus('classCourse'),
									data = checkStatus.data;
									if(data.length==0){
										layer.msg('请选择下载的文件')
									}else{
										var fileIds = []; 
										//value是遍历的数组内容；index是对应的数组索引，array是数组本身。
										$.each(data, function(index, value, array) {
											fileIds.push(value.fileId)
										});
										console.log(fileIds);
										var form=$("<form>");//定义一个form表单  
										form.attr("style","display:none");  
										form.attr("method","post");  
										form.attr("action",'attachFiles/downloadZip');  
										var input1=$("<input>");  
										input1.attr("type","hidden");  
										input1.attr("name","fileIds");  
										input1.attr("value",fileIds);  
										$("body").append(form);//将表单放置在web中  
										form.append(input1);  
										form.submit();//表单提交 
										/*  $.ajax({
											data : {'fileIds':fileIds}, 
											url : 'attachFiles/downloadZip',
											traditional:true,
											type : 'POST',
											success : function(data) {
												debugger
												console.log(data);
											}
										}); */ 
										layer.msg('下载')
									}
								},
								reload: function(){
								      var demoReload = $('#demoReload');
								      
								      //执行重载
								      table.reload('classCourse', {
								        page: {
								          curr: 1 //重新从第 1 页开始
								        }
								        ,where: {
								          key: {
								            id: demoReload.val()
								          }
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