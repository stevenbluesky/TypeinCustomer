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
        <div class="text-center"><h1>用户信息</h1></div>
        <hr>
    </div>
    <form id="defaultForm" method="POST" class="form-horizontal">
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">姓名:</label>
                <div class="col-md-8">
                    ${customer.name}
                    <%--<input type="text" class="form-control" id="searchname" name="searchname" placeholder="姓名" value="${customer.name}">--%>
                </div>
            </div>
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">电话:</label>
                <div class="col-md-8">
                    ${customer.phonenumber}
                    <%--<input type="text" class="form-control" id="phonenumber" name="searchname" placeholder="电话" value="${customer.phonenumber}">--%>
                </div>
            </div>
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">性别:</label>
                <div class="col-md-8">
                    <c:if test="${customer.sex=='1'}">男</c:if>
                    <c:if test="${customer.sex=='0'}">女</c:if>
<%--                    <select id="sex" name="sex" class="form-control" style="width:100%">
                        <option value="1" <c:if test="${customer.sex=='1'}">selected</c:if>>男</option>
                        <option value="0" <c:if test="${customer.sex=='0'}">selected</c:if>>女</option>
                    </select>--%>
                </div>
            </div>
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">职务:</label>
                <div class="col-md-8">
                    <c:if test="${customer.title=='1'}">列车长</c:if>
                    <c:if test="${customer.title=='2'}">乘务员</c:if>
                    <%--<select id="title" name="title"  class="form-control" style="width: 100%" >
                        <option value="1" <c:if test="${customer.title=='1'}">selected</c:if>>列车长</option>
                        <option value="2" <c:if test="${customer.title=='2'}">selected</c:if>>乘务员</option>
                    </select>--%>
                </div>
            </div>
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">身份证:</label>
                <div class="col-md-8">
                    ${customer.identity}
                    <%--<input type="text" class="form-control" id="identity" name="identity" value="${customer.identity}" placeholder="身份证">--%>
                </div>
            </div>
            <div class="form-group col-md-6" align="left">
                <label class="col-md-4">邮箱:</label>
                <div class="col-md-8">
                    ${customer.mail}
                    <%--<input type="text" class="form-control" id="mail" name="searchname" value="${customer.mail}" placeholder="邮箱">--%>
                </div>
            </div>
            <div class="form-group col-md-12" align="left">
                <label  class="col-md-2">住址:</label>
                <div class="col-md-10">
                    ${customer.address}
                    <%--<input type="text" class="form-control" id="address" name="searchname" value="${customer.address}" placeholder="住址">--%>
                </div>
            </div>
        <div class="form-group col-md-12" align="left">
            <label  class="col-md-2">标签:</label>
            <div class="col-md-10">
                ${customer.label}
               <%-- <input type="text" class="form-control" id="label" name="searchname" value="${customer.label}" placeholder="标签">--%>
            </div>
        </div>
    </form>
    </div>
</div>
<div class="col-md-1"></div>
<script type="text/javascript">
    function toindex() {
        window.location.href="index";
        window.event.returnValue=false;
    }
</script>
</body>
</html>
