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
}
