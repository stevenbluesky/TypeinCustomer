<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="static/js/spop.js"></script>
    <link rel="stylesheet" href="static/css/spop.css">
    <script src="static/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>欢迎</h1>
            <form class="form" id="defaultForm" method="post">
                <input type="text" name="personCode" placeholder="用户名">
                <input type="password" name="personPassword" placeholder="密码" onkeydown=KeyDown()>
                <button type="button" id="login-button">登录</button>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        loginInterceptor();
    });
    $("#login-button").click(function(r){
        $.ajax({
            type: 'post',
            url: 'checklogin',
            traditional: true,
            data:  $('#defaultForm').serialize() ,
            success: function (data) {//返回json结果
                if(data=="unregistered"){
                    spop({template: '用户名或密码错误！', position  : 'top-center', style: 'warning', autoclose: 2000});
                }else if(data=="success") {
                    window.location.href="index";
                    window.event.returnValue=false;
                }else{
                    spop({template: '登录失败！', position  : 'top-center', style: 'error'});
                }
            },
            error: function (data) {// 请求失败处理函数
                spop({template: '登录失败！', position  : 'top-center', style: 'error'});
            }
        });
    });
    function KeyDown() {
        if (event.keyCode == 13) {
            event.returnValue=false;
            event.cancel = true;
            $("#login-button").click();
        }
    }
    function loginInterceptor() {
        if (document != window.self.parent.document) {
            window.self.parent.document.location.reload();
        }
    }
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
    <h1>客户信息录入平台</h1>
</div>
</body>
</html>
