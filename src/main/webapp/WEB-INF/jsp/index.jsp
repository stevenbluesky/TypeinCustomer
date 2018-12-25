<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>客户信息录入平台</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <script src="static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link href="static/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="static/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="static/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="static/js/spop.js"></script>
    <link rel="stylesheet" href="static/css/spop.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/js/bootstrapValidator.min.js"></script>
    <script src="static/js/bootstrap-datetimepicker.min.js"></script>
    <script src="static/js/bootstrap-select.min.js"></script>
    <script src="static/js/bootstrap-table.min.js"></script>
</head>
    <div class="col-md-1"></div>
    <div class="col-md-10">
<div class="row">
    <div class="text-right">欢迎您：
        <c:if test="${person.realName==''}">${person.personCode}</c:if>
        <c:if test="${person.realName!=''}">${person.realName}</c:if>
        <a href="logout">安全退出</a></div>
    <div class="text-center"><h1>用户信息列表</h1></div>
    <hr>
    <form id="searchform" class="form-horizontal">
        <div class="form-group col-md-12">
            <div class="form-group col-md-4" align="right">
                <label for="searchname" class="col-md-5 control-label">姓名</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="searchname" name="searchname" placeholder="姓名">
                </div>
            </div>
            <div class="form-group col-md-4" align="right">
                <label for="searchphonenumber" class="col-md-5 control-label">电话</label>
                <div  class="col-md-7">
                    <input type="text" class="form-control" id="searchphonenumber" name="searchphonenumber" placeholder="电话">
                </div>
            </div>
            <div class="form-group col-md-4" align="right">
                <label for="searchlabel" class="col-md-5 control-label">标签</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="searchlabel" name="searchlabel" placeholder="标签">
                </div>
            </div>
        </div>
        <div class="form-group col-md-12">
            <div class="form-group col-md-4" align="right">
                <label for="searchsex" class="col-md-5 control-label">性别</label>
                <div  class="col-md-7">
                    <select id="searchsex" name="serachsex"  class="form-control" style="width: 100%" title="性别">
                        <option value="2">全部</option>
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-4" align="right">
                <label for="searchtitle" class="col-md-5 control-label">职务</label>
                <div class="col-md-7">
                    <select id="searchtitle" name="searchtitle"  class="form-control" style="width: 100%" title="职务">
                        <option value="0">全部</option>
                        <option value="1">列车长</option>
                        <option value="2">乘务员</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-4" align="right">
                <div class="col-md-5"></div>
                <div class="col-md-7">
                    <button type="button" id="searchbtn" class="btn btn-default" style="width:100%;">搜索</button>
                </div>
            </div>
        </div>

</form>
</div>
<hr>
<button style="float: right;" class='btn btn-danger' onclick="deletecustomer();">删除</button>
<button style="float: right;" class="btn btn-default" onclick="modifycustomer();">修改</button>
<button style="float: right;" class='btn btn-default' id='btn1'  onclick="window.location.href='addcustomer'">增加</button>
<table id="table" data-toggle="table">
    <thead>
    <tr>
        <th data-field=""></th>
        <th data-field="customerid" data-visible="false">ID</th>
        <th data-field="name" class="text-center">姓名</th>
        <th data-field="sex" class="text-center" data-formatter="formatter_sex">性别</th>
        <th data-field="phonenumber" class="text-center">电话</th>
        <th data-field="title" class="text-center" data-formatter="formatter_title">职务</th>
        <th data-field="label" class="text-center">标签</th>
        <th data-field="typeinpersonname" class="text-center">录入员</th>
    </tr>
    </thead>

</table>
</div>
<div class="col-md-1"></div>
<script type="text/javascript">
    $('#table').bootstrapTable({
        url: 'customerJsonList',
        method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: '10',                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "customerid",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams: function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            var temp = {
                name: $("#searchname").val(),
                phonenumber:$("#searchphonenumber").val(),
                label:$("#searchlabel").val(),
                sex:$("#searchsex").val(),
                title:$("#searchtitle").val(),
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名
                sortOrder: params.order //排位命令（desc，asc）
            };
            return temp;
        },
        columns: [{
            checkbox: true,
            visible: true
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
        },
        onDblClickRow: function (row, $element) {
            var id = row.customerid;
            $("#iframeDetail").attr("src", 'customerinfo?customerid='+id);
            $('#myModal').modal('show');
        }
    });
    //当点击搜索按钮后，表格刷新并跳到第一页
    $("#searchbtn").click(function () {
        $("#table").bootstrapTable("refreshOptions", {pageNumber: 1})
    });
    function modifycustomer() {
        if (getCheckedId() == null || getCheckedId().length == 0) {
            spop({template: '请选择一个用户！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return;
        } else if (getCheckedId().length > 1) {
            spop({template: '只能选择一个用户！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return;
        }
        $("#iframeDetail").attr("src", 'modifycustomer?customerid='+getCheckedId()[0]);
        $('#myModal').modal('show');
    }
    function deletecustomer() {
        var ids = getCheckedId();
        if (getCheckedId() == null || getCheckedId().length == 0) {
            spop({template: '请至少选择一个用户！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return;
        }
        if (!confirm("删除后不可恢复，确认删除吗？")) {
            return;
        }
        //异步更新
        $.ajax({
            type: 'post',
            url: 'deletecustomer',
            contentType: 'application/json',
            traditional: true,
            data:  JSON.stringify(ids) ,
            success: function (data) {//返回json结果
                spop({template: '删除成功！', position  : 'top-center', style: 'warning', autoclose: 2000});
                $('#table').bootstrapTable('refresh');
            },
            error: function () {// 请求失败处理函数
                spop({template: '删除失败！', position  : 'top-center', style: 'warning', autoclose: 2000});
                $('#table').bootstrapTable('refresh');
            }

        });
    }
    // <#--获取复选框选中的列的id数组-->
    function getCheckedId() {
        var arr = $("#table").bootstrapTable('getSelections');
        var ids = [];
        arr.forEach(function (val, index, arr) {
            ids[index] = val.customerid;
        });
        return ids;
    }
    function formatter_sex(value) {
        if (value == 1)
            return '男';
        if (value == 0)
            return '女';
    }
    function formatter_title(value) {
        if (value == 1)
            return '列车长';
        if (value == 2)
            return '乘务员';
    }
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:80%;height:90%;">
        <div class="modal-content">
            <%--引入详情界面--%>
                <div class="modal-body">
                    <div class="col-md-12" style="height:540px;width:100%">
                        <iframe id="iframeDetail" class="embed-responsive-item" frameborder="0" src=""
                                style="height:100%;width:100%;"></iframe>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭</button>
                </div>
        </div>
    </div>
</div>

</body>
</html>
