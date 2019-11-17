<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="layui/layui.js"></script>
    <script src="layui/layui.all.js"></script>
    <script src="jquery/jquery-3.3.1.js"></script>

    <style type="text/css">
        .login-bg {
            background: #FFFFFFF 0 0 no-repeat;
            /*background: url(image/bg.png) no-repeat center;*/
            width: 100%;
            height: auto;
            background-size: 100%;
            /*overflow: hidden;*/
        }

        .myForm, .layui-form-select {
            width: 80%
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Form div</title>
</head>
<body class="layui-container">
<div class="layui-row ">
    <div class="layui-col-md12 ">
        <div class="layui-row ">
            <div class="layui-col-md10 layui-col-md-offset1">
                <form class="layui-form " action="editTerm" id="termInfoForm"
                      style="margin-top: 20px; padding: 5px">
                    <div class="layui-row" id="mainPanle">

                        <div class="classInfo" id="item1">
                            <div class="layui-form-item">
                                <label class="layui-form-label">学期值</label>
                                <div class="layui-input-block">
                                    <input type="text" name="termValue" autocomplete="off" required id="termValue"
                                           lay-verify="required" placeholder="请输入学期值（如：201901）"
                                           class="layui-input myForm">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">学期名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="termName" id="termName" autocomplete="off" required
                                           lay-verify="required" placeholder="请输入学期名（如：2019春季）"
                                           class="layui-input myForm">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item" style="padding: 5px">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="termSubmit">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>

                        <input type="text" id="indexId" class="layui-input layui-hide">
                        <input type="text" id="type" class="layui-input layui-hide">

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form', 'layer'], function () {

        var form = layui.form, $ = layui.jquery, layer = layui.layer;
        //getInstituteList();
        form.render("");
        //监听提交


        form.on('submit(termSubmit)', function (data) {
			var type = $('#type').val()
            var action = type === 'edit' ? 'editTerm' : type === 'add' ? 'addTerm' : '';
            if (action==='') return false;
            var paramMap = data.field;
            $.ajax({
                data: JSON.stringify(paramMap),
                url: action,
                contentType: "application/json",
                type: 'POST',
                dataType: "json",
                success: function (data) {
                    if (data.code === 0) {
                        parent.layer.close($('#indexId').val());
                        console.log(data);
                        parent.layer.msg("添加成功")
                    } else if (data.code === 2) {
                        layer.msg("已存在")
                    } else {
                        layer.msg("添加失败")
                    }
                }

            });
            return false;
        });

    });
</script>

</body>

</html>