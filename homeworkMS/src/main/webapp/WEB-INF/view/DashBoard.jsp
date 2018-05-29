<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>作业管理系统</title>
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="jquery/jquery-3.3.1.js"></script>
<style type="text/css">
/* .layui-tab-title li:first-child > i {
display: none;
}  */
.my-tab ul.layui-tab-title li:nth-child(1) i {
	display: none;
} /*第一个不可删除*/
.myFontSize a {
	font-size:17px;
}
.myFontSize1 a {
	font-size:16px;
}
</style>

</head>
<body class="layui-layout-body" style="overflow-y: hidden;">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo layui-icon" style="font-size:22px">&#xe68e;&nbsp;作业管理系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<!-- <ul class="layui-nav layui-layout-left" lay-filter="test1">
				<li class="layui-nav-item"><a href="javascript:;"
					name="loginForm">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul> -->
			<ul class="layui-nav layui-layout-right layui-icon myFontSize1"  lay-filter="leftNav">
				<li class="layui-nav-item"><a href="javascript:;"> 欢迎你,
						${userName} <c:choose>
							<c:when test="${userType eq 1||userType eq 0}">老师</c:when>
							<c:otherwise>同学</c:otherwise>
						</c:choose>
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" href-url="userInfoForm">基本资料</a> <!-- &#xe606;&nbsp; -->
						</dd>
						<dd>
							<a href="javascript:;" href-url="editUserPasswordForm">修改密码</a> <!-- &#xe614;&nbsp; -->
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="logout">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black ">
			<div class="layui-side-scroll ">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree myFontSize layui-icon" lay-filter="leftNav">

					<c:if test="${userType eq 0}">
						<li class="layui-nav-item"><a href="javascript:;">&#xe612;&nbsp;人员管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;" href-url="listUserForm">&nbsp;&nbsp;&nbsp;人员详情</a>
								</dd>


							</dl></li>
					</c:if>
					<c:if test="${userType eq 1||userType eq 0}">
						<li class="layui-nav-item"><a href="javascript:;">&#xe629;&nbsp;班级管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;" href-url="listClassForm">&nbsp;&nbsp;&nbsp;班级详情</a>
								</dd>

								<!-- <dd>
									<a href="javascript:;" href-url="classCourseForm">班级课程详情</a>
								</dd> -->

							</dl></li>
					</c:if>
					<li class="layui-nav-item "><a class=" myFontSize" href="javascript:;">&#xe857;&nbsp;课程管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" href-url="listCourseInfoForm">&nbsp;&nbsp;&nbsp;课程详情</a>
							</dd>
							
						</dl>
					</li>
					<li class="layui-nav-item "><a href="javascript:;">&#xe705;&nbsp;作业管理
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a  href="javascript:;" href-url="listHomeworkForm">&nbsp;&nbsp;&nbsp;作业详情</a>
							</dd>
							<dd>
								<a href="javascript:;" href-url="listHomeworkScoreForm">&nbsp;&nbsp;&nbsp;作业批改情况</a>
							</dd>
							<c:if test="${userType eq 0}">
							<dd>
								<a href="javascript:;" href-url="upload">&nbsp;&nbsp;&nbsp;上传文件</a>
							</dd>
							<dd>
								<a href="javascript:;" href-url="attachFiles">&nbsp;&nbsp;&nbsp;文件列表</a>
							</dd>
							</c:if>
							
						</dl></li>
					<c:if test="${userType eq 1||userType eq 0}">
						<li class="layui-nav-item"><a href="javascript:;">&#xe63c;&nbsp;学生管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;" href-url="listStudentInfoForm">&nbsp;&nbsp;&nbsp;学生详情</a>
								</dd>

								<dd>
									<a href="javascript:;" href-url="uploadStudentInfoForm">&nbsp;&nbsp;&nbsp;上传学生信息
									</a>
								</dd>

							</dl></li>
					</c:if>

				</ul>
			</div>
		</div>

		<div class="layui-body" style="bottom: 1px;overflow: auto;">
			<!-- 内容主体区域 -->
			<!-- <fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 50px;">
				<legend>动态操作Tab</legend>
			</fieldset> -->

			<div class="layui-tab my-tab" lay-filter="demo" lay-allowclose="true">
				<ul class="layui-tab-title">
					<li class="layui-this " lay-id="11">后台首页 <i
						class="layui-icon layui-unselect layui-tab-close" data-id="11">ဆ</i></li>

				</ul>
				<!-- <ul class="layui-nav closeBox">
				  <li class="layui-nav-item">
				    <a href="javascript:;"><i class="layui-icon caozuo"></i> 页面操作<span class="layui-nav-more"></span></a>
				    <dl class="layui-nav-child layui-anim layui-anim-upbit">
					  <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">ဂ</i> 刷新当前</a></dd>
				      <dd><a href="javascript:;" class="closePageOther"><i class="seraph icon-prohibit"></i> 关闭其他</a></dd>
				      <dd><a href="javascript:;" class="closePageAll"><i class="seraph icon-guanbi"></i> 关闭全部</a></dd>
				    </dl>
				  </li>
				<span class="layui-nav-bar" style="left: 0px; top: 35px; width: 0px; opacity: 0;"></span>
				</ul> -->
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<div style=" ">
							<div style=" float:left;width:50%">
							<ul class="layui-timeline">
							  <li class="layui-timeline-item">
							    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
							    <div class="layui-timeline-content layui-text">
							      <h3 class="layui-timeline-title">课程管理</h3>
							     	<ul>
							         	<li>按条件搜索查看课程详情，新增课程。</li>
								       	<li>课程列表中可以查看课程作业，分配课程班级</li>
								       	<li>查看课程作业可以添加作业，下载或者删除已经存在的作业 </li>
							     	</ul>
							    </div>
							  </li>
							  <li class="layui-timeline-item">
							    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
							    <div class="layui-timeline-content layui-text">
							      <h3 class="layui-timeline-title">作业管理</h3>
							      	<ul>
							        	<li>按条件搜索查看作业详情，新增作业</li>
							        	<li>作业列表也可下载选中的作业文件，查看学生作业上交情况</li>
							        	<li>按条件查看学生作业情况列表，下载学生提交的作业</li>
							        	<li>按条件查看作业批改情况</li>
							      	</ul>
							    </div>
							  </li>
							  <li class="layui-timeline-item">
							    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
							    <div class="layui-timeline-content layui-text">
							      <h3 class="layui-timeline-title">学生管理</h3>
							      	<ul>
							        	<li>按条件搜索查看学生信息详情</li>
							        	<li>上传学生信息文件，学生信息通过Excel文件上传并添加到数据库中</li>
							      	</ul>
							      	
							    </div>
							  </li>
							  <li class="layui-timeline-item">
							    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
							    <div class="layui-timeline-content layui-text">
							      <h3 class="layui-timeline-title">人员管理</h3>
							      	<ul>
							        	<li>按条件搜索查看当前角色权限可以看到所有人员详情</li>
							      	</ul>
							    </div>
							    <li class="layui-timeline-item">
							    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
							    <div class="layui-timeline-content layui-text">
							      <h3 class="layui-timeline-title">班级管理</h3>
							      	<ul>
							        	<li>按条件搜索查看的班级信息详情</li>
							      	</ul>
							    </div>
							  </li>
							  </li>
							</ul>
							</div>
						
							<div style="float:left;margin-left:20px; ">
								<span id="testView"></span>
								<div id="calendar"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

		 <div class="layui-footer" >
			© 2018 作业管理系统 
		</div>  
	</div>


