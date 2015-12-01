<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>

<link rel="stylesheet" href="jquery-ui-1.11.4/jquery-ui.min.css">
<script type="text/javascript" src="jquery-2.1.4/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="jquery-ui-1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="jquery-ui-1.11.4/jquery-ui.theme.min.css">
<link rel="stylesheet"
	href="jquery-ui-1.11.4/jquery-ui.structure.min.css">

<script type="text/javascript">
	$(function() {

		$("#dialog").dialog({
			autoOpen : false,
			width : 400,
			buttons : [ {
				text : "Ok",
				click : function() {
					$(this).dialog("close");
				}
			}, {
				text : "Cancel",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
		$("#button").click(function(event) {
			$("#dialog").dialog("open");
			event.preventDefault();
		});
		$("#button").button();
		//请求用户
		$
				.ajax(
						{
							url : "admin/getUserList",
							type : 'GET',
							timeout : 10000,
							//cache : false,
							data : {
								"page" : 1
							},
							dataType : "json",
							beforeSend : function() {
								$("#info").html("正在加载");
							},
							error : function() {
								$("#info").html("载入出错");
							},
							success : function(data) {
								$("#info").hide();
								var users = data["users"];
								var i;
								for (i = 0; i < users.length; i++) {
									var t = "<h3>" + users[i].username
											+ "     密码:" + users[i].password
									"</h3>";
									var x = "<div><table><tr><td>支付宝账号:</td><td>aaa</td><td>appid:</td><td>appid</td></tr><tr><td>微信账号:</td><td>bbb</td><td>appid</td><td>appid</td></tr><tr><td>机器列表:</td><td><td></tr><tr><td></td><td>售卖机器人</td><tr></table></div>";
									$("#accordion").append(t);
									$("#accordion").append(x);
								}
								$("#accordion").accordion({
									collapsible : true
								});
							},
						}).done(function() {
					$(this).addClass("done");
				});
	});
</script>
</head>
<body>
	<h1>商户管理</h1>
	<button id="button">添加商户</button>
	<ul id="info"></ul>
	<div id="accordion"></div>
	<div id="dialog" title="Dialog Title">
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
			do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
			enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
			ut aliquip ex ea commodo consequat.</p>
	</div>
</body>

</html>