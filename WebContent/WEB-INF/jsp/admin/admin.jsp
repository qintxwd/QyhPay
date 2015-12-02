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
		var maxAddMachineIndex = 0;
		var hasContent = false;
		function getNext(prefix) {
			s = parseInt(prefix.substring(10, prefix.length)) + 1;
			next = "addmachine" + s.toString();
			return next;
		}
		$("body").on("keydown", ".addmachine", function() {
			if ($(this).val() != "") {
				hasContent = true;
			} else {
				hasContent = false;
			}
		});
		$("body")
				.on(
						"keyup",
						".addmachine",
						function() {
							if ($(this).val() != "") {
								if (hasContent) {
									//donothing
								} else {
									//添加内容
									next = "addmachine"
											+ (maxAddMachineIndex + 1)
													.toString();
									newTr = "<tr><td><label>添加机器:</label></td><td><input type=\"text\" required=\"required\" class=\"addmachine\" id=\""+next+"\" name=\""+next+"\"></td></tr>";
									$("#addUserTable").append(newTr);
									maxAddMachineIndex++;
								}
							} else {
								if (hasContent) {
									//删除最大的
									$("#" + maxAddMachineIndex).parent()
											.parent().remove();
									maxAddMachineIndex--;
								} else {
									//donothing
								}
							}
						});

		$("body")
				.on(
						"keyup",
						".addmachine",
						function() {
							if ($(this).val() != "") {
								var idstr = $(this).attr('id');
								next = getNext(idstr);
								needadd = true;
								while ($("#" + next).length) {
									if ($("#" + next).val() != "") {
										next = getNext(next);
										continue;
									} else {
										needadd = false;
										break;
									}
								}
								if (needadd) {
									newTr = "<tr><td><label>添加机器:</label></td><td><input type=\"text\" required=\"required\" class=\"addmachine\" id=\""+next+"\" name=\""+next+"\"></td></tr>";
									$("#addUserTable").append(newTr);
								}
							} else {
								var idstr = $(this).attr('id');
								next = getNext(idstr);
								while ($("#" + next).length) {
									if ($("#" + next).val() != "") {
										next = getNext(next);
										continue;
									} else {
										$("#" + next).parent().parent()
												.remove();
										break;
									}
								}
							}
						});

		$("#dialog").dialog({
			autoOpen : false,
			width : 400,
			buttons : [ {
				text : "添加",
				click : function() {
					var machineNames = new Array();
					j = 0;
					for (i = 0; i <= maxAddMachineIndex; i++) {
						id = "addmachine" + (i).toString();
						if ($("#" + id).length) {
							if($("#" + id).val()!="")
								machineNames[j++] = $("#" + id).val();
						}
					}
					$.ajax({
						url : "admin/addUser",
						type : 'GET',
						timeout : 10000,
						traditional : true,
						data : {
							"username" : $("#add-username").val(),
							"password" : $("#add-password").val(),
							"partner" : $("#add-partner").val(),
							"pid" : $("#add-pid").val(),
							"md5key" : $("#add-md5key").val(),
							"seller_email" : $("#add-seller_email").val(),
							"seller_id" : $("#add-seller_id").val(),
							"appid" : $("#add-appid").val(),
							"key" : $("#add-key").val(),
							"mch_id" : $("#add-mch_id").val(),
							"secret" : $("#add-secret").val(),
							"machines" : machineNames,
						},
						dataType : "text",
						beforeSend : function() {
							$("#add-info").html("正在处理...");
						},
						error : function() {
							$("#add-info").html("发生错误...");
						},
						success : function(data) {
							//alert(data);
							if ("添加成功" == data) {
								//load();
								$("#add-info").html("");
								$("#dialog").dialog("close");
							} else
								$("#add-info").html(data);
						},
					});
				}
			}, {
				text : "取消",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
		$("#button")
				.click(
						function(event) {
							//清除原来的内容
							$(".addmachine").parent().parent().remove();
							//添加新的内容
							$("#addUserTable")
									.append(
											"<tr><td><label>添加机器:</label></td><td><input type=\"text\" required=\"required\" class=\"addmachine\" id=\"addmachine0\" name=\"addmachine0\"></td></tr>");
							maxAddMachineIndex = 0;
							hasContent = false;
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
											+ "</h3>";
									var x = "<div><table><tr><td>用户名:</td><td>"
											+ users[i].username
											+ "</td><td>密码:</td><td>"
											+ users[i].password
											+ "</td></tr><tr><tr><td>支付宝账号:</td><td id=\"ali\">aaa</td><td>appid:</td><td>appid</td></tr><tr><td>微信账号:</td><td>bbb</td><td>appid</td><td>appid</td></tr><tr><td>机器列表:</td><td><td></tr><tr><td></td><td>售卖机器人</td><tr></table></div>";
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
	<div id="dialog" title="添加商户">
		<table id="addUserTable">
			<tr>
				<td><label for="username">用户名:</label></td>
				<td><input type="text" name="username" required="required"
					id="add-username"></td>
			</tr>
			<tr>
				<td><label for="password">密 码:</label></td>
				<td><input type="password" name='password' required="required"
					id="add-password"></td>
			</tr>
			<tr>
				<td colspan="2" style="border-bottom: 1px solid #000"></td>
			</tr>
			<tr>
				<td><label for="partner">支付宝partner:</label></td>
				<td><input type="text" name='partner' required="required"
					id="add-partner"></td>
			</tr>
			<tr>
				<td><label for="pid">支付宝pid:</label></td>
				<td><input type="text" name='pid' required="required"
					id="add-pid"></td>
			</tr>
			<tr>
				<td><label for="md5key">支付宝md5key:</label></td>
				<td><input type="text" name='md5key' required="required"
					id="add-md5key"></td>
			</tr>
			<tr>
				<td><label for="seller_email">支付宝seller_email:</label></td>
				<td><input type="text" name='seller_email' required="required"
					id="add-seller_email"></td>
			</tr>
			<tr>
				<td><label for="seller_id">支付宝seller_id:</label></td>
				<td><input type="text" name='seller_id' required="required"
					id="add-seller_id"></td>
			</tr>
			<tr>
				<td colspan="2" style="border-bottom: 1px solid #000"></td>
			</tr>
			<tr>
				<td><label for="appid">微信appid:</label></td>
				<td><input type="text" name='appid' required="required"
					id="add-appid"></td>
			</tr>
			<tr>
				<td><label for="key">微信key:</label></td>
				<td><input type="text" name='key' required="required"
					id="add-key"></td>
			</tr>
			<tr>
				<td><label for="mch_id">微信mch_id:</label></td>
				<td><input type="text" name='mch_id' required="required"
					id="add-mch_id"></td>
			</tr>
			<tr>
				<td><label for="secret">微信secret:</label></td>
				<td><input type="text" name='secret' required="required"
					id="add-secret"></td>
			</tr>
			<tr>
				<td colspan="2" style="border-bottom: 1px solid #000"></td>
			</tr>
			<tr>
				<td><label>添加机器:</label></td>
				<td><input type="text" required="required" class="addmachine"
					id="addmachine0" name="addmachine0"></td>
			</tr>
		</table>
		<ul id="add-info"></ul>
	</div>
</body>

</html>