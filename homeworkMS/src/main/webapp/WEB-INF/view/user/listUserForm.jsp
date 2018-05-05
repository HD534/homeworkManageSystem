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
<script src="layui/layui.js" charset="utf-8"></script>
</head>
<body>
	<div>

		<form class="layui-form" action="">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="userName" id="userName"
						autocomplete="off">
				</div>

				<label class="layui-form-label">学号/工号</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="userCode" id="userCode"
						autocomplete="off">
				</div>

			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">用户类型</label>
				<div class="layui-input-inline">
					<select name="userType" id="userType" id="userTypeSelect"
						lay-filter="userTypeSelect">
						<option value=""></option>
						<option value="0">管理员</option>
						<option value="1">教师</option>
						<option value="2">学生</option>
					</select>
				</div>


				<label class="layui-form-label">Email</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="email" id="email"
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

		<table class="layui-hide " id="userTable" lay-filter="userTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑人员</a>
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除人员</a>
			</script>
		</table>

	</div>

	<script>
		layui
				.use(
						[ 'table', 'form', 'layer' ],
						function() {
							var table = layui.table;
							var form = layui.form;
							var layer = layui.layer;

							form.render();

							var tableIns = table.render({
								id : 'userTable',
								elem : '#userTable',
								url : 'listUser',
								cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
								//,height:  'full-20'
								,
								page : true,
								cols : [ [ {
									field : 'userName',
									width : '10%',
									title : '姓名'
								}, {
									field : 'userCode',
									width : '10%',
									title : '学号/工号'
								}, {
									field : 'age',
									width : '10%',
									title : '年龄'
								}, {
									field : 'sex',
									title : '性别',
									width : '10%',
								//minWidth : 100
								} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
								, {
									field : 'email',
									title : 'Email',
									width : '10%'
								}, {
									field : 'instituteName',
									title : '学院名称',
									width : '15%'
								}, {
									field : 'userTypeName',
									title : '账户类型',
									width : '10%'
								}, {
									title : '操作',
									fixed : 'right',
									width : '25%',
									align : 'center',
									toolbar : '#barDemo'
								} ] ]
							});

							//监听工具条
							table
									.on(
											'tool(userTable)',
											function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
												var data = obj.data //获得当前行数据
												, layEvent = obj.event; //获得 lay-event 对应的值
												console.log(data)
												if (layEvent === 'del') {
													layer
															.confirm(
																	'真的删除行么',
																	function(
																			index) {
																		obj
																				.del(); //删除对应行（tr）的DOM结构
																		layer
																				.close(index);
																		//向服务端发送删除指令
																	});
												} else if (layEvent === 'edit') {
													layer
															.open({
																type : 2,
																area : [
																		'600px',
																		'580px' ],
																content : 'editUserForm',
																success : function(
																		layero,
																		index) {
																	var body = layer
																			.getChildFrame(
																					'body',
																					index);
																	var iframeWin = window[layero
																			.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
																	//iframeWin.window.setDefaultData(data);
																	//console.log(body.html()) //得到iframe页的body内容
																	//body.find('input').val('Hi，我是从父页来的')
																	body
																			.find(
																					'#indexId')
																			.val(
																					index)
																	var sexSel = data.sex == '男' ? 1
																			: 2;
																	body
																			.find(
																					"#sex"
																							+ sexSel
																							+ '')
																			.prop(
																					"checked",
																					true);
																	body
																			.find(
																					"#userName")
																			.val(
																					data.userName);
																	body
																			.find(
																					"#userCode")
																			.val(
																					data.userCode);
																	body
																			.find(
																					"#age")
																			.val(
																					data.age);
																	body
																			.find(
																					"#userTypeName")
																			.val(
																					data.userTypeName);
																	body
																			.find(
																					"#instituteName")
																			.val(
																					data.instituteName);
																	body
																			.find(
																					"#email")
																			.val(
																					data.email);
																	body
																			.find(
																					'#userId')
																			.val(
																					data.userId)
																	var iframeWindow = layero
																			.find('iframe')[0].contentWindow;
																	//重新渲染checkbox
																	iframeWindow.layui.form
																			.render();
																	debugger

																},
																end : function() {
																	layer
																			.msg("刷新表格")
																	//执行重载
																	table
																			.reload(
																					'userTable',
																					{
																						page : {
																							curr : 1
																						//重新从第 1 页开始
																						}
																					});
																}
															});

												}
											});

							var $ = layui.$, active = {
								reload : function() {
									var userName_val = $('#userName').val();
									userName_val = userName_val == null ? null
											: userName_val == '' ? null
													: userName_val;
									var userType_val = $('#userType').val();
									userType_val = userType_val == null ? null
											: userType_val == '' ? null
													: userType_val;
									var email_val = $('#email').val();
									email_val = email_val == null ? null
											: email_val == '' ? null
													: email_val;

									debugger;
									//执行重载
									table.reload('userTable', {
										page : {
											curr : 1
										//重新从第 1 页开始
										},
										method: 'POST',
										where : {
											userName : userName_val,
											userType : userType_val,
											email : email_val
										}
									});
								},
								addNewUser : function() {
									layer
											.open({
												type : 2,
												area : [ '600px', '540px' ],
												content : 'addUserForm',
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
													body.find('#indexId').val(
															index)
												},
												end : function() {
													layer.msg("刷新表格")
													//执行重载
													table.reload(
															'courseInfoTable',
															{
																page : {
																	curr : 1
																//重新从第 1 页开始
																}
															});
												}
											});
								}

							};

							$('.buttonClick .layui-btn').on(
									'click',
									function() {
										var type = $(this).data('type');
										active[type] ? active[type].call(this)
												: '';
									});

						});
	</script>

</body>
</html>