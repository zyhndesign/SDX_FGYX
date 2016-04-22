    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>
        <head>
        <%@ include file="head.jsp"%>
    <link href="resources/css/lib/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="resources/css/lib/jquery.dataTables.css" rel="stylesheet" type="text/css">
    <link href="resources/css/lib/jquery.toastmessage.css" rel="stylesheet" type="text/css">
    <link href="resources/css/src/main.css" rel="stylesheet" type="text/css">
</head>
<body>

        <%@ include file="header.jsp"%>

        <div class="left">
        <%@ include file="menu.jsp"%>
        </div>


<section class="right">
    <article class="main">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title">用户选择</h1>
            </div>
            <div class="panel-body" id="opt-body">
                <c:forEach items="${list}" var="l">
                    <img src="${l.imageUrl}">
                </c:forEach>
            </div>
        </div>
    </article>
</section>



<div class="loading hidden" id="loading">
    <span class="text">Loading...</span>
</div>


<script src="resources/js/lib/jquery-2.0.3.min.js"></script>
<script src="resources/js/lib/bootstrap.min.js"></script>
<script src="resources/js/lib/jquery.dataTables.min.js"></script>
<script src="resources/js/lib/jquery.validate.min.js"></script>
<script src="resources/js/lib/jquery.form.js"></script>
<script src="resources/js/lib/jquery.toastmessage.js"></script>
<script src="resources/js/src/config.js"></script>
<script src="resources/js/src/functions.js"></script>
<script src="resources/js/src/userMgr.js"></script>

</body>
</html>