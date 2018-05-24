<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
	<link href="css/collections.css" rel="stylesheet">
	<div class="modal fade" id="modal-create-collections" role="dialog"
		aria-labelledby="创建快捷方式">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">创建快捷方式</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="collectionsForm" method="post"
						action="collections/create">
						<div class="form-group form-group-sm">
							<label for="title" class="col-sm-2 control-label">名称:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name" name="name"
									required>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="path" class="col-sm-2 control-label">URL:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="path" name="path"
									required>
							</div>
						</div>
						<div class="form-group form-group-sm">
							<label for="pic" class="col-sm-2 control-label">图标:</label>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary"
									onclick="uploadImgBtn.click();">
									<i class="icon-cloud-upload"></i>
								</button>
							</div>
							<div class="col-sm-5">
								<input id="uploadImgBtn" type="file" style="display: none;"
									name="file" accept="image/*" onchange="upload(this,uploadImg);" />
								<img alt="" src="" id="uploadImg">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="saveCollections">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-primary">
		<div style="margin-bottom: 10px">
			<button class="btn btn-primary" type="button" data-toggle="modal"
				id="createBtn" data-target="#modal-create-collections">
				<i class="icon-plus"></i>&nbsp;创建快捷方式
			</button>
		</div>
		<div class="panel-heading">shortcut管理</div>
		<table class="table">
			<tbody>
				<c:if test="${total%4 == 0}"><c:set value="${total/4 - 1}" var="cycles"></c:set></c:if>
				<c:if test="${total%4 != 0}"><c:set value="${total/4}" var="cycles"></c:set></c:if>
				<c:forEach begin="0" end="${cycles}" varStatus="rowNum">
					<tr>
						<c:forEach items="${collectionsList}" var="item"
							begin="${4*rowNum.index + 0}" end="${4*rowNum.index + 3}">
							<td>
								<div class="row">
								<div style="background-image:url(${item.imgData})" class="col-sm-6 col-sm-offset-3 shortcutItem">
									<span class="shortcut-remove" style="display:none" data-id="${item.id}"><i class="icon-remove icon-2x" aria-hidden="true"></i></span>
									<span class="shortcut-edit" style="display:none"><i class="icon-edit icon-2x" aria-hidden="true"></i></span>
								</div>
								</div>
								<div class="row">
								<span class="col-sm-10 col-sm-offset-1 text-center title">${item.name}</span>
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="js/service.js"></script>
	<script src="js/collections.js"></script>
</body>