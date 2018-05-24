<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>新闻</title>
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
</head>
<body>  
  <link type="text/css" href="/cms-web/css/news_sport_template.css" rel="stylesheet">
      <div class="article">
       	<h1>${title}</h1>
       	<div class="article-info"><span class="article-time">${createDate}</span><span class="article-type">${type}</span>&nbsp;作者:${author}</div>
	    <div class="article-body">${content}</div>	
      </div>
 </body>
 </html>