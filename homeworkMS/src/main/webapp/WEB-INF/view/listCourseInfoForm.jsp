<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<legend>课程信息详情</legend>

			<div class="layui-form-item">
				<label class="layui-form-label">课程名：</label>
				<div class="layui-input-block ">
					<input class="layui-input " name="id" id="demoReload"
						autocomplete="off" style="width: 30%">
				</div>
				<div class="layui-input-block demoTable" style="margin-top: 10px">
					<button class="layui-btn" data-type="reload">查询</button>
				</div>
			</div>
			<c:if test="${userType eq 1||userType eq 0}">
				<div class="layui-input-block demoTable">
					<button class="layui-btn" data-type="addNewCourse">新增课程</button>
				</div>
			</c:if>
		</fieldset>


		<table class="layui-hide " id="courseInfoTable"
			lay-filter="courseInfoTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="assignClass">分配课程</a>
				<a class="layui-btn  layui-btn-xs" lay-event="edit">编辑课程</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除课程</a>
			</script>
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
		layui.use('table', function() {
			var table = layui.table;

			var tableIns = table.render({
				id : 'courseInfoTable',
				elem : '#courseInfoTable',
				url : 'listCourse',
				cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
				//,height:  'full-20'
				,
				page : true,
				cols : [ [ {
					field : 'courseName',
					width : '12%',
					title : '课程名称'
				},{
					field : 'courseId',minWidth: '0', width: '0', type: 'space',
					style: 'display: none'
				}, {
					field : 'courseDesc',
					width : '15%',
					title : '课程简介'
				}, {
					field : 'instituteName',
					width : '12%',
					title : '学院'
				}, {
					field : 'userName',
					width : '8%',
					title : '教师'
				}, {
					field : 'termName',
					width : '8%',
					title : '学期'
				}, {
					field : 'createDate',
					width : '10%',
					title : '创建日期',
					type : 'date'
				},{
					field : 'className',
					width : '15%',
					title : '课程班级',
					type : 'date'
				}, {
					title : '操作',
					fixed : 'right',
					width : '20%',
					align : 'center',
					toolbar : '#barDemo'
				} ] ]
			});

			//监听工具条
			table.on('tool(courseInfoTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data //获得当前行数据
				, layEvent = obj.event; //获得 lay-event 对应的值
				console.log("当前记录："+data);
				if (layEvent === 'assignClass') {
					debugger
					layer.msg('assignClass');
					layer.open({
						type : 2,
						area : [ '500px', '470px' ],
						content : 'assignCourseForm',
						success : function(layero, index) {
							var body = layer.getChildFrame('body',
									index);
							var iframeWin = window[layero
									.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
							// console.log(body.html()) //得到iframe页的body内容
							body.find('input[name=courseName]').val(data.courseName)
							body.find('input[name=courseId]').val(data.courseId)
							
							debugger;
							body.find('#indexId').val(index)
						},
						end : function() {
							layer.msg("刷新表格")
							//执行重载
							table.reload('courseInfoTable', {
								page : {
									curr : 1
								//重新从第 1 页开始
								}
							});
						}
					});
				} else if (layEvent === 'del') {
					layer.confirm('真的删除行么', function(index) {
						layer.msg('删除操作');
					//	obj.del(); //删除对应行（tr）的DOM结构
					//	layer.close(index);
						//向服务端发送删除指令
					});
				} else if (layEvent === 'edit') {
					layer.msg('编辑操作');
				}
			});

			var $ = layui.$, active = {

				reload : function() {
					var demoReload = $('#demoReload');
					debugger;
					console.log(demoReload.val());

					//执行重载
					table.reload('courseInfoTable', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						where : {
							courseName : demoReload.val()
						}
					});
				},

				addNewCourse : function() {
					var currBoxHeight = $('.layui-body').height(); //获取当前容器的高度
					var tabHeight = $('.layui-tab-title').height(); //获取高度
					var iframeHeight = currBoxHeight - tabHeight;
					layer
							.open({
								type : 2,
								area : [ '500px', '470px' ],
								content : 'addCourseForm',
								success : function(layero, index) {
									var body = layer.getChildFrame('body',
											index);
									var iframeWin = window[layero
											.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
									// console.log(body.html()) //得到iframe页的body内容
									//body.find('input').val('Hi，我是从父页来的')
									debugger;
									body.find('#indexId').val(index)
								},
								end : function() {
									layer.msg("刷新表格")
									//执行重载
									table.reload('courseInfoTable', {
										page : {
											curr : 1
										//重新从第 1 页开始
										}
									});
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