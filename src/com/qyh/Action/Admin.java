package com.qyh.Action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Admin {
	@RequestMapping("/admin/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		// request.setAttribute("userName", userName);
		// request.setAttribute("password", password);
		System.out.println("username = " + username);
		System.out.println("password = " + password);
		return "admin/admin";
	}
}
