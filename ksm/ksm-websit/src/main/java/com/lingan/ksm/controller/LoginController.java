package com.lingan.ksm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/","login"})
public class LoginController {
	
	@PostMapping
	public String login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord,Model model)
	{	
		try 
	{	
		UsernamePasswordToken token=new UsernamePasswordToken(userName,passWord);		
		Subject subject=SecurityUtils.getSubject();		
		subject.login(token);		
		}
		catch(Exception e)
		{
			e.getMessage();
			return "login";
		}
				
		return "redirect:/user/"+userName;
	}

	
	@GetMapping
	public String firstlogin()
	{
		return "login";
	}	
}
