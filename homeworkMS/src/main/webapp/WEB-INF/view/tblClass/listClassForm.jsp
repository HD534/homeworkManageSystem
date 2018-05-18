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
				<label class="layui-form-label">班级名称</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="className" id="className"
						autocomplete="off">
				</div>
				<label class="layui-form-label">学院</label>
				<div class="layui-input-inline">
					<select name="instituteId" id="instituteSelect"
						lay-filter="instituteSelect" lay-verify="required" class="myForm">
						<option value=""></option>
					</select>
				</div>
			</div>

			<div class="buttonClick">
				<div class="layui-input-inline demoTable"
					style="padding: 10px; margin-left: 20px; margin-right: 20px">
					<button type="button" class="layui-btn layui-icon"
						data-type="addClass"
						style="margin-left: 20px; margin-right: 20px">&#xe61f;
						新增班级</button>
					<button type="button" class="layui-btn layui-icon"
						data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
				</div>
			</div>
		</form>

		<table class="layui-hide " id="classTable" lay-filter="classTable">
			<!--<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="download">下载</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			 </script> -->
		</table>

	</div>
	<script src="layui/layui.js" charset="utf-8"></script>

	<script>
		layui.use([ 'table', 'form', 'layer' ], function() {
			var table = layui.table;
			var form = layui.form;
			var layer = layui.layer;
			var $ = layui.jquery;
			form.render();

			var tableIns = table.render({
				id : 'classTable',
				elem : '#classTable',
				url : 'listClass',
				cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
				//,height:  'full-20'
				,
				page : true,
				cols : [ [ {
					field : 'className',
					width : '20%',
					title : '班级'
				}, {
					field : 'instituteName',
					width : '20%',
					title : '学院'
				}, {
					field : 'stuCount',
					width : '20%',
					title : '学生人数'
				}, {
					field : 'createDate',
					width : '20%',
					title : '班级创建年月'
				}
				/* , {
					title : '操作',
					fixed : 'right',
					width : '20%',
					align : 'center',
					toolbar : '#barDemo'
				}  */
				] ]
			});

			getInstituteList();

			//监听工具条
			table.on('tool(classTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data //获得当前行数据
				, layEvent = obj.event; //获得 lay-event 对应的值
				console.log(data)
				if (layEvent === 'download') {
					debugger
					/* window.open("util/download?fileId="
							+ data.fileId) */
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
			
			function classTableReload(){
				var className_val = $('#className').val();
				className_val = className_val == null ? '' : className_val;
				var instituteId_val = $('#instituteSelect').val();
				instituteId_val = instituteId_val == null ? '': instituteId_val;

				debugger;
				//执行重载
				table.reload('classTable', {
					page : {
						curr : 1
					//重新从第 1 页开始
					},
					method : 'POST',
					where : {
						className : className_val,
						instituteId : instituteId_val
					}
				});
			}

			var $ = layui.$, active = {
				reload : function() {
					classTableReload();

				},
				addClass : function(){
					var openIndex;
					layer
					.open({
						type : 2,
						area : [ '500px', '400px' ],
						content : 'addClassForm',
						success : function(layero,
								index) {
							var body = layer
									.getChildFrame(
											'body',
											index);
							var iframeWin = window[layero
									.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
							// console.log(body.html()) //得到iframe页的body内容
							//body.find('input').val('Hi，我是从父页来的')
							debugger;
							openIndex = index;
							body.find('#indexId').val(
									index)
						},
						end : function() {
							layer.msg("刷新表格");
							layer.close(openIndex)
							classTableReload();
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