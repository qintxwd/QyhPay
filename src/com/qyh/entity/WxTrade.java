package com.qyh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

//微信预支付订单
@Entity
public class WxTrade implements Serializable {

	private static final long serialVersionUID = 8904281547213597384L;
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Machine machine;
	@Column(name = "nonce_str", length = 500, nullable = false)
	private String nonce_str;
	@Column(name = "sign", length = 500, nullable = false)
	private String sign;
	@Column(name = "body", length = 500, nullable = false)
	private String body;
	@Column(name = "detail", length = 1024)
	private String detail;
	@Column(name = "attach", length = 500)
	private String attach;
	@Column(name = "out_trade_no", length = 500, nullable = false)
	private String out_trade_no;
	@Column(name = "total_fee", length = 500, nullable = false)
	private int total_fee;
	@Column(name = "spbill_create_ip", length = 500)
	private String spbill_create_ip;
	@Column(name = "time_start", length = 500)
	private String time_start;
	@Column(name = "time_expire", length = 500)
	private String time_expire;
	@Column(name = "good_tag", length = 500)
	private String good_tag;
	@Column(name = "notify_url", length = 500)
	private String notify_url;
	@Column(name = "trade_type", length = 500)
	private String trade_type;
	@Column(name = "product_id", length = 500)
	private String product_id;
	@Column(name = "limit_pay", length = 500)
	private String limit_pay;
	// 以下为返回预订单的结果值:
	@Column(name = "result_code", length = 500)
	private String result_code;
	@Column(name = "err_code", length = 500)
	private String err_code;
	@Column(name = "err_code_des", length = 500)
	private String err_code_des;
	@Column(name = "prepay_id", length = 500)
	private String prepay_id;
	@Column(name = "code_url", length = 500)
	private String code_url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGood_tag() {
		return good_tag;
	}

	public void setGood_tag(String good_tag) {
		this.good_tag = good_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
}
