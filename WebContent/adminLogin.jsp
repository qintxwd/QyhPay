<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
body {
	background: #7f9b4e url(images/bg2.jpg) no-repeat center top;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
}

.container>header h1, .container>header h2 {
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.7);
}
</style>

</head>

<body>

	<div class="container">
		<section class="main">
		<form class="form-4" action="admin/login" method="post">
			<h1>管理员登录</h1>
			<p>
				<label for="username">请输入您的用户名</label> <input type="text"
					name="username" placeholder="请输入您的用户名" required="required">
			</p>
			<p>
				<label for="password">请输入您的用户密码</label> <input type="password"
					name='password' placeholder="请输入您的用户密码" required="required">
			</p>

			<p>
				<input type="submit" name="submit" value="登录">
			</p>
			<div class="error">
				<span id="errorinfo"></span>
			</div>
		</form>
		</section>
	</div>

</body>

</html>