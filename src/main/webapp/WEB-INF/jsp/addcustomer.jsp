<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>录入信息</title>
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
        <button style="float: left;" class='btn btn-default' id='btn1'  onclick="toindex()">主页</button>
        <div class="text-right">欢迎您：
            <c:if test="${person.realName==''}">${person.personCode}</c:if>
            <c:if test="${person.realName!=''}">${person.realName}</c:if>
            <a href="logout">安全退出</a></div>
        <div class="text-center"><h1>录入用户信息</h1></div>
        <hr>
    </div>
    <form id="defaultForm" method="POST" class="form-horizontal">
        <div class="form-group col-md-12" align="">
                <label for="name" class="col-md-2  control-label">姓名<font color="red">*</font>:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
                </div>
                <label for="phonenumber" class="col-md-2 control-label">电话<font color="red">*</font>:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="phonenumber" name="phonenumber" placeholder="电话">
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="sex" class="col-md-2  control-label">性别:</label>
                <div class="col-md-4">
                    <select id="sex" name="sex"  class="form-control" style="width: 100%" title="性别">
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </div>
                <label for="title" class="col-md-2 control-label">职务:</label>
                <div class="col-md-4">
                    <select id="title" name="title"  class="form-control" style="width: 100%" title="职务">
                        <option value="2">乘务员</option>
                        <option value="1">列车长</option>
                    </select>
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="identity" class="col-md-2  control-label">身份证:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="identity" name="identity" placeholder="身份证">
                </div>
                <label for="mail" class="col-md-2 control-label">邮箱:</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="mail" name="mail" placeholder="邮箱">
                </div>
            </div>
            <div class="form-group col-md-12" align="">
                <label for="address" class="col-md-2  control-label">住址:</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="address" name="address" placeholder="住址">
                </div>
            </div>
        <div class="form-group col-md-12" align="">
            <label for="label" class="col-md-2 control-label">标签:</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="label" name="label" placeholder="标签">
            </div>
        </div>
        <div class="form-group col-md-12" align="">
            <label for="fingerprintdevice" class="col-md-2 control-label">指纹:</label>
            <div class="col-md-1 control-label" id="weiluru">未录入</div>
            <div class="col-md-3">
                <select id="fingerprintdevice" name="fingerprintdevice"  class="form-control" style="width: 100%" title="选择指纹录入器">
                </select>
            </div>
            <div class="col-md-2"><button style="float: left;" type="button" class='btn btn-success' id='s' onclick="readFingerpring()">录入指纹</button></div>
            <div class="col-md-2"><input type="hidden" name="fingerprint" id="fingerprint"/></div>
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
    var ifcouldclick =1;
    var again = 1;
    $().ready(function(){
        $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {
                name: {
                    message: 'The name is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            min: 2,
                            max: 30,
                            message: '用户名长度必须在2到30之间'
                        },
                        regexp: {
                            regexp: /^[A-Za-z0-9_\-\u4e00-\u9fa5-\s+]+$/,
                            message: '用户名只能由字母、数字、下划线、空格、点组成！'
                        }
                    }
                },
                phonenumber:{
                    message: 'The phonenumber is not valid',
                    validators: {
                        notEmpty: {
                            message: '手机号码不能为空'
                        },
                        regexp: {
                            regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))[0-9]{8}$/,
                            message: '请输入正确的手机号码'
                        }
                    }
                },
                mail: {
                    validators: {
                        emailAddress: {
                            message: '请输入正确的邮件地址如：123@qq.com'
                        }
                    }
                },
            }
        });
        getFingerDevice();

    });
    function toindex() {
        window.location.href="index";
        window.event.returnValue=false;
    }
    $("#resetform").click(function(r){
        $("input").val("");
        $("#weiluru").html("未录入");
    });
    $("#submitform").click(function(r){
        $("#defaultForm").bootstrapValidator('validate');//提交验证
        if ($("#defaultForm").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码
            $.ajax({
                type: 'post',
                url: 'savecustomer',
                traditional: true,
                data: $('#defaultForm').serialize(),
                success: function (data) {//返回json结果
                    if (data == "-1") {
                        spop({template: '请填写姓名和电话号码！', position: 'top-center', style: 'warning', autoclose: 2000});
                    } else {
                        toindex();
                    }
                },
                error: function (data) {// 请求失败处理函数
                    spop({template: '添加失败！', position: 'top-center', style: 'error',});
                }
            });
        }else{
            alert("请完善数据！");
        }
    });
    function getFingerDevice() {
        $.ajax({
            type: 'post',
            url: 'findfingerdevice',
            traditional: true,
            success: function (data) {//返回json结果
                if(data!=""&&data!=null){
                    var str ="<option value=''>选择指纹录入器</option>";
                    for (var i = 0; i < data.length; i++) {
                        str += '<option value=' + data[i].ptDeviceId + '>'+ data[i].deviceName + '</option>'
                    }
                    $("#fingerprintdevice").html(str);
                }else{
                    var str ="<option value=''>没有可用的指纹录入器</option>";
                    $("#fingerprintdevice").html(str);
                }
            },
            error: function (data) {// 请求失败处理函数
                var str ="<option value=''>没有可用的指纹录入器</option>";
                $("#fingerprintdevice").html(str);
            }
        });
    }
    function readFingerpring() {
        var zwavedeviceid= $("#fingerprintdevice").val();
        if(zwavedeviceid==""||zwavedeviceid=="undefined"){
            spop({template: '请选择指纹录入器！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return;
        }
        if(ifcouldclick==0){
            spop({template: '请录入指纹或刷新再试！', position  : 'top-center', style: 'warning', autoclose: 2000});
            return ;
        }
        ifcouldclick =0;
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
                   ifcouldclick =1;
                   spop({template: data, position  : 'top-center', style: 'warning', autoclose: 2000});
               }
            },
            error: function (data) {
                ifcouldclick =1;
                spop({template: '操作失败，请重试！', position  : 'top-center', style: 'warning', autoclose: 2000});
            }
        });
    }
    function queryStatus() {
        var repeat = 17;
        timer = setInterval(function () {
            var result = queryStatusOfReadRingerpring();
            if (repeat == 1) {
                ifcouldclick=1;
                again=1;
                clearInterval(timer);
                spop({template: "超时，采集停止", position  : 'top-center', style: 'success', autoclose: 2000});
            }
            repeat--;
            if("failed"!= result){
                ifcouldclick=1;
                again=1;
                clearInterval(timer);
                $("#fingerprint").val(result);
                $("#weiluru").html("已录入");
                spop({template: "录入成功", position  : 'top-center', style: 'success', autoclose: 2000});
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
                }else if(data.temp!=""&&data.temp!=undefined&&again==1){
                    again=2;
                    spop({template: data.temp, position  : 'top-center', style: 'success', autoclose: 2000});
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
