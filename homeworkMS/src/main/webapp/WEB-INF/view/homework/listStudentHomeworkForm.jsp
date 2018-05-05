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
<!-- 
<script src="layui/layui.js" charset="utf-8"></script> -->
<script src="layui/layui.all.js" charset="utf-8"></script>
<script src="jquery/jquery-3.3.1.js"></script>
</head>
<body>
	<div>

		<form class="layui-form" action=""
			style="margin-top: 5px; padding: 5px">
			<%-- <div class="layui-form-item">
				<label class="layui-form-label">学期</label>
				<div class="layui-input-inline">
					<select name="termSelect" id="termSelect" id="termSelect"
						lay-filter="termSelect">
						<option value=""></option>
					</select>
				</div>
				<c:if test="${userType eq 0}">
					<label class="layui-form-label">教师</label>
					<div class="layui-input-inline">
						<input class="layui-input" name="userName" id="userName"
							autocomplete="off" />
					</div>
				</c:if>
			</div> --%>
			<div class="layui-form-item">
				<label class="layui-form-label">班级</label>
				<div class="layui-input-inline">
					<select name="classSelect" id="classSelect"
						lay-filter="classSelect">
						<option value=""></option>
						<option value="1">班级1</option>
						<option value="1">班级2</option>
					</select>
				</div>


				<label class="layui-form-label">学号</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="courseName" id="courseName"
						autocomplete="off" />
				</div>

				
			<div class="layui-input-inline demoTable"
				style=" margin-left: 20px; margin-right: 20px">
				<button type="button" class="layui-btn layui-icon" id="search-btn"
					data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
			</div>

			</div>
			<div class="layui-input-block demoTable">
						<button type="button" class="layui-btn layui-btn-normal"
						data-type="downLoadChecked">下载已选择</button>
						<button type="button" class="layui-btn layui-btn-normal"
						data-type="downLoadAll" style="margin-left:20px">全部选择</button>
			</div>

		</form>
		<input class="layui-input layui-hide" name="homeworkId" id="homeworkId" autocomplete="off" />
		<input class="layui-input layui-hide" name="indexId" id="indexId" autocomplete="off" />
		<input class="layui-input layui-hide" name="courseName" id="courseName" autocomplete="off" />
		
		<table class="layui-hide " id="studentHomeworkTable"
			lay-filter="studentHomeworkTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn layui-btn-xs" lay-event="download">下载作业</a>
				<c:if test="${userType eq 0||userType eq 1}">
  					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
				</c:if>
				<c:if test="${userType eq 2}">
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="uploadHomework">提交作业</a>
				</c:if>
			</script>
		</table>

	</div>

	<script>
		$(function() {
			
			var homeworkId = ''
			function setHomeworkId(homeworkId) {
				debugger;
				homeworkId = homeworkId;
				$('#homeworkId').val(homeworkId);
				tableReload(homeworkId)
			}

			window.setHomeworkId = setHomeworkId;

			layui.use([ 'table', 'form', 'layer' ], function() {
				var table = layui.table;
				var form = layui.form;
				var layer = layui.layer;
				var $ = layui.jquery;
				//getTermList();
				//getInstituteList();
				form.render();
				
				var tableIns = table.render({
					id : 'studentHomeworkTable',
					elem : '#studentHomeworkTable',
					cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
					//,height:  'full-20'
					,
					page : true,
					cols : [ [ 
					{
						type:'checkbox', fixed: 'left'
					},
					/* {
						field : 'courseName',
						title : '课程',
						width : '10%'
					}, {
						field : 'termName',
						title : '学期',
						width : '8%'
					}, */ 
					{
						field : 'homeworkName',
						title : '作业名称',
						width : '12%'
					}, {
						field : 'className',
						width : '12%',
						title : '班级'
					}, {
						field : 'userCode',
						width : '12%',
						title : '学号'
					}, {
						field : 'studentName',
						width : '10%',
						title : '学生'
					}, {
						field : 'studentHomeworkName',
						width : '12%',
						title : '学生作业'
					}, {
						field : 'uploadDate',
						title : '上传日期',
						width : '12%',
					//minWidth : 100
					} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
					, {
						field : 'score',
						width : '8%',
						title : '分数',
						//type : 'numbers',
						edit:'text'
					},{
						title : '操作',
						fixed : 'right',
						width : '20%',
						align : 'center',
						toolbar : '#barDemo'
					},{
						field : 'homeworkId',minWidth: '0', width: '0', type: 'space',
						style: 'display: none'
					},{
						field : 'fileid',minWidth: '0', width: '0', type: 'space',
						style: 'display: none'
					},{
						field : 'studentId',minWidth: '0', width: '0', type: 'space',
						style: 'display: none'
					} ] ]
				});
				
				debugger;
				var homeId = $('#homeworkId').val();
				setTimeout("tableReload();", 200);

				//监听修改分数
				table.on('edit(studentHomeworkTable)', function(obj) { 
					debugger;
					var value = obj.value,
					data = obj.data,
					field = obj.field;
					
					var paramMap = data;
					
					if(!isNaN(value)){
						var reg=/^(([^0][\d]?)|0|100)$/;
						if(reg.test(value)){
							layer.msg('是数字 0-100')
							
							$.ajax({
							data : JSON.stringify(paramMap),
							url : 'insertStudentHomeworkScore',
							contentType : "application/json",
							type : 'POST',
							dataType : 'json',
							success : function(data) {
								if (data.code == 0) {
									//layer.close(indexId)
									//layer.closeAll();
									debugger;
									//parent.layer.close(indexId);
									//console.log(data)
									layer.msg("修改成功")
								}  else {
									layer.msg("修改失败")
								}

							}

						});  
							
						}else{
							
							layer.msg('数值范围 0-100， 请重新填写！')	
							setTimeout("tableReload();", 400);
							
						}

					}else{
							layer.msg('不是数字，请重新填写！');	
							setTimeout("tableReload();", 400);
					}
					
					
					
					
					var paramMap = data;

					
					//layer.msg('修改分数成功!')
				});
				
				
				//监听工具条
				table.on('tool(studentHomeworkTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data //获得当前行数据
					, layEvent = obj.event; //获得 lay-event 对应的值
					console.log(data)
					if (layEvent === 'download') {
						debugger
						window.open("util/download?fileId=" + data.fileid)
						layer.msg('查看操作');
					} else if (layEvent === 'del') {
						layer.confirm('真的删除行么', function(index) {
							obj.del(); //删除对应行（tr）的DOM结构
							layer.close(index);
							//向服务端发送删除指令
						});
					} else if (layEvent === 'uploadHomework') {
						layer
								.open({
									type : 2,
									title : data.courseName + '-上传作业',
									area : [ '500px', '400px' ],
									content : 'uploadCourseHomeworkForm',
									success : function(layero, index) {
										var body = layer.getChildFrame('body',
												index);
										var iframeWin = window[layero
												.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
										// console.log(body.html()) //得到iframe页的body内容
										//body.find('input').val('Hi，我是从父页来的')
										debugger;
										body.find('#indexId').val(index);
										body.find('#homeworkId').val(
												data.homeworkId);
									},
									end : function() {
										layer.msg("刷新表格")
										//执行重载
										/* table.reload('courseInfoTable', {
											page : {
												curr : 1
											//重新从第 1 页开始
											}
										}); */
									}
								});
					}
				});

				

				function tableReload() {
					
				/* 	var userName_val = $('#userName').val();
					userName_val = userName_val == null ? null
							: userName_val == '' ? null : userName_val;
					var termValue_val = $('#termSelect').val();
					termValue_val = termValue_val == null ? null
							: termValue_val == '' ? null : termValue_val;
					var instituteId_val = $('#instituteSelect').val();
					instituteId_val = instituteId_val == null ? null
							: instituteId_val == '' ? null : instituteId_val;
					var courseName_val = $('#courseName').val();
					courseName_val = courseName_val == null ? null
							: courseName_val == '' ? null : courseName_val; 
					
					userName : userName_val,
					termValue : termValue_val,
					instituteId : instituteId_val,
					courseName : courseName_val,
					*/
					
					var homeworkId_val = $('#homeworkId').val();

					//courseId = courseId==null?null:courseId==''?null:courseId;
					debugger;
					//执行重载
					table.reload('studentHomeworkTable', {
						url : 'listStudentHomework',
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						method : 'POST',
						where : {
							homeworkId : $('#homeworkId').val()
						}
					});
				}

				window.tableReload = tableReload

				var $ = layui.$, active = {
					reload : function() {
						if(table.cache.studentHomeworkTable.length==0){
							
							layer.msg("暂无数据")
						}else{
							tableReload();
						}
					},
					addHomeworkForm : function() {
						layer
								.open({
									type : 2,
									area : [ '600px', '540px' ],
									content : 'addHomeworkForm',
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
										table.reload('studentHomeworkTable', {
											page : {
												curr : 1
											//重新从第 1 页开始
											}
										})
									}
								});
					},
					
					downLoadChecked: function(){
						var checkStatus = table.checkStatus('studentHomeworkTable'),
						data = checkStatus.data;
						if(data.length==0){
							layer.msg('请选择下载的文件')
						}else{
							
							debugger;
							var fileIds = []; 
							//value是遍历的数组内容；index是对应的数组索引，array是数组本身。
							$.each(data, function(index, value, array) {
								fileIds.push(value.fileid)
							});
							console.log(fileIds);
							var classSelect = $('#classSelect').find("option:selected").text();
							
							var courseName = $('#courseName').val();
							var destFileName = courseName+'-'+classSelect+(classSelect==''?'':'-')+'作业情况.zip'
							console.log(fileIds);
							var form=$("<form>");//定义一个form表单  
							form.attr("style","display:none");  
							form.attr("method","post");  
							form.attr("action",'attachFiles/downloadZip');  
							var input1=$("<input>");  
							input1.attr("type","hidden");  
							input1.attr("name","fileIds");  
							input1.attr("value",fileIds); 
							var input2 = $('<input>');
							input2.attr("type","hidden");  
							input2.attr("name","destFileName");  
							input2.attr("value",destFileName); 
							$("body").append(form);//将表单放置在web中  
							form.append(input1);  
							form.append(input2);  
							//form.submit();//表单提交 
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
					downLoadAll: function(){
						if(table.cache.studentHomeworkTable.length==0){
							
							layer.msg("暂无数据")
						}else{
							debugger
							layer.confirm('确定全部下载吗', function(index) {
								layer.msg("全部下载")
								layer.close(index);
								//向服务端发送下载指令
							});
						}
					}
					

				};

				$('.demoTable .layui-btn').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});

			});
		})
	</script>

</body>
</html>