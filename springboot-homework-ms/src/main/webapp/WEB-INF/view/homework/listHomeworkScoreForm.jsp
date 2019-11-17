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
			<div class="layui-form-item">
				<label class="layui-form-label">学期</label>
				<div class="layui-input-inline">
					<select name="termSelect" id="termSelect" id="termSelect"
						lay-filter="termSelect">
						<option value=""></option>
					</select>
				</div>
				<c:if test="${userType eq 0}">
					<label class="layui-form-label">学院</label>
					<div class="layui-input-inline">
						<select name="instituteId" id="instituteSelect"
							lay-filter="instituteSelect">
							<option value=""></option>
						</select>
					</div>
					<label class="layui-form-label">班级</label>
					<div class="layui-input-inline">
						<input type="text" name="className" id="className"
						autocomplete="off" class="layui-input">
					</div>
				</c:if>

			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">课程名称</label>
				<div class="layui-input-inline">
					<input type="text" name="courseName" id="courseName"
						   autocomplete="off" class="layui-input">
				</div>
				<label class="layui-form-label">作业名称</label>
				<div class="layui-input-inline">
					<input type="text" name="homeworkName" id="homeworkName"
						   autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<c:if test="${userType eq 0||userType eq 1}">
					<label class="layui-form-label">学号</label>
					<div class="layui-input-inline">
						<input class="layui-input" name="userCode" id="userCode"
							autocomplete="off" />
					</div>
					<label class="layui-form-label">学生</label>
					<div class="layui-input-inline">
						<input class="layui-input" name="userName" id="userName"
							autocomplete="off" />
					</div>
				</c:if>
			</div>

			<div class="layui-input-inline demoTable"
				style="margin-left: 20px; margin-right: 20px">
				<button type="button" class="layui-btn layui-icon" id="search-btn"
					data-type="reload" style="margin-left: 50px">搜索 &#xe615;</button>
			</div>

		</form>
		
		<input class="layui-input layui-hide" name="homeworkId"
			id="homeworkId" autocomplete="off" /> <input
			class="layui-input layui-hide" name="indexId" id="indexId"
			autocomplete="off" /> <input class="layui-input layui-hide"
			name="courseName" id="courseName" autocomplete="off" />

		<table class="layui-hide " id="studentHomeworkScoreTable"
			lay-filter="studentHomeworkScoreTable">
			<!-- <script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn layui-btn-xs" lay-event="download">下载作业</a>
				<c:if test="${userType eq 0||userType eq 1}">
  					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
				</c:if>
				<c:if test="${userType eq 2}">
					<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="uploadHomework">提交作业</a>
				</c:if>
			</script> -->
		</table>

	</div>

	<script>
		$(function() {

			layui
					.use(
							[ 'table', 'form', 'layer' ],
							function() {
								var table = layui.table;
								var form = layui.form;
								var layer = layui.layer;
								var $ = layui.jquery;
								
								getTermList();
								getInstituteList();
								form.render();

								var tableIns = table.render({
									url : 'listHomeworkScoreInfo',
									id : 'studentHomeworkScoreTable',
									elem : '#studentHomeworkScoreTable',
									cellMinWidth : 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
									//,height:  'full-20'
									,
									page : true,
									cols : [ [ {
										field : 'instituteName',
										title : '学院',
										width : '10%'
									},
									 {
										field : 'courseName',
										title : '课程',
										width : '10%'
									}, {
										field : 'termName',
										title : '学期',
										width : '8%'
									}, 
									{
										field : 'homeworkName',
										title : '作业名称',
										width : '12%'
									}, {
										field : 'className',
										width : '10%',
										title : '班级'
									}, {
										field : 'userCode',
										width : '10%',
										title : '学号'
									}, {
										field : 'studentName',
										width : '10%',
										title : '学生'
									}, {
										field : 'comment',
										width : '12%',
										title : '批改意见'
									}, {
										field : 'scoreDate',
										title : '批改日期',
										width : '10%',
									//minWidth : 100
									} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
									, {
										field : 'score',
										width : '8%',
										title : '分数',
										//type : 'numbers',
										//edit : 'text'
									},
									/* {
										title : '操作',
										fixed : 'right',
										width : '20%',
										align : 'center',
										toolbar : '#barDemo'
									},  */
									{
										field : 'instituteId',
										minWidth : '0',
										width : '0',
										type : 'space',
										style : 'display: none'
									}, {
										field : 'courseId',
										minWidth : '0',
										width : '0',
										type : 'space',
										style : 'display: none'
									}, {
										field : 'studentId',
										minWidth : '0',
										width : '0',
										type : 'space',
										style : 'display: none'
									} ] ]
								});


								function studentHomeworkScoreTableReload() {

										var userName_val = $('#userName').val();
										userName_val = userName_val == null ? null
												: userName_val == '' ? null : userName_val;
										
										var homeworkName_val = $('#homeworkName').val();
										homeworkName_val = homeworkName_val == null ? null
												: homeworkName_val == '' ? null : homeworkName_val;
										
										var userCode_val = $('#userCode').val();
										userCode_val = userCode_val == null ? null
												: userCode_val == '' ? null : userCode_val;
										
										var termValue_val = $('#termSelect').val();
										termValue_val = termValue_val == null ? null
												: termValue_val == '' ? null : termValue_val;
										
										var instituteId_val = $('#instituteSelect').val();
										instituteId_val = instituteId_val == null ? null
												: instituteId_val == '' ? null : instituteId_val;
										
										var courseName_val = $('#courseName').val();
										courseName_val = courseName_val == null ? null
												: courseName_val == '' ? null : courseName_val; 
										
										var className_val = $('#className').val();
										className_val = className_val == null ? null
												: className_val == '' ? null : className_val; 
										
										var className_val = $('#className').val();
										className_val = className_val == null ? null
												: className_val == '' ? null : className_val; 
										
									debugger;
									//执行重载
									table.reload('studentHomeworkScoreTable', {
										page : {
											curr : 1
										//重新从第 1 页开始
										},
										method : 'POST',
										where : {
											userName : userName_val,
											homeworkName : homeworkName_val,
											userCode : userCode_val,
											termValue : termValue_val,
											instituteId : instituteId_val,
											courseName : courseName_val,
											className : className_val
										}
									});
								}

								var $ = layui.$, active = {
									reload : function() {
										studentHomeworkScoreTableReload();
									}

								};

								$('.demoTable .layui-btn').on(
										'click',
										function() {
											var type = $(this).data('type');
											active[type] ? active[type]
													.call(this) : '';
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
										/* 	$form.find('select[name=instituteName]').append(html);
											console.log($form.find('select[name=instituteName]')) */
											form.render('select');
										}

									})
								}


							});
		})
	</script>

</body>
</html>