<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>CMS</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Pragma" content="no-cache" /> 
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="robots" content="noindex" />
<link type="text/css" href="<%=request.getContextPath()%>/vendor/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link type="text/css" href="<%=request.getContextPath()%>/css/common.css"
	rel="stylesheet">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/image/favicon.jpg" type="image/x-icon">
</head>
<body>
	<script src="<%=request.getContextPath()%>/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendor/jquery/jquery.form.js"></script>
	<script src="<%=request.getContextPath()%>/vendor/bootstrap/js/bootstrap.js"></script>
	<div id="loading-over" class="loading-over"></div>
	<div id="loading" class="loading">
        <div><span></span></div>
        <div><span></span></div>
        <div><span></span></div>
        <div><span></span></div>
	</div>
	<jsp:include page="header.jsp"/>
	<section class="container-fluid">
		<sitemesh:write property='body' />
	</section>
	<footer style="border-top: 1px solid"><jsp:include page="footer.jsp"/></footer>
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
</body>
</html>