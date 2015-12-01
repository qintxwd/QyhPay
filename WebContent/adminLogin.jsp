<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">

<head>
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<div class="page-container">
		<h1>管理员登录</h1>
		<form action="admin/login" method="post">
			<input type="text" name="username" class="username"
				placeholder="请输入您的用户名！" required="required"> <input
				type="password" name="password" class="password"
				placeholder="请输入您的用户密码！" required="required">
			<button type="submit" class="submit_button">登录</button>
			<div class="error">
				<span id="errorinfo">+</span>
			</div>
		</form>
	</div>

	<!-- Javascript -->
	<script src="js/jquery-1.8.2.min.js"></script>
	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<script src="js/scripts.js"></script>

</body>

</html>