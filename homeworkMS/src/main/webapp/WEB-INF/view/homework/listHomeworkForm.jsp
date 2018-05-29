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

		<form class="layui-form" action="">
			<div class="layui-form-item">
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
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学院</label>
				<div class="layui-input-inline">
					<select name="instituteSelect" id="instituteSelect"
						id="instituteSelect" lay-filter="instituteSelect">
						<option value=""></option>
					</select>
				</div>


				<label class="layui-form-label">课程名称</label>
				<div class="layui-input-inline">
					<input class="layui-input" name="courseName" id="courseName"
						autocomplete="off" />
				</div>

			</div>
			<div class="buttonClick">
				<div class="layui-input-inline demoTable"
					style="padding: 10px; margin-left: 20px; margin-right: 20px">
					<c:if test="${userType eq 0||userType eq 1}">
						<button type="button" class="layui-btn layui-icon"
							data-type="addHomeworkForm"
							style="margin-left: 20px; margin-right: 20px">&#xe61f;
							添加作业</button>
					</c:if>
					<button type="button" class="layui-btn layui-icon" id="search-btn"
						data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
				</div>
			</div>
		</form>


		<table class="layui-hide " id="homeworkTable"
			lay-filter="homeworkTable">
			<script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-xs" lay-event="download">下载作业</a>
				<c:if test="${userType eq 0||userType eq 1}">
  					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  					<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="listStudentHomeWork">查看学生作业情况</a>
				</c:if>
				<c:if test="${userType eq 0||userType eq 2}">
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="uploadHomework">提交作业</a>
				</c:if>
			</script>
		</table>

	</div>

	<script>
		$(function() {
			function setId(courseId){
				
				$('#courseId').val(courseId);
			}
			
			window.setId = setId;
			
			layui.use([ 'table', 'form', 'layer' ], function() {
				var table = layui.table;
				var form = layui.form;
				var layer = layui.layer;
				var $ = layui.jquery;
				getTermList();
				getInstituteList();
				form.render();

				var tableIns = table.render({
					id : 'homeworkTable',
					elem : '#homeworkTable',
					url : 'listHomework',
					cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
					//,height:  'full-20'
					,
					page : true,
					cols : [ [ {
						field : 'instituteName',
						title : '学院名称',
						width : '10%'
					}, {
						field : 'fileId',
						minWidth : '0',
						width : '0',
						type : 'space',
						style : 'display: none'
					}, {
						field : 'termName',
						title : '学期',
						width : '8%'
					}, {
						field : 'courseName',
						width : '10%',
						title : '课程'
					}, {
						field : 'homeworkName',
						width : '12%',
						title : '作业名'
					}, {
						field : 'homeworkDesc',
						width : '12%',
						title : '作业简介'
					}, {
						field : 'publishDate',
						title : '发布日期',
						width : '10%',
					//minWidth : 100
					} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
					, {
						field : 'DueDate',
						title : '截至日期',
						width : '10%'
					}, {
						title : '操作',
						fixed : 'right',
						width : '27%',
						align : 'center',
						toolbar : '#barDemo'
					} ] ]
				});

				//监听工具条
				//监听工具条
				table.on('tool(homeworkTable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data //获得当前行数据
					, layEvent = obj.event; //获得 lay-event 对应的值
					//console.log(data)
					if (layEvent === 'download') {
						debugger
						window.open("util/download?fileId=" + data.fileId)
						layer.msg('查看操作');
					} else if (layEvent === 'del') {
						layer.confirm('真的删除行么', function(index) {
							obj.del(); //删除对应行（tr）的DOM结构
							layer.close(index);
							//向服务端发送删除指令
						});
					}else if (layEvent === 'uploadHomework') {
						
						debugger;
						if(checkDueDate(data.DueDate)){
							layer
							.open({
								type : 2,
								title : data.courseName+'-提交作业',
								area : [ '500px', '500px' ],
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
									body.find('#homeworkId').val(data.homeworkId);
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
						}else{
							
							layer.open({
						        type: 1
						        ,title: "提示信息" //不显示标题栏
						        ,area: '300px'
						        ,shade: 0.8
						        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
						        ,btn: ['关闭']
						        ,btnAlign: 'c'
						        ,moveType: 1 //拖拽模式，0或者1
						        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
						        +'作业截止日期已经到了！ <br><br>需要提交作业请联系任课教师 ！</div>'
						        ,yes: function(index, layero){
								  layer.close(index)
						        } 
						       
							});
							//layer.msg("作业截止日期已经到了")
						}
						
						
					}else if (layEvent === 'listStudentHomeWork') {
						var listStudentHomeworkFormIndex;
						layer
						.open({
							type : 2,
							title : data.courseName+'-学生作业情况',
							area : [ '1000px', '550px' ],
							content : 'listStudentHomeworkForm',
							success : function(layero, index) {
								var body = layer.getChildFrame('body',
										index);
								var iframeWin = window[layero
										.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
								// console.log(body.html()) //得到iframe页的body内容
								//body.find('input').val('Hi，我是从父页来的')
								
								debugger;
								listStudentHomeworkFormIndex = index;
								body.find('#indexId').val(index);
								body.find('#homeworkId').val(data.homeworkId);
								
								body.find('#courseName').val(data.courseName);
								//iframeWindow.layui.form.render();
								//iframeWin.setHomeworkId(data.homeworkId);
							},
							end : function() {
								layer.msg("刷新表格")
								layer.closeAll();
								//layer.close(listStudentHomeworkFormIndex);
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

				function getTermList() {
					$.ajax({
						url : "getTermList",
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
							$("#termSelect").append(html);
							form.render('select');
						}

					})
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

				function tableReload(courseId) {
					var userName_val = $('#userName').val();
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

					//courseId = courseId==null?null:courseId==''?null:courseId;

					debugger;
					console.log(courseId)
					//执行重载
					table.reload('homeworkTable', {
						page : {
							curr : 1
						//重新从第 1 页开始
						},
						method : 'POST',
						where : {
							userName : userName_val,
							termValue : termValue_val,
							instituteId : instituteId_val,
							courseName : courseName_val,
							courseId : courseId
						}
					});
				}

				window.tableReload = tableReload

				var $ = layui.$, active = {
					reload : function() {
						tableReload();
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
										table.reload('homeworkTable', {
											page : {
												curr : 1
											//重新从第 1 页开始
											}
										})
									}
								});
					}

				};
				
				$('.demoTable .layui-btn').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});

			});
		})
		
		function getFormatDate(){    
		    var nowDate = new Date();     
		    var year = nowDate.getFullYear();    
		    var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;    
		    var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();    
		    // var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();    
		    // var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();    
		    // var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();    
		    return year + "-" + month + "-" + date;    
		}    
		
		//查看今日是否小于作业截至日期
		function checkDueDate(DueDate){ 
			var Today = getFormatDate();
		  
		    var dueDate=new Date(DueDate.replace("-", "/").replace("-", "/"));  
		   
		    var today=new Date(Today.replace("-", "/").replace("-", "/"));  
		    //今日小于截止日期，可以交作业
		    if(today<dueDate){  
		        return true;  
		    }  
		    return false;  
		}  

	</script>

</body>
</html>