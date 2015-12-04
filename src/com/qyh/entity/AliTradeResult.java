package com.qyh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class AliTradeResult implements Serializable {

	private static final long serialVersionUID = 8904281547213597386L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "partner")
	private String partner;
	@Column(name = "out_trade_no")
	private String out_trade_no;
	@Column(name = "error_code")
	private String error_code;
	@Column(name = "return_url")
	private String return_url;
	@Column(name = "buyer_email")
	private String buyer_email;
	@Column(name = "buyer_id")
	private String buyer_id;
	@Column(name = "seller_email")
	private String seller_email;
	@Column(name = "seller_id")
	private String seller_id;

	@Column(name = "notify_time")
	private Date notify_time;
	@Column(name = "notify_type")
	private String notify_type;
	@Column(name = "notify_id")
	private String notify_id;
	@Column(name = "sign_type")
	private String sign_type;
	@Column(name = "sign")
	private String sign;
	@Column(name = "subject")
	private String subject;
	@Column
	private String payment_type;
	@Column
	private String trade_no;
	@Column
	private String trade_status;
	@Column
	private Date gmt_create;
	@Column
	private Date gmt_payment;
	@Column
	private Date gmt_refund;
	@Column
	private double price;
	@Column
	private double total_fee;
	@Column
	private double quantity;
	@Column
	private String body;
	@Column
	private double discount;
	@Column
	private String is_total_fee_adjust;
	@Column
	private String use_coupon;
	@Column
	private String extra_common_param;
	@Column
	private String out_channel_type;
	@Column
	private String out_channel_amount;
	@Column
	private String out_channel_inst;
	@Column
	private String business_scene;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public Date getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public Date getGmt_refund() {
		return gmt_refund;
	}

	public void setGmt_refund(Date gmt_refund) {
		this.gmt_refund = gmt_refund;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}

	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}

	public String getUse_coupon() {
		return use_coupon;
	}

	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}

	public String getExtra_common_param() {
		return extra_common_param;
	}

	public void setExtra_common_param(String extra_common_param) {
		this.extra_common_param = extra_common_param;
	}

	public String getOut_channel_type() {
		return out_channel_type;
	}

	public void setOut_channel_type(String out_channel_type) {
		this.out_channel_type = out_channel_type;
	}

	public String getOut_channel_amount() {
		return out_channel_amount;
	}

	public void setOut_channel_amount(String out_channel_amount) {
		this.out_channel_amount = out_channel_amount;
	}

	public String getOut_channel_inst() {
		return out_channel_inst;
	}

	public void setOut_channel_inst(String out_channel_inst) {
		this.out_channel_inst = out_channel_inst;
	}

	public String getBusiness_scene() {
		return business_scene;
	}

	public void setBusiness_scene(String business_scene) {
		this.business_scene = business_scene;
	}

}
