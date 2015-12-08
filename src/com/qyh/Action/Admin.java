package com.qyh.Action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyh.entity.Machine;
import com.qyh.entity.User;
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
		return "admin/NewAdmin";
	}

	@ResponseBody
	@RequestMapping(path = "/admin/getUserList")
	public Map<String, Object> getUserList(@RequestParam("page") int page, @RequestParam("rows") int perPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> users = userService.getUserList(page, perPage);
		long i = userService.getUserCount();
		map.put("total", i);
		map.put("rows", users);
		return map;
	}

	@ResponseBody
	@RequestMapping(path = "/admin/addUser")
	public Map<String, Object> addUser(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("partner") String partner,
			@RequestParam("pid") String pid, @RequestParam("md5key") String md5key,
			@RequestParam("seller_email") String seller_email, @RequestParam("seller_id") String seller_id,
			@RequestParam("appid") String appid, @RequestParam("key") String key, @RequestParam("mch_id") String mch_id,
			@RequestParam("secret") String secret, @RequestParam("machines") String[] machineNames) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			try {
				username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
				password = new String(password.getBytes("ISO-8859-1"), "UTF-8");
				partner = new String(partner.getBytes("ISO-8859-1"), "UTF-8");
				pid = new String(pid.getBytes("ISO-8859-1"), "UTF-8");
				md5key = new String(md5key.getBytes("ISO-8859-1"), "UTF-8");
				seller_email = new String(seller_email.getBytes("ISO-8859-1"), "UTF-8");
				seller_id = new String(seller_id.getBytes("ISO-8859-1"), "UTF-8");
				appid = new String(appid.getBytes("ISO-8859-1"), "UTF-8");
				key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
				mch_id = new String(mch_id.getBytes("ISO-8859-1"), "UTF-8");
				secret = new String(secret.getBytes("ISO-8859-1"), "UTF-8");
				for (int i = 0; i < machineNames.length; i++) {
					machineNames[i] = new String(machineNames[i].getBytes("ISO-8859-1"), "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				map.put("success", false);
				map.put("errorMsg", "发生转码错误");
				return map;
			}
			if (username.equals("")) {
				map.put("success", false);
				map.put("errorMsg", "用户名不能为空");
				return map;
			}
			if (password.equals("")) {
				map.put("success", false);
				map.put("errorMsg", "密码不能为空");
				return map;
			}
			if (!((partner.equals("") && pid.equals("") && md5key.equals("") && seller_email.equals("")
					&& seller_id.equals(""))
					|| (!partner.equals("") && !pid.equals("") && !md5key.equals("") && !seller_email.equals("")
							&& !seller_id.equals("")))) {
				map.put("success", false);
				map.put("errorMsg", "请完善支付宝信息");
				return map;
			}
			if (!((appid.equals("") && key.equals("") && mch_id.equals("") && secret.equals(""))
					|| (!appid.equals("") && !key.equals("") && !mch_id.equals("") && !secret.equals("")))) {
				map.put("success", false);
				map.put("errorMsg", "请完善微信信息");
				return map;
			}

			User u = new User(username, password);
			// if (!md5key.equals("")) {
			u.setMd5key(md5key);
			u.setPartner(partner);
			u.setPid(pid);
			u.setSeller_email(seller_email);
			u.setSeller_id(seller_id);
			// }
			// if (!appid.equals("")) {
			u.setAppid(appid);
			u.setKey(key);
			u.setMch_id(mch_id);
			u.setSecret(secret);
			// }
			if (machineNames != null && machineNames.length != 0) {
				Set<Machine> machines = new HashSet<Machine>();
				for (int i = 0; i < machineNames.length; i++) {
					Machine m = new Machine();
					m.setName(machineNames[i]);
					m.setUser(u);
					machines.add(m);
				}
				u.setMachines(machines);
			}
			userService.save(u);

			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", e.toString());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(path = "/admin/deleteUser")
	public Map<String, Object> deleteUser(@RequestParam("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			userService.delete(id);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", e.toString());
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(path = "/admin/updateUser")
	public Map<String, Object> deleteUser(@RequestParam("id") int id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("partner") String partner,
			@RequestParam("pid") String pid, @RequestParam("md5key") String md5key,
			@RequestParam("seller_email") String seller_email, @RequestParam("seller_id") String seller_id,
			@RequestParam("appid") String appid, @RequestParam("key") String key, @RequestParam("mch_id") String mch_id,
			@RequestParam("secret") String secret) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User u = userService.get(id);
			u.setAppid(appid);
			u.setKey(key);
			u.setMch_id(mch_id);
			u.setPartner(partner);
			u.setPassword(password);
			u.setPid(pid);
			u.setSecret(secret);
			u.setSeller_email(seller_email);
			u.setSeller_id(seller_id);
			u.setUsername(username);
			u.setMd5key(md5key);
			userService.update(u);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", e.toString());
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(path = "/admin/getUserDetail")
	public Map<String, Object> getDetailUser(@RequestParam("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			User u = userService.get(id);
			map.put("user", u);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", e.toString());
		}
		return map;
	}
}
