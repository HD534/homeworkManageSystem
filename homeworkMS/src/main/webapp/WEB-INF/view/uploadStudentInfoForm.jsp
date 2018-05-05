<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="layui/css/layui.css">
<script src="layui/layui.js"></script>
<script src="jquery/jquery-3.3.1.js"></script>
<title>Upload Form</title>
</head>
<body>
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 30px;">
		<legend>上传学生信息</legend>
	</fieldset>

	<div class="layui-upload">
		<button type="button" class="layui-btn layui-btn-normal" id="testList"
			style="margin-left: 20px">
			<i class="layui-icon">&#xe61f;</i>选择文件
		</button>
		&nbsp;<span style="color: #FF5722">(只能上传.xls或者.xlsx文件)</span>

		<button type="button" class="layui-btn " id="downLoadTemplate"
			style="margin-left: 20px">
			<i class="layui-icon">&#xe601;</i> 下载Excel模板文件
		</button>
		<div class="layui-upload-list">
			<table class="layui-table">
				<thead>
					<tr>
						<th>文件名</th>
						<th>大小</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="demoList"></tbody>
			</table>
		</div>
		<button type="button" class="layui-btn" id="testListAction"
			style="margin-left: 20px">
			<i class="layui-icon">&#xe67c;</i> 开始上传
		</button>
	</div>
</body>

<script type="text/javascript">
	layui.use([ 'upload', 'layer' ], function() {
		var $ = layui.jquery, 
		upload = layui.upload,
		layer=layui.layer;

		$("#downLoadTemplate").bind({
			click:function(){
				layer.open({
				        type: 1
				        ,title: "提示信息" //不显示标题栏
				        ,area: '300px;'
				        ,shade: 0.8
				        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
				        ,btn: ['下载', '取消']
				        ,btnAlign: 'c'
				        ,moveType: 1 //拖拽模式，0或者1
				        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">'
				        +'模板文件中的表头不要修改<br><br>也不需要添加其他的表头<br><br>如需添加其他信息，请联系管理员</div>'
				        ,yes: function(index, layero){
				          var fileId = '8696243b848442f69ac990b19c486666';
						  window.open("util/download?fileId="+ fileId);
						  layer.close(index)
				        } 
				        ,btn2: function(index, layero){
					       layer.close(index)
						}
				});
				
			}
		})
		
		//多文件列表示例
		  var demoListView = $('#demoList')
		  ,uploadListIns = upload.render({
		    elem: '#testList'
		    ,url: 'uploadStudentInfo'
		    ,accept: 'file'
		    ,exts:'xls|xlsx'
		    //,multiple: true
		    ,auto: false
		    ,bindAction: '#testListAction'
		    ,choose: function(obj){   
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">'
		          ,'<td>'+ file.name +'</td>'
		          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
		          ,'<td>等待上传</td>'
		          ,'<td>'
		            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
		            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
		          ,'</td>'
		        ,'</tr>'].join(''));
		        
		        //单个重传
		        tr.find('.demo-reload').on('click', function(){
		          obj.upload(index, file);
		        });
		        
		        //删除
		        tr.find('.demo-delete').on('click', function(){
		          delete files[index]; //删除对应的文件
		          tr.remove();
		          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		        });
		        
		        demoListView.append(tr);
		      });
		    }
		    ,done: function(res, index, upload){
		      if(res.code == 0){ //上传成功
		    	  debugger;
		        var tr = demoListView.find('tr#upload-'+ index)
		        ,tds = tr.children();
		        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		        //tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		        tds.eq(3).html('<button class="layui-btn layui-btn-mini demo-download">撤销</button>'); //清空操作
		        
		      /* //单个下载
		         tr.find('.demo-download').on('click', function(){
		        	console.log(res)
		        	console.log(index)
		        	console.log(upload)
		        	window.open("util/download?filename="+res.data[0])
		        	debugger;
		        });  */
		        layer.msg('上传成功');
		         return delete this.files[index]; //删除文件队列已经上传成功的文件
		        //return 0;
		      }
		      
		      this.error(index, upload);
		    }
		    ,error: function(index, upload){
		    	debugger
		      var tr = demoListView.find('tr#upload-'+ index)
		      ,tds = tr.children();
		      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		  });

	});
</script>

</html>