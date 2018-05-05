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
		<legend>高级应用：制作一个多文件列表</legend>
	</fieldset>

	<div class="layui-upload">
		<button type="button" class="layui-btn layui-btn-normal" id="testList" style="margin-left: 20px">选择多文件</button>
		<div class="layui-upload-list">
			<table class="layui-table" >
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
		<button type="button" class="layui-btn" id="testListAction" style="margin-left: 20px">开始上传</button>
	</div>
</body>

<script type="text/javascript">
	layui.use('upload', function() {
		var $ = layui.jquery, 
		upload = layui.upload;

		//指定允许上传的文件类型
		upload.render({
			elem : '#test3',
			url : 'util/uploadMul',
			accept : 'file' //普通文件
			,
			done : function(res) {
				console.log(res)
			}
		});
		
		//多文件列表示例
		  var demoListView = $('#demoList')
		  ,uploadListIns = upload.render({
		    elem: '#testList'
		    ,url: 'util/uploadMul'
		    ,accept: 'file'
		    ,multiple: true
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
		        tds.eq(3).html('<button class="layui-btn layui-btn-mini demo-download">下载</button>'); //清空操作
		        
		      //单个下载
		         tr.find('.demo-download').on('click', function(){
		        	console.log(res)
		        	console.log(index)
		        	console.log(upload)
		        	window.open("util/download?filename="+res.data[0])
		        	debugger;
		        }); 
		        
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