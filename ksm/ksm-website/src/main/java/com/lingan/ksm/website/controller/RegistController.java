package com.lingan.ksm.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingan.ksm.entity.ksmUser;
import com.lingan.ksm.service.UserService;

@Controller
@RequestMapping("/regist")
public class RegistController 
{
	@Autowired
	private UserService us;
	
	//@Autowired
	//private RoleService rs;
		
	@GetMapping
	public String fisrt()
	{		
		return "regist";
	}
	
    @PostMapping
     public String regist(ksmUser user)
    {
    	try
    	{   	    	 
	     us.saveUser(user);    
    	}	    
    	catch(Exception e)
    	{
    		System.out.print("some error");
    	}
    	return "login";
    }
	
}
