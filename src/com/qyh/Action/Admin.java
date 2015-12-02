package com.qyh.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyh.entity.User;
import com.qyh.entity.UserAliInfo;
import com.qyh.entity.UserWXInfo;
import com.qyh.service.impl.UserService;

@Controller
public class Admin {
	@Autowired
	private UserService userService;

	@RequestMapping("/admin/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		// request.setAttribute("userName", userName);
		// request.setAttribute("password", password);
		System.out.println("username = " + username);
		System.out.println("password = " + password);
		return "admin/admin";
	}

	@ResponseBody
	@RequestMapping(path = "/admin/getUserList", method = RequestMethod.GET)
	public Map<String, Object> getUserList(@RequestParam("page") int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = userService.getUserList(page);
		map.put("users", users);
		return map;
	}

	@ResponseBody
	@RequestMapping(path = "/admin/addUser", produces = "text/html;charset=UTF-8")
	public String addUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("partner") String partner, @RequestParam("pid") String pid,
			@RequestParam("md5key") String md5key, @RequestParam("seller_email") String seller_email,
			@RequestParam("seller_id") String seller_id, @RequestParam("appid") String appid,
			@RequestParam("key") String key, @RequestParam("mch_id") String mch_id,
			@RequestParam("secret") String secret) {
		String s = new String();
		if (username.equals("")) {
			s = "�û�������Ϊ��";
			return s;
		}
		if (password.equals("")) {
			s = "���벻��Ϊ��";
			return s;
		}
		if (!((partner.equals("") && pid.equals("") && md5key.equals("") && seller_email.equals("")
				&& seller_id.equals(""))
				|| (!partner.equals("") && !pid.equals("") && !md5key.equals("") && !seller_email.equals("")
						&& !seller_id.equals("")))) {
			s = "������֧������Ϣ";
			return s;
		}
		if (!((appid.equals("") && key.equals("") && mch_id.equals("") && secret.equals(""))
				|| (!appid.equals("") && !key.equals("") && !mch_id.equals("") && !secret.equals("")))) {
			s = "������΢����Ϣ";
			return s;
		}

		User u = new User(username, password);
		if (!md5key.equals("")) {
			UserAliInfo uali = new UserAliInfo();
			uali.setMd5key(md5key);
			uali.setPartner(partner);
			uali.setPid(pid);
			uali.setSeller_email(seller_email);
			uali.setSeller_id(seller_id);
			uali.setUser(u);
			u.setUserAliInfo(uali);
		}
		if (!appid.equals("")) {
			UserWXInfo uwx = new UserWXInfo();
			uwx.setAppid(appid);
			uwx.setKey(key);
			uwx.setMch_id(mch_id);
			uwx.setSecret(secret);
			uwx.setUser(u);
			u.setUserWXInfo(uwx);
		}
		userService.save(u);
		s = "��ӳɹ�";
		return s;
	}
}
