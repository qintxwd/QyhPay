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
<title>用户管理--管理员界面</title>

<script type="text/javascript" src="jquery-2.1.4/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.4/themes/color.css">
<script type="text/javascript"
	src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>


<script type="text/javascript">
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加商户');
		$('#fm').form('clear');
		url = 'admin/addUser';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('center').dialog('setTitle',
					'编辑商户信息');
			$('#fm').form('load', row);
			url = 'admin/updateUser?id=' + row.id;
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.errorMsg) {
					$.messager.show({
						title : 'Error',
						msg : result.errorMsg
					});
				} else {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	}
	function destroyUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认删除',
					'确定要删除？？？', function(r) {
						if (r) {
							$.post('admin/deleteUser', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
										msg : result.errorMsg
									});
								}
							}, 'json');
						}
					});
		}
	}
	$(function() {

	});
</script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>


</head>
<body>
	<h2>商户管理</h2>

	<table id="dg" title="商户信息列表" class="easyui-datagrid"
		style="width: 1024px; height: 450px" url="admin/getUserList"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="username">用户名</th>
				<th field="password">密码</th>
				<th field="partner">支付宝partner</th>
				<th field="pid">支付宝pid</th>
				<th field="md5key">支付宝md5key</th>
				<th field="seller_email">支付宝seller_email</th>
				<th field="seller_id">支付宝seller_id</th>
				<th field="appid">微信appid</th>
				<th field="key">微信key</th>
				<th field="mch_id">微信mch_id</th>
				<th field="secret">微信secret</th>
				<th field="machines">机器</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">添加</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
	</div>

	<!-- 以下为弹出的添加商户的弹出框 -->
	<div id="dlg" class="easyui-dialog"
		style="width: 520px; height: 520px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">请填写商户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>用户名:</label> <input name="username" class="easyui-textbox"
					required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-textbox"
					required="true">
			</div>
			<hr />
			<div class="fitem">
				<label>支付宝partner:</label> <input name="partner"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>支付宝pid:</label> <input name="pid" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>支付宝md5key:</label> <input name="md5key"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>支付宝seller_email:</label> <input name="seller_email"
					class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>支付宝seller_id:</label> <input name="seller_id"
					class="easyui-textbox">
			</div>
			<hr />
			<div class="fitem">
				<label>微信appid:</label> <input name="appid" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>微信key:</label> <input name="key" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>微信mch_id:</label> <input name="mch_id" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>微信secret:</label> <input name="secret" class="easyui-textbox">
			</div>
			<hr />
			<div class="fitem">
				<label>机器列表:</label>
			</div>
			<div class="fitem">
				<label></label> <input name="machines" class="easyui-textbox">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()"
			style="width: 90px">保存</a> <a class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>

</body>
</html>