package com.qyh.Action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.qyh.entity.AliTrade;
import com.qyh.entity.AliTradeResult;
import com.qyh.entity.Machine;
import com.qyh.entity.User;
import com.qyh.service.impl.AliTradeResultService;
import com.qyh.service.impl.AliTradeService;
import com.qyh.service.impl.UserService;
import com.qyh.utils.MD5;

@Controller
public class AliPay {
	private static final String service = "create_direct_pay_by_user";
	private static final String sign_type = "MD5";
	private static final String input_charset = "UTF-8";
	private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
	private static final String payment_type = "1";
	private static final String qr_pay_mode = "0";
	private static final String it_b_pay = "2m";
	private static final String ALI_TRADE_PREFIX = "ALITRADE";
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	private static final String error_notify_url = "";
	// private static final String notify_url =
	// "http://localhost:8080/QyhPay2/alipay/notifyUrl";
	private static final String notify_url = "";
	private static final String return_url = "";

	private static String md5key;

	@Autowired
	private UserService userService;

	@Autowired
	private AliTradeService aliTradeService;

	@Autowired
	private AliTradeResultService aliTradeResultService;

	public String produceTradeNo() {
		String myUuid = UUID.randomUUID().toString(); // 生成一个uuid
		String newUuid = myUuid.replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dataString = sdf.format(date);
		return ALI_TRADE_PREFIX + dataString + newUuid;
	}

	private Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	public String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	public String buildRequestMysign(Map<String, String> sPara) {
		String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		if (sign_type.equals("MD5")) {
			mysign = MD5.sign(prestr, md5key, input_charset);
		}
		return mysign;
	}

	private Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildRequestMysign(sPara);

		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", sign_type);