</body>

<script>
	layui.use([ 'form', 'layer', 'element','laydate' ], function() {
		debugger
		var element = layui.element;
		var form = layui.form;
		var layer = layui.layer;
		var laydate = layui.laydate;
		
		laydate.render({
			  elem: '#calendar'
			  ,position: 'static'
			  ,change: function(value, date){ //监听日期被切换
			    lay('#testView').html(value);
			  }
			});

		//一些事件监听
		element.on('nav(leftNav)', function(elem) {
			var title = elem[0].innerText; // 导航栏text
			var src = elem.children('a').attr("href-url"); // 导航栏跳转URL
			if (src == "logout") {
				window.location.href = src;
				return;
			}
			tabAdd(title,src)
		});
		
		function tabAdd(title,src){
			
			var card = 'demo'; // 选项卡对象
			var id = new Date().getTime(); // ID
			var flag = getTitleId(card, title); // 是否有该选项卡存在
			var currBoxHeight = $('.layui-body').height(); //获取当前容器的高度
			var tabHeight = $('.layui-tab-title').height(); //获取高度
			var iframeHeight = currBoxHeight - tabHeight - 5
			iframeHeight = 580
			debugger
			// 大于0就是有该选项卡了
			if (flag > 0) {
				id = flag;
				debugger
				element.tabChange(card, id);
				//$('#' + flag).attr('src', $('#' + flag).attr('src'));
			} else {

				//新增
				element.tabAdd(card, {
					title : '<span>' + title + '</span>', // border:none;outline:none; 
					content : '<iframe id="' + id + '" src="' + src
							+ '" frameborder="0"  style="width:100%; height:'
							+ iframeHeight + 'px;"></iframe>',
					id : id
				});
				element.tabChange(card, id);
			}
			// 切换相应的ID tab

			//$('#'+id).contentWindow.location.reload(true);
			// 提示信息
			layer.msg(title, {
				time : 1000
			});
			return id;
		}
		window.tabAdd = tabAdd;
		
	});

		// 根据导航栏text获取lay-id
		function getTitleId(card, title) {
			var id = -1;
			$(document).find(".layui-tab[lay-filter=" + card + "] ul li").each(
					function() {
						var t = $(this).find('span').text()
						if (title.trim() === $(this).find('span').text().trim()) {
							id = $(this).attr('lay-id');
						}
					});
			return id;
		}
		window.getTitleId = getTitleId;

		function addIframe() {
			debugger;
			layer.open({
				type : 2,
				title : 'test',
				shadeClose : false,
				shade : 0.8,
				area : [ '555px', '475px' ],
				skin : 'layui-layer-molv',
				content : 'loginForm',
				success : function(layero, index) {

				}
			});
		}

		/* 	function setIframeHeight(iframe) {
		 if (iframe) {
		 var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		 if (iframeWin.document.body) {
		 iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		 }
		 }
		 }; */
	
</script>
</html>