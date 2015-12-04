<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
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
<title>塔米支付宝交易接口(即时到账)</title>
</head>
<%
	String ip = request.getHeader("x-forwarded-for");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}
%>
<script type="text/javascript" src="jquery-2.1.4/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#mainForm").submit();
	});
</script>

<form id="mainForm" action="alipay/getTrade" method="get">
	<input type="hidden" name="username" id="username" value="塔米智能" /> <input
		type="hidden" name="password" id="password" value="tamitami" /> <input
		type="hidden" name=subject value="可口可乐" /> <input type="hidden"
		name="total_fee" value="1" /> <input type="hidden" name="body"
		value="售卖的可口可乐" /> <input type="hidden" name="machine" value="售卖机器人" />
	<input type="hidden" name="exter_invoke_ip" value="<%=ip%>" />
	<button type="submit">提交</button>
</form>

<body>
</body>
</html>