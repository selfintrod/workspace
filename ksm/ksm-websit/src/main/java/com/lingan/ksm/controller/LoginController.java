package com.lingan.ksm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lingan.ksm.shiro.Service.shiroService;
import com.lingan.ksm.utils.models.returnModel;



@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private shiroService ss;
	
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord,Model model)
	{	
		try 
	{	
		ss.login(userName, passWord);	
		}
		catch(AuthenticationException e)
		{
			returnModel rm=new returnModel();
			rm.setDesc("用户名或密码错误");
			rm.setState(false);
			model.addAttribute("returnModel",rm);
			return "login";
		}
				
		return "redirect:/user/"+userName;
	}

	@GetMapping("/logout")
	public String logout()
	{
		ss.logout();
		return "login";
	}
	
	@GetMapping("/login")
	public String firstlogin()
	{
		return "login";
	}	
}
