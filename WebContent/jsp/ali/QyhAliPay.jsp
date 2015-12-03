<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>塔米支付宝交易接口(即时到账)</title>
</head>
<%
	//订单名称
	String subject = request.getParameter("subject");//new String(request.getParameter("subject").getBytes("ISO-8859-1"),"UTF-8");
	//付款金额
	String total_fee = request.getParameter("total_fee");//new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
	//订单描述
	String body = request.getParameter("body");//new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

	//把请求参数打包成数组
	Map<String, String> sParaTemp = new HashMap<String, String>();
	sParaTemp.put("subject", subject);
	sParaTemp.put("total_fee", total_fee);
	sParaTemp.put("body", body);

	//建立请求
	//String sHtmlText = QyhHttpRequestPack.buildRequest(sParaTemp, "get", "确认");
	//out.println(sHtmlText);
%>
<body>
</body>
</html>