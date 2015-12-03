package com.qyh.Action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qyh.entity.AliTrade;
import com.qyh.entity.Machine;
import com.qyh.entity.User;
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
	private static final String SHOUMAIJIQIREN_PREFIX = "SHOUMAI";
	// TODO
	private static final String error_notify_url = "http://localhost:8080/QyhPay2/alipay/error";
	private static final String notify_url = "http://localhost:8080/QyhPay2/alipay/notify";
	private static final String return_url = "http://localhost:8080/QyhPay2/alipay/return";
	private static String md5key;

	@Autowired
	private UserService userService;

	@Autowired
	private AliTradeService aliTradeService;

	public static String produceTradeNo() {
		String myUuid = UUID.randomUUID().toString(); // 生成一个uuid
		String newUuid = myUuid.replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dataString = sdf.format(date);
		return SHOUMAIJIQIREN_PREFIX + dataString + newUuid;

	}

	private static Map<String, String> paraFilter(Map<String, String> sArray) {

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

	public static String createLinkString(Map<String, String> params) {

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

	public static String buildRequestMysign(Map<String, String> sPara) {
		String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String mysign = "";
		if (sign_type.equals("MD5")) {
			mysign = MD5.sign(prestr, md5key, input_charset);
		}
		return mysign;
	}

	private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
		// 除去数组中的空值和签名参数
		Map<String, String> sPara = paraFilter(sParaTemp);
		// 生成签名结果
		String mysign = buildRequestMysign(sPara);

		// 签名结果与签名方式加入请求提交参数组中
		sPara.put("sign", mysign);
		sPara.put("sign_type", sign_type);

		return sPara;
	}

	@RequestMapping("/AliPay/getTrade")
	public String getTrade(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("machine") String machine, @RequestParam("subject") String subject,
			@RequestParam("total_fee") String total_fee, @RequestParam("body") String body,
			@RequestParam("exter_invoke_ip") String exter_invoke_ip) {
		User user = userService.get(username, password);
		if (user == null) {
			return "用户名或者密码错误";
		}
		md5key = user.getUserAliInfo().getMd5key();
		Machine m = null;
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
		aliTradeService.save(at);
		//

		List<String> keys = new ArrayList<String>(sPara.keySet());
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<form id=\"tamipaysubmit\" name=\"tamipaysubmit\" action=\"" + ALIPAY_GATEWAY_NEW
				+ "_input_charset=" + input_charset + "\" method=\"get\">");

		for (int i = 0; i < keys.size(); i++) {
			String name = (String) keys.get(i);
			String value = (String) sPara.get(name);
			sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\"" + value + "\"/>");
		}
		// submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" value=\"submit\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['tamipaysubmit'].submit();</script>");
		return sbHtml.toString();

	}

}
