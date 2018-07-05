<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <script src="js/jquery.min.js"></script>
    <script src="js/spop.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="css/spop.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/bootstrap-table.min.css" rel="stylesheet">
    <link href="css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrapValidator.min.js"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/bootstrap-select.min.js"></script>
    <script src="js/bootstrap-table.min.js"></script>
</head>
<body>
<div class="col-md-1"></div>
<div class="col-md-10">
    <div class="row">
    <div class="form-group">
        <%--<button style="float: left;" class='btn btn-default' id='btn1'  onclick="toindex()">主页</button>--%>
        <div class="text-center"><h1>修改用户信息</h1></div>
        <hr>
    </div>
    <form id="defaultForm" method="POST" class="form-horizontal">
            <div class="form-group col-md-12" align="">
                <label for="name" class="col-md-2  control-label">姓名<font color="red">*</font>:</label>
                <div class="col-md-4">
                    <input type="hidden" class="form-control" id="customerid" name="customerid" placeholder="用户id" value="${customer.customerid}">
                    <input type="text" class="form-control" id="name" name="name" placeholder="姓名" value="${customer.name}">
                </div>
                <label for="phonenumber" class="col-md-2 control-label">电话<font color="red">*</font>:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="电话" value="${customer.phonenumber}">
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="sex" class="col-md-2  control-label">性别:</label>
                <div class="col-md-4">
                    <select id="sex" name="sex" class="form-control" style="width:100%">
                        <option value="1" <c:if test="${customer.sex=='1'}">selected</c:if>>男</option>
                        <option value="0" <c:if test="${customer.sex=='0'}">selected</c:if>>女</option>
                    </select>
                </div>
                <label for="title" class="col-md-2 control-label">职务:</label>
                <div class="col-md-4">
                    <select id="title" name="title"  class="form-control" style="width: 100%" >
                        <option value="1" <c:if test="${customer.title=='1'}">selected</c:if>>列车长</option>
                        <option value="2" <c:if test="${customer.title=='2'}">selected</c:if>>乘务员</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="identity" class="col-md-2  control-label">身份证:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="identity" name="identity" value="${customer.identity}" placeholder="身份证">
                </div>
                <label for="mail" class="col-md-2 control-label">邮箱:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="mail" name="mail" value="${customer.mail}" placeholder="邮箱">
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="address" class="col-md-2  control-label">住址:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="address" name="address" value="${customer.address}" placeholder="住址">
                </div>
            </div>
        <div class="form-group col-md-12" align="">
            <label for="label" class="col-md-2 control-label">标签:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="label" name="label" value="${customer.label}" placeholder="标签">
            </div>
        </div>
        <div class="form-group col-md-12" align="">
            <label for="fingerprintdevice" class="col-md-2 control-label">指纹:</label>
            <div class="col-md-1 control-label" id="weiluru"><c:if test="${customer.fingerprint==''}">未采集</c:if><c:if test="${customer.fingerprint!=''}">已采集</c:if></div>
            <div class="col-md-3">
                <select id="fingerprintdevice" name="fingerprintdevice"  class="form-control" style="width: 100%" title="选择指纹录入器">
                </select>
            </div>
            <div class="col-md-2"><button style="float: left;" type="button" class='btn btn-success' id='s' onclick="readFingerpring()">录入指纹</button></div>
            <div class="col-md-2"><input type="hidden" name="fingerprint" id="fingerprint" value="${customer.fingerprint}"/></div>
        </div>
        <br/>
        <div class="form-group col-md-6 row text-center" align="right">
            <button style="width:25%;" type="button" class='btn btn-default' id='submitform'>确认</button>
        </div>
        <div class="form-group col-md-6 row text-center" align="right">
            <button style="width:25%;" type="button" class='btn btn-default' id='resetform'>清除</button>
        </div>
    </form>
    </div>
</div>
<div class="col-md-1"></div>
<script type="text/javascript">
    $().ready(function(){
        $("#defaultForm").validate({
            rules : {//rules 用于对表单内容进行约束
                name : {
                    required:true,
                    rangelength:[2,20]
                },
                phonenumber:{
                    required:true,
                    number:true
                },
                mail : {
                    email : true//email :true 验证是否是邮箱格式
                }

            },
            messages : {
                name : {
                    required:"请输入用户名",
                    rangelength:"输入长度范围为{0}到{1}"
                },
                phonenumber:{
                    required:"请输入手机号码",
                    number:"手机号码格式不对"
                },
                mail : {
                    email : "邮箱格式不正确"
                }
            }
        });
        getFingerDevice();
    });
    $("#resetform").click(function(r){
        $("input").val("");
        $("#weiluru").html("未录入");
    });
    $("#submitform").click(function(r){
        $.ajax({
            type: 'post',
            url: 'savecustomer',
            traditional: true,
            data:  $('#defaultForm').serialize() ,
            success: function (data) {//返回json结果
                if(data=="-1"){
                    spop({template: '请填写姓名和手机号码！', position  : 'top-center', style: 'warning', autoclose: 2000});
                }else {
                    parent.location.href = parent.location.href;
                }
            },
            error: function (data) {
                spop({template: '修改失败！', position  : 'top-center', style: 'error',});
            }
        });
    });
    function getFingerDevice() {
        $.ajax({
            type: 'post',
            url: 'findfingerdevice',
            traditional: true,
            success: function (data) {//返回json结果
                var str ="<option value=''>选择指纹录入器</option>";
                for (var i = 0; i < data.length; i++) {
                    str += '<option value=' + data[i].ptDeviceId + '>'+ data[i].deviceName + '</option>'
                }
                $("#fingerprintdevice").html(str);
            },
            error: function (data) {// 请求失败处理函数

            }
        });
    }
    function readFingerpring() {
        var zwavedeviceid= $("#fingerprintdevice").val();
        if(zwavedeviceid==""||zwavedeviceid=="undefined"){
            spop({template: '请选择指纹录入器！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return;
        }
        $.ajax({
            type: 'post',
            url: 'readfingerpring',
            traditional: true,
            data:{
                zwavedeviceid:zwavedeviceid
            },
            success: function (data) {//返回json结果
                if(data=="success"){
                    queryStatus();
                }else{
                    spop({template: data, position  : 'top-center', style: 'warning', autoclose: 2000});
                }
            },
            error: function (data) {
                spop({template: '操作失败，请重试！', position  : 'top-center', style: 'warning', autoclose: 2000});
            }
        });
    }
    function queryStatus() {
        timer = setInterval(function () {
            var result = queryStatusOfReadRingerpring();
            if("failed"!= result){
                clearInterval(timer);
                $("#fingerprint").val(result);
                $("#weiluru").html("已录入");
            }
        },1000);

    }
    function queryStatusOfReadRingerpring() {
        var flag = "false";
        var msg = "";
        $.ajax({
            type: 'post',
            url: 'querystatusofreadfingerpring',
            traditional: true,
            async : false,
            data:{
                zwavedeviceid:$("#fingerprintdevice").val()
            },
            success: function (data) {
                if(data.success!=""&&data.success!=undefined){
                    flag = "true";
                    msg = data.success;
                }else{
                }
            },
            error: function (data) {
            }
        });
        if(flag=="true"){
            return msg;
        }else{
            return "failed";
        }
    }
</script>
</body>
</html>
