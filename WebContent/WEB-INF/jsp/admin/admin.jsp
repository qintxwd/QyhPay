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
		var addMachineNotBlankIndex = 0; //-1代表没有空白  0 - n代表 id为n的为空白输入框
		function getNext(prefix) {
			s = parseInt(prefix.substring(10, prefix.length)) + 1;
			next = "addmachine" + s.toString();
			return next;
		}
		$("body")
				.on(
						"keyup",
						".addmachine",
						function() {
							if ($(this).val() != "") {
								if ($(
										"#addmachine"
												+ addMachineNotBlankIndex
														.toString()).val() != "") {
									//add 并且指向新添加的addMachineNotBlankIndex = add
									//添加
									next = "addmachine"
											+ (maxAddMachineIndex + 1)
													.toString();
									newTr = "<tr><td><label>添加机器:</label></td><td><input type=\"text\" required=\"required\" class=\"addmachine\" id=\""+next+"\" name=\""+next+"\"></td></tr>";
									$("#addUserTable").append(newTr);
									maxAddMachineIndex++;
									addMachineNotBlankIndex = maxAddMachineIndex;
								} else {
									//donothing
								}
							} else {
								if ($(this).attr('id') == ("addmachine" + addMachineNotBlankIndex
										.toString())) {
									//本来就是空的donothing
								} else {
									//删除原来的 //指向新的kongbai
									$(
											"#addmachine"
													+ addMachineNotBlankIndex
															.toString())
											.parent().parent().remove();
									var nowid = $(this).attr('id');
									addMachineNotBlankIndex = parseInt(nowid
											.substring(10, nowid.length));
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
							if ($("#" + id).val() != "")
								machineNames[j++] = $("#" + id).val();
						}
					}
					$.ajax({
						url : "admin/addUser",
						type : 'GET',
						traditional : true,
						contentType : 'application/json;charset=UTF-8',
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
									//组成机器列表
									var ml = "";
									var j = 0;
									if (users.length <= 0)
										ml = "<td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td><td width='9%' align='center'></td>";
									else {
										for (j = 0; j < users[i].machines.length; j++) {
											ml += "<td width='9%' align='center'>"
													+ users[i].machines[j].name
													+ "</td>"
											if (j % 10 == 9) {
												ml += "</tr><tr><td  width='9%' align='center'></td>"
											}
										}
										if (j % 10 == 0) {
											ml = ml.substring(0, ml.length - 4);
										} else {
											for (; j % 10 != 0; j++) {
												ml += "<td width='9%' align='center'></td>"
											}
											ml += "</tr>"
										}
									}
									var t, x;
									if (users[i].username != null) {
										t = "<h3>" + users[i].username
												+ "</h3>";
									} else {
										t = "<h3></h3>";
									}
									x = "<div><table  style='border-collapse:collapse;'><tr><th align='center' width='9%'>用户名</th><th align='center'  width='9%'>密码</th><th align='center'  width='9%'>支付宝email</th><th align='center'  width='9%'>支付宝id</th><th width='9%' align='center' >支付宝pid</th><th width='9%' align='center' >支付宝partner</th><th width='9%' align='center' >支付宝md5key</th><th width='9%' align='center' >微信mch_id</th><th width='9%'>微信appid</th><th width='9%' align='center' >微信key</th><th width='9%' align='center' >微信secret</th></tr><tr><td width='9%' align='center' >";
									if (users[i].username != null) {
										x += users[i].username;
									}
									x += "</td><td width='9%' align='center' >";
									if (users[i].password != null)
										x += users[i].password;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userAliInfo != null
											&& users[i].userAliInfo.seller_email != null)
										x += users[i].userAliInfo.seller_email;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userAliInfo != null
											&& users[i].userAliInfo.seller_id != null)
										x += users[i].userAliInfo.seller_id;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userAliInfo != null
											&& users[i].userAliInfo.pid != null)
										x += users[i].userAliInfo.pid;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userAliInfo != null
											&& users[i].userAliInfo.partner != null)
										x += users[i].userAliInfo.partner;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userAliInfo != null
											&& users[i].userAliInfo.md5key != null)
										x += users[i].userAliInfo.md5key;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userWXInfo != null
											&& users[i].userWXInfo.mch_id != null)
										x += users[i].userWXInfo.mch_id;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userWXInfo != null
											&& users[i].userWXInfo.appid != null)
										x += users[i].userWXInfo.appid;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userWXInfo != null
											&& users[i].userWXInfo.key != null)
										x += users[i].userWXInfo.key;
									x += "</td><td width='9%' align='center' >";
									if (users[i].userWXInfo != null
											&& users[i].userWXInfo.secret != null)
										x += users[i].userWXInfo.secret;
									x += "</td></tr><tr><td colspan=\"11\" style=\"border-bottom: 1px solid #000\"></td></tr><tr><td>机器列表</td>";
									x += ml + "</table>";
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