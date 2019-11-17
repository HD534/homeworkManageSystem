<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listTermInfoForm</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all">
</head>
<body>
<div>


    <button type="button" class="layui-btn layui-icon" id="addNewTermBtn"
            data-type="addNewTerm" style="margin-left: 20px; margin-right: 20px">&#xe61f;新增学期
    </button>


    <table class="layui-hide " id="termInfoTable" lay-filter="termInfoTable">
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </table>

</div>
<script src="../../layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['table', 'form', 'layer'], function () {
            var table = layui.table;
            var form = layui.form;
            var layer = layui.layer;
            var $ = layui.$;

            var tableIns = table.render({
                id: 'termInfoTable',
                elem: '#termInfoTable',
                url: 'getTermList',
                cellMinWidth: 80, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                //,height:  'full-20'
                page: true,
                cols: [[
                    {field: 'id', width: '40%', title: '学期值'},
                    {field: 'name', width: '40%', title: '学期名'},
                    {fixed: 'right', width: '20%', align: 'center', toolbar: '#barDemo'}
                ]]
            });
            form.render();

            table.on('tool(termInfoTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    , layEvent = obj.event; //获得 lay-event 对应的值
                if (layEvent === 'del') {
                    layer.confirm('确认删除此学期 : ' + data.name + ' 吗？', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if (layEvent === 'edit') {
                    openAddOrEditForm('edit',data)
                }
            });

            $('#addNewTermBtn').on('click', function () {
                openAddOrEditForm('add')

            });

            function openAddOrEditForm(type,data) {
                if (type !== 'add' && type !== 'edit')
                    return false;
                var title = type === 'add' ? '新增学期' : type === 'edit' ? '修改学期' : '';
                var openIndex;
                layer.open({
                    type: 2,
                    title: title,
                    content: 'editTermForm',
                    area: ['500px', '300px'],
                    closeBtn: 1,
                    btn: [],
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        //body.find('input').val('Hi，我是从父页来的')
                        openIndex = index;
                        body.find('#type').val(type);
                        body.find('#indexId').val(index);
                        if (type === 'edit') {
                            body.find("#termName").val(data.name);
                            body.find('#termValue').val(data.id);
                        }
                    },
                    end: function () {
                        layer.msg("刷新表格");
                        layer.close(openIndex)
                        table.reload('termInfoTable', {
                            page: {
                                curr: 1
                                //重新从第 1 页开始
                            }
                        });
                    }
                });
            }
        }
    )
</script>
</body>
</html>
