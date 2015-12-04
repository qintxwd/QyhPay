package com.qyh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class AliTrade implements Serializable {

	private static final long serialVersionUID = 8904281547213597385L;
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Machine machine;
	@Column(name = "sign", length = 500, nullable = false)
	private String sign;
	@Column(name = "notify_url", length = 500)
	private String notify_url;
	@Column(name = "return_url", length = 500)
	private String return_url;
	@Column(name = "error_url", length = 500)
	private String error_notify_url;
	@Column(name = "out_trade_no", length = 500, nullable = false)
	private String out_trade_no;
	@Column(name = "subject", length = 500, nullable = false)
	private String subject;
	@Column(name = "total_fee", length = 500, nullable = false)
	private String total_fee;
	@Column(name = "body", length = 500)
	private String body; // ��Ʒ����
	@Column(name = "exter_invoke_ip", length = 500)
	private String exter_invoke_ip;
	@Column(name = "it_b_pay", length = 500)
	private String it_b_pay;
	@Column(name="date_time")
	private Date date;

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getError_notify_url() {
		return error_notify_url;
	}

	public void setError_notify_url(String error_notify_url) {
		this.error_notify_url = error_notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}

	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public String getIt_b_pay() {
		return it_b_pay;
	}

	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
