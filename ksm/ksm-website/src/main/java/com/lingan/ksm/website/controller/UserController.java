package com.lingan.ksm.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/{userName}")
	public String UserProfile(@PathVariable("userName") String userName,Model model)
	{			
		ksmUser user=us.getUserByName(userName);
		model.addAttribute("ksmUser",user);
		return "user";		
	}
		
}
