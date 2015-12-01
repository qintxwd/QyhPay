package com.qyh.Action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAction {

	@RequestMapping("/test")
	public String test(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
		System.out.println("username:" + username + " password:" + password);
		return null;
	}
}
