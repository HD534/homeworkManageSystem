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
	<form class="layui-form" action="">
			<div class="layui-form-item">
				<c:if test="${userType eq 1||userType eq 0}">
					<label class="layui-form-label">学院</label>
					<div class="layui-input-inline">
						<select name="instituteId" id="instituteSelect"
							lay-filter="instituteSelect"  class="myForm">
							<option value=""></option>
						</select>
					</div>
				
					<label class="layui-form-label">班级名称</label>
					<div class="layui-input-inline">
						<input class="layui-input" name="className" id="className"
							autocomplete="off">
					</div>
				</c:if>
				<label class="layui-form-label">课程名称</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="courseName" id="courseName"
						autocomplete="off">
				</div>
			</div>

			<div class="buttonClick">
				<div class="layui-input-inline demoTable"
					style="padding: 10px; margin-left: 20px; margin-right: 20px">
					<c:if test="${userType eq 1||userType eq 0}">
						<button type="button" class="layui-btn layui-icon"  data-type="addNewCourse">&#xe61f;新增课程</button>
					</c:if>
					<button type="button" class="layui-btn layui-icon"
						data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
				</div>
			</div>
		</form>


		<table class="layui-hide " id="courseInfoTable"
			lay-filter="courseInfoTable">
			<script type="text/html" id="barDemo">
				<a class="layui-btn  layui-btn-xs" lay-event="watchHomework">查看课程作业</a>
			<c:if test="${userType eq 1||userType eq 0}">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="assignClass">分配课程班级</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除课程</a>
			</c:if>
			</script>
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
	
		layui.use([ 'table', 'form', 'layer','element' ], function() {
			var table = layui.table;
			var form = layui.form;
			var layer = layui.layer;
			var $ = layui.jquery;
			var element = layui.element;
			getInstituteList();
			var tableIns = table.render({
				id : 'courseInfoTable',
				elem : '#courseInfoTable',
				url : 'listCourseByUserType',
				cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
				//,height:  'full-20'
				,
				page : true,
				cols : [ [ {
					field : 'courseName',
					width : '12%',
					title : '课程名称'
				}, {
					field : 'courseId',
					minWidth : '0',
					width : '0',
					type : 'space',
					style : 'display: none'
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
					title : '班级创建日期',
					type : 'date'
				}, {
					field : 'className',
					width : '10%',
					title : '课程班级',
					type : 'date'
				}, {
					title : '操作',
					fixed : 'right',
					width : '25%',
					align : 'center',
					toolbar : '#barDemo'
				} ] ]
			});
			
			form.render();

			//监听工具条
			table.on('tool(courseInfoTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data //获得当前行数据
				, layEvent = obj.event; //获得 lay-event 对应的值
				console.log("当前记录：" + data);
				if (layEvent === 'assignClass') {
					debugger
					layer.msg('assignClass');
					layer
							.open({
								type : 2,
								area : [ '500px', '470px' ],
								content : 'assignCourseForm',
								success : function(layero, index) {
									var body = layer.getChildFrame('body',
											index);
									var iframeWin = window[layero
											.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
									// console.log(body.html()) //得到iframe页的body内容
									body.find('input[name=courseName]').val(
											data.courseName)
									body.find('input[name=courseId]').val(
											data.courseId)

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
				} else if (layEvent === 'watchHomework') {
					
					var openIndex;

					layer
							.open({
								type : 2,
								title : data.courseName + '-作业详情',
								area : [ '1000px', '500px' ],
								content : 'listCourseHomeworkForm',
								success : function(layero, index) {
									openIndex = index;
									var body = layer.getChildFrame('body',
											index);
									var iframeWin = window[layero
											.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
									// console.log(body.html()) //得到iframe页的body内容
									//body.find('input').val('Hi，我是从父页来的')
									debugger;
									body.find('#indexId').val(index);
									body.find('#courseId').val(data.courseId);
									//iframeWin.setId(data.courseId);
								},
								end : function() {
									layer.msg("刷新表格");
									layer.close(openIndex);
									//执行重载
									 table.reload('courseInfoTable', {
										page : {
											curr : 1
										//重新从第 1 页开始
										}
									}); 
								}
							});
					/* layer.msg('查看课程作业');
					var title = data.courseName+'-作业详情'; // 导航栏text
					var src = 'listCourseHomeworkForm'; // 导航栏跳转URL
					console.log(data);
					debugger;
					
					var iframeId = parent.tabAdd(title,src);
					
					var iframe = $('#'+iframeId);
					
					var iframeWin = $(window.parent.document).contents().find("#"+iframeId)[0].contentWindow
					debugger;
					//iframeWin.onload=function(){
					   iframeWin.setId(data.courseId); */

					/* 	debugger;
						var body = layer.getChildFrame('body',iframeId);
						var courseId = data.courseId
						//body.find('#courseId').attr("value",data.courseId);
						//body.find('#search-btn').click();
						body.find('#courseId').val(data.courseId);
						//iframeWin.tableReload(courseId);
						var ifr = parent.document.getElementById(iframeId);
						var iiiicourseId = parent.document.getElementById(iframeId).contentWindow.document.getElementById('#courseId');
						debugger; */

					/* iframeWin.layui,table.reload('homeworkTable', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						method : 'POST',
						where : {
							courseId : courseId
						}
					}); */
					//}
				}
			});
			
			function courseTableReload(){
				var className_val = $('#className').val();
				className_val = className_val == null ? '' : className_val;
				var courseName_val = $('#courseName').val();
				courseName_val = courseName_val == null ? '' : courseName_val;
				var instituteId_val = $('#instituteSelect').val();
				instituteId_val = instituteId_val == null ? '': instituteId_val;

				debugger;
				//执行重载
				table.reload('courseInfoTable', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					method : 'POST',
					where : {
						className : className_val,
						instituteId : instituteId_val,
						courseName : courseName_val
					}
				});
			}
			
			
			var $ = layui.$, active = {

				reload : function() {
					courseTableReload();
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

		});
	</script>

</body>
</html>