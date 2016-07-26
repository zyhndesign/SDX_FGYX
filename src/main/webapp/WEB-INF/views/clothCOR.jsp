    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
        <%@ include file="head.jsp"%>

    <link type="text/css" rel="stylesheet" href="resources/css/lib/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="resources/css/lib/jquery.toastmessage.css">
    <link type="text/css" rel="stylesheet" href="resources/css/src/main.css">
</head>
<body>

        <%@ include file="header.jsp"%>

        <div class="left">
        <%@ include file="menu.jsp"%>
        </div>


<div class="right">
    <div class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">新建/修改服装</h1>
            </div>
            <div class="panel-body">
                <form class="form-horizontal" id="myForm" action="style/insert" method="post">
                    <div class="form-group">
                        <label class="control-label col-md-2">图片*</label>
                        <div class="col-md-10" id="uploadContainer">
                            <a href="#" class="btn btn-success" id="uploadBtn">上传</a>
                            <p class="help-block">请上传512x700的jpg，png</p>
                            <img  id="image"  style="width:100px"
                                  src="resources/images/app/defaultPeopleImage.jpg"/>
                            <input type="hidden" id="imageUrl" name="image_url">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-2">
                            <button type="submit" class="btn btn-success form-control">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>

<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/jquery.form.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/plupload.full.min.js"></script>
<script src="resources/js/lib/qiniu.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/clothCOR.js"></script>

</body>
</html>