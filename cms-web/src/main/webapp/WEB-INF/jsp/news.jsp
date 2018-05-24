<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<link href="css/news.css" rel="stylesheet">
	<link href="css/ckCustomerStyle.css" rel="stylesheet">
	<script src="ckeditor/ckeditor.js"></script>
	<div class="modal fade" id="modal-create-news" role="dialog"
		aria-labelledby="创建新闻">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">创建新闻</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="newsForm" method="post"
						action="news/save">
						<input type="hidden" id="doc" name="doc">
						<input type="hidden" id="createDate" name="createDate">
						<div class="form-group form-group-sm">
							<label for="news-title" class="col-sm-2 control-label">标题:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="title" name="title"
									required>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="news-type" class="col-sm-2 control-label">新闻类别:</label>
							<div class="col-sm-5">
								<select class="form-control" id="type" name="type">
									<option selected value="SPORTS">体育新闻</option>
									<option value="SOCIAL">社会新闻</option>
								</select>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="news-content" class="col-sm-2 control-label">内容:</label>
							<div class="col-sm-10">
								<textarea class="form-control" id="content" rows="3"
									name="content" required></textarea>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="news-author" class="col-sm-2 control-label">作者:</label>
							<input type="text" class="form-control-static" id="author"
								name="author" value="张松" readonly />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="newsReviewBtn"
						data-toggle="modal" data-target="#newsReviewModal">预览</button>
					<button type="button" class="btn btn-primary" id="saveNews">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="newsReviewModal" tabindex="-1"
		role="dialog" aria-labelledby="newsReviewModal" aria-hidden="true"></div>
	<div class="search-wraper">
		<form class="form-inline" role="form">
			<div class="form-group">
				<input class="form-control search clearable" placeholder="输入关键字"
					autocomplete="off" autofocus autocorrect="off" autocapitalize="off"
					spellcheck="false"> <a href="#" class="search-icon"> <span
					class="glyphicon glyphicon-search"></span>
				</a>
			</div>
			<button class="btn btn-default" type="button" data-toggle="modal"
				id="createBtn">创建新闻</button>
		</form>
	</div>
	<div class="panel panel-primary">
		<div class="panel-heading">
			新闻管理&nbsp;<span class="badge">${totalNews}</span>
		</div>
		<div class="panel-body">
			<button class="btn btn-success" type="button">
				已发布&nbsp;<span class="badge">${publishedCount}</span>
			</button>
			<button class="btn btn-danger" type="button">
				待发布&nbsp;<span class="badge">${newStatusCount}</span>
			</button>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>标题</th>
					<th>新闻类别</th>
					<th>作者</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${newsList}" var="news">
					<tr>
						<td><a href="news/review/doc?name=${news.doc}"
							target="_blank">${news.title}</a></td>
						<td><c:if test="${news.type eq 'SPORTS'}">
								<span>体育新闻</span>
							</c:if> <c:if test="${news.type eq 'SOCIAL'}">
								<span>社会新闻</span>
							</c:if></td>
						<td>${news.author}</td>
						<td>${news.createDate}</td>
						<td>${news.updateDate}</td>
						<td><c:if test="${news.status eq 'NEW'}">
								<button class="btn btn-danger" type="button">待发布</button>
							</c:if> <c:if test="${news.status eq 'PUBLISHED'}">
								<button class="btn btn-success" type="button">已发布</button>
							</c:if></td>
						<td>
							<button type="button"
								style="background-color: transparent; border: 0;"
								class="editorNews" data-news-doc="${news.doc}">
								<span class="glyphicon glyphicon-edit"></span>
							</button> &nbsp;
							<button type="button"
								style="background-color: transparent; border: 0;"
								class="deleteNews" data-news-doc="${news.doc}">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align: center">
		<ul class="pagination">
			<li class="previous"><a href="javascript:void(0);"
				id="previousPage" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
			<c:forEach begin="1" end="${totalPages}" var="index">
				<c:if test="${currentPageIndex == index}">
					<li class="active pageIndex"><a href="javascript:void(0);">${index}</a></li>
				</c:if>
				<c:if test="${currentPageIndex != index}">
					<li class="pageIndex"><a href="javascript:void(0);">${index}</a></li>
				</c:if>
			</c:forEach>
			<li class="next"><a href="javascript:void(0);" id="nextPage"
				aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			<li style="margin-left: 10px;"><div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span id="pageSize">${pageSize}</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pageSize">
						<li><a href="javascript:void(0);">10</a></li>
						<li><a href="javascript:void(0);">20</a></li>
						<li><a href="javascript:void(0);">50</a></li>
					</ul>
				</div></li>
		</ul>
		<form id="pageForm" method="post" action="news/save"></form>
	</div>
	<script>
    CKEDITOR.replace('content', {
      customConfig: '../js/ckCustomerConfig.js'
    });
  </script>
	<script>
    var currentPage = ${currentPageIndex};
    var totalPages = ${totalPages};
    if (currentPage == 1) {
      $(".pagination li.previous").addClass("disabled");
    }
    if (currentPage == totalPages) {
      $(".pagination li.next").addClass("disabled");
    }
  </script>
	<script src="js/news.js"></script>
</body>