		return sPara;
	}

	public boolean verify(Map<String, String> params, User user) {

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "false";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id, user);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign, user);

		// 写日志记录（若要调试，请取消下面两行注释）
		// String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign +
		// "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		// AlipayCore.logResult(sWord);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean getSignVeryfy(Map<String, String> Params, String sign, User user) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		if (sign_type.equals("MD5")) {
			isSign = MD5.verify(preSignStr, sign, user.getUserAliInfo().getMd5key(), input_charset);
		}
		return isSign;
	}

	private String verifyResponse(String notify_id, User user) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

		String partner = user.getUserAliInfo().getPartner();
		String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notify_id;

		return checkUrl(veryfy_url);
	}

	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}

		return inputLine;
	}

	@ResponseBody
	@RequestMapping(path = "alipay/getTrade", produces = "text/html;charset=UTF-8")
	public String getTrade(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("machine") String machine, @RequestParam("subject") String subject,
			@RequestParam("total_fee") String total_fee, @RequestParam("body") String body,
			@RequestParam("exter_invoke_ip") String exter_invoke_ip) {
		try {
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			password = new String(password.getBytes("ISO-8859-1"), "UTF-8");
			machine = new String(machine.getBytes("ISO-8859-1"), "UTF-8");
			subject = new String(subject.getBytes("ISO-8859-1"), "UTF-8");
			total_fee = new String(total_fee.getBytes("ISO-8859-1"), "UTF-8");
			body = new String(body.getBytes("ISO-8859-1"), "UTF-8");
			exter_invoke_ip = new String(exter_invoke_ip.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "转码错误";
		}

		User user = userService.get(username, password);
		if (user == null) {
			return "用户名或者密码错误";
		}
		md5key = user.getUserAliInfo().getMd5key();

		Machine m = null;
		Set<Machine> ms = user.getMachines();
		for (Iterator<Machine> iterator = ms.iterator(); iterator.hasNext();) {
			Machine temp = iterator.next();
			if (temp.getName().equals(machine)) {
				m = temp;
			}
		}
		if (m == null) {
			return "该用户名下不存在该机器";
		}
		Map<String, String> sParaTemp = new HashMap<String, String>();
		// 加入动态参数
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		// 加入固定的参数:
		sParaTemp.put("service", service);
		sParaTemp.put("partner", user.getUserAliInfo().getPartner());
		sParaTemp.put("seller_email", user.getUserAliInfo().getSeller_email());
		sParaTemp.put("seller_id", user.getUserAliInfo().getSeller_id());
		sParaTemp.put("_input_charset", input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("qr_pay_mode", qr_pay_mode);
		sParaTemp.put("it_b_pay", it_b_pay);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		String out_trade_no = produceTradeNo();
		sParaTemp.put("out_trade_no", out_trade_no);
		Map<String, String> sPara = buildRequestPara(sParaTemp);
		// 保存订单：
		AliTrade at = new AliTrade();
		at.setBody(body);
		at.setError_notify_url(error_notify_url);
		at.setExter_invoke_ip(exter_invoke_ip);
		at.setIt_b_pay(it_b_pay);
		at.setMachine(m);
		at.setNotify_url(notify_url);
		at.setOut_trade_no(out_trade_no);
		at.setReturn_url(return_url);
		at.setSign(sPara.get("sign"));
		at.setSubject(subject);
		at.setTotal_fee(total_fee);
		at.setDate(new Date());
		aliTradeService.save(at);
		//

		List<String> keys = new ArrayList<String>(sPara.keySet());
		String jspBody = "";
		jspBody += ("<form id=\"tamipaysubmit\" name=\"tamipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
				+ "_input_charset=" + input_charset + "\" method=\"get\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			jspBody += ("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
		// submit按钮控件请不要含有name属性
		jspBody += ("<input type=\"submit\" value=\"submit\" style=\"display:none;\"></form>");
		jspBody += ("<script>document.forms['tamipaysubmit'].submit();</script>");
		return jspBody;
	}

	// http://localhost:8080/QyhPay2/alipay/getLastTradeNo?username=塔米智能&password=tamitami&machine=收买机器人
	@ResponseBody
	@RequestMapping(path = "alipay/getLastTradeNo", produces = "text/html;charset=UTF-8")
	private String getLastTradeNo(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("machine") String machine) {
		// 验证用户名和密码
		try {
			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
			password = new String(password.getBytes("ISO-8859-1"), "UTF-8");
			machine = new String(machine.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "转码错误";
		}

		User user = userService.get(username, password);
		if (user == null) {
			return "用户名或者密码错误";
		}
		Machine m = null;
		Set<Machine> ms = user.getMachines();
		for (Iterator<Machine> iterator = ms.iterator(); iterator.hasNext();) {
			Machine temp = iterator.next();
			if (temp.getName().equals(machine)) {
				m = temp;
			}
		}
		if (m == null) {
			return "该用户名下不存在该机器";
		}
		AliTrade at = aliTradeService.getLastTrade(username, machine);
		if (at == null)
			return "尚未有请求交易记录";
		return at.getOut_trade_no();
	}

	@RequestMapping(path = "alipay/notifyUrl")
	public String notifyUrl(WebRequest webRequest, Model model) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			Map<String, String[]> requestParams = webRequest.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String[] values = requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				params.put(name, valueStr);
			}

			// 商户订单号
			String out_trade_no = new String(webRequest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(webRequest.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(webRequest.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			User user = aliTradeResultService.getUserByAliOutTradeNo(out_trade_no);
			if (user == null) {
				// 并没有生成该订单号！肿么办？？ TODO
				return "fail";
			}

			if (verify(params, user)) {// 验证成功
				AliTradeResult ar = new AliTradeResult();
				ar.setOut_trade_no(out_trade_no);
				ar.setTrade_no(trade_no);
				ar.setTrade_status(trade_status);
				ar.setBody(params.get("body"));
				ar.setSubject(params.get("subject"));
				ar.setSign(params.get("sign"));
				ar.setTotal_fee(Double.valueOf(params.get("total_fee")));
				try {
					ar.setSeller_email(params.get("seller_email"));
					ar.setSeller_id(params.get("seller_id"));
					ar.setSign_type(params.get("sign_type"));
					ar.setBuyer_email(params.get("buyer_email"));
					ar.setBuyer_id(params.get("buyer_id"));
					ar.setError_code(params.get("error_code"));
					ar.setPartner(params.get("partner"));
					ar.setPayment_type(params.get("payment_type"));
					ar.setBusiness_scene(params.get("business_scene"));
					ar.setDiscount(Double.valueOf(params.get("discount")));

					ar.setExtra_common_param(params.get("extra_common_param"));
					// new SimpleDateFormat("yyyy-MM-dd
					// hh:mm:ss").parse(params.get("gmt_create"));

					ar.setGmt_create(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params.get("gmt_create")));
					ar.setGmt_payment(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params.get("gmt_payment")));
					ar.setGmt_refund(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params.get("gmt_refund")));
					ar.setNotify_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params.get("notify_time")));

					ar.setIs_total_fee_adjust(params.get("is_total_fee_adjust"));
					ar.setNotify_id(params.get("notify_id"));
					ar.setNotify_type(params.get("notify_type"));
					ar.setOut_channel_amount(params.get("out_channel_amount"));
					ar.setOut_channel_inst(params.get("out_channel_inst"));
					ar.setOut_channel_type(params.get("out_channel_type"));

					ar.setPrice(Double.valueOf(params.get("price")));
					ar.setQuantity(Double.valueOf(params.get("quantity")));
					ar.setReturn_url(params.get("return_url"));

					ar.setUse_coupon(params.get("use_coupon"));
				} catch (ParseException e) {
					// e.printStackTrace();
				}
				aliTradeResultService.save(ar);
				return "success";
			} else {// 验证失败
				return "fail";
			}
		} catch (UnsupportedEncodingException e) {
			return "fail";
		}
	}
}
