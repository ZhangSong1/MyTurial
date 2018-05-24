<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="vendor/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="vendor/Font-Awesome-3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/login.css">
<link rel="shortcut icon" href="image/favicon.jpg">
</head>
<body>
	<header> </header>
	<section class="container-fluid">
		<div class="row background-login">
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-2">
						<canvas id="clock" width="120%" height="120%" style></canvas>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="panel-title text-center">
									<h3 class="logInShow">Log In</h3>
									<h3 class="signUpShow" style="display: none">Sign Up</h3>
								</div>
								<div style="text-align: center" class="logInShow">
									New to CMS? <a style="color: inherit; cursor: pointer;"
										href="javascript:void(0);" id="signUpLink">Sign Up</a>
								</div>
								<div style="text-align: center; display: none"
									class="signUpShow">
									Already have a CMS account? <a
										style="color: inherit; cursor: pointer;"
										href="javascript:void(0);" id="logInLink">Log In</a>
								</div>
							</div>
							<div class="panel-body">
							
								<div class="logInShow">
								<c:if test="${not empty error}"><label class="server_error">${error}</label></c:if>
									<form class="form-horizontal" id="loginForm" action="loginAction"
										method="POST">
										<div class="form-group">
											<div class="input-group">
												<input type="text" class="form-control" name="email"
													id="username" placeholder="Email"
													aria-describedby="pepole-addon" />
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<input class="form-control" type="password" name="password"
													id="password" placeholder="Password"
													aria-describedby="pwd-addon" />
											</div>
										</div>
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>
									<div class="row text-right margin-bottom">
										<div class="col-sm-12">
											<input id="rememberMe" name="rememberMe" type="checkbox" checked="checked"/>Auto Login
											<a href="ui/forgotPwd" target="_self">Forgot Password?</a>
										</div>
									</div>

									<div class="row margin-bottom">
										<div class="col-sm-12">
											<a href="javascript:void(0);"
												class="btn btn-primary largeBtn" id="login">Log In</a>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<label
												style="padding-left: 12px; padding-top: 10px; font-weight: normal;">Or
												log in with</label>
											<div class="socialIcon"></div>
										</div>
									</div>
								</div>
								<div class="signUpShow" style="display: none">
									<form class="form-horizontal" id="signUpForm"
										action="ui/signUp" method="post">
										<div class="form-group">
											<div class="input-group">
												<input type="text" class="form-control" name="username"
													id="signup_username" placeholder="Email"
													aria-describedby="pepole-addon" />
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<input class="form-control" type="password" name="password"
													id="signup_password" placeholder="Password"
													aria-describedby="pwd-addon" />
											</div>
										</div>
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form>
									<div class="row margin-bottom">
										<div class="col-sm-12">
											<a href="javascript:void(0);"
												class="btn btn-primary largeBtn" id="signUp">Sign Up</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/hideshowpassword/hideShowPassword.js"></script>
	<script src="js/clock.js"></script>
	<script src="js/common.js"></script>
	<script src="js/login.js"></script>
</body>
</html